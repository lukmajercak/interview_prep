package daily;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * This problem was recently asked by Google.
 *
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 *
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 *
 * Bonus: Can you do this in one pass?
 */

public class AddUpToK {

  public static void main(String[] args) {
    List<Integer> numbers = new ArrayList();
    numbers.add(10);
    numbers.add(15);
    numbers.add(3);
    numbers.add(7);
    System.out.println(doAddUp(numbers, 17));
    System.out.println(doAddUp(numbers, 25));
    System.out.println(doAddUp(numbers, 10));
    System.out.println(doAddUp(numbers, 22));
    System.out.println(doAddUp(numbers, 16));
    System.out.println(doAddUp(numbers, 7));
    System.out.println(doAddUp(numbers, 28));

    numbers = new ArrayList();
    numbers.add(-3);
    numbers.add(5);
    numbers.add(0);
    System.out.println(doAddUp(numbers, 5));
    System.out.println(doAddUp(numbers, 2));
    System.out.println(doAddUp(numbers, -3));

    numbers = new ArrayList();
    numbers.add(-5);
    numbers.add(-6);
    numbers.add(-3);
    System.out.println(doAddUp(numbers, 11));
    System.out.println(doAddUp(numbers, -11));
    System.out.println(doAddUp(numbers, -8));
  }

  static boolean doAddUp(List<Integer> numbers, Integer k) {
    Set<Integer> seen = new HashSet<>();

    for (Integer number : numbers) {
      if (seen.contains(k - number)) {
        return true;
      }
      seen.add(number);
    }
    return false;
  }

  /**
   * O(1) space O(n * log n) solution:
   * Sort the list and then do a binary search for each element.
   *
   * [3, 7, 10, 15]    k = 17
   *
   * When looking at 3, do a binary search in [7, 10, 15] looking for 14.
   */
}
