package game;

class BowlingGameState {
    private static final int ALL_PINS = 10;

    private int currentRoll = 0;
    private final int[] rolls = new int[21];

    public void roll(int pin) {
        rolls[currentRoll++] = pin;
    }

    public int calculateScore() {
        int score = 0;

        for (int frame = 0, rollIndex = 0; frame < 10; frame++) {
            if (isStrike(rollIndex)) {
                score += ALL_PINS + strikeBonus(rollIndex);
                rollIndex++;
            }
            else if (isSpare(rollIndex)) {
                score += ALL_PINS + spareBonus(rollIndex);
                rollIndex += 2;
            }
            else {
                score += sumPinsInFrame(rollIndex);
                rollIndex += 2;
            }
        }

        return score;
    }

    private int getRoll(int rollIndex) {
        return rolls[rollIndex];
    }

    private boolean isStrike(int rollIndex) {
        return getRoll(rollIndex) == ALL_PINS;
    }

    private boolean isSpare(int rollIndex) {
        return getRoll(rollIndex) + getRoll(rollIndex + 1) == ALL_PINS;
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