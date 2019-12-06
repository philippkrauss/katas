package adventOfCode

import spock.lang.Specification
import spock.lang.Unroll

class Day4SecureContainerSuite extends Specification {

	@Unroll
	def "meets criteria"() {
		expect:
		new Day4SecureContainer().meetsAllCriteria(password) == meetsCriteria
		where:
		password | meetsCriteria
		'111111' | true
		'223450' | false
		'123789' | false
	}
}
