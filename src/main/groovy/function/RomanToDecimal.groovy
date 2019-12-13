package function
/**
 * https://ccd-school.de/coding-dojo/function-katas/from-roman-numerals/
 */
class RomanToDecimal {

	/**
	 * write a function that converts roman numbers into decimals
	 * examples:
	 *
	 * „I“ -> 1
	 * „II“ -> 2
	 * „IV“ -> 4
	 * „V“ -> 5
	 * „IX“ -> 9
	 * „XLII“ -> 42
	 * „XCIX“ -> 99
	 * „MMXIII“ -> 2013
	 * The roman numbers range from "I" to "MMM"
	 *
	 * Assume the input values are correct!
	 */
	int romanToDecimal(String romanNumber) {
		def values = [
				'IV': 4,
				'IX': 9,
				'XL': 40,
				'XC': 90,
				'CD': 400,
				'CM': 900,
				'I' : 1,
				'V' : 5,
				'X' : 10,
				'L' : 50,
				'C' : 100,
				'D' : 500,
				'M' : 1000,
		]
		int result = 0
		while (romanNumber) {
			boolean reducedSize = false
			values.each {
				if (romanNumber.startsWith(it.key)) {
					result += it.value
					romanNumber = romanNumber.drop(it.key.size())
					reducedSize = true
				}
			}
			if (!reducedSize) {
				throw new Exception('Invalid number!')
			}
		}
		return result
	}
}
