package leetcode.google;

import java.util.Arrays;

public class RangeOfPositionsOfElementInSortedArray {

  /**
   * Given an array of integers nums sorted in ascending order, find the
   * starting and ending position of a given target value.
   *
   * Your algorithm's runtime complexity must be in the order of O(log n).
   *
   * If the target is not found in the array, return [-1, -1].
   *

   *
   * Input: nums = [5,7,7,8,8,10], target = 6
   * Output: [-1,-1]
   */
  public static void main(String[] args) {
    RangeOfPositionsOfElementInSortedArray solution =
        new RangeOfPositionsOfElementInSortedArray();

    // Input: nums = [5,7,7,8,8,10], target = 8
    // Output: [3,4]
    int[] nums = new int[]{5,7,7,8,8,10};
    System.out.println(Arrays.toString(solution.searchRange(nums, 8)));

    // Input: nums = [5,7,7,8,8,10], target = 6
    // Output: [-1,-1]
    nums = new int[]{5,7,7,8,8,10};
    System.out.println(Arrays.toString(solution.searchRange(nums, 6)));

    // Input: nums = [5,7,7,8,8,10], target = 10
    // Output: [5, 5]
    nums = new int[]{5,7,7,8,8,10};
    System.out.println(Arrays.toString(solution.searchRange(nums, 10)));

    // Input: nums = [5,7,7,8,8,10], target = 5
    // Output: [0, 0]
    nums = new int[]{5,7,7,8,8,10};
    System.out.println(Arrays.toString(solution.searchRange(nums, 5)));

    // Input: nums = [5,7,7,7,7,10], target = 7
    // Output: [1, 4]
    nums = new int[]{5,7,7,7,7,10};
    System.out.println(Arrays.toString(solution.searchRange(nums, 7)));

    // Input: nums = [5,7,7,7,7,7,10], target = 7
    // Output: [1, 5]
    nums = new int[]{5,7,7,7,7,7,10};
    System.out.println(Arrays.toString(solution.searchRange(nums, 7)));

    // Input: nums = [7,7,7,7,7], target = 7
    // Output: [0, 4]
    nums = new int[]{7,7,7,7,7};
    System.out.println(Arrays.toString(solution.searchRange(nums, 7)));

    // Input: nums = [7,7,7,7], target = 7
    // Output: [0, 3]
    nums = new int[]{7,7,7,7};
    System.out.println(Arrays.toString(solution.searchRange(nums, 7)));

    // Input: nums = [7,7], target = 7
    // Output: [0, 1]
    nums = new int[]{7,7};
    System.out.println(Arrays.toString(solution.searchRange(nums, 7)));

    // Input: nums = [7], target = 7
    // Output: [0, 0]
    nums = new int[]{7};
    System.out.println(Arrays.toString(solution.searchRange(nums, 7)));

    // Input: nums = [], target = 7
    // Output: [-1, -1]
    nums = new int[]{};
    System.out.println(Arrays.toString(solution.searchRange(nums, 7)));
  }

  public int[] searchRange(int[] nums, int target) {
    int[] result = new int[]{-1, -1};
    bfs(nums, target, 0, nums.length - 1, result);
    return result;
  }

  void bfs(int[] nums, int target, int startIndex, int endIndex, int[] startEndPos) {
   if (startIndex > endIndex) {
     return;
   }
   int guessIndex = (endIndex + startIndex) / 2;
   int guess = nums[guessIndex];

   if (guess == target) {
     // found an index of target
     if (startEndPos[0] == -1) {
       startEndPos[0] = guessIndex;
     }
     startEndPos[0] = Math.min(startEndPos[0], guessIndex);
     startEndPos[1] = Math.max(startEndPos[1], guessIndex);

     // recurse on both sides
     bfs(nums, target, startIndex, guessIndex - 1, startEndPos);
     bfs(nums, target, guessIndex + 1, endIndex, startEndPos);
   } else if (guess > target) {
     // Guess bigger than target, go left
     bfs(nums, target, startIndex, guessIndex - 1, startEndPos);
   } else {
     // Guess bigger than target, go right
     bfs(nums, target, guessIndex + 1, endIndex, startEndPos);
   }
  }
}
