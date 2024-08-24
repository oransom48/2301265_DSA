import com.sun.source.tree.BinaryTree;

public class Main {
    public static void main(String[] args) {
        AvlTree tre=  new AvlTree();
        tre.add(10,30,60,22,5,9,23,8,16);
        tre.printinorder();
        System.out.println("This is size of tree"+tre.size());

    }

}