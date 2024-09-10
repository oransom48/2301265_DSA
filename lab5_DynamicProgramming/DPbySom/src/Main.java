import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void printDP(boolean[][] arr, int[] num) {
        for (int i=0; i< arr.length; i++) {
            System.out.printf("%d\t", num[i]);
            for (int j=0; j<arr[0].length; j++) {
                if (arr[i][j]) System.out.print("T ");
                else System.out.print("F ");
            }
            System.out.print("\n");
        }
    }

    public static void printSubset(ArrayList<Integer> ans) {
        for (int a: ans) {
            System.out.printf("%d ", a);
        }
        System.out.print("\n");
    }

    public static void findSubsetRecord(boolean[][] dp, int[] num, int i, int sum, ArrayList<Integer> ans) {
        if (i==0 && sum==0) {
            printSubset(ans);
            return;
        }

        if (dp[i-1][sum]) {
            ArrayList<Integer> b = new ArrayList<>(ans);
            findSubsetRecord(dp, num, i-1, sum, b);
        }

        if (num[i] <= sum && dp[i-1][sum-num[i]]) {
            ans.add(num[i]);
            findSubsetRecord(dp, num, i-1, sum-num[i], ans);
        }

        System.out.printf("Hi %d %d\n", i, sum);
    }

    public static boolean[][] findTargetSubset(int target, int[] num) {
        int n = num.length;
        boolean[][] dp = new boolean[n][target+1];

        for (int i=0; i<n; i++) dp[i][0] = true;
        for (int j=1; j<target+1; j++) dp[0][j] = false;

        for (int i=1; i<n; i++) {
            for (int j=1; j<=target; j++) {
                if (num[i] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-num[i]];
                }
            }
        }
        return dp;
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
        int[] num = strToIntArr(strArr);

        ArrayList<Integer> ans = new ArrayList<>();
        findSubsetRecord(findTargetSubset(target, num), num, num.length-1, target, ans);
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
 */
