import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        String expression = "5 + (((3.3 + 6.6) * 9.2 ) + 12,546) * 2,323 +" +
                "( ( ( 33.3 + 2342.1 ) * 55.555 ) - 10000.009 ) + 11.334 * 2.3 ^3";

        ArithmeticCalculator tree = new ArithmeticCalculator();
        Node root = tree.constructTree(expression);

        BigDecimal result = tree.evaluate(root);
        System.out.println("The result of the expression is: " + result);

    }
}