
public class Main {
    static int count =0;
    public static void main(String[] args) {
        double[] prices = { 20, 60, 12, 30,35.10, 35.01, 35.11, 35.02, 35.08, 35.03, 35.09, 35.12, 35.04};

        Result result = MaxProfit.findMaxProfit(prices);

        System.out.println("Buy on day: " + result.buyDay);
        System.out.println("Sell on day: " + result.sellDay);
        System.out.println("Maximum profit: " + result.profit);
        System.out.println("this is count" + count );
    }

}
