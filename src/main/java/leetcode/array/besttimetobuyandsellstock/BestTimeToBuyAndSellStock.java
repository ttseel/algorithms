package leetcode.array.besttimetobuyandsellstock;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        Solution s = new Solution();

//        int[] prices = {7, 1, 5, 3, 6, 4};

        int[] prices = {7, 6, 4, 3, 1};

        System.out.println(s.maxProfit(prices));
    }
}


class Solution {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }

        return maxProfit;
    }
}
