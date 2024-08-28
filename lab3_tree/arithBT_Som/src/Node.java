public class Node {
    private String data;
    private Node leftNode;
    private Node rightNode;

    public Node() {
        this("", null, null);
    }

    public Node(String data) {
        this(data, null, null);
    }

    public Node(String data, Node leftNode, Node rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public String getData() {
        return data;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public boolean isLeaf() {
        return leftNode == null && rightNode == null;
    }
}
