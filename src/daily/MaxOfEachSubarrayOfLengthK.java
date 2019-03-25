package daily;

import java.util.LinkedList;
import java.util.Queue;

public class MaxOfEachSubarrayOfLengthK {

  /**
   * This problem was asked by Google.
   *
   * Given an array of integers and a number k, where 1 <= k <= length of the array, compute the maximum values of each subarray of length k.
   *
   * For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8], since:
   *
   * 10 = max(10, 5, 2)
   * 7 = max(5, 2, 7)
   * 8 = max(2, 7, 8)
   * 8 = max(7, 8, 7)
   * Do this in O(n) time and O(k) space. You can modify the input array in-place and you do not need to store the results.
   * You can simply print them out as you compute them.
   */
  public static void main(String[] args) {
    MaxOfEachSubarrayOfLengthK solution = new MaxOfEachSubarrayOfLengthK();

    int[] numbers = new int[]{10, 5, 2, 7, 8, 7};
    solution.printMaximums(numbers, 3);

    numbers = new int[]{10, 9, 2, 7, 8, 7};
    solution.printMaximums(numbers, 3);
  }

  void printMaximums(int[] numbers, int k) {
    Queue<Integer> maxIndices = new LinkedList<>();
    int i = 0;
    for (i = 0; i < k; i++) {
      while (!maxIndices.isEmpty() && numbers[i] > numbers[maxIndices.peek()]) {
        maxIndices.remove();
      }
      maxIndices.offer(i);
    }
    for (; i < numbers.length; i++) {
      System.out.println(numbers[maxIndices.peek()]);
      while (!maxIndices.isEmpty() && maxIndices.peek() <= i - k) {
        maxIndices.remove();
      }
      while (!maxIndices.isEmpty() && numbers[i] > numbers[maxIndices.peek()]) {
        maxIndices.remove();
      }
      maxIndices.offer(i);
    }
    System.out.println(numbers[maxIndices.peek()]);
  }
}
