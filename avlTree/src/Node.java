public class Node {
    private Node left,right;
    private int value ,height;
    public Node(){
        left = null;
        right = null;

    }
    public Node(int value){
        this.value = value;
        left = null;
        right = null;
        height =0;

    }

    public int getHeight() {
        return height;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
