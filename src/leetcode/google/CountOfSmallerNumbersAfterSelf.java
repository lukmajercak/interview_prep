package leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CountOfSmallerNumbersAfterSelf {

  /**
   * You are given an integer array nums and you have to return a new counts array.
   * The counts array has the property where counts[i] is the number of smaller elements
   * to the right of nums[i].
   */
  public static void main(String[] args) {
    CountOfSmallerNumbersAfterSelf solution =
        new CountOfSmallerNumbersAfterSelf();

    // Input: [5,2,6,1]
    // Output: [2,1,1,0]
    // Explanation:
    // To the right of 5 there are 2 smaller elements (2 and 1).
    // To the right of 2 there is only 1 smaller element (1).
    // To the right of 6 there is 1 smaller element (1).
    // To the right of 1 there is 0 smaller element.
    int[] input = new int[]{5, 2, 6, 1};
    System.out.println(solution.countSmaller(input));

    // Input: [2,1,0]
    // Output: [2,1,0]
    // Explanation:
    // To the right of 5 there are 2 smaller elements (2 and 1).
    // To the right of 2 there is only 1 smaller element (1).
    // To the right of 6 there is 1 smaller element (1).
    // To the right of 1 there is 0 smaller element.
    input = new int[]{2, 1, 0};
    System.out.println(solution.countSmaller(input));

    // Input: [26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41]
    // Output: [10,27,10,35,12,22,28,8,19,2,12,2,9,6,12,5,17,9,19,12,14,6,12,5,12,3,0,10,0,7,8,4,0,0,4,3,2,0,1,0]

    //         [10,27,10,35,12,22,28,8,19,2,12,2,9,6,12,5,17,9,19,12,14,6,12,5,12,3,0,10,0,7,8,4,0,0,4,3,2,0,1,0]
    // Explanation:
    // To the right of 5 there are 2 smaller elements (2 and 1).
    // To the right of 2 there is only 1 smaller element (1).
    // To the right of 6 there is 1 smaller element (1).
    // To the right of 1 there is 0 smaller element.
    input = new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
    System.out.println(solution.countSmaller(input));

    input = new int[]{10, 6, 6, 6};
    System.out.println(solution.countSmaller(input));

    Random r = new Random();
    AVLTree.AVLNode tree = null;
    while (true) {
      tree = AVLTree.add(tree, r.nextInt(10000), new AtomicInteger());
    }
  }

  /**
   *
   *                                        66
   *                   21                                       97
   *        9                   41                     78                  99
   *    3     13         32           65            76    84           98    100
   *  2               28    36       51           69    81   94
   *
   *
   *
   * 51, 32, 28, 36
   *
   */

  public List<Integer> countSmaller(int[] nums) {
    List<Integer> counts = new ArrayList<>(nums.length);
    for (int n : nums) {
      counts.add(0);
    }

    AVLTree.AVLNode tree = null;
    for (int i = nums.length - 1; i >= 0; i--) {
      AtomicInteger numSmaller = new AtomicInteger();
      tree = AVLTree.add(tree, nums[i], numSmaller);
      counts.set(i, numSmaller.get());
    }

    return counts;
  }
}
