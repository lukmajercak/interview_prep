package leetcode.google;

public class PeakIndexInAMountainArray {

  /**
   * Let's call an array A a mountain if the following properties hold:
   *
   * A.length >= 3
   * There exists some 0 < i < A.length - 1 such that
   *   A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
   * Given an array that is definitely a mountain, return any i such that
   *   A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
   */
  public static void main(String[] args) {
    PeakIndexInAMountainArray solution =
        new PeakIndexInAMountainArray();

    // Input: [0,1,0]
    // Output: 1
    System.out.println(solution.peakIndexInMountainArray(new int[]{0, 1, 0}));

    // Input: [0,2,1,0]
    // Output: 1
    System.out.println(solution.peakIndexInMountainArray(new int[]{0, 2, 1, 0}));
  }

  public int peakIndexInMountainArray(int[] A) {
    int max = Integer.MIN_VALUE;
    int maxIndex = 0;

    for (int i = 1; i < A.length - 1; i++) {
      if (A[i] > max) {
        max = A[i];
        maxIndex = i;
      }
    }

    return maxIndex;
  }
}
