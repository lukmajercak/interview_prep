package leetcode.google;

public class FirstMissingPositive {

  /**
   * Given an unsorted integer array, find the smallest missing positive integer.
   *
   * Note:
   * Your algorithm should run in O(n) time and uses constant extra space.
   */
  public static void main(String[] args) {
    FirstMissingPositive solution = new FirstMissingPositive();

    // Example 1:
    //
    //Input: [1,2,0]
    //Output: 3
    int[] nums = new int[]{1, 2, 0};
    System.out.println(solution.firstMissingPositive(nums));

    // Example 2:
    //
    //Input: [3,4,-1,1]
    //Output: 2
    nums = new int[]{3, 4, -1, 1};
    System.out.println(solution.firstMissingPositive(nums));

    // Example 3:
    //
    //Input: [7,8,9,11,12]
    //Output: 1
    nums = new int[]{7, 8, 9, 11, 12};
    System.out.println(solution.firstMissingPositive(nums));

    // 2
    nums = new int[]{1};
    System.out.println(solution.firstMissingPositive(nums));

    // 3
    nums = new int[]{1, 2};
    System.out.println(solution.firstMissingPositive(nums));
  }

  public int firstMissingPositive(int[] nums) {
    int n = nums.length;
    // put them into their correct index

    for (int i = 0; i < n; i++) {
      int expected = i + 1;
      while (nums[i] != expected) {
        if (nums[i] <= 0 || nums[i] >= nums.length) {
          break;
        }
        if (nums[i] == nums[nums[i] - 1]) {
          break;
        }
        int tmp = nums[i];
        nums[i] = nums[tmp - 1];
        nums[tmp - 1] = tmp;
      }
    }

    for (int i = 0; i < n; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    return n + 1;
  }
}
