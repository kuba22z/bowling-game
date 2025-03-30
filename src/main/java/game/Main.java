package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final var game = new BowlingGameState(10,10);

        for (int frame = 1; frame <= game.getNumberOfFrames(); frame++) {
            System.out.println("Frame " + frame);

            final var firstRoll = promptValidScore(scanner, "Enter 1.roll: ", 0, game.getNumberOfPins());
            game.roll(firstRoll);

            if (!game.hasCurrentFrameStrike()) {
                final var secondRoll = promptValidScore(scanner, "Enter 2.roll: ", 0, game.getNumberOfPins() - firstRoll);
                game.roll(secondRoll);
            }
        }

        if(game.hasPreviousFrameSpare() || game.hasPreviousFrameStrike()) {
            int bonusRolls = game.hasPreviousFrameStrike() ? 2 : 1;

            for (int i = 0; i < bonusRolls; i++) {
                game.roll(promptValidScore(scanner, "Enter bonus roll: ", 0, game.getNumberOfPins()));
            }
        }

        scanner.close();
        System.out.println("Final Score: " + game.calculateScore());
    }

    private static int promptValidScore(Scanner scanner, String message, int min, int max) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                final var score = scanner.nextInt();
                if (isInputValid(score, min, max)) {
                    return score;
                }
            } else {
                scanner.next();
            }
            System.out.printf("Please enter a number between %s and %s%n", min, max);
        }
    }

    private static boolean isInputValid(int score, int min, int max) {
        return score >= min && score <= max;
    }
}
