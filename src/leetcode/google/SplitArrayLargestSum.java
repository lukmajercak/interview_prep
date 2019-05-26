package leetcode.google;

public class SplitArrayLargestSum {

  /**
   * Given an array which consists of non-negative integers and an integer m,
   * you can split the array into m non-empty continuous subarrays.
   * Write an algorithm to minimize the largest sum among these m subarrays.
   *
   * Note:
   * If n is the length of array, assume the following constraints are satisfied:
   *    1 ≤ n ≤ 1000
   *    1 ≤ m ≤ min(50, n)
   */
  public static void main(String[] args) {
    SplitArrayLargestSum solution = new SplitArrayLargestSum();

    // Input:
    // nums = [7,2,5,10,8]
    // m = 2
    //
    // Output:
    // 18
    //
    //Explanation:
    // There are four ways to split nums into two subarrays.
    // The best way is to split it into [7,2,5] and [10,8],
    // where the largest sum among the two subarrays is only 18.
    int[] nums = new int[]{7,2,5,10,8};
    System.out.println(solution.splitArray(nums, 2));

    nums = new int[]{5,1,3,5,10,7,4,9,2,8};
    System.out.println(solution.splitArray(nums, 1));
  }


  /**
   * The problem can be solved by using binary search, which is a quite brilliant way.
   * If m equals length of the array, the largest sum should be the maximum among the elements.
   * If m equals 1, then it should be the sum of all elements in the array.
   * Now the maximum sum of a subarray should be between these two numbers.
   *
   * The idea is to using binary search and find this minimum maximum sum.
   * We set left to the maximum element of the array and right to the sum of the array.
   * First we choose the mid of these two and find if there exist m subarrays that have
   * largest sum less than or equal to mid. If we can find such split, we know we probably
   * can do better. So we set right to mid. We keep on doing this until we find a value that
   * we cannot get by splitting the array to m subarrays, i.e., the number is too small that
   * we need to split the array further more. Now we increase left to mid + 1.
   *
   * When left = right, we find the number.
   */
  public int splitArray(int[] nums, int m) {
    int ret = 0;

    int max = Integer.MIN_VALUE;
    int maxSum = 0;

    for (int num : nums) {
      max = Math.max(max, num);
      maxSum += num;
    }

    if (m == nums.length) {
      return max;
    }

    if (m == 1) {
      return maxSum;
    }

    // binary search
    while (max < maxSum) {
      int targetSum = (maxSum + max) / 2;

      if (canSplit(nums, m, targetSum)) {
        ret = targetSum;

        // move left
        maxSum = targetSum;
      } else {
        // move right
        max = targetSum + 1;
      }
    }

    return ret;
  }

  boolean canSplit(int[] nums, int m, int targetSum) {
    int divisions = 1;
    int currentSum = 0;

    for (int num : nums) {
      currentSum += num;

      if (currentSum > targetSum) {
        currentSum = num;
        divisions++;

        if (divisions > m) {
          return false;
        }
      }
    }
    return true;
  }
}
