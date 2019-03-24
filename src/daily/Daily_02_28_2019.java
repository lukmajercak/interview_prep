package daily;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Daily_02_28_2019 {

  /**
   * This problem was asked by Airbnb.
   *
   * Given a list of integers, write a function that returns the largest sum of non-adjacent numbers.
   * Numbers can be 0 or negative.
   *
   * For example:
   * [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5.
   * [5, 1, 1, 5] should return 10, since we pick 5 and 5.
   *
   * Follow-up: Can you do this in O(N) time and constant space?
   */
  public static void main(String[] args) {
    int[] numbers = new int[]{2, 4, 6, 2, 5};
    System.out.println(sum(numbers));
    System.out.println(sumBottomUp(numbers));

    numbers = new int[]{5, 1, 1, 5};
    System.out.println(sum(numbers));
    System.out.println(sumBottomUp(numbers));

    numbers = initializeRandom(5000);

    long start = System.currentTimeMillis();
    System.out.println(sum(numbers) +
        " recursive took " + (System.currentTimeMillis() - start) + "ms");
    start = System.currentTimeMillis();
    System.out.println(sumBottomUp(numbers) +
        " bottom up took " + (System.currentTimeMillis() - start) + "ms");

    numbers = initializeRandom(500000);
    start = System.currentTimeMillis();
    System.out.println(sumBottomUp(numbers) +
        " bottom up took " + (System.currentTimeMillis() - start) + "ms");
  }

  static int sum(int[] numbers) {
    return sum(numbers, 0, new HashMap<>());
  }

  static int sum(int[] numbers, int i, Map<Integer, Integer> memo) {
    if (memo.get(i) != null) {
      return memo.get(i);
    }

    int currentValue = numbers[i];
    if (i == numbers.length - 1) {
      memo.put(i, currentValue);
      return numbers[i];
    }

    Integer sumNextElem = Integer.MIN_VALUE;
    Integer sumSkipElem = Integer.MIN_VALUE;

    if (i + 1 < numbers.length) {
      sumNextElem = sum(numbers, i + 1, memo);
    }
    if (i + 2 < numbers.length) {
      sumSkipElem = sum(numbers, i + 2, memo);
    }

    int sumMax = Math.max(sumNextElem, currentValue + sumSkipElem);
    memo.put(i, sumMax);

    return sumMax;
  }

  static int sumBottomUp(int[] numbers) {
    for (int i = numbers.length - 2; i >= 0; i--) {
      Integer sumNextElem = Integer.MIN_VALUE;
      Integer sumSkipElem = Integer.MIN_VALUE;

      if (i + 1 < numbers.length) {
        sumNextElem = numbers[i + 1];
      }
      if (i + 2 < numbers.length) {
        sumSkipElem = numbers[i + 2];
      }

      numbers[i] = Math.max(sumNextElem, numbers[i] + sumSkipElem);
    }
    return numbers[0];
  }

  static int[] initializeRandom(int numElems) {
    Random r = new Random();
    int[] numbers = new int[numElems];
    for (int i = 0; i < numElems; i++) {
      numbers[i] = r.nextInt();
    }
    return numbers;
  }
}
