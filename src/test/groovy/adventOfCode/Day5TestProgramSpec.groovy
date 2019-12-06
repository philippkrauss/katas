package adventOfCode

import spock.lang.Specification
import spock.lang.Unroll

class Day5TestProgramSpec extends Specification {

	@Unroll
	def "calculate output"() {
		expect:
		new Day5TestProgram().calculateOutput(program) == output
		where:
		program                         | output
		[4, 0, 99]                      | [4]
		[104, 0, 99]                    | [0]
		[4, 0, 3, 1, 4, 0, 99]          | [4, 1]
		[4, 0, 1, 0, 0, 2, 4, 2, 99]    | [4, 8]
		[4, 0, 101, 0, 0, 2, 4, 2, 99]  | [4, 4]
		[4, 0, 1001, 0, 0, 2, 4, 2, 99] | [4, 4]
		[4, 0, 2, 0, 0, 2, 4, 2, 99]    | [4, 16]
		[4, 0, 102, 1, 0, 2, 4, 2, 99]  | [4, 4]
		[4, 0, 1002, 0, 2, 2, 4, 2, 99] | [4, 8]
	}
}
