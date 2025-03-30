package game;

class BowlingGameState {
    private int currentRoll;
    private final int[] rolls;
    private final int numberOfPins;
    private final int numberOfFrames;

    public BowlingGameState(int numberOfPins, int numberOfFrames) {
        if (numberOfPins < 1 || numberOfFrames < 1) {
            throw new IllegalArgumentException("Number of pins and frames must be positive");
        }
        this.numberOfPins = numberOfPins;
        this.numberOfFrames = numberOfFrames;
        this.currentRoll = 0;
        this.rolls = new int[numberOfFrames * 2 + 1];
    }

    public void roll(int pin) {
        rolls[currentRoll++] = pin;
    }

    public int calculateScore() {
        int score = 0;

        for (int frame = 0, rollIndex = 0; frame < 10; frame++) {
            if (isStrike(rollIndex)) {
                score += numberOfPins + strikeBonus(rollIndex);
                rollIndex++;
            } else if (isSpare(rollIndex)) {
                score += numberOfPins + spareBonus(rollIndex);
                rollIndex += 2;
            } else {
                score += sumPinsInFrame(rollIndex);
                rollIndex += 2;
            }
        }

        return score;
    }

    public boolean hasCurrentFrameStrike() {
        return hasRoll(1) && isStrike(currentRoll - 1);
    }

    public boolean hasPreviousFrameStrike() {
        return hasRoll(2) && isStrike(currentRoll - 2);
    }

    public boolean hasPreviousFrameSpare() {
        return hasRoll(2) && isSpare(currentRoll - 2);
    }

    public int getNumberOfFrames() {
        return this.numberOfFrames;
    }

    public int getNumberOfPins() {
        return this.numberOfPins;
    }

    private int getRoll(int rollIndex) {
        return rolls[rollIndex];
    }

    private boolean hasRoll(int offset) {
        return currentRoll - offset >= 0;
    }

    private boolean isStrike(int rollIndex) {
        return getRoll(rollIndex) == numberOfPins;
    }

    private boolean isSpare(int rollIndex) {
        return getRoll(rollIndex) + getRoll(rollIndex + 1) == numberOfPins;
    }

    private int strikeBonus(int rollIndex) {
        return getRoll(rollIndex + 1) + getRoll(rollIndex + 2);
    }

    private int spareBonus(int rollIndex) {
        return getRoll(rollIndex + 2);
    }

    private int sumPinsInFrame(int rollIndex) {
        return getRoll(rollIndex) + getRoll(rollIndex + 1);
    }
}