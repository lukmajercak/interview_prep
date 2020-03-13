package leetcode.google;

public class MaximumSubarray {

  /**
   * Given an integer array nums, find the contiguous subarray (containing at least one number)
   * which has the largest sum and return its sum.
   */
  public static void main(String[] args) {
    MaximumSubarray solution = new MaximumSubarray();

    // Input: [-1, -2],
    // Output: -1
    int[] nums = new int[]{-1, -2};
    System.out.println(solution.maxSubArray(nums));

    // Output: 4
    nums = new int[]{-2, 1, -3, 4};
    System.out.println(solution.maxSubArray(nums));

    // Input: [-2,1,-3,4,-1,2,1,-5,4],
    // Output: 6
    // Explanation: [4,-1,2,1] has the largest sum = 6.
    nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(solution.maxSubArray(nums));

    // Input: [-2,1,-3,4,-1,2,-100,6,4],
    // Output: 10
    // Explanation: [6,4] has the largest sum = 10.
    nums = new int[]{-2, 1, -3, 4, -1, 2, -100, 6, 4};
    System.out.println(solution.maxSubArray(nums));

    // Input: [-1],
    // Output: -1
    nums = new int[]{-1};
    System.out.println(solution.maxSubArray(nums));

    // Input: [-2, -1],
    // Output: -1
    nums = new int[]{-2, -1};
    System.out.println(solution.maxSubArray(nums));

    // Input: [-2, -1, -3],
    // Output: -1
    nums = new int[]{-2, -1, -3};
    System.out.println(solution.maxSubArray(nums));
  }

  int maxSubArray(int[] nums) {
    int max_so_far = nums[0];
    int curr_max = nums[0];

    for (int i = 1; i < nums.length; i++) {
      // this max will basically start the sum over if the current
      // element is bigger than what we would get after adding
      // the element to the current max sum
      curr_max = Math.max(nums[i], curr_max + nums[i]);

      max_so_far = Math.max(max_so_far, curr_max);
    }
    return max_so_far;
  }
}
