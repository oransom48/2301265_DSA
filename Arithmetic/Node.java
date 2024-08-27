class Node {
    String data;
    Node left;
    Node right;

    public Node(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}