import java.util.*;

public class SubsetGenerator {

    public static int cutBracket(String txt) {
        if (txt.charAt(0) == '{') {
            return Integer.parseInt(txt.substring(1, txt.length()-1));
        }
        return Integer.parseInt(txt);
        
    }

    // Function to generate all subsets using a stack
    public static void generateSubsetsStack(List<String> T) {
        Stack<List<String>> stk = new Stack<>();

        // Start with an empty subset
        stk.push(new ArrayList<>());

        while (!stk.isEmpty()) {
            // Get the top element of the stack
            List<String> current = stk.pop();

            // Print the current subset
            System.out.print("{ ");
            for (String x : current) {
                System.out.print(x + " ");
            }
            System.out.println("}");

            // Generate new subsets by adding remaining elements
            for (int i = 0; i < T.size(); i++) {
                if (current.isEmpty() || cutBracket(T.get(i)) > cutBracket(current.get(current.size() - 1))) {
                    List<String> newSubset = new ArrayList<>(current);
                    newSubset.add(T.get(i));
                    stk.push(newSubset);
                }
            }
        }
    }

    // Function to generate all subsets using a queue
    public static void generateSubsetsQueue(List<String> T) {
        Queue<List<String>> q = new LinkedList<>();

        // Start with an empty subset
        q.offer(new ArrayList<>());

        while (!q.isEmpty()) {
            // Get the front element of the queue
            List<String> current = q.poll();

            // Print the current subset
            System.out.print("{ ");
            for (String x : current) {
                System.out.print(x + " ");
            }
            System.out.println("}");

            // Generate new subsets by adding remaining elements
            for (int i = 0; i < T.size(); i++) {
                if (current.isEmpty() || cutBracket(T.get(i)) > cutBracket(current.get(current.size() - 1))) {
                    List<String> newSubset = new ArrayList<>(current);
                    newSubset.add(T.get(i));
                    q.offer(newSubset);
                }
            }
        }
    }
}
