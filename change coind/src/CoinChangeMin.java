import java.util.ArrayList;
import java.util.List;

public class CoinChangeMin {
    public static List<List<Integer>> result = new ArrayList<>();
    public static int minSize ;

    public static List<List<Integer>> coinChangeMin(int[] coins, int amount) {
        result.clear();
        minSize = coins.length;
        findSubsets(coins, amount, 0, new ArrayList<>());
        return result;
    }
    private static void findSubsets(int[] coins, int amount, int start, List<Integer> current) {
        if (amount == 0) {
            if (current.size() < minSize) {
                minSize = current.size(); // Update the minimum size
                result.clear(); // Clear previous results
                result.add(new ArrayList<>(current)); // Add the new minimum subset
            }
            else if (current.size() ==minSize) result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < coins.length; i++) {
            if (coins[i] > amount) continue;
            current.add(coins[i]);
            findSubsets(coins, amount - coins[i], i, current);
            // Backtrack: remove the last added coin
            current.remove(current.size() - 1);
        }
    }

}

