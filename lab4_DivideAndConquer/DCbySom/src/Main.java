import java.util.Scanner;

public class Main {

    private static int count = 0;

    public static int findMinIndex(float[] arr, int low, int high) {
        int minIndex = low;
        for (int i=low+1; i<=high; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static int findMaxIndex(float[] arr, int low, int high) {
        int maxIndex = low;
        for (int i=low+1; i<=high; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static Pair<Integer> maxDiff(float[] arr, int low, int high) {
        count++;

        if (low >= high) {
            return new Pair<>(high, high);
        }

        int mid = (low + high) / 2;

        // 1. get indexes of 2 elements which make the maximum difference on the left side
        Pair<Integer> leftMaxDiffIndex = maxDiff(arr, low, mid);

        // 2. get indexes of 2 elements which make the maximum difference on the right side
        Pair<Integer> rightMaxDiffIndex = maxDiff(arr, mid+1, high);

        // 3. get the minimum element's index on the left side and maximum element's index on the right side
        int minIndex = findMinIndex(arr, low, mid);
        int maxIndex = findMaxIndex(arr, mid+1, high);

        // 4. find what index's pair will give the maximum difference
        float maxDiff = Math.max( Math.max(arr[leftMaxDiffIndex.getSecond()] - arr[leftMaxDiffIndex.getFirst()],
                arr[rightMaxDiffIndex.getSecond()] - arr[rightMaxDiffIndex.getFirst()]),
                arr[maxIndex] - arr[minIndex]);

        if (maxDiff == arr[leftMaxDiffIndex.getSecond()] - arr[leftMaxDiffIndex.getFirst()]) {
            return leftMaxDiffIndex;
        } else if (maxDiff == arr[rightMaxDiffIndex.getSecond()] - arr[rightMaxDiffIndex.getFirst()]) {
            return rightMaxDiffIndex;
        }
        return new Pair<>(minIndex, maxIndex);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        float[] rate = new float[n];
        for (int i=0; i<n; i++) {
            rate[i] = input.nextFloat();
        }

        Pair<Integer> maxDiffIndex = maxDiff(rate, 0, n-1);

        System.out.println(maxDiffIndex.getFirst() + 1);
        System.out.printf("%.2f\n", rate[maxDiffIndex.getFirst()]);

        System.out.println(maxDiffIndex.getSecond() + 1);
        System.out.printf("%.2f\n", rate[maxDiffIndex.getSecond()]);

        System.out.printf("%.2f\n", rate[maxDiffIndex.getSecond()] - rate[maxDiffIndex.getFirst()]);
        System.out.println(maxDiffIndex.getSecond() - maxDiffIndex.getFirst());

        System.out.println("count = " + count);

    }
}

/* test case example
20
35.10 35.01 35.11 35.02 35.08 35.03 35.09 35.12 35.04 35.17 35.14 35.19 35.13 35.07 35.16 35.20 35.06 35.05 35.18 35.16
*/

/* test case 1
20
43.12 44.49 43.01 41.54 40.32 40.12 37.91 32.40 32.99 34.33 31.72 30.48 31.07 30.72 32.48 34.25 35.29 33.93 32.32 31.18

20
40.24 39.85 40.52 47.03 50.21 49.92 47.61 44.35 48.45 47.81 42.10 42.43 39.94 40.84 43.17 38.01 39.05 38.30 38.15 38.30

20
67.99 64.06 64.48 67.92 73.94 73.01 69.85 64.80 61.00 53.67 49.01 48.89 49.24 48.09 53.51 52.36 47.85 43.69 43.11 39.70

20
55.00 40.00 32.00 31.00 30.00 29.00 16.00 15.00 14.00 12.00 11.00 10.00 9.00 9.00 9.00 8.00 6.00 4.00 2.00 1.00

80
43.12 44.49 43.01 41.54 40.32 40.12 37.91 32.40 32.99 34.33 31.72 30.48 31.07 30.72 32.48 34.25 35.29 33.93 32.32 31.18 40.24 39.85 40.52 47.03 50.21 49.92 47.61 44.35 48.45 47.81 42.10 42.43 39.94 40.84 43.17 38.01 39.05 38.30 38.15 38.30 67.99 64.06 64.48 67.92 73.94 73.01 69.85 64.80 61.00 53.67 49.01 48.89 49.24 48.09 53.51 52.36 47.85 43.69 43.11 39.70 55.00 40.00 32.00 31.00 30.00 29.00 16.00 15.00 14.00 12.00 11.00 10.00 9.00 9.00 9.00 8.00 6.00 4.00 2.00 1.00

33
30.48 31.07 30.72 32.48 34.25 35.29 33.93 32.32 31.18 40.24 39.85 40.52 47.03 50.21 49.92 47.61 44.35 48.45 47.81 42.10 42.43 39.94 40.84 43.17 38.01 39.05 38.30 38.15 38.30 67.99 64.06 64.48 67.92 73.94

34
31.72 30.48 31.07 30.72 32.48 34.25 35.29 33.93 32.32 31.18 40.24 39.85 40.52 47.03 50.21 49.92 47.61 44.35 48.45 47.81 42.10 42.43 39.94 40.84 43.17 38.01 39.05 38.30 38.15 38.30 67.99 64.06 64.48 67.92 73.94
*/

/* our test case
10
10.00 9.09 9.08 9.07 9.06 9.05 9.04 9.03 9.02 9.01

10
9.01 9.02 9.03 9.04 9.05 9.06 9.07 9.08 9.09 10.00

4
20.00 60.00 12.00 30.00

7
20.00 10.00 58.00 52.00 15.00 80.00 40.00

22
31.07 30.72 30.48 32.48 34.25 35.29 33.93 32.32 31.18 40.24 40.84 43.17 38.01 39.05 38.30 38.15 38.30 67.99 64.06 64.48 73.94 67.92
*/
