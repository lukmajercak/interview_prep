package leetcode.google;

public class MaxConsecutiveOnes {

  /**
   * Given a binary array, find the maximum number of consecutive 1s in this array.
   */
  public static void main(String[] args) {
    MaxConsecutiveOnes solution = new MaxConsecutiveOnes();

    // Example 1:
    //Input: [1,1,0,1,1,1]
    //Output: 3
    //Explanation: The first two digits or the last three digits are consecutive 1s.
    //    The maximum number of consecutive 1s is 3.
    int[] nums = new int[]{1,1,0,1,1,1};
    System.out.println(solution.findMaxConsecutiveOnes(nums));
  }

  public int findMaxConsecutiveOnes(int[] nums) {
    int max = 0;
    int soFar = 0;

    for (int num : nums) {
      soFar = soFar * num + num;
      max = Math.max(max, soFar);
    }

    return max;
  }
}
