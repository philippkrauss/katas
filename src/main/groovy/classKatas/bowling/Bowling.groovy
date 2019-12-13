package classKatas.bowling

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * https://ccd-school.de/coding-dojo/class-katas/bowling/
 */
class Bowling {
	final List<Frame> frames = [new Frame()]

	void addRoll(int pins) {
		if (frames[-1].complete) {
			addFrame()
		}
		frames.each { it.addRoll(pins) }
	}

	private void addFrame() {
		frames << (frames.size() < 9 ? new Frame() : new LastFrame())
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
	RollUpdater rollUpdater = new FirstRollUpdater(frame: this)

	void addRoll(int roll) {
		rollUpdater.updateRoll(roll)
		rollUpdater = rollUpdater.next()
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


interface RollUpdater {
	void updateRoll(int roll)

	RollUpdater next()
}

class FirstRollUpdater implements RollUpdater {
	Frame frame

	void updateRoll(int roll) {
		frame.pinsRolled << roll
		frame.score += roll
	}

	RollUpdater next() {
		return new SecondRollUpdater(frame: frame)
	}
}

class SecondRollUpdater implements RollUpdater {
	Frame frame

	void updateRoll(int roll) {
		if (frame.strike) {
			frame.score += roll
		}
		if (frame.pinsRolled[0] < 10) {
			frame.pinsRolled << roll
			frame.score += roll
		}
	}

	RollUpdater next() {
		return new ThirdRollUpdater(frame: frame)
	}
}

class ThirdRollUpdater implements RollUpdater {
	Frame frame

	void updateRoll(int roll) {
		if (frame.strike || frame.spare) {
			frame.score += roll
		}
	}

	RollUpdater next() {
		return new NoopRollUpdater()
	}
}

class NoopRollUpdater implements RollUpdater {
	void updateRoll(int roll) {}

	RollUpdater next() {
		this
	}
}
