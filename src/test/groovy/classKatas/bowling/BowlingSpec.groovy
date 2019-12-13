package classKatas.bowling

import spock.lang.Specification
import spock.lang.Unroll

class BowlingSpec extends Specification {

	Bowling bowling

	void setup() {
		bowling = new Bowling()
	}

	void "a new bowling game is not over and has a score of 0"() {
		expect:
		!(bowling.over)
		bowling.totalScore == 0
	}

	void "a roll updates pinsRolled in frames"() {
		when:
		rolls.each { bowling.addRoll(it) }
		then:
		bowling.frames.pinsRolled == expectedPinsRolled
		bowling.over == expectedOver
		where:
		rolls                                          | expectedPinsRolled                                                 | expectedOver
		[1]                                            | [[1]]                                                              | false
		[2]                                            | [[2]]                                                              | false
		[1, 1]                                         | [[1, 1]]                                                           | false
		[1, 1, 1]                                      | [[1, 1], [1]]                                                      | false
		[1, 2, 3, 4]                                   | [[1, 2], [3, 4]]                                                   | false
		[10, 1]                                        | [[10], [1]]                                                        | false
		[10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1]    | [[10], [10], [10], [10], [10], [10], [10], [10], [10], [10, 1]]    | false
		[10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 1] | [[10], [10], [10], [10], [10], [10], [10], [10], [10], [10, 1, 1]] | true
		[10, 10, 10, 10, 10, 10, 10, 10, 10, 8, 2, 1]  | [[10], [10], [10], [10], [10], [10], [10], [10], [10], [8, 2, 1]]  | true
		[10, 10, 10, 10, 10, 10, 10, 10, 10, 8, 2]     | [[10], [10], [10], [10], [10], [10], [10], [10], [10], [8, 2]]     | false
		[10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 1]     | [[10], [10], [10], [10], [10], [10], [10], [10], [10], [1, 1]]     | true
	}

	void "a roll updates score in frames"() {
		when:
		rolls.each { bowling.addRoll(it) }
		then:
		bowling.frames.score == expectedScore
		where:
		rolls                                          | expectedScore
		[1]                                            | [1]
		[2]                                            | [2]
		[1, 1]                                         | [2]
		[1, 1, 1]                                      | [2, 1]
		[1, 2, 3, 4]                                   | [3, 7]
		[10, 1]                                        | [11, 1]
		[10, 1, 1]                                     | [12, 2]
		[8, 2, 1]                                      | [11, 1]
		[10, 10, 10, 1, 1]                             | [30, 21, 12, 2]
		[10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1]    | [30, 30, 30, 30, 30, 30, 30, 30, 21, 11]
		[10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 1] | [30, 30, 30, 30, 30, 30, 30, 30, 21, 12]
		[10, 10, 10, 10, 10, 10, 10, 10, 10, 8, 2, 1]  | [30, 30, 30, 30, 30, 30, 30, 28, 20, 11]
	}

	void "total score is updated"() {
		when:
		rolls.each { bowling.addRoll(it) }
		then:
		bowling.totalScore == expectedScore
		where:
		rolls                                       | expectedScore
		[1]                                         | 1
		[1, 1]                                      | 2
		[10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1] | 272
	}

}
