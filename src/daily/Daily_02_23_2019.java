package daily;

import java.util.Arrays;

public class Daily_02_23_2019 {

  /**
   * Given an array of integers, find the first missing positive integer in linear time and constant space.
   * In other words, find the lowest positive integer that does not exist in the array.
   * The array can contain duplicates and negative numbers as well.
   *
   * For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
   * [1, 4, -1, 3]
   * [-1, 4, 1, 3]
   *
   * [3, 1, 5, 5, 6, 2] -> 4
   *
   * [1, 3] - 2
   * [-1, 2] - 1
   * [0, 0] - 1
   *
   * [4, 1, 2] - 3
   * [0, 1, 3] - 2
   * [4, 2, -2] - 1
   *
   *   [-2,      [4, 2, 5, 5, 3, 1, 7] - 6
   * You can modify the input array in-place.
   */
  public static void main(String[] args) {
    System.out.println(findLowest(new int[]{3, 4, -1, 1})); // should be 2
    System.out.println(findLowest(new int[]{1, 2, 0})); // 3
    System.out.println(findLowest(new int[]{3, 1, 5, 5, 6, 2})); // 4 ??
    System.out.println(findLowest(new int[]{1, 3})); // 2
    System.out.println(findLowest(new int[]{-1, 2})); // 1
    System.out.println(findLowest(new int[]{0, 0})); // 1
    System.out.println(findLowest(new int[]{4, 1, 2})); // 3
    System.out.println(findLowest(new int[]{0, 1, 3})); // 2
    System.out.println(findLowest(new int[]{4, 2, -2})); // 1
  }

  static int findLowest(int[] array) {
    // 1. find numbers that are <= 0 and put them at the beginning
    int numNonPositive = 0;
    for (int i = 0; i < array.length; i++) {
      int number = array[i];
      if (number <= 0) {
        int tmp = array[numNonPositive];
        array[numNonPositive++] = -1;
        array[i] = tmp;
      }
    }

    if (numNonPositive == array.length) {
      return 1;
    }

    // [3, 4, -1, 1]
    // became [-1, 4, 3, 1]

    // 2. start at numNonPositive
    // for each number:
    //    put it at position [number - 1 + numPositive]
    //         if that position doesnt exist or already has the number,
    //         set the current position to -1 and go to the next index
    int curIndex = numNonPositive;
    while (curIndex < array.length) {
      int number = array[curIndex];

      int expectedIndex = number - 1 + numNonPositive;

      if (number == -1 || expectedIndex == curIndex) {
        curIndex++;
        continue;
      }

      if (expectedIndex >= array.length || array[expectedIndex] == number) {
        array[curIndex] = -1;
        curIndex++;
      } else {
        // swap the numbers and go to the next index
        int tmp = array[expectedIndex];
        array[expectedIndex] = number;
        array[curIndex] = tmp;
      }
    }

    // after this, [-1, 4, 3, 1] becomes [-1, 1, -1, 3]

    // 3. start at numPositive + 1 , looking for -1 or the end of the array
    int index = numNonPositive;
    for (; index < array.length; index++) {
      if (array[index] == -1) {
        return index + 1 - numNonPositive;
      }
    }
    return index;
  }


}
