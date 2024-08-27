import java.math.BigDecimal;
import java.util.Stack;

public class ArithmeticCalculator {
    Node root;

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
    private static void printTree(Node node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.data);
            printTree(node.right, prefix + (isTail ? "    " : "│   "), false);
            printTree(node.left, prefix + (isTail ? "    " : "│   "), true);
        }
    }
    public void printtree(){
        printTree(root,"",true);

    }
    private int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    // Method to construct the expression tree from a string
    public Node constructTree(String expression) {
        expression = expression.replaceAll(",", "");
        if (!areParenthesesBalanced(expression)) {
            throw new IllegalArgumentException("Invalid expression: unbalanced parentheses.");
        }

        Stack<Node> nodes = new Stack<>();
        Stack<Character> operators = new Stack<>();
        boolean lastWasOperator = true;

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                continue;
            }

            if (ch == '(') {
                operators.push(ch);
            } else if (Character.isDigit(ch) || (ch == '-' && lastWasOperator)) {
                StringBuilder num = new StringBuilder();
                if (ch == '-') {
                    num.append(ch);
                    i++;
                }
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--; // To counteract the extra increment from the while loop
                nodes.push(new Node(num.toString()));
                lastWasOperator = false;
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    Node right = nodes.pop();
                    Node left = nodes.pop();
                    Node operator = new Node(String.valueOf(operators.pop()));
                    operator.left = left;
                    operator.right = right;
                    nodes.push(operator);
                }
                operators.pop(); // Pop the '('
                lastWasOperator = false;
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                    Node right = nodes.pop();
                    Node left = nodes.pop();
                    Node operator = new Node(String.valueOf(operators.pop()));
                    operator.left = left;
                    operator.right = right;
                    nodes.push(operator);
                }
                operators.push(ch);
                lastWasOperator = true;
            }
        }

        while (!operators.isEmpty()) {
            Node right = nodes.pop();
            Node left = nodes.pop();
            Node operator = new Node(String.valueOf(operators.pop()));
            operator.left = left;
            operator.right = right;
            nodes.push(operator);
            //test input node into tree structure
            //printTree(nodes.peek(),"", true);
        }

        return nodes.peek();
    }

    // Helper method to determine if a character is an operator
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

}
