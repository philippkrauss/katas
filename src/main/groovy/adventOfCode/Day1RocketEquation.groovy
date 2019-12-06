package adventOfCode

class Day1RocketEquation {

	int calculateFuel(String input) {
		int result = 0
		input.eachLine {
			result += ((int)(Integer.parseInt(it) / 3)) - 2
		}
		result
	}
}
