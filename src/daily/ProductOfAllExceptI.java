package daily;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ProductOfAllExceptI {

  /**
   *
   * This problem was asked by Uber.
   *
   * Given an array of integers, return a new array such that each element at index i of the
   * new array is the product of all the numbers in the original array except the one at i.
   *
   * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24].
   *
   * If our input was [3, 2, 1], the expected output would be [2, 3, 6].
   *
   * Follow-up: what if you can't use division?
   *
   * Have a great day!
   */

  public static void main(String[] args) {
    System.out.println(solution(Arrays.asList(new Integer[]{1, 2, 3, 4, 5})));
    System.out.println(solution(Arrays.asList(new Integer[]{3, 2, 1})));

    System.out.println(solution2(Arrays.asList(new Integer[]{1, 2, 3, 4, 5})));
    System.out.println(solution2(Arrays.asList(new Integer[]{3, 2, 1})));
  }

  static List<Integer> solution(List<Integer> input) {
    List<Integer> output = new ArrayList<>(input.size());

    Integer total = 1;

    for (Integer num : input) {
      total *= num;
    }

    for (Integer num : input) {
      output.add(total / num);
    }
    return output;
  }

  static List<Integer> solution2(List<Integer> input) {
    List<Integer> output = Arrays.asList(new Integer[input.size()]);
    solution2(input, output, 0, 1);
    return output;
  }

  static Integer solution2(
      List<Integer> input, List<Integer> output, int i, Integer soFar) {
    if (i == input.size() - 1) {
      output.set(i, soFar);
      return input.get(i);
    }

    Integer after = solution2(input, output, i + 1, input.get(i) * soFar);
    output.set(i, soFar * after);

    return after * input.get(i);
  }
}
