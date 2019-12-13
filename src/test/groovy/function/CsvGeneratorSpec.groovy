package function

import spock.lang.Specification
import spock.lang.Unroll

class CsvGeneratorSpec extends Specification {

	@Unroll
	void 'generate heading (#description)'() {
		when:
		String result = new CsvGenerator().generateCsv(input)
		then:
		result.startsWith(output)
		where:
		input                    | output                      | description
		'Name'                   | 'Name|\n'                   | 'single item'
		'Name;Strasse;Ort;Alter' | 'Name|Strasse|Ort|Alter|\n' | 'multiple items'
		'Name\nName'             | 'Name|'                     | 'multiple lines'
		'Key\nValue'             | 'Key  |'                    | 'multiple lines, values have different sizes'
		'Key;LongKey\nValue;val' | 'Key  |LongKey|'            | 'multiple lines and values with different sizes'
	}

	@Unroll
	void 'generate separation line (#description)'() {
		when:
		String result = new CsvGenerator().generateCsv(input)
		then:
		result.split('\n')[1] == separationLine
		where:
		input                    | separationLine            | description
		'Name'                   | '----+'                   | 'single item'
		'Name;Strasse;Ort;Alter' | '----+-------+---+-----+' | 'multiple items'
		'Name\nName'             | '----+'                   | 'multiple lines'
		'Key\nValue'             | '-----+'                  | 'multiple lines, values have different sizes'
		'Key;LongKey\nValue;val' | '-----+-------+'          | 'multiple lines and values with different sizes'
	}

	@Unroll
	void 'generate columns (#description)'() {
		when:
		String result = new CsvGenerator().generateCsv(input)
		then:
		result.split('\n')[2] == columnLine
		where:
		input                    | columnLine       | description
		'hdr1;hdr2\nval1;val2'   | 'val1|val2|'     | 'multiple values, all same size'
		'Name\nName'             | 'Name|'          | 'multiple lines'
		'Key\nValue'             | 'Value|'         | 'multiple lines, value larger than heading'
		'LongKey\nValue'         | 'Value  |'       | 'multiple lines, value smaller than heading'
		'Key;LongKey\nValue;val' | 'Value|val    |' | 'multiple lines and values with different sizes'
	}

	void 'test with test data from requirements'() {
		def testData = new StringBuilder()
		testData << 'Name;Strasse;Ort;Alter' << '\n'
		testData << 'Peter Pan;Am Hang 5;12345 Einsam;42' << '\n'
		testData << 'Maria Schmitz;Kölner Straße 45;50123 Köln;43' << '\n'
		testData << 'Paul Meier;Münchener Weg 1;87654 München;65'

		def expectedResult = new StringBuilder()
		expectedResult << 'Name         |Strasse         |Ort          |Alter|' << '\n'
		expectedResult << '-------------+----------------+-------------+-----+' << '\n'
		expectedResult << 'Peter Pan    |Am Hang 5       |12345 Einsam |42   |' << '\n'
		expectedResult << 'Maria Schmitz|Kölner Straße 45|50123 Köln   |43   |' << '\n'
		expectedResult << 'Paul Meier   |Münchener Weg 1 |87654 München|65   |' << '\n'
		when:
		String result = new CsvGenerator().generateCsv(testData as String)
		then:
		result == expectedResult.toString()
	}
}
