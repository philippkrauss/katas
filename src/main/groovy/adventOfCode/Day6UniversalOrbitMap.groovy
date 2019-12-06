package adventOfCode

/**
 * https://adventofcode.com/2019/day/6
 */
class Day6UniversalOrbitMap {

	int calculateOrbits(String map) {
		Set<String> individuals = []
		Map<String, String> paths = [:]
		map.eachLine { def line ->
			def entries = line.split('\\)')
			def left = entries[0]
			def right = entries[1]
			individuals.add(left)
			individuals.add(right)
			paths[right] = left
		}
		int result = 0
		individuals.each {
			if (it == 'COM') {
				return
			}
			result += calculateOrbitsOfIndividual(it, paths)
		}
		return result
	}


	int calculateOrbitsOfIndividual(String individual, Map<String, String> paths) {
		if (paths[individual] == 'COM') {
			return 1
		}
		1 + calculateOrbitsOfIndividual(paths[individual], paths)
	}
}
