import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static int precedence(String c) {
        return switch (c) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> -1;
        };
    }

    private static BinaryTree applyOp(BinaryTree left, BinaryTree right, String op) {
        double a = left.getRootDataAsDouble();
        double b = right.getRootDataAsDouble();

        /* debug
        double result = 0;
        switch (op) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> result = a / b;
            case "^" -> result = Math.pow(a,b);
        };
        System.out.println(a + op + b + "=" + result);
         */

        return switch (op) {
            case "+" -> new BinaryTree(a + b);
            case "-" -> new BinaryTree(a - b);
            case "*" -> new BinaryTree(a * b);
            case "/" -> {
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                yield new BinaryTree(a / b);
            }
            case "^" -> new BinaryTree(Math.pow(a,b));
            default -> new BinaryTree(0);
        };
    }

    private static void performOperation(Stack<BinaryTree> tree, Stack<String> operators) {
        BinaryTree right = tree.pop();
        BinaryTree left = tree.pop();
        String op = operators.pop();
        tree.push(applyOp(left, right, op));
    }

    private static void concatTree(Stack<BinaryTree> tree, Stack<String> operators) {
        BinaryTree right = tree.pop();
        BinaryTree left = tree.pop();
        String op = operators.pop();

        BinaryTree newBT = new BinaryTree(op);
        newBT.concat(left, right);
        tree.push(newBT);
    }

    private static ArrayList<String> getTokens(String expression) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (c == ' ') {
                continue;
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c=='^') {
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();
        ArrayList<String> tokens = getTokens(expression);

        Stack<BinaryTree> tree = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "(" -> operators.push(token);
                case ")" -> {
                    while (!operators.isEmpty() && !operators.peek().equals("(")) {
                        // performOperation(tree, operators);
                        concatTree(tree, operators);
                    }
                    operators.pop();
                }
                case "+", "-", "*", "/", "^" -> {
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                        // performOperation(tree, operators);
                        concatTree(tree, operators);
                    }
                    operators.push(token);
                }
                default -> tree.push(new BinaryTree(token));
            }
        }

        while (!operators.isEmpty() ) {
            // performOperation(tree, operators);
            concatTree(tree, operators);
        }

        if (!tree.isEmpty()) {
            //System.out.println(tree.pop().getRootData());
            tree.peek().printPostOrder();
            System.out.println();
            System.out.println(tree.pop().evaluate());
        }
    }
}

/* testcase
(3 + 5) * ((2 - 8) / (4 + 6))
5 + 3 - 2
2 + 3 * (4 - 2)
3 + 5 - 2
(1 + 2) * 3
(2 + (3 - 1)) * 4
2 + (3 - (4 + 5))
1 + (2 + (3 + (4 - 5)))
((2 + 3) * (4 + 5) - (3 + 4) / 2)
-3 + (-2) * (-2)
(10 + 20 - 30) + (40 * 2) - (50 / 10)
8 * 3 / 4
10 / (2 + (3 * 2))
((2 + 3) * (5 - 2)) / 5
 */
