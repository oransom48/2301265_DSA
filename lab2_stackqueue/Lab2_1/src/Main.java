import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String txt = input.nextLine();
        int txtlength = txt.length();

        Stack<Character> s = new Stack<>();
        String message = "";
        boolean allpaired = true;

        for (int i=0; i<txtlength; i++) {
            if (txt.charAt(i) == '(' || txt.charAt(i) == '{' || txt.charAt(i) == '[') {
                s.push(txt.charAt(i));
            } else {
                if (s.isEmpty()) {
                    allpaired = false;
                    break;
                } else if ((txt.charAt(i) == ')' && s.peek() == '(') ||
                        (txt.charAt(i) == '}' && s.peek() == '{') ||
                        (txt.charAt(i) == ']' && s.peek() == '['))  {
                    s.pop();
                } else {
                    allpaired = false;
                    break;
                }
            }
        }
        if (!s.isEmpty() && allpaired) {
            allpaired = false;
        }

        if (allpaired) {
            System.out.println("The file is balanced. \n" + txt);
        } else {
            System.out.println("The file is not balanced. \n " + txt);
        }
    }
}