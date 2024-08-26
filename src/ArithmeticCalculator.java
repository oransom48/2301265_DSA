import java.math.BigDecimal;
import java.util.Stack;

public class ArithmeticCalculator {
    Node root;
    int size;

    // Method to evaluate the expression tree
    public BigDecimal evaluate(Node root) {
        if (root.isLeaf()) return BigDecimal.valueOf(Double.parseDouble(root.data));

        BigDecimal leftValue = evaluate(root.left);
        BigDecimal rightValue = evaluate(root.right);

        switch (root.data) {
            case "+":
                return leftValue.add(rightValue);
            case "-":
                return leftValue.add(rightValue.negate());
            case "*":
                return leftValue.multiply(rightValue);
            case "/":
                if (rightValue.equals(0)) {
                    throw new ArithmeticException("Error! Division by zero is not allowed.");
                }
                return leftValue.divide(rightValue);
            case "^":
                return leftValue.pow(rightValue.intValue());
            default:
                throw new IllegalArgumentException("Invalid operator: " + root.data);
        }
    }

    // Method to check if the parentheses are balanced
    public boolean areParenthesesBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // Method to construct the expression tree from a string
    public Node constructTree(String expression) {
        expression = expression.replaceAll(",", ""); // Remove thousand separators
        if (!areParenthesesBalanced(expression)) {
            throw new IllegalArgumentException("Invalid expression: unbalanced parentheses.");
        }

        Stack<Node> nodes = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                continue;
            }

            if (ch == '(') {
                continue;
            } else if (Character.isDigit(ch) || ch == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--; // To counteract the extra increment from the while loop
                nodes.push(new Node(num.toString()));
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    Node right = nodes.pop();
                    Node left = nodes.pop();
                    Node operator = new Node(String.valueOf(operators.pop()));
                    operator.left = left;
                    operator.right = right;
                    nodes.push(operator);
                }
            } else if (isOperator(ch)) {
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            Node right = nodes.pop();
            Node left = nodes.pop();
            Node operator = new Node(String.valueOf(operators.pop()));
            operator.left = left;
            operator.right = right;
            nodes.push(operator);
        }

        return nodes.peek();
    }

    // Helper method to determine if a character is an operator
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    public static void main(String[] args) {
        String expression = "5 + (((3.3 + 6.6) * 9.2 ) + 12,546) * 2,323 +"
                + "( ( ( 33.3 + 2342.1 ) * 55.555 ) - 10000.009 ) + 11.334 * 2.3 ^ 3";

        ArithmeticCalculator calculator = new ArithmeticCalculator();
        Node root = calculator.constructTree(expression);

        BigDecimal result = calculator.evaluate(root);
        System.out.println("The result of the expression is: " + result);
    }
}
