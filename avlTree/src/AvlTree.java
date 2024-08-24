import static java.lang.Math.max;
public class AvlTree {
    private static int size;
    public Node root;
    private static void inorder(Node st){
        Node rootnode = st;
        if(st == null) return ;
        inorder(st.getLeft());
        System.out.print(st.getValue()+ " " );
        inorder(st.getRight());
    }
    private static int height(Node node) {
        return node != null ? node.getHeight() : -1;
    }

    public AvlTree(){
        size =0;
        root = null;

    }
    public boolean isEmpty(){return size==0;}
    public int size (){return size;}
    //to find locate to add number from add method
    public static Node insert(Node rootnode , int number){

        if (rootnode == null ) {
            return new Node(number);
        }
        if (number < rootnode.getValue()){
            rootnode.setLeft(insert(rootnode.getLeft(), number));
        }
        else if (number > rootnode.getValue()){
            rootnode.setRight(insert(rootnode.getRight(), number));
        }
        else {
            throw new IllegalArgumentException("BST already contains a node with key " + number);
        }
        updateHeight(rootnode);
        int balace_factor = balanceFactor(rootnode);
        //imbalance on the  ̶r̶i̶g̶h̶t̶ ̶h̶a̶n̶d̶ ̶s̶i̶d̶e̶?̶ ̶ right
        if(balace_factor >1){
            //right left
            if ( number < rootnode.getRight().getValue()){
                rootnode.setRight(rightRotation(rootnode.getRight()));
            }
            return leftRotation(rootnode);

        }

        //imbalance on the left
        else if ( balace_factor < -1){
            //left right
            if ( number > rootnode.getValue()){
                rootnode.setLeft(leftRotation(rootnode.getLeft()));
            }
            return rightRotation(rootnode);
        }
        return rootnode;
    }
    //right imbalance
    private static Node leftRotation(Node node) {
        Node nodechild = node.getRight();
        node.setRight(nodechild.getLeft());
        nodechild.setLeft(node);
        return nodechild;
    }
    //left imbalance
    private static Node rightRotation(Node node) {
        Node nodechild = node.getLeft();
        node.setLeft(nodechild.getRight());
        nodechild.setRight(node);
        return nodechild;
    }
    private static void updateHeight(Node node){

        if (node != null) {
            int leftChildHeight = height(node.getLeft());
            int rightChildHeight = height(node.getRight());
        node.setHeight(Math.max(leftChildHeight, rightChildHeight) +1) ;}
    }
    private static int balanceFactor(Node node) {
        return height(node.getRight()) - height(node.getLeft());
    }

    public void add(int... input ){
        Node finalroot = null;
        for(int x: input){
            if ( size == 0){
                root = new Node(x);
                size++;
            }

            //add element
            else{
                finalroot = insert(root,x);
                root = finalroot;

                size++;
            }


        }

    }

    public void printinorder(){

        inorder(root);
        //to test root node
        //System.out.println("The root is "+ root.getValue());
        System.out.println();
    }

}

