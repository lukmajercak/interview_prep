package leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

  /**
   * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
   * Find all unique triplets in the array which gives the sum of zero.
   *
   * Note:
   * The solution set must not contain duplicate triplets.
   */
  public static void main(String[] args) {
    ThreeSum solution = new ThreeSum();

    // Given array nums = [-1, 0, 1, 2, -1, -4],
    //
    // A solution set is:
    // [
    //   [-1, 0, 1],
    //   [-1, -1, 2]
    // ]
    System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));


    // output: [[-2,0,2]]
    System.out.println(solution.threeSum(new int[]{-2, 0, 0, 2, 2}));
  }

  // -4, -1, -1, 0, 1, 2
  //
  // Algorithm:
  //    1. Sort numbers
  //    2. Iterate over numbers, for each number n, check whether the
  //        rest sums to it. To do that, keep two pointers (start and end)
  //        and increment start/decrement end depending whether how far
  //        are we to n
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);

    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      int j = i + 1;
      int k = nums.length - 1;

      // Check for duplicates
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      while (j < k) {
        // Check for duplicates
        if (k < nums.length - 1 && nums[k] == nums[k + 1]) {
          k--;
          continue;
        }

        int sum = nums[i] + nums[j] + nums[k];
        if (sum < 0) {
          j++;
        } else if (sum > 0) {
          k--;
        } else {
          // Found triplet
          List<Integer> triplet = new ArrayList<>(3);
          triplet.add(nums[i]);
          triplet.add(nums[j]);
          triplet.add(nums[k]);

          result.add(triplet);
          j++;
          k--;
        }
      }
    }

    return result;
  }
}
