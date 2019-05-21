package leetcode.google;

import java.util.*;

public class IntersectionOfTwoArrays {

  /**
   * Given two arrays, write a function to compute their intersection.
   */
  public static void main(String[] args) {
    IntersectionOfTwoArrays solution = new IntersectionOfTwoArrays();

    // Example 1:
    //
    //Input: nums1 = [1,2,2,1], nums2 = [2,2]
    //Output: [2]
    int[] nums1 = {1, 2, 2, 1};
    int[] nums2 = {2, 2};
    System.out.println(Arrays.toString(solution.intersection(nums1, nums2)));

    // Example 2:
    //
    //Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    //Output: [9,4]
    nums1 = new int[]{4, 9, 5};
    nums2 = new int[]{9, 4, 9, 8, 4};
    System.out.println(Arrays.toString(solution.intersection(nums1, nums2)));
  }

  public Integer[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> set1 = new HashSet<>();
    for (int num : nums1) {
      set1.add(num);
    }
    Set<Integer> resultSet = new HashSet<>();
    for (int num : nums2) {
      if (set1.contains(num)) {
        resultSet.add(num);
      }
    }
    Integer[] result = new Integer[resultSet.size()];
    result = resultSet.toArray(result);

    return result;
  }
}
