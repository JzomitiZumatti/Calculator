import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    Scanner scanner = new Scanner(System.in);
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_RESET = "\u001B[0m";
    private double tempArg;

    public void numbersValidator() {
        double input;
        boolean inputIsValid = false;

        do {
            try {
                input = scanner.nextDouble();
                tempArg = input;
                inputIsValid = true;
            } catch (InputMismatchException e) {
                System.out.print(ANSI_RED + "Wrong input! Try Again: " + ANSI_RESET);
                scanner.next();
            }
        } while (!inputIsValid);
    }

    public double doCalc(double firstArg, double secondArg, String operator) {
        double result = Double.NaN;
        try {
            switch (operator) {
                case "+" -> result = firstArg + secondArg;
                case "-" -> result = firstArg - secondArg;
                case "*" -> result = firstArg * secondArg;
                case "/" -> {
                    if (secondArg != 0) {
                        result = firstArg / secondArg;
                    } else {
                        throw new ArithmeticException(ANSI_RED + "Dividing by zero!" + ANSI_RESET);
                    }
                }
                default -> System.out.println(ANSI_RED + "Wrong option!" + ANSI_RESET);
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void menu() {
        while (true) {
            System.out.print("Choose option (+, -, *, /) or \"exit\" for ending program: ");
            String operator = scanner.next().toLowerCase();

            while (!operator.equals("+") && !operator.equals("-") && !operator.equals("*")
                    && !operator.equals("/") && !operator.equals("exit")) {
                System.out.print(ANSI_RED + "Wrong operator! Try again: " + ANSI_RESET);
                operator = scanner.next().toLowerCase();
            }

            if (operator.equalsIgnoreCase("exit")) {
                System.out.println("See you later!");
                break;
            }

            System.out.print("Enter the first number: ");
            numbersValidator();
            double firstArg = tempArg;

            System.out.print("Enter the second number: ");
            numbersValidator();
            double secondArg = tempArg;

            double result = doCalc(firstArg, secondArg, operator);

            if (!Double.isNaN(result)) {
                System.out.printf("Result is: %.2f.\n", result);
            }
        }
    }
}