import java.util.Scanner;

public class Sol2 {
    public static void main(String[] args) {
        System.out.println("Sol2.java is running...");
        Scanner input = new Scanner(System.in);

        String pattern = input.next();
        char[] arr = pattern.toCharArray();
        int k = input.nextInt();
        int n = arr.length;

        int maxPassenger = 0;

        // build prefix sum prefixSum
        int[] prefixSum = new int[n];
        int tmp = 0;
        for (int i=0; i<n; i++) {
            if (arr[i] == 'P') {
                tmp++;
            }
            prefixSum[i] = tmp;
        }

        // check if there's any Grab car exist
        boolean hasGrabCar = false;
        for (char c : arr) {
            if (c == 'G') {
                hasGrabCar = true;
                break;
            }
        }

        if (!hasGrabCar) {
            System.out.println("There is no Grab car!");

        } else if (n <= k) {
            System.out.println("Maximum number of passenger: " + prefixSum[n-1]);

        } else {
            // phase 1: 0 - k
            for (int i = 0; i <= k; i++) {
                int tmpidx = Math.min(i + k, n - 1);
                if (arr[i] == 'G' && prefixSum[tmpidx] > maxPassenger) {
                    maxPassenger = prefixSum[tmpidx];
                }
            }
            // phase 2: k+1 - n-k-1
            for (int i = k + 1; i < n - k; i++) {
                int diff = prefixSum[i + k] - prefixSum[i - k - 1];
                if (arr[i] == 'G' && diff > maxPassenger) {
                    maxPassenger = diff;
                }
            }
            // phase 3: n-k - n-1
            for (int i = n - k; i < n; i++) {
                int tmpidx = Math.max(0, i - k - 1);
                int diff = prefixSum[n - 1] - prefixSum[tmpidx];
                if (arr[i] == 'G' && diff > maxPassenger) {
                    maxPassenger = diff;
                }
            }

            System.out.println("Maximum number of passenger: " + maxPassenger);
        }
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

G
4
 */
