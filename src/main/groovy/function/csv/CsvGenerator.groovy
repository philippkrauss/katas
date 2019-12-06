package function.csv

/**
 * https://ccd-school.de/coding-dojo/function-katas/csv-tabellieren/
 */
class CsvGenerator {

	/**
	 * input:
	 * Name;Strasse;Ort;Alter
	 * Peter Pan;Am Hang 5;12345 Einsam;42
	 * Maria Schmitz;Kölner Straße 45;50123 Köln;43
	 * Paul Meier;Münchener Weg 1;87654 München;65
	 *
	 * output:
	 * Name         |Strasse         |Ort          |Alter|
	 * -------------+----------------+-------------+-----+
	 * Peter Pan    |Am Hang 5       |12345 Einsam |42   |
	 * Maria Schmitz|Kölner Straße 45|50123 Köln   |43   |
	 * Paul Meier   |Münchener Weg 1 |87654 München|65   |
	 *
	 * requirements:
	 * - values separated by semicolon
	 * - no special cases like semicolons inside values need to be handled
	 * - input is always correct, no error handling necessary
	 * - output is a formatted table with input data
	 * - first line of input is interpreted as heading
	 * - heading is separated from data by separation line
	 * - column width is defined by the widest data point (heading or value)
	 */
	String generateCsv(String input) {
		String[] lines = input.readLines()
		String[][] matrix = lines.collect { it.split(';') }
		int[] columnSizes = calculateColumnSizes(matrix)
		String[] headers = matrix[0]
		String result = generateLine(headers, columnSizes)
		result += generateSeparatorLine(headers, columnSizes)
		matrix.tail().each {
			result += generateLine(it, columnSizes)
		}
		result
	}

	private String generateLine(def headerList, def columnSizes) {
		String result = (headerList as List).withIndex().collect { element, index ->
			element.padRight(columnSizes[index])
		} join('|')
		result + '|\n'
	}

	private String generateSeparatorLine(def headerList, def columnSizes) {
		def individualSeparationLines = (headerList as List).withIndex().collect { element, index ->
			'-'.multiply(columnSizes[index] as int)
		}
		String separationLine = individualSeparationLines.join('+')
		separationLine + '+\n'
	}

	int[] calculateColumnSizes(String[][] matrix) {
		int[] columnSizes = new int[matrix[0].length]
		matrix.each {
			it.collect { it.size() } eachWithIndex { int entry, int index ->
				if (columnSizes[index] < entry) {
					columnSizes[index] = entry
				}
			}
		}
		columnSizes
	}
}
