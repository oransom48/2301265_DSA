//import java.util.Scanner;
//
//public class MinMax {
//    static class Result {
//        float min;
//        float max;
//        int minIndex;
//        int maxIndex;
//    }
//
//    public static Result findMinMax(float[] arr, int low, int high) {
//        Result result = new Result();
//        Result leftResult;
//        Result rightResult;
//
//        // If there is only one element
//        if (low == high) {
//            result.min = arr[low];
//            result.max = arr[low];
//            result.minIndex = low;
//            result.maxIndex = low;
//            return result;
//        }
//
//        // If there are two elements
//        if (high == low + 1) {
//            if (arr[low] > arr[high]) {
//                result.min = arr[high];
//                result.max = arr[low];
//                result.minIndex = high;
//                result.maxIndex = low;
//            } else {
//                result.min = arr[low];
//                result.max = arr[high];
//                result.minIndex = low;
//                result.maxIndex = high;
//            }
//            return result;
//        }
//
//        // If there are more than two elements
//        int mid = (low + high) / 2;
//        leftResult = findMinMax(arr, low, mid);
//        rightResult = findMinMax(arr, mid + 1, high);
//
//        // Compare results of two subarrays for min value
//        if (leftResult.min < rightResult.min) {
//            result.min = leftResult.min;
//            result.minIndex = leftResult.minIndex;
//        } else {
//            result.min = rightResult.min;
//            result.minIndex = rightResult.minIndex;
//        }
//
//        // Compare results of two subarrays for max value
//        if (leftResult.max > rightResult.max) {
//            result.max = leftResult.max;
//            result.maxIndex = leftResult.maxIndex;
//        } else {
//            result.max = rightResult.max;
//            result.maxIndex = rightResult.maxIndex;
//        }
//
//        return result;
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // Input array size
//        System.out.print("Enter the size of the array: ");
//        int n = scanner.nextInt();
//        scanner.nextLine();  // Consume the newline character
//
//        // Ensure the user inputs the correct number of elements
//        System.out.println("Enter " + n + " elements of the array separated by spaces:");
//        String input = scanner.nextLine();
//        String[] elements = input.split(" ");
//
//        if (elements.length != n) {
//            System.out.println("Error: The number of elements provided does not match the specified array size.");
//            scanner.close();
//            return;
//        }
//
//        float[] arr = new float[n];
//
//        try {
//            // Convert the input to an array of floats
//            for (int i = 0; i < n; i++) {
//                arr[i] = Float.parseFloat(elements[i]);
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("Error: Please ensure all inputs are valid float numbers.");
//            scanner.close();
//            return;
//        }
//
//        // Find and display min and max
//        Result result = findMinMax(arr, 0, n - 1);
//        int indexDifference = Math.abs(result.maxIndex - result.minIndex);
//        float valueDifference = result.max - result.min;
//
//        System.out.println("Minimum value: " + result.min + " at index " + result.minIndex);
//        System.out.println("Maximum value: " + result.max + " at index " + result.maxIndex);
//        System.out.println("Difference in indices: " + indexDifference);
//        System.out.println("Difference in values: " + valueDifference);
//
//        scanner.close();
//    }
//}

import java.util.Scanner;

public class MinMax {
    static class Result {
        float min;
        float max;
        int minIndex;
        int maxIndex;
    }

    public static Result findMinMax(float[] arr, int low, int high) {
        Result result = new Result();
        Result leftResult;
        Result rightResult;

        // If there is only one element
        if (low == high) {
            result.min = arr[low];
            result.max = arr[low];
            result.minIndex = low + 1;  // Adjusting for 1-based index
            result.maxIndex = low + 1;  // Adjusting for 1-based index
            return result;
        }

        // If there are two elements
        if (high == low + 1) {
            if (arr[low] > arr[high]) {
                result.min = arr[high];
                result.max = arr[low];
                result.minIndex = high + 1;  // Adjusting for 1-based index
                result.maxIndex = low + 1;   // Adjusting for 1-based index
            } else {
                result.min = arr[low];
                result.max = arr[high];
                result.minIndex = low + 1;   // Adjusting for 1-based index
                result.maxIndex = high + 1;  // Adjusting for 1-based index
            }
            return result;
        }

        // If there are more than two elements
        int mid = (low + high) / 2;
        leftResult = findMinMax(arr, low, mid);
        rightResult = findMinMax(arr, mid + 1, high);

        // Compare results of two subarrays for min value
        if (leftResult.min < rightResult.min) {
            result.min = leftResult.min;
            result.minIndex = leftResult.minIndex;
        } else {
            result.min = rightResult.min;
            result.minIndex = rightResult.minIndex;
        }

        // Compare results of two subarrays for max value
        if (leftResult.max > rightResult.max) {
            result.max = leftResult.max;
            result.maxIndex = leftResult.maxIndex;
        } else {
            result.max = rightResult.max;
            result.maxIndex = rightResult.maxIndex;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input array size
        System.out.print("จำนวนวัน: ");
        int n = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Ensure the user inputs the correct number of elements
        System.out.println("อัตราแลกเปลี่ยนจากวันที่ 1 ถึงวันที่ " + n + " โดยคั่นด้วยวรรค: ");
        String input = scanner.nextLine();
        String[] elements = input.split(" ");

        if (elements.length != n) {
            System.out.println("Error: The number of elements provided does not match the specified array size.");
            scanner.close();
            return;
        }

        float[] arr = new float[n];

        try {
            // Convert the input to an array of floats
            for (int i = 0; i < n; i++) {
                arr[i] = Float.parseFloat(elements[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please ensure all inputs are valid float numbers.");
            scanner.close();
            return;
        }

        // Find and display min and max
        Result result = findMinMax(arr, 0, n - 1);
        int indexDifference = Math.abs(result.maxIndex - result.minIndex);
        float valueDifference = result.max - result.min;

        System.out.println("วันที่ควรซื้อ: " + result.minIndex);
        System.out.println("อัตราแลกเปลี่ยนในวันที่ซื้อ: " + result.min);
        System.out.println("วันที่ควรขาย: " + result.maxIndex);
        System.out.println("อัตราแลกเปลี่ยนในวันที่ขาย : " + result.max);
        System.out.println("กำไร: " + valueDifference);
        System.out.println("จำนวนวันที่ถือเงินสกุล USD: " + indexDifference);
    }
}

//code from GPT

//test case
//20
//43.12 44.49 43.01 41.54 40.32 40.12 37.91 32.40 32.99 34.33 31.72 30.48 31.07 30.72 32.48 34.25 35.29 33.93 32.32 31.18