import java.util.ArrayList;
import java.util.Stack;

public class ArithmeticEvaluation {

    private static int precedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '^') {
            return 3;
        } else {
            return -1;
        }
    }

    private static double applyOp(double a, double b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                yield a / b;
            }
            case '^' -> Math.pow(a,b);
            default -> 0;
        };
    }

    private static void performOperation(Stack<Double> operands, Stack<Character> operators) {
        double b = operands.pop();
        double a = operands.pop();
        char op = operators.pop();
        operands.push(applyOp(a, b, op));
    }

    public static String evaluate(String expression) {
        ArrayList<String> tokens = getTokens(expression);

        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "(" -> operators.push(token.charAt(0));
                case ")" -> {
                    while (!operators.isEmpty() && operators.peek() != '(') {//แฮนเดิ้ล การใส่() หรือใส่แค่(
                        performOperation(operands, operators);
                    }
                    operators.pop();
                }
                case "+", "-", "*", "/" , "^" -> {
                    char currentOperator = token.charAt(0);
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(currentOperator)) {
                        performOperation(operands, operators);
                    }
                    operators.push(currentOperator);
                }
                default -> operands.push(Double.parseDouble(token));
            }


        }

        while (!operators.isEmpty()) {
            performOperation(operands, operators);
        }

        if (!operands.isEmpty()) {
            return String.valueOf(operands.pop());
        }

        return "0";
    }

    private static ArrayList<String> getTokens(String expression) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c=='^') {
                if (!sb.isEmpty()) {
                    tokens.add(sb.toString());
                    sb.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else {
                sb.append(c);
            }
        }

        if (!sb.isEmpty()) {
            tokens.add(sb.toString());
        }
        return tokens;
    }

}
