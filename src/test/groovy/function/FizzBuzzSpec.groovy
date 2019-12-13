package function

import spock.lang.Specification
import spock.lang.Unroll

class FizzBuzzSpec extends Specification {

	def "list is 100 elements long"() {
		when:
		List fizzBuzz = new FizzBuzz().fizzBuzz()
		then:
		fizzBuzz.size() == 100
	}

	static final String UNCHANGED = 'unchanged'

	@Unroll
	def "elements #description are #expectedValue"() {
		when:
		List fizzBuzz = new FizzBuzz().fizzBuzz()
		then:
		indexes.each {
			assert fizzBuzz[it - 1] == expectedValue
		}
		where:
		description                | expectedValue | indexes
		'divisible by 3'           | 'Fizz'        | [3, 6, 9, 12, 18, 21, 24, 27, 33, 36, 39, 42, 48, 51, 54, 57, 63, 66, 69, 72, 78, 81, 84, 87, 93, 96, 99]
		'divisible by 5'           | 'Buzz'        | [5, 10, 20, 25, 35, 40, 50, 55, 65, 70, 80, 85, 95, 100]
		'divisible by 3 and 5'     | 'FizzBuzz'    | [15, 30, 45, 60, 75, 90]
	}
	def "numbers not divisible by 3 and 5 are unchanged"() {
		when:
		List fizzBuzz = new FizzBuzz().fizzBuzz()
		then:
		[1, 2, 4, 7, 8, 11, 14, 16, 17, 19, 22, 23, 26, 28, 29, 31, 32, 34, 37, 38, 41, 43, 44, 46, 47, 49, 52, 53, 56, 58, 59, 61, 62, 64, 67, 68, 71, 73, 74, 76, 77, 79, 82, 83, 86, 88, 89, 91, 92, 94, 97, 98].each {
			assert fizzBuzz[it - 1] == it
		}
	}
}
