import java.util.Stack;

public class BinaryTree {
    private Node root = null;

    public BinaryTree(String data) {
        this(new Node(data));
    }

    public BinaryTree(int data) {
        this(new Node(String.valueOf(data)));
    }

    public BinaryTree(double data) {
        this(new Node(Double.toString(data)));
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return this.root;
    }

    public String getRootData() {
        return this.root.getData();
    }

    public int getRootDataAsInt() {
        return Integer.parseInt(this.root.getData());
    }

    public double getRootDataAsDouble() {
        return Double.parseDouble(this.root.getData());
    }

    public void concat(BinaryTree leftTree, BinaryTree rightTree) {
        root.setLeftNode(leftTree.getRoot());
        root.setRightNode(rightTree.getRoot());
    }

    private void printPreOrderHelper(Node current) {
        if (current != null) {
            System.out.printf(current.getData() + " ");
            printInOrderHelper(current.getLeftNode());
            printInOrderHelper(current.getRightNode());
        }
    }

    public void printPreOrder() {
        printInOrderHelper(root);
    }

    private void printInOrderHelper(Node current) {
        if (current != null) {
            printInOrderHelper(current.getLeftNode());
            System.out.printf(current.getData() + " ");
            printInOrderHelper(current.getRightNode());
        }
    }

    public void printInOrder() {
        printInOrderHelper(root);
    }

    private void printPostOrderHelper(Node current) {
        if (current != null) {
            printPostOrderHelper(current.getLeftNode());
            printPostOrderHelper(current.getRightNode());
            System.out.printf(current.getData() + " ");
        }
    }

    public void printPostOrder() {
        printPostOrderHelper(root);
    }

    private void postOrderStackHelper(Node current, Stack<String> postOrder) {
        if (current != null) {
            postOrderStackHelper(current.getLeftNode(), postOrder);
            postOrderStackHelper(current.getRightNode(), postOrder);
            postOrder.push(current.getData());
        }
    }

    public Stack<String> postOrderStack() {
        Stack<String> postOrder = new Stack<>();
        postOrderStackHelper(root, postOrder);

        return postOrder;
    }

    private double evaluateHelper(Node current) {
        if (current.isLeaf()) {
            return Double.parseDouble(current.getData());
        }

        double left = evaluateHelper(current.getLeftNode());
        double right = evaluateHelper(current.getRightNode());
        switch (current.getData()) {
            case "+" -> {
                return left + right;
            } case "-" -> {
                return left - right;
            } case "*" -> {
                return left * right;
            } case "/" -> {
                return left / right;
            } case "^" -> {
                return Math.pow(left, right);
            } default -> {
                return 0;
            }
        }
    }

    public double evaluate() {
        return evaluateHelper(root);
    }
}
