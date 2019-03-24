package daily;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 There's a staircase with N steps, and you can climb 1 or 2 steps at a time.
 Given N, write a function that returns the number of unique ways you can climb the staircase.
 The order of the steps matters.

 For example, if N is 4, then there are 5 unique ways:

 1, 1, 1, 1
 2, 1, 1
 1, 2, 1
 1, 1, 2
 2, 2
 What if, instead of being able to climb 1 or 2 steps at a time,
 you could climb any number from a set of positive integers STEPS?

 For example, if STEPS = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.
 Generalize your function to take in STEPS.
 */
public class sample {

  public static void main(String[] args) {
    Set<Integer> steps = new HashSet<>();
    steps.add(1);
    steps.add(2);
    /*System.out.println(numWays(100, steps));
    System.out.println(numWays(4, steps));
    System.out.println(numWays(2, steps));*/

    // STEPS = 1, 3, 5
    steps = new HashSet<>();
    steps.add(1);
    steps.add(3);
    steps.add(5);
    System.out.println(numWays(10, steps));
  }

  private static Long numWays(long n, Set<Integer> steps, Map<Long, Long> memo) {
    Long result = memo.get(n);
    if (result != null) {
      return result;
    }
    if (n == 0) {
      result = 1L;
    } else {
      result = 0L;
      for (Integer step : steps) {
        if (n >= step) {
          result += numWays(n - step, steps, memo);
        }
      }
    }
    memo.put(n, result);
    return result;
  }


  static long numWays(int n, Set<Integer> steps) {
    return numWays(n, steps, new HashMap<>());
  }
}
