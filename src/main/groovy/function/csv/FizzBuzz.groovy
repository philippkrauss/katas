package function.csv
/**
 * https://ccd-school.de/coding-dojo/function-katas/fizzbuzz/
 */
class FizzBuzz {
	/**
	 * write a function that returns the numbers from 1 to 100.
	 * Some numbers have to be translated.
	 *
	 * - multiples of 3 translate to "Fizz"
	 * - multiples of 5 translate to "Buzz"
	 * - multiples of 3 and 5 translate to "FizzBuzz"
	 * Example:
	 * 1
	 * 2
	 * Fizz
	 * 4
	 * Buzz
	 * Fizz
	 * 7
	 * ...
	 * 14
	 * FizzBuz
	 * 16
	 * ...
	 */
	List fizzBuzz() {
		return (1..100).collect {
			if (it % 15 == 0) {
				return 'FizzBuzz'
			}
			if (it % 3 == 0) {
				return 'Fizz'
			}
			if (it % 5 == 0) {
				return 'Buzz'
			}
			return it
		}
	}

	static void main(String[] args) {
		new FizzBuzz().fizzBuzz().each { println it }
	}
}
