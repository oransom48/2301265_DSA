import java.util.Scanner;

public class Main {

    public static void print2DArr(int[][] arr, int[] rowHead) {
        for (int i=0; i<arr.length; i++) {
            System.out.printf("%d\t", rowHead[i]);
            for (int j=0; j<arr[0].length; j++) {
                System.out.printf("%d ", arr[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static void printMinWay(int[] coins, int[] rec) {
        if (rec[rec.length-1] == -1) {
            System.out.println("No solution");
            return;
        }

        int idx = rec.length - 1;
        while (idx != 0) {
            System.out.printf("%d ", coins[rec[idx]]);
            idx = idx - coins[rec[idx]];
        }
        System.out.print("\n");
    }

    public static int numOfWays(int target, int[] coins) {
        if (coins.length == 1) return 0;
        if (target == 0) return 1;

        int n = coins.length;
        int[][] dp = new int[n][target+1];

        for (int j=1; j<=target; j++) dp[0][j] = 0;
        for (int i=0; i<n; i++) dp[i][0] = 1;
        for (int i=1; i<n; i++) {
            for (int j=1; j<=target; j++) {
                if (coins[i] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] + dp[i][j-coins[i]];
            }
        }

        // print2DArr(dp, coins);
        return dp[n-1][target];
    }

    public static int minWay(int target, int[] coins) {
        if (target == 0 || coins.length == 1) return 0;

        int[] dp = new int[target+1];
        dp[0] = 0;
        int[] rec = new int[target+1];

        for (int i=1; i<=target; i++) dp[i] = Integer.MAX_VALUE-1;
        for (int i=0; i<=target; i++) rec[i] = -1;

        for (int i = 1; i< coins.length; i++) {
            for (int j = coins[i]; j<=target; j++) {
                dp[j] = Math.min(dp[j], dp[j- coins[i]] + 1);
                rec[j] = i;
            }
        }

        // printMinWay(coins, rec);
        return dp[target];
    }

    public static int[] strToIntArr(String s) {
        String[] str = s.split(" ");
        int[] num = new int[str.length+1];
        num[0] = 0;
        for (int i=1; i<=str.length; i++) {
            num[i] = Integer.parseInt(str[i-1]);
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // get input
        String strTarget = input.nextLine();
        int target = Integer.parseInt(strTarget);

        String strArr = input.nextLine();
        int[] coins = strToIntArr(strArr);

        int ans1 = numOfWays(target, coins);
        System.out.println(ans1);

        int ans2 = minWay(target, coins);
        if (ans2 == Integer.MAX_VALUE-1) System.out.println(ans1);
        else System.out.println(ans2);
    }
}

/*
12
2 6 7 10

4
1 2 3

10
1 2 3 4 5

10
2 3 5 6 8 10

3
2

0
1
 */
