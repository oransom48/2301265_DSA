import java.util.Scanner;

public class GrabCarBruteForce {
    public static int findMaxPassengersBruteForce(char[] arr, int k) {
        int maxPassengers = 0;

        // Loop through all positions to find Grab cars
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'G') {
                int count = 0;
                // Check within the range of k units to the left and right
                for (int j = Math.max(0, i - k); j <= Math.min(arr.length - 1, i + k); j++) {
                    if (arr[j] == 'P') {
                        count++;
                    }
                }
                // Update maxPassengers if the current count is greater
                maxPassengers = Math.max(maxPassengers, count);
            }
        }

        return maxPassengers;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("arr[] = ");
        String input = scanner.next();
        System.out.print("k = ");
        int k = scanner.nextInt();

        char[] arr = input.toCharArray();
        int maxPassengers = findMaxPassengersBruteForce(arr, k);

        System.out.println(maxPassengers + ".");
        scanner.close();
    }
}

//tc1
//GPGPGPGPGPGPGPGPGPGP
//2
// out 2

//tc2
//GGPPGGGGPPPG
//3
// out 3

//tc3
//GGPPGPPPGGPP
//3
// out 5