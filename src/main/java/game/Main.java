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

        final var bonusRollMessage = "Enter bonus roll: ";
        if(game.hasPreviousFrameSpare()) {
            game.roll(promptValidScore(scanner, bonusRollMessage, 0, game.getNumberOfPins()));
        }
        else if(game.hasPreviousFrameStrike()) {
                game.roll(promptValidScore(scanner, bonusRollMessage, 0, game.getNumberOfPins()));
                if (game.hasCurrentFrameStrike()) {
                    game.roll(promptValidScore(scanner, bonusRollMessage, 0, game.getNumberOfPins()));
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
