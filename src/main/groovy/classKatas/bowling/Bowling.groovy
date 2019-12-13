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
		if (frames.last().isComplete() && frames.size() < MAX_NUMBER_OF_FRAMES) {
			frames << new Frame()
		}
		Frame currentFrame = frames.last()
		currentFrame.addRoll(pins)
		if (frames.size() > 1) {
			Frame previousFrame = frames[-2]
			if (currentFrame.pinsRolled.size() <= 2 && previousFrame.strike) {
				previousFrame.addScore(pins)
			}
			if (currentFrame.pinsRolled.size() == 1 && previousFrame.spare) {
				previousFrame.addScore(pins)
			}
		}
		if (frames.size() > 2) {
			Frame previousPreviousFrame = frames[-3]
			Frame previousFrame = frames[-2]
			if (currentFrame.pinsRolled.size() == 1 && previousFrame.pinsRolled.size() == 1) {
				previousPreviousFrame.addScore(pins)
			}
		}
	}

	List<Frame> getFrames() {
		frames
	}

	int getTotalScore() {
		frames.inject(0) { sum, frame -> sum + frame.score }
	}

	boolean isOver() {
		return frames.size() == 10 && frames.last().completeLastFrame
	}
}

@EqualsAndHashCode
@ToString(includePackage = false)
class Frame {
	List<Integer> pinsRolled = []
	int score = 0

	Frame() {}

	Frame(List<Integer> pinsRolled, int score) {
		this.pinsRolled = pinsRolled
		this.score = score
	}

	void addRoll(int roll) {
		pinsRolled << roll
		score += roll
	}

	void addScore(int roll) {
		score += roll
	}

	boolean isStrike() {
		return pinsRolled.size() == 1 && pinsRolled[0] == 10
	}

	boolean isSpare() {
		return pinsRolled.size() == 2 && pinsRolled[0] + pinsRolled[1] == 10
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

	boolean isCompleteLastFrame() {
		if (pinsRolled.size() == 3) {
			return true
		}
		if (pinsRolled.size() == 2 && pinsRolled[0] + pinsRolled[1] < 10) {
			return true
		}
		return false
	}

}
