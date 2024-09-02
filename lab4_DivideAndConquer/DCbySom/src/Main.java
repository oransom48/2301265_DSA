import java.util.Scanner;

public class Main {

    private static int countMin = 0;
    private static int countMax = 0;

    public static int minElementIndex(float[] arr, int low, int high) {
        countMin++;

        if (low == high) {
            return low;
        }

        int mid = (low + high) / 2;
        int leftMinIndex = minElementIndex(arr, low, mid);
        int rightMinIndex = minElementIndex(arr, mid + 1, high);
        float minEle = Math.min(arr[leftMinIndex], arr[rightMinIndex]);

        if (minEle == arr[leftMinIndex]) {
            return leftMinIndex;
        }
        return rightMinIndex;
    }

    public static int maxElementIndex(float[] arr, int low, int high) {
        countMax++;

        if (low == high) {
            return low;
        }

        int mid = (low + high) / 2;
        int leftMaxIndex = maxElementIndex(arr, low, mid);
        int rightMaxIndex = maxElementIndex(arr, mid + 1, high);
        float maxEle = Math.max(arr[leftMaxIndex], arr[rightMaxIndex]);

        if (maxEle == arr[leftMaxIndex]) {
            return leftMaxIndex;
        }
        return rightMaxIndex;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        float[] rate = new float[n];
        for (int i=0; i<n; i++) {
            rate[i] = input.nextFloat();
        }

        int minEleIndex = minElementIndex(rate, 0, n-1);
        System.out.println(minEleIndex + 1);
        System.out.printf("%.2f\n", rate[minEleIndex]);

        int maxEleIndex = maxElementIndex(rate, minEleIndex, n-1);
        System.out.println(maxEleIndex + 1);
        System.out.printf("%.2f\n", rate[maxEleIndex]);

        System.out.printf("%.2f\n", rate[maxEleIndex] - rate[minEleIndex]);
        System.out.println(maxEleIndex - minEleIndex);

        System.out.println("find min count: " + countMin);
        System.out.println("find max count: " + countMax);

    }
}

/* test case example
20
35.10 35.01 35.11 35.02 35.08 35.03 35.09 35.12 35.04 35.17 35.14 35.19 35.13 35.07 35.16 35.20 35.06 35.05 35.18 35.16
*/

/* test case 1
20
43.12 44.49 43.01 41.54 40.32 40.12 37.91 32.40 32.99 34.33 31.72 30.48 31.07 30.72 32.48 34.25 35.29 33.93 32.32 31.18
*/
