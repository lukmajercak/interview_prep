package leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class MaximumProductSubarray {

  /**
   * Given an integer array nums, find the contiguous subarray within an array
   * (containing at least one number) which has the largest product.
   */
  public static void main(String[] args) {
    MaximumProductSubarray solution = new MaximumProductSubarray();

    // Input: [2,3,-2,4]
    // Output: 6
    // Explanation: [2,3] has the largest product 6.
    int[] nums = new int[]{2, 3, -2, 4};
    System.out.println(solution.maxProduct(nums));

    // Input: [-2,0,-1]
    // Output: 0
    // Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
    nums = new int[]{-2, 0, -1};
    System.out.println(solution.maxProduct(nums));
  }

  public int maxProduct(int[] nums) {
    int currentMaxProduct = nums[0];
    int prevMaxProduct = nums[0];
    int prevMinProduct = nums[0];
    int ret = nums[0];

    for (int i = 1; i < nums.length; i++) {
      int num = nums[i];

      currentMaxProduct = Math.max(prevMaxProduct * num,
          prevMinProduct * num);
      currentMaxProduct = Math.max(currentMaxProduct, num);

      int currentMinProduct = Math.min(prevMaxProduct * num,
          prevMinProduct * num);
      currentMinProduct = Math.min(currentMinProduct, num);

      prevMaxProduct = currentMaxProduct;
      prevMinProduct = currentMinProduct;

      ret = Math.max(ret, currentMaxProduct);
    }

    return ret;
  }
}
