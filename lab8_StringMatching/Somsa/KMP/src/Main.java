import java.util.Scanner;

public class Main {
    public static int[] computePrefix(String[] pattern, int n) {
        int[] prefix = new int[n+1];
        prefix[0] = 0;
        prefix[1] = 0;
        int k = 0;
        for (int i=2; i<=n; i++) {
            while (k > 0 && !pattern[k+1].equals(pattern[i])) {
                k = prefix[k];
            }
            if (pattern[k+1].equals(pattern[i])) {
                k++;
            }
            prefix[i] = k;
        }
        return prefix;
    }

    public static String[] getArrFromTable(String[][] table, int startIndex, int directionIndex) {
        int row = table.length;
        int col = table[0].length;

        if (directionIndex == 0) {
            // 0 = UB
            String[] arr = new String[row + 1];
            for (int i=1; i<=row; i++) {
                arr[i] = table[i-1][startIndex];
            }
            return arr;
        } else if (directionIndex == 1) {
            // 1 = BU
            String[] arr = new String[row + 1];
            for (int i=1; i<=row; i++) {
                arr[i] = table[row-i][startIndex];
            }
            return arr;
        } else if (directionIndex == 2) {
            // 2 = LR
            String[] arr = new String[col + 1];
            for (int i=1; i<=col; i++) {
                arr[i] = table[startIndex][i-1];
            }
            return arr;
        } else if (directionIndex == 3) {
            // 3 = RL
            String[] arr = new String[col + 1];
            for (int i=1; i<=col; i++) {
                arr[i] = table[startIndex][col-i];
            }
            return arr;
        }
        return null;
    }

    public static void KMPMatch(String[] text, String[] pattern, int[] prefix, int startIndex, int directionIndex) {
        // String[] direction = {"UB", "BU", "LR", "RL"};

        int n = text.length;
        int m = pattern.length;

        int q = 0;
        for (int i=1; i<n; i++) {
            while (q > 0 && !pattern[q + 1].equals(text[i])) {
                q = prefix[q];
            }
            if (pattern[q + 1].equals(text[i])) {
                q++;
            }
//            System.out.println(q + " " + i);
            if (q == m-1) {
                int row = -1, col = -1;

                if (directionIndex == 0) {
                    row = i - (m - 1);
                    col = startIndex;
                } else if (directionIndex == 1) {
                    row = n - i + m - 3;
                    col = startIndex;
                } else if (directionIndex == 2) {
                    row = startIndex;
                    col = i - (m - 1);
                } else if (directionIndex == 3) {
                    row = startIndex;
                    col = n - i + m - 3;
                }

//                System.out.println(i + " " + n);
                System.out.printf("> %d %d %d\n", row, col, directionIndex);
                q = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // input
        String[] sigma = input.nextLine().split(" ");
        int row = input.nextInt();
        int col = input.nextInt();
        int n = input.nextInt();
        String[][] table = new String[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                table[i][j] = input.next();
            }
        }
        input.nextLine();
        String[] pattern = new String[n+1];
        for (int i = 1; i<=n; i++) {
            pattern[i] = input.next();
        }

        // get prefix table
        int[] prefix = computePrefix(pattern, n);
        for (int j : prefix) {
            System.out.print(j + " ");
        }
        System.out.print("\n");

        // KMPmatch
        // String[] direction = {"UB", "BU", "LR", "RL"};
        for (int i = 0; i<4; i++) {
            if (i == 0 || i == 1) {
                // 0 = UB, 1 = BU
                for (int j = 0; j < col; j++) {
                    String[] text = getArrFromTable(table, j, i);
                    assert text != null;
                    KMPMatch(text, pattern, prefix, j, i);
                }
            } else {
                // 2 = LR, 3 = RL
                for (int j = 0; j < row; j++) {
                    String[] text = getArrFromTable(table, j, i);
                    assert text != null;
                    KMPMatch(text, pattern, prefix, j, i);
                }
            }
        }
    }
}

/*
A B C E F
8 8 4
A B E F B B A F
A B B C B E E B
A B A B B A A C
F E B C F A B B
C E C B C B B A
A C A E C A C E
F C E E C C C F
A A A B A C B C
A B A C

A B C
4 4 4
A B A C
B A A A
A A A B
C A B A
A B A C

A B C
6 6 4
A A A A A A
A A B A C A
A B A A A A
A A A A B A
A C A B A A
A A A A A A
A B A C

A B C
4 8 4
A C A C A B A C
B A B A C A B A
A B A B A B A C
C A C A C A B A
A B A C

A B C
1 10 3
A B C A B C A B C A
A B C

A B
4 10 4
A B A B A B A B A B
B A B A B A B A B A
A B A B A B A B A B
B A B A B A B A B A
A B A B
 */
