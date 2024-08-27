import javax.swing.*;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        String expression = "-3 + (-2) * (-2)" ;

        ArithmeticCalculator tree = new ArithmeticCalculator();
        tree.root = tree.constructTree(expression);
        tree.printtree();

        BigDecimal result = tree.evaluate(tree.root);
        System.out.println("The result of the expression is: " + result);


    }
}