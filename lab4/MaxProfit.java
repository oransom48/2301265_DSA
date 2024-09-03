public class MaxProfit {



    public static Result findMaxProfit(double[] prices) {
        return findMaxProfit(prices, 0, prices.length - 1);
    }

    private static Result findMaxProfit(double[] prices, int left, int right) {
        if (left >= right) {
            return new Result(left, left, 0);
        }

        int mid = (left + right) / 2;
        Main.count++;

        Result leftResult = findMaxProfit(prices, left, mid);
        Result rightResult = findMaxProfit(prices, mid + 1, right);
        Result crossResult = findCrossMaxProfit(prices, left, mid, right);

        if (leftResult.profit >= rightResult.profit && leftResult.profit >= crossResult.profit) {
            return leftResult;
        } else if (rightResult.profit >= leftResult.profit && rightResult.profit >= crossResult.profit) {
            return rightResult;
        } else {
            return crossResult;
        }
    }

    private static Result findCrossMaxProfit(double[] prices, int left, int mid, int right) {
        double minPrice = Integer.MAX_VALUE;
        double maxProfit = 0;
        int buyDay = mid;
        int sellDay = mid;

        for (int i = mid; i >= left; i--) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
                buyDay = i;
            }
        }

        for (int i = mid + 1; i <= right; i++) {
            if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
                sellDay = i;
            }
        }

        return new Result(buyDay, sellDay, maxProfit);
    }}