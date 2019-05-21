package leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class MedianOfTwoSortedArrays {

  /**
   * There are two sorted arrays nums1 and nums2 of size m and n respectively.
   *
   * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
   *
   * You may assume nums1 and nums2 cannot be both empty.
   */
  public static void main(String[] args) {
    MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();

    // Median is 4.0
    int[] nums1 = new int[]{1, 2, 3, 4, 7};
    int[] nums2 = new int[]{0, 5, 6, 9};
    System.out.println("4.0 == " + solution.findMedianSortedArrays(nums1, nums2));

    // Example 1:
    //
    //nums1 = [1, 3]
    //nums2 = [2]
    //
    //The median is 2.0
    nums1 = new int[]{1, 3};
    nums2 = new int[]{2};
    System.out.println("2.0 == " + solution.findMedianSortedArrays(nums1, nums2));


    //Example 2:
    //
    //nums1 = [1, 2]
    //nums2 = [3, 4]
    //
    //The median is (2 + 3)/2 = 2.5
    nums1 = new int[]{1, 2};
    nums2 = new int[]{3, 4};
    System.out.println("2.5 == " + solution.findMedianSortedArrays(nums1, nums2));


    // [1, 2, 4, 5]
    // [3]
    //The median is 3.0
    nums1 = new int[]{1, 2, 4, 5};
    nums2 = new int[]{3};
    System.out.println("3.0 == " + solution.findMedianSortedArrays(nums1, nums2));

    //nums1 = [1, 9]
    //nums2 = [3, 4]
    //
    //The median is (3 + 4)/2 = 3.5
    nums1 = new int[]{1, 9};
    nums2 = new int[]{3, 4};
    System.out.println("3.5 == " + solution.findMedianSortedArrays(nums1, nums2));

    //nums1 = [1, 9]
    //nums2 = [3, 4]
    //
    //The median is (3 + 4)/2 = 3.5
    nums1 = new int[]{3, 4};
    nums2 = new int[]{1, 9};
    System.out.println("3.5 == " + solution.findMedianSortedArrays(nums1, nums2));

    //nums1 = [1, 3]
    //nums2 = [2, 4]
    //
    //The median is 2.5
    nums1 = new int[]{1, 3};
    nums2 = new int[]{2, 4};
    System.out.println("2.5 == " + solution.findMedianSortedArrays(nums1, nums2));

    //nums1 = [1, 3, 5]
    //nums2 = []
    //
    //The median is 3
    nums1 = new int[]{1, 3, 5};
    nums2 = new int[]{};
    System.out.println("3.0 == " + solution.findMedianSortedArrays(nums1, nums2));

    //nums1 = []
    //nums2 = [1, 3, 5, 9]
    //
    //The median is 4
    nums1 = new int[]{};
    nums2 = new int[]{1, 3, 5, 9};
    System.out.println("4.0 == " + solution.findMedianSortedArrays(nums1, nums2));

    //nums1 = [1, 2]
    //nums2 = [1, 2, 3]
    //
    //The median is 2.0
    nums1 = new int[]{1, 2};
    nums2 = new int[]{1, 2, 3};
    System.out.println("2.0 == " + solution.findMedianSortedArrays(nums1, nums2));

    //nums1 = [1, 2]
    //nums2 = [1, 2]
    //
    //The median is 1.5
    nums1 = new int[]{1, 2};
    nums2 = new int[]{1, 2};
    System.out.println("1.5 == " + solution.findMedianSortedArrays(nums1, nums2));

    //nums1 = [1]
    //nums2 = [2, 3, 4]
    //
    //The median is 2.5
    nums1 = new int[]{1};
    nums2 = new int[]{2, 3, 4};
    System.out.println("2.5 == " + solution.findMedianSortedArrays(nums1, nums2));
  }


  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // 1. take as a guess middle element of nums1
    // for this guess G to be the overall median, it has to:
    //           a) be bigger than          (nums1.length + nums2.length) / 2  - nums1.length/2    elements from nums2
    //           b) be smaller than the same index + 1

    // if a) is false, move guess to the right in nums1
    // if b) is false, move guess to the left in nums1

    // repeat for nums2 as a guess if needed

    List<Double> medians = new ArrayList<>();
    binarySearch(0, nums1.length - 1, nums1, nums2, medians);

    if (medians.size() < 2) {
      binarySearch(0, nums2.length - 1, nums2, nums1, medians);
    }

    Double median = medians.get(0);
    if (medians.size() == 2) {
      median = (median + medians.get(1)) / 2;
    }
    return median;
  }


  // binary search
  void binarySearch(
      int startIndex, int endIndex, int[] guessArray, int[] otherArray,
      List<Double> medians) {
    if (startIndex > endIndex) {
      return;
    }

    int guessIndex = (startIndex + endIndex) / 2;
    int guess = guessArray[guessIndex];


    // 1. Check whether the guess is bigger than element it should from otherArray
    int toBeBiggerThanOtherIndex = ((guessArray.length + otherArray.length) / 2)
        - guessIndex - 1;

    boolean isEven = (guessArray.length + otherArray.length) % 2 == 0;
    if (isEven) {
      toBeBiggerThanOtherIndex--;
    }

    boolean isBiggerThanElementsItShould = false;
    if (toBeBiggerThanOtherIndex < 0) {
      isBiggerThanElementsItShould = true;
    } else {
      isBiggerThanElementsItShould = toBeBiggerThanOtherIndex < otherArray.length &&
          guess >= otherArray[toBeBiggerThanOtherIndex];
    }

    if (!isBiggerThanElementsItShould) {
      // bfs to the right
      binarySearch(guessIndex + 1, endIndex, guessArray, otherArray, medians);
      return;
    }


    // 2. Check whether the guess is smaller than element it should from otherArray
    int toBeSmallerThanOtherIndex = toBeBiggerThanOtherIndex + 1;
    if (isEven) {
      toBeSmallerThanOtherIndex++;
    }

    boolean isSmallerThanElementsItShould = false;
    if (toBeSmallerThanOtherIndex >= otherArray.length) {
      isSmallerThanElementsItShould = true;
    } else {
      isSmallerThanElementsItShould = toBeSmallerThanOtherIndex >= 0 &&
          guess <= otherArray[toBeSmallerThanOtherIndex];
    }

    if (!isSmallerThanElementsItShould) {
      // bfs to the left
      binarySearch(startIndex, guessIndex - 1, guessArray, otherArray, medians);
      return;
    }

    medians.add(Double.valueOf(guess));
    if (medians.size() == 2) {
      return;
    }
    if (isEven) {
      // bfs to the right
      binarySearch(guessIndex + 1, endIndex, guessArray, otherArray, medians);
      // bfs to the left
      binarySearch(startIndex, guessIndex - 1, guessArray, otherArray, medians);
    }
  }
}
