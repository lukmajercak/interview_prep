package leetcode.google;

import java.util.*;

public class CoinChange {

  /**
   * You are given coins of different denominations and a total amount of money amount.
   * Write a function to compute the fewest number of coins that you need to make up that amount.
   * If that amount of money cannot be made up by any combination of the coins, return -1.
   */
  public static void main(String[] args) {
    CoinChange solution = new CoinChange();

    // Input: coins = [1, 2, 5], amount = 11
    // Output: 3
    // Explanation: 11 = 5 + 5 + 1
    int[] coins = new int[]{1, 2, 5};
    System.out.println(solution.coinChange(coins, 11));

    // Input: coins = [2], amount = 3
    // Output: -1
    coins = new int[]{2};
    System.out.println(solution.coinChange(coins, 3));

    // 0
    coins = new int[]{1};
    System.out.println(solution.coinChange(coins, 0));

    // 1
    coins = new int[]{1};
    System.out.println(solution.coinChange(coins, 1));

    // 2
    coins = new int[]{1};
    System.out.println(solution.coinChange(coins, 2));

    // 20
    coins = new int[]{186, 419, 83, 408};
    System.out.println(solution.coinChange(coins, 6249));
  }


  public int coinChange(int[] coins, int amount) {
    int minWays = coinChange(coins, amount, new HashMap<>());
    return minWays == Integer.MAX_VALUE ? -1 : minWays;
  }

  Integer coinChange(int[] coins, int remaining, Map<Integer, Integer> memo) {
    if (memo.get(remaining) != null) {
      return memo.get(remaining);
    }
    if (remaining < 0) {
      return null;
    }
    if (remaining == 0) {
      return 0;
    }

    int minWays = Integer.MAX_VALUE;
    for (int coin : coins) {
      Integer numWays = coinChange(coins, remaining - coin, memo);
      if (numWays != null && numWays != Integer.MAX_VALUE) {
        minWays = Math.min(minWays, numWays + 1);
      }
    }
    memo.put(remaining, minWays);

    return minWays;
  }
}
