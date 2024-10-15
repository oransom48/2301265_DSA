import java.util.ArrayList;
import java.util.Scanner;

public class GrabCarGreedy {
    public static int findMaxPassengersGreedy(char[] arr, int k) {
        int maxPassengers = 0;

        // List of all passenger positions
        ArrayList<Integer> passengerPositions = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'P') {
                passengerPositions.add(i);
            }
        }

        // Loop through all positions to find Grab cars
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'G') {
                int count = 0;
                // Check the passenger positions to count how many can be picked up
                for (int j = 0; j < passengerPositions.size(); j++) {
                    int passengerPos = passengerPositions.get(j);
                    if (Math.abs(passengerPos - i) <= k) {
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
        int maxPassengers = findMaxPassengersGreedy(arr, k);

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