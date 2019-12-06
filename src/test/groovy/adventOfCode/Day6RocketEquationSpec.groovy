package adventOfCode

import spock.lang.Specification
import spock.lang.Unroll

class Day6RocketEquationSpec extends Specification {

	@Unroll
	def "calculate fuel"() {
		expect:
		new Day1RocketEquation().calculateFuel(input) == expectedFuel
		where:
		input                                                                    | expectedFuel
		'12'                                                                     | 2
		'14'                                                                     | 2
		'1969'                                                                   | 654
		'12\n1969'                                                               | 656
		Day1UniversalOrbitMapSpec.getResourceAsStream('RocketEquation.txt').text | 3152919
	}
}
