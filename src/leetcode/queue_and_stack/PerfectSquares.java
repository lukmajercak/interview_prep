package leetcode.queue_and_stack;

import java.util.LinkedList;
import java.util.Queue;

public class PerfectSquares {

  /**
   * Given a positive integer n, find the least number of perfect square numbers
   * (for example, 1, 4, 9, 16, ...) which sum to n.
   *
   * Example 1:
   *
   * Input: n = 12
   * Output: 3
   * Explanation: 12 = 4 + 4 + 4.
   * Example 2:
   *
   * Input: n = 13
   * Output: 2
   * Explanation: 13 = 4 + 9.
   */
  public static void main(String[] args) {
    PerfectSquares perfectSqures = new PerfectSquares();
    System.out.println(perfectSqures.numSquares(12));
    System.out.println(perfectSqures.numSquares(13));
  }

  public int numSquares(int n) {
    Queue<Integer> remainders = new LinkedList<>();
    remainders.add(n);

    int numSteps = 0;

    while (!remainders.isEmpty()) {
      int size = remainders.size();
      numSteps++;
      for (int i = 0; i < size; i++) {
        Integer remainder = remainders.poll();
        if (isPerfectSquare(remainder)) {
          return numSteps;
        }

        int nextPerfectSquareBase = 1;
        int nextPerfectSquare = 1;
        while (nextPerfectSquare < remainder) {
          remainders.offer(remainder - nextPerfectSquare);
          nextPerfectSquareBase++;
          nextPerfectSquare = (int) Math.pow(nextPerfectSquareBase, 2);
        }
      }
    }
    return -1;
  }

  boolean isPerfectSquare(int n) {
    double sq = Math.sqrt(n);
    return (sq - Math.floor(sq)) == 0;
  }
}
