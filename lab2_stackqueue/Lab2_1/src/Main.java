import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static String stringIgnore(String txt) {
        StringBuilder ansBuilder = new StringBuilder();
        int txtLength = txt.length();

        boolean foundQuote = false;
        for (int i=0; i<txtLength; i++) {
            if (txt.charAt(i) == '\"' && foundQuote) { // foundQuote = true
                foundQuote = false;
            } else if (txt.charAt(i) == '\"' && !foundQuote) { // foundQuote = false
                foundQuote = true;
            } else if (!foundQuote) { // foundQuote = false
                ansBuilder.append(txt.charAt(i));
            }
        }
        return ansBuilder.toString();
    }

    public static void main(String[] args) {
        StringBuilder txt = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("string.txt"));
            String line = reader.readLine().strip();

            while (line != null) {
                if (line.charAt(0) != '#') {
                    txt.append(stringIgnore(line));
                    System.out.println(stringIgnore(line));
                }
                // read next line
                line = reader.readLine().strip();
            }

            reader.close();
        } catch (Exception e) {
            txt = new StringBuilder();
        }
        int txtLength = txt.length();

        Stack<Character> s = new Stack<>();
        String message= "";
        boolean balance = true;

        for (int i=0; i<txtLength; i++) {
            if (txt.charAt(i) == '(' || txt.charAt(i) == '{' || txt.charAt(i) == '[') {
                s.push(txt.charAt(i));
            } else if (txt.charAt(i) == ')' || txt.charAt(i) == '}' || txt.charAt(i) == ']') {
                if (s.isEmpty()) {
                    balance = false;
                    message = "Too much close parenthese";
                    break;
                } else if ((txt.charAt(i) == ')' && s.peek() == '(') ||
                        (txt.charAt(i) == '}' && s.peek() == '{') ||
                        (txt.charAt(i) == ']' && s.peek() == '['))  {
                    s.pop();
                } else {
                    balance = false;
                    message = "Wrong parenthese pair";
                    break;
                }
            }
        }
        if (!s.isEmpty() && balance) {
            message = "Too much open parenthese";
            balance = false;
        }

//        System.out.println(txt);

        if (balance) {
            System.out.println("The file is balanced.");
        } else {
            System.out.println("The file is not balanced.");
            System.out.println(message);
        }
    }
}