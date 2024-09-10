import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CoinChange {

    // ฟังก์ชันในการหาวิธีการทอนเงินทั้งหมด
    public static void findWays(int amount, int[] coins, List<Integer> currentCombination, int index, List<List<Integer>> allCombinations) {
        if (amount == 0) {
            allCombinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = index; i < coins.length; i++) {
            if (amount >= coins[i]) {
                currentCombination.add(coins[i]);
                findWays(amount - coins[i], coins, currentCombination, i, allCombinations);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }

    // ฟังก์ชันในการหาจำนวนวิธีในการทอนเงิน
    public static int countWaysToMakeChange(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // base case: มีวิธีเดียวในการทำให้จำนวน 0 คือไม่ใช้เหรียญ

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }

        return dp[amount];
    }

    // ฟังก์ชันในการหาจำนวนเหรียญน้อยที่สุดในการทอนเงิน
    public static int minCoins(int amount, int[] coins, int[] coinUsed) {
        int[] dp = new int[amount + 1];
        // กำหนดค่าเริ่มต้นให้เป็นค่าที่ใหญ่ที่สุด
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0; // base case: 0 เหรียญ ในการทำให้จำนวน 0

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                if (dp[j - coin] != Integer.MAX_VALUE && dp[j] > dp[j - coin] + 1) {
                    dp[j] = dp[j - coin] + 1;
                    coinUsed[j] = coin;
                }
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[amount];
        }
    }

    // ฟังก์ชันที่ใช้แสดงชุดเหรียญที่น้อยที่สุดในการทอนเงิน
    public static void printMinCoinChange(int amount, int[] coinUsed) {
        List<Integer> minCoins = new ArrayList<>();
        while (amount > 0) {
            int coin = coinUsed[amount];
            minCoins.add(coin);
            amount -= coin;
        }
        // เรียงลำดับชุดเหรียญจากน้อยไปมาก
        Collections.sort(minCoins);
        System.out.print("{");
        for (int i = 0; i < minCoins.size(); i++) {
            if (i > 0) System.out.print(",");
            System.out.print(minCoins.get(i));
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // รับจำนวนเงิน
        System.out.print("Amount = ");
        int amount = scanner.nextInt();
        scanner.nextLine(); // ล้างบรรทัดที่เหลือ

        // รับชุดเหรียญในรูปแบบ {1,2,3}
        System.out.print("coins [] = ");
        String coinInput = scanner.nextLine().trim();
        // ลบเครื่องหมาย '{' และ '}' และแยกตัวเลขด้วยเครื่องหมาย ','
        coinInput = coinInput.replaceAll("[{}]", "").trim();
        String[] coinStrings = coinInput.split(",");
        Set<Integer> coinSet = new HashSet<>();
        for (String coinStr : coinStrings) {
            int coin = Integer.parseInt(coinStr.trim());
            coinSet.add(coin); // ใช้ Set เพื่อป้องกันค่าซ้ำ
        }
        int[] coins = coinSet.stream().mapToInt(Integer::intValue).toArray();

        // คำนวณและแสดงวิธีการทอนเงินทั้งหมด
        List<List<Integer>> allCombinations = new ArrayList<>();
        findWays(amount, coins, new ArrayList<>(), 0, allCombinations);
        System.out.println("Ways to make change = " + allCombinations.size());
        for (List<Integer> combination : allCombinations) {
            System.out.print("{");
            for (int i = 0; i < combination.size(); i++) {
                if (i > 0) System.out.print(",");
                System.out.print(combination.get(i));
            }
            System.out.print("} ");
        }
        System.out.println();

        // คำนวณและแสดงจำนวนเหรียญน้อยที่สุด
        int[] coinUsed = new int[amount + 1]; // สร้างอาร์เรย์เพื่อเก็บเหรียญที่ใช้
        int minCoins = minCoins(amount, coins, coinUsed);
        if (minCoins == -1) {
            System.out.println("ไม่สามารถเปลี่ยนเงินจำนวน " + amount + " ได้ด้วยเหรียญที่ให้มา");
        } else {
            System.out.println("Minimum of Coins is " + minCoins);
            // แสดงผลลัพธ์ชุดเหรียญที่น้อยที่สุด
            printMinCoinChange(amount, coinUsed);
        }

        scanner.close();
    }
}

