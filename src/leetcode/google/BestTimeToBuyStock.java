package leetcode.google;

public class BestTimeToBuyStock {

  /**
   * Say you have an array for which the ith element is the price of a given stock on day i.
   *
   * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
   * design an algorithm to find the maximum profit.
   *
   * Note that you cannot sell a stock before you buy one.
   */
  public static void main(String[] args) {
    BestTimeToBuyStock solution = new BestTimeToBuyStock();

    // Input: [7,1,5,3,6,4]
    // Output: 5
    // Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    //             Not 7-1 = 6, as selling price needs to be larger than buying price.
    int[] prices = new int[]{7,1,5,3,6,4};
    System.out.println(solution.maxProfit(prices));

    // Input: [7,6,4,3,1]
    // Output: 0
    // Explanation: In this case, no transaction is done, i.e. max profit = 0.
    prices = new int[]{7,6,4,3,1};
    System.out.println(solution.maxProfit(prices));
  }

  public int maxProfit(int[] prices) {
    Integer minSoFar = null;
    Integer maxProfitSoFar = 0;

    for (int price : prices) {
      if (minSoFar != null) {
        maxProfitSoFar = Math.max(maxProfitSoFar, price - minSoFar);
      }

      minSoFar = minSoFar == null ? price : Math.min(minSoFar, price);
    }

    return maxProfitSoFar;
  }
}
