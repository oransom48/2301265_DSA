import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        //testing
        int[] coins = {2, 6,7 ,10};
        int amount = 12;
        String coin = "";


        //Scanner input = new Scanner(System.in);

        /*int amount = input.nextInt();
        System.out.println();
        String[] stringCoins= input.nextLine().strip().split(",");
        int[] coins = new int[stringCoins.length];
        String coin = "";
        for(int i = 0; i< stringCoins.length ; i++){
            coins[i] = Integer.parseInt(stringCoins[i]);
            coin += stringCoins[i] +" ";
        }*/
        List<List<Integer>> subsets = CoinChangeMin.coinChangeMin(coins, amount);
        System.out.println("Amount = "+ amount);
        System.out.println("Coin [] = { " + coin + "}");
        System.out.println("Ways to make change = "+ subsets.size());
        System.out.println("All subsets to reach the amount: ");
        for (List<Integer> subset : subsets) {
            System.out.print("{ ");
            for(Integer x: subset){
                System.out.print(x+" ");
            }
            System.out.print("}");
            if (subsets.getLast() == subset) break;
            System.out.print(",");
        }
    }

}