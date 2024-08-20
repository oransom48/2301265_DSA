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

    private static int applyOp(int a, int b, char op) {
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
            default -> 0;
        };
    }

    private static void performOperation(Stack<Integer> operands, Stack<Character> operators) {
        int b = operands.pop();
        int a = operands.pop();
        char op = operators.pop();
        operands.push(applyOp(a, b, op));
    }

    public static String evaluate(String expression) {
        ArrayList<String> tokens = getTokens(expression);

        Stack<Integer> operands = new Stack<>();
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
                case "+", "-", "*", "/" -> {
                    char currentOperator = token.charAt(0);
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(currentOperator)) {
                        performOperation(operands, operators);
                    }
                    operators.push(currentOperator);
                }
                default -> operands.push(Integer.parseInt(token));
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
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
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
