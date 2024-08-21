import java.util.*;

public class SubsetGenerator {

    // Function to generate all subsets using a stack
    public static void generateSubsetsStack(List<Integer> T) {
        Stack<List<Integer>> stk = new Stack<>();

        // Start with an empty subset
        stk.push(new ArrayList<>());

        while (!stk.isEmpty()) {
            // Get the top element of the stack
            List<Integer> current = stk.pop();

            // Print the current subset
            System.out.print("{ ");
            for (int x : current) {
                System.out.print(x + " ");
            }
            System.out.println("}");

            // Generate new subsets by adding remaining elements
            for (int i = 0; i < T.size(); i++) {
                if (current.isEmpty() || T.get(i) > current.get(current.size() - 1)) {
                    List<Integer> newSubset = new ArrayList<>(current);
                    newSubset.add(T.get(i));
                    stk.push(newSubset);
                }
            }
        }
    }

    // Function to generate all subsets using a queue
    public static void generateSubsetsQueue(List<Integer> T) {
        Queue<List<Integer>> q = new LinkedList<>();

        // Start with an empty subset
        q.offer(new ArrayList<>());

        while (!q.isEmpty()) {
            // Get the front element of the queue
            List<Integer> current = q.poll();

            // Print the current subset
            System.out.print("{ ");
            for (int x : current) {
                System.out.print(x + " ");
            }
            System.out.println("}");

            // Generate new subsets by adding remaining elements
            for (int i = 0; i < T.size(); i++) {
                if (current.isEmpty() || T.get(i) > current.get(current.size() - 1)) {
                    List<Integer> newSubset = new ArrayList<>(current);
                    newSubset.add(T.get(i));
                    q.offer(newSubset);
                }
            }
        }
    }
}
