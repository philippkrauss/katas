package adventOfCode

class Day2ProgramAlarm {

	int calculateOpCode(def program) {
		int opCounter = 0
		int opCode = program[opCounter]
		while(opCode != 99) {
			int pos1 = program[opCounter + 1]
			int pos1Value = program[pos1]
			int pos2 = program[opCounter + 2]
			int pos2Value = program[pos2]
			int out = program[opCounter + 3]
			if (opCode == 1) {
				program[out] = pos1Value + pos2Value
			} else if (opCode == 2) {
				program[out] = pos1Value * pos2Value
			}
			opCounter += 4
			opCode = program[opCounter]
		}
		return program[0]
	}
}
