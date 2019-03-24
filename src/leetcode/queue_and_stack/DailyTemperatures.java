package leetcode.queue_and_stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

  /**
   * Given a list of daily temperatures T, return a list such that, for each day in the input,
   * tells you how many days you would have to wait until a warmer temperature.
   * If there is no future day for which this is possible, put 0 instead.
   *
   * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
   * your output should be [1, 1, 4, 2, 1, 1, 0, 0].
   *
   * Note: The length of temperatures will be in the range [1, 30000].
   * Each temperature will be an integer in the range [30, 100].
   */
  public static void main(String[] args) {
    DailyTemperatures solution = new DailyTemperatures();

    int[] temps = new int[]{
        73, 74, 75, 71, 69, 72, 76, 73
    };
    System.out.println(Arrays.toString(solution.dailyTemperatures(temps)));
  }

  public int[] dailyTemperatures(int[] T) {
    int[] ret = new int[T.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < T.length; i++) {
      while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
        int idx = stack.pop();
        ret[idx] = i - idx;
      }
      stack.push(i);
    }
    return ret;
  }
}
