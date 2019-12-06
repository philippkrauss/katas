package adventOfCode

class Day4SecureContainer {

	boolean meetsAllCriteria(String password) {
		return checkDouble(password) && checkNoDecrease(password)
	}


	private boolean checkDouble(String password) {
		boolean containsDouble = false
		(0..4).each {
			containsDouble |= (password[it] == password[it + 1])
		}
		containsDouble
	}

	private boolean checkNoDecrease(String password) {
		boolean containsDecrease = false
		(0..4).each {
			containsDecrease |= (password[it] > password[it + 1])
		}
		!containsDecrease
	}

	public static void main(String[] args) {
		Day4SecureContainer secureContainer = new Day4SecureContainer()
		int count = 0;
		(271973..785961).each {
			if (secureContainer.meetsAllCriteria(new Integer(it).toString())) {
				count++
			}
		}
		println count
	}
}
