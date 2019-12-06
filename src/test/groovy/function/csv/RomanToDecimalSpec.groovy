package function.csv

import spock.lang.Specification
import spock.lang.Unroll

class RomanToDecimalSpec extends Specification {

	@Unroll
	def "convert '#romanNumber' to #decimalNumber"() {
		expect:
		new RomanToDecimal().romanToDecimal(romanNumber) == decimalNumber
		where:
		romanNumber | decimalNumber
		'I'         | 1
		'II'        | 2
		'III'       | 3
		'IV'        | 4
		'V'         | 5
		'VI'        | 6
		'IX'        | 9
		'X'         | 10
		'XX'        | 20
		'XL'        | 40
		'L'         | 50
		'XC'        | 90
		'C'         | 100
		'CD'        | 400
		'D'         | 500
		'CM'        | 900
		'M'         | 1000
		'MMXIII'    | 2013
		'MMXIX'     | 2019
	}
}
