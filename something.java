import java.util.Scanner;
/**
 * Simple console program that scores the strength of a password.
 */
public class PasswordStrengthChecker {

    enum Strength { WEAK, MEDIUM, STRONG }

    static Strength evaluate(String password) {
        int score = 0;
        if (password.length() >= 8) score++;
        if (password.length() >= 12) score++;
        if (password.chars().anyMatch(Character::isLowerCase)) score++;
        if (password.chars().anyMatch(Character::isUpperCase)) score++;
        if (password.chars().anyMatch(Character::isDigit)) score++;
        if (password.chars().anyMatch(c -> !Character.isLetterOrDigit(c))) score++;

        if (score <= 2) return Strength.WEAK;
        if (score <= 4) return Strength.MEDIUM;
        return Strength.STRONG;
    }

    static void printTips(String password) {
        if (password.length() < 12) {
            System.out.println(" - Use at least 12 characters.");
        }
        if (password.chars().noneMatch(Character::isUpperCase)) {
            System.out.println(" - Add an uppercase letter.");
        }
        if (password.chars().noneMatch(Character::isDigit)) {
            System.out.println(" - Add a number.");
        }
        if (password.chars().allMatch(Character::isLetterOrDigit)) {
            System.out.println(" - Add a special character.");
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a password to check: ");
            String password = scanner.nextLine();
            Strength result = evaluate(password);
            System.out.println("Strength: " + result);
            if (result != Strength.STRONG) {
                System.out.println("Suggestions:");
                printTips(password);
            }
        }
    }
}
