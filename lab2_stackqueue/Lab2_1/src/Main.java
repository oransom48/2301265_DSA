import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String txt = "({()[{}]})";
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
                    message = "too much close parentheses";
                    break;
                } else if ((txt.charAt(i) == ')' && s.peek() == '(') ||
                        (txt.charAt(i) == '}' && s.peek() == '{') ||
                        (txt.charAt(i) == ']' && s.peek() == '['))  {
                    s.pop();
                } else {
                    allpaired = false;
                    message = "wrong parenthese pair";
                    break;
                }
            }
        }
        if (!s.isEmpty() && allpaired) {
            allpaired = false;
            message = "too much open parentheses";
        }

        if (allpaired) {
            System.out.println("All paired");
        } else {
            System.out.println((message));
        }
    }
}