
import java.util.ArrayList;

import java.util.List;

public class CoinChange {
    public static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> coinChange(int[] coins, int amount) {
        findSubsets(coins, amount, 0, new ArrayList<>());
        return result;
    }
    private static void findSubsets(int[] coins, int amount, int start, List<Integer> current) {
        if (amount == 0) {
            result.add(new ArrayList<>(current));
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




