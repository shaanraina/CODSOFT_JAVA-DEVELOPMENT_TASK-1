import java.util.Random;
import java.util.Scanner;

class NumberGuessingGame {

    private static final int MAX_ATTEMPTS = 10;
    private static final int RANGE_START = 1;
    private static final int RANGE_END = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        int totalScore = 0;
        int round = 1;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            int randomNumber = generateRandomNumber(RANGE_START, RANGE_END);
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + round + ":");
            System.out.println("I have generated a number between " + RANGE_START + " and " + RANGE_END + ". Try to guess it!");

            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                int userGuess = getUserGuess(scanner, RANGE_START, RANGE_END);
                attempts++;
                guessedCorrectly = checkGuess(userGuess, randomNumber);

                if (guessedCorrectly) {
                    System.out.println("Congratulations! You guessed the correct number.");
                } else {
                    provideHint(userGuess, randomNumber);
                    System.out.println("Attempts left: " + (MAX_ATTEMPTS - attempts));
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber + ".");
            }

            int roundScore = calculateScore(attempts);
            totalScore += roundScore;
            System.out.println("Round " + round + " score: " + roundScore);
            System.out.println("Total score: " + totalScore);

            playAgain = askToPlayAgain(scanner);
            round++;

        } while (playAgain);

        System.out.println("Thank you for playing! Your final score is " + totalScore);
        scanner.close();
    }

    private static int generateRandomNumber(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start + 1) + start;
    }

    private static int getUserGuess(Scanner scanner, int start, int end) {
        int guess = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter your guess (" + start + " - " + end + "): ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                if (guess >= start && guess <= end) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number within the specified range.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // clear invalid input
            }
        }
        return guess;
    }

    private static boolean checkGuess(int guess, int randomNumber) {
        return guess == randomNumber;
    }

    private static void provideHint(int guess, int randomNumber) {
        if (guess < randomNumber) {
            System.out.println("Your guess is too low. Try again.");
        } else {
            System.out.println("Your guess is too high. Try again.");
        }
    }

    private static int calculateScore(int attempts) {
        return MAX_ATTEMPTS - attempts;
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play another round? (yes/no): ");
        String response = scanner.next().trim().toLowerCase();
        return response.equals("yes");
    }
}