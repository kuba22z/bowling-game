package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final var game = new BowlingGameState();

        final var score = promptValidScore(scanner, "Enter 1.roll: ", 0, 10);

        scanner.close();
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
