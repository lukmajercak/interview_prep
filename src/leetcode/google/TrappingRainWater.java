package leetcode.google;

public class TrappingRainWater {
  /**
   * Given n non-negative integers representing an elevation map where the width of each bar is 1,
   * compute how much water it is able to trap after raining.
   */
  public static void main(String[] args) {
    TrappingRainWater solution = new TrappingRainWater();

    // Example:
    //
    //Input: [0,1,0,2,1,0,1,3,2,1,2,1]
    //Output: 6
    int[] heights = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
    System.out.println(solution.trap(heights));
  }

  /**
   * create array for left max and right max
   *
   *     [0,1,0,2,1,0,1,3,2,1,2,1]
   *     leftmax =
   *     [0,0,1,1,2,2,2,2,3,3,3,3]
   *     rightmax =
   *     [3,3,3,3,3,3,3,2,2,2,1,1]
   *
   *     res = Min(rightmax, leftmax) - current
   *     [0,0,1,0,1,2,1,0,0,1,0,0]
   * @param heights
   */
  public int trap(int[] heights) {
    int[] leftMax = new int[heights.length];
    Integer max = 0;
    for (int i = 0; i < heights.length; i++) {
      leftMax[i] = max;
      max = Math.max(max, heights[i]);
    }

    int[] rightMax = new int[heights.length];
    max = 0;
    for (int i = heights.length - 1; i >= 0; i--) {
      rightMax[i] = max;
      max = Math.max(max, heights[i]);
    }

    int trapped = 0;
    for (int i = 0; i < heights.length; i++) {
      trapped += Math.max(Math.min(leftMax[i], rightMax[i]) - heights[i], 0);
    }
    return trapped;
  }
}
