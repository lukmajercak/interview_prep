package leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class AndroidUnlockPatterns {

  /**
   * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9,
   * count the total number of unlock patterns of the Android lock screen,
   * which consist of minimum of m keys and maximum n keys.
   *
   *
   *
   * Rules for a valid pattern:
   *
   * Each pattern must connect at least m keys and at most n keys.
   * All the keys must be distinct.
   * If the line connecting two consecutive keys in the pattern passes through any other keys,
   * the other keys must have previously selected in the pattern.
   * No jumps through non selected key is allowed.
   * The order of keys used matters.
   */
  public static void main(String[] args) {
    AndroidUnlockPatterns solution = new AndroidUnlockPatterns();

    // Input: m = 1, n = 1
    // Output: 9
    System.out.println(solution.numberOfPatterns(1, 1));

    // Input: m = 1, n = 2
    // Output: 65
    System.out.println(solution.numberOfPatterns(1, 2));

    // Input: m = 2, n = 2
    // Output: 56
    System.out.println(solution.numberOfPatterns(2, 2));

    // Input: m = 3, n = 3
    // Output: 320
    System.out.println(solution.numberOfPatterns(3, 3));

    // Input: m = 1, n = 3
    // Output: 385
    System.out.println(solution.numberOfPatterns(1, 3));
  }

  class Count {
    int count = 0;
  }


  public int numberOfPatterns(int m, int n) {
    // [[1, 2, 3],
    //  [4, 5, 6],
    //  [7, 8, 9]]

    int totalPatterns = 0;

    // 1. count middle
    boolean[][] visited = new boolean[3][3];
    Count middle = new Count();
    numberOfPatterns(m, n, 1, 1, middle, visited, 0);
    totalPatterns += middle.count;

    // 2. count corners
    visited = new boolean[3][3];
    Count corners = new Count();
    numberOfPatterns(m, n, 0, 0, corners, visited, 0);
    totalPatterns += 4 * corners.count;

    // 3. count middles of sides
    visited = new boolean[3][3];
    Count middlesOfSides = new Count();
    numberOfPatterns(m, n, 0, 1, middlesOfSides, visited, 0);
    totalPatterns += 4 * middlesOfSides.count;

    return totalPatterns;
  }

  private void numberOfPatterns(
      int m, int n, int i, int j, Count counter, boolean[][] visited, int visitedNum) {
    if (visited[i][j] || visitedNum > n) {
      return;
    }

    visitedNum += 1;

    if (visitedNum >= m && visitedNum <= n) {
      counter.count++;
    }

    if (visitedNum == n) {
      return;
    }

    visited[i][j] = true;
    List<int[]> nextPositions = getNextPositions(i, j, visited);
    for (int[] nextPosition : nextPositions) {
      numberOfPatterns(m, n, nextPosition[0], nextPosition[1], counter, visited, visitedNum);
    }
    visited[i][j] = false;
  }


  // [[1, 2, 3],
  //  [4, 5, 6],
  //  [7, 8, 9]]
  List<int[]> getNextPositions(int i, int j, boolean[][] visited) {
    List<int[]> next = new ArrayList<>();

    for (int row = Math.max(0, i - 2); row <= Math.min(2, i + 2); row++) {
      for (int col = Math.max(0, j - 2); col <= Math.min(2, j + 2); col++) {
        if (visited[row][col]) {
          continue;
        }
        int rowDiff = Math.abs(i - row);
        int colDiff = Math.abs(j - col);
        int distance = Math.max(rowDiff, colDiff);

        if (distance == 1) {
          // neighbors
          next.add(new int[]{row, col});
        } else if (distance == 2) {
          // check if visited in path

          boolean toAdd;
          if (row == i) {
            // same row
            toAdd = visited[row][1];
          } else if (col == j) {
            // same col
            toAdd = visited[1][col];
          } else if (rowDiff == colDiff) {
            // 5 should have been visited
            toAdd = visited[1][1];
          } else {
            toAdd = true;
          }

          if (toAdd) {
            next.add(new int[]{row, col});
          }
        }
      }
    }
    return next;
  }
}
