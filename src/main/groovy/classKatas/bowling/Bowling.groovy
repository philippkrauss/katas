package classKatas.bowling

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * https://ccd-school.de/coding-dojo/class-katas/bowling/
 */
class Bowling {
	public static int MAX_NUMBER_OF_FRAMES = 10
	private final List<Frame> frames = [new Frame()]

	void addRoll(int pins) {
		if (frames[-1].complete) {
			addFrame()
		}
		frames.each { it.addRoll(pins) }
	}

	private void addFrame() {
		frames << (frames.size() < 9 ? new Frame() : new LastFrame())
	}

	List<Frame> getFrames() {
		frames
	}

	int getTotalScore() {
		frames.inject(0) { sum, frame -> sum + frame.score }
	}

	boolean isOver() {
		return frames.size() == 10 && frames[-1].complete
	}
}

@EqualsAndHashCode
@ToString(includePackage = false, includes = ['pinsRolled', 'score'])
class Frame {
	List<Integer> pinsRolled = []
	int score = 0
	private int rollCounter = 0

	void addRoll(int roll) {

		switch (rollCounter) {
			case 0:
				pinsRolled << roll
				score += roll
				break
			case 1:
				if (strike) {
					score += roll
				}
				if (pinsRolled[0] < 10) {
					pinsRolled << roll
					score += roll
				}
				break
			case 2:
				if (strike || spare) {
					score += roll
				}
				break
		}
		rollCounter++
	}

	boolean isComplete() {
		if (pinsRolled.size() == 1 && pinsRolled[0] == 10) {
			return true
		}
		if (pinsRolled.size() == 2) {
			return true
		}
		return false
	}

	boolean isStrike() {
		return pinsRolled.size() == 1 && pinsRolled[0] == 10
	}

	boolean isSpare() {
		return pinsRolled.size() == 2 && pinsRolled[0] + pinsRolled[1] == 10
	}
}

class LastFrame extends Frame {
	void addRoll(int roll) {
		pinsRolled << roll
		score += roll
	}

	boolean isComplete() {
		if (pinsRolled.size() == 3) {
			return true
		}
		if (pinsRolled.size() == 2 && pinsRolled[0] + pinsRolled[1] < 10) {
			return true
		}
		return false
	}
}
