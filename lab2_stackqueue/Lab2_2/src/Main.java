import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String expression;
        Scanner input = new Scanner(System.in);
        expression = input.nextLine();

        System.out.println(ArithmeticEvaluation.evaluate(expression));

    }
}
