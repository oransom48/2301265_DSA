import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String pattern = input.next();
        char[] arr = pattern.toCharArray();
        int k = input.nextInt();
        int n = arr.length;

        int maxPassenger = 0;
        int numOfSol = 0;
        int leftCount;
        int rightCount;
        int allCount;
        for (int i=0; i<n; i++) {
            if (arr[i] == 'G') {

                // right count
                rightCount = 0;
                for (int j=i-1; j>=i-k && j>=0; j--) {
                    if (arr[j] == 'P') {
                        rightCount++;
                    }
                }

                // left count
                leftCount = 0;
                for (int j=i+1; j<=i+k && j<n; j++) {
                    if (arr[j] == 'P') {
                        leftCount++;
                    }
                }

                // save max result
                allCount = leftCount + rightCount;
                if (allCount > maxPassenger) {
                    maxPassenger = allCount;
                    // reset number of solutions
                    numOfSol = 1;
                } else if (allCount == maxPassenger) {
                    numOfSol++;
                }
            }
        }

        System.out.println("Number of solutions: " + numOfSol);
        System.out.println("Maximum number of passenger: " + maxPassenger);
    }
}

/*
GPPGP
1

PPGGPG
2

GPGPPG
3

GPGPGPGPGPGPGPGPGPGP
2

GGPPGGGGPPPG
3

GGPPGPPPGGPP
3
 */
