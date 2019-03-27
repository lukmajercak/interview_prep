package leetcode.google;

public class MaxConsecutiveOnes2 {

  /**
   * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
   */
  public static void main(String[] args) {
    MaxConsecutiveOnes2 solution = new MaxConsecutiveOnes2();

    // Example 1:
    //Input: [1,0,1,1,0]
    //Output: 4
    //Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    //    After flipping, the maximum number of consecutive 1s is 4.
    int[] nums = new int[]{1,0,1,1,0};
    System.out.println(solution.findMaxConsecutiveOnes(nums));

    nums = new int[]{1,1,0,1,1,1};
    System.out.println(solution.findMaxConsecutiveOnes(nums));

    nums = new int[]{1};
    System.out.println(solution.findMaxConsecutiveOnes(nums));

    nums = new int[]{1,1,1,0,1,1,0,1,1,1,1,1};
    System.out.println(solution.findMaxConsecutiveOnes(nums));
  }

  public int findMaxConsecutiveOnes(int[] nums) {
    int max = 0;
    Integer previousConsecutiveOnes = null;
    boolean previousZero = false;
    int currentConsecutiveOnes = 0;

    for (int num : nums) {
      if (num == 0) {
        if (previousZero) {
          previousConsecutiveOnes = null;
        } else {
          previousConsecutiveOnes = currentConsecutiveOnes;
        }
        currentConsecutiveOnes = 0;
      } else {
        currentConsecutiveOnes++;
      }
      previousZero = num == 0;
      if (previousConsecutiveOnes != null) {
        max = Math.max(max, currentConsecutiveOnes + previousConsecutiveOnes + 1);
      }
      max = Math.max(max, currentConsecutiveOnes);
    }
    return max;
  }
}
