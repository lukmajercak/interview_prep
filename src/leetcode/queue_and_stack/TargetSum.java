package leetcode.queue_and_stack;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {

  /**
   * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
   * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
   *
   * Find out how many ways to assign symbols to make sum of integers equal to target S.
   */
  public static void main(String[] args) {
    TargetSum solution = new TargetSum();

    //Example 1:
    //Input: nums is [1, 1, 1, 1, 1], S is 3.
    //Output: 5
    //Explanation:
    //
    //-1+1+1+1+1 = 3
    //+1-1+1+1+1 = 3
    //+1+1-1+1+1 = 3
    //+1+1+1-1+1 = 3
    //+1+1+1+1-1 = 3
    //
    //There are 5 ways to assign symbols to make the sum of nums be target 3.
    int[] nums = new int[]{1, 1, 1, 1, 1};
    System.out.println(solution.findTargetSumWays(nums, 3));
    System.out.println(solution.findTargetSumWays2(nums, 3));

    nums = new int[]{1, 1};
    System.out.println(solution.findTargetSumWays(nums, 2));
    System.out.println(solution.findTargetSumWays2(nums, 2));
    System.out.println(solution.findTargetSumWays(nums, 5));
    System.out.println(solution.findTargetSumWays2(nums, 5));
  }

  public int findTargetSumWays(int[] nums, int S) {
    return findTargetSumWays(nums, S, 0, 0, new HashMap<>());
  }

  public int findTargetSumWays(
      int[] nums, int S, int i, int sumSoFar, Map<Memo, Integer> memo) {
    Memo key = new Memo(i, sumSoFar);
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    if (i == nums.length) {
      return S == sumSoFar ? 1 : 0;
    }
    int ret = findTargetSumWays(nums, S, i + 1, sumSoFar + nums[i], memo) +
        findTargetSumWays(nums, S, i + 1, sumSoFar - nums[i], memo);
    memo.put(key, ret);
    return ret;
  }

  public int findTargetSumWays2(int[] nums, int S) {
    Count c = new Count();
    findTargetSumWays2(nums, S, 0, 0, c);
    return c.count;
  }

  public void findTargetSumWays2(
      int[] nums, int S, int i, int sumSoFar, Count count) {
    if (i == nums.length) {
      if (sumSoFar == S) {
        count.count++;
      }
      return;
    }
    int num = nums[i];
    findTargetSumWays2(nums, S, i + 1, sumSoFar + num, count);
    findTargetSumWays2(nums, S, i + 1, sumSoFar - num, count);
  }

  static class Count {
    int count = 0;
  }

  class Memo {
    int i;
    int sumSoFar;

    Memo(int i, int sumSoFar) {
      this.i = i;
      this.sumSoFar = sumSoFar;
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Memo)) {
        return false;
      }
      Memo that = (Memo) o;
      return this.i == that.i && this.sumSoFar == that.sumSoFar;
    }

    @Override
    public int hashCode() {
      return new String(Integer.toString(this.i) + "," +
          Integer.toString(this.sumSoFar)).hashCode();
    }
  }
}
