import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> T = Arrays.asList(1, 2, 3, 4);

        System.out.println("All possible subsets stack:");
        SubsetGenerator.generateSubsetsStack(T);

        System.out.println("All possible subsets queue:");
        SubsetGenerator.generateSubsetsQueue(T);
    }
}
