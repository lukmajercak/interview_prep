package leetcode.google;

import java.util.Arrays;

public class NextPermutation {

  /**
   * Implement next permutation, which rearranges numbers into the
   * lexicographically next greater permutation of numbers.
   *
   * If such arrangement is not possible, it must rearrange it as the
   * lowest possible order (ie, sorted in ascending order).
   *
   * The replacement must be in-place and use only constant extra memory.
   */
  public static void main(String[] args) {
    NextPermutation solution = new NextPermutation();

    // 1,2,3 → 1,3,2
    int[] nums = new int[]{1, 2, 3};
    solution.nextPermutation(nums);
    System.out.println(Arrays.toString(nums));

    //3,2,1 → 1,2,3
    nums = new int[]{3, 2, 1};
    solution.nextPermutation(nums);
    System.out.println(Arrays.toString(nums));

    //1,1,5 → 1,5,1
    nums = new int[]{1, 1, 5};
    solution.nextPermutation(nums);
    System.out.println(Arrays.toString(nums));
  }

  // 1) scan from right to left, find the first element that is less than its previous one.
  //    8 4 5 6 3 2 1
  //        |
  //        p
  // 2) scan from right to left, find the first element that is greater than p.
  //    8 4 5 6 3 2 1
  //          |
  //          q
  // 3) swap p and q
  //    8 4 5 6 3 2 1
  //        swap
  //    8 4 6 5 3 2 1
  // 4) reverse elements [p+1, nums.length]
  //    8 4 6 1 2 3 5
  public void nextPermutation(int[] nums) {
    //find first decreasing digit
    int mark = -1;
    for (int i = nums.length - 1; i > 0; i--) {
      if (nums[i] > nums[i - 1]) {
        mark = i - 1;
        break;
      }
    }

    if (mark == -1) {
      reverse(nums, 0, nums.length - 1);
      return;
    }

    int idx = nums.length - 1;
    for (int i = nums.length - 1; i >= mark + 1; i--) {
      if (nums[i] > nums[mark]) {
        idx = i;
        break;
      }
    }

    swap(nums, mark, idx);

    reverse(nums, mark + 1, nums.length - 1);
  }

  private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }

  private void reverse(int[] nums, int i, int j) {
    while (i < j) {
      swap(nums, i, j);
      i++;
      j--;
    }
  }
}
