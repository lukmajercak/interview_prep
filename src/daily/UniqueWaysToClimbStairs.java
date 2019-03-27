package daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueWaysToClimbStairs {

  /**
   * There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time.
   * Given N, write a function that returns the number of unique ways you can climb the staircase.
   * The order of the steps matters.
   *
   * For example, if N is 4, then there are 5 unique ways:
   *
   * 1, 1, 1, 1
   * 1, 1, 2
   * 1, 2, 1
   * 2, 2
   * 2, 1, 1
   * What if, instead of being able to climb 1 or 2 steps at a time,
   * you could climb any number from a set of positive integers X?
   * For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(numWays(100, Arrays.asList(new Integer[]{5, 10})));
  }

  static long numWays(int n, List<Integer> steps) {
    return numWays(n, steps, new HashMap<>());
  }

  static long numWays(int n, List<Integer> steps, Map<Integer, Long> memo) {
    if (memo.get(n) != null) {
      return memo.get(n);
    }

    long result = 0;
    for (Integer step : steps) {
      if (step == n) {
        result += 1;
      } if (step < n) {
        result += numWays(n - step, steps, memo);
      }
    }

    memo.put(n, result);
    return result;
  }
}
