import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder txt = new StringBuilder();
        try {
            File f = new File("string.txt");
            Scanner input = new Scanner(f);

            while (input.hasNextLine()) {
                txt.append(input.nextLine());
            }
            input.close();
        } catch (FileNotFoundException e) {
            txt = new StringBuilder();
        }
        int txtLength = txt.length();

        Stack<Character> s = new Stack<>();
        boolean balance = true;

        for (int i=0; i<txtLength; i++) {
            if (txt.charAt(i) == '(' || txt.charAt(i) == '{' || txt.charAt(i) == '[') {
                s.push(txt.charAt(i));
            } else if (txt.charAt(i) == ')' || txt.charAt(i) == '}' || txt.charAt(i) == ']') {
                if (s.isEmpty()) {
                    balance = false;
                    break;
                } else if ((txt.charAt(i) == ')' && s.peek() == '(') ||
                        (txt.charAt(i) == '}' && s.peek() == '{') ||
                        (txt.charAt(i) == ']' && s.peek() == '['))  {
                    s.pop();
                } else {
                    balance = false;
                    break;
                }
            }
        }
        if (!s.isEmpty() && balance) {
            balance = false;
        }

        System.out.println(txt);

        if (balance) {
            System.out.println("The file is balanced.");
        } else {
            System.out.println("The file is not balanced.");
        }
    }
}