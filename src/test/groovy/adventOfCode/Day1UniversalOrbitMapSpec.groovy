package adventOfCode

import spock.lang.Specification
import spock.lang.Unroll

class Day1UniversalOrbitMapSpec extends Specification {

	@Unroll
	def "calculate orbits"() {
		expect:
		new Day6UniversalOrbitMap().calculateOrbits(map) == expectedOrbits
		where:
		map                                                                         | expectedOrbits
		'COM)A'                                                                     | 1
		'COM)A\nA)B'                                                                | 3
		'COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L'                   | 42
		Day1UniversalOrbitMapSpec.getResourceAsStream('UniversalOrbitMap.txt').text | 140608
	}


}
