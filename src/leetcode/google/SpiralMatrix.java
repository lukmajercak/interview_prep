package leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

  /**
   * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
   */
  public static void main(String[] args) {
    SpiralMatrix solution = new SpiralMatrix();

    // Example 1:
    //
    //Input:
    //[
    // [ 1, 2, 3 ],
    // [ 4, 5, 6 ],
    // [ 7, 8, 9 ]
    //]
    //Output: [1,2,3,6,9,8,7,4,5]
    int[][] matrix = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    System.out.println(solution.spiralOrder(matrix));

    // Example 2:
    //
    //Input:
    //[
    //  [1, 2, 3, 4],
    //  [5, 6, 7, 8],
    //  [9,10,11,12]
    //]
    //Output: [1,2,3,4,8,12,11,10,9,5,6,7]
    matrix = new int[][]{
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9,10,11,12}
    };
    System.out.println(solution.spiralOrder(matrix));

    // Example 3:
    //
    //Input:
    //[
    //  [1, 2, 3],
    //  [4, 5, 6],
    //  [7, 8, 9],
    //  [10,11,12],
    //  [13,14,15]
    //]
    //Output: [1, 2, 3, 6, 9, 12, 15, 14, 13, 10, 7, 4, 5, 8, 11]
    matrix = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9},
        {10,11,12},
        {13,14,15},
    };
    System.out.println(solution.spiralOrder(matrix));

    // Example 3:
    //
    //Input:
    //[
    //  [1, 2, 3],
    //  [4, 5, 6],
    //  [7, 8, 9],
    //  [10,11,12],
    //  [13,14,15]
    //]
    //Output: [1, 2, 3, 6, 9, 12, 15, 14, 13, 10, 7, 4, 5, 8, 11]
    matrix = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9},
        {10,11,12},
        {13,14,15},
    };
    System.out.println(solution.spiralOrder(matrix));

    // Example 4:
    //
    //Input:
    //[
    //  [1, 2, 3, 4, 5],
    //  [6, 7, 8, 9,10],
    //  [11,12,13,14,15],
    //  [16,17,18,19,20],
    //  [21,22,23,24,25],
    //]
    //Output: [1, 2, 3, 4, 5, 10, 15, 20, 25, 24, 23, 22, 21, 16, 11, 6, 7, 8, 9, 14, 19, 18, 17, 12, 13]
    matrix = new int[][]{
        {1, 2, 3, 4, 5},
        {6, 7, 8, 9,10},
        {11,12,13,14,15},
        {16,17,18,19,20},
        {21,22,23,24,25},
    };
    System.out.println(solution.spiralOrder(matrix));
  }

  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> output = new ArrayList<>();
    int numRows = matrix.length;
    if (numRows == 0) {
      return output;
    }
    int numCols = matrix[0].length;

    int startRow = 0;
    int startColumn = 0;
    int endRow = numRows - 1;
    int endColumn = numCols - 1;

    while (startRow <= endRow && startColumn <= endColumn) {
      drawCircle(startRow, endRow, startColumn, endColumn, matrix, output);
      startRow++;
      endRow--;
      startColumn++;
      endColumn--;
    }
    return output;
  }

  void drawCircle(
      int startRow, int endRow, int startColumn,
      int endColumn, int[][] matrix, List<Integer> output) {
    if (startRow == endRow) {
      drawHorizontal(startRow, startColumn, endColumn, matrix, output);
    } else if (startColumn == endColumn) {
      drawVertical(startColumn, startRow, endRow, matrix, output);
    } else {
      drawHorizontal(startRow, startColumn, endColumn, matrix, output);
      drawVertical(endColumn, startRow + 1, endRow - 1, matrix, output);
      drawHorizontalReverse(endRow, endColumn, startColumn, matrix, output);
      drawVerticalReverse(startColumn, endRow - 1, startRow + 1, matrix, output);
    }
  }

  void drawHorizontal(int row, int startColumn, int endColumn, int[][] matrix, List<Integer> output) {
    while (startColumn <= endColumn) {
      output.add(matrix[row][startColumn]);
      startColumn++;
    }
  }

  void drawHorizontalReverse(int row, int endColumn, int startColumn, int[][] matrix, List<Integer> output) {
    while (endColumn >= startColumn) {
      output.add(matrix[row][endColumn]);
      endColumn--;
    }
  }

  void drawVertical(int col, int startRow, int endRow, int[][] matrix, List<Integer> output) {
    while (startRow <= endRow) {
      output.add(matrix[startRow][col]);
      startRow++;
    }
  }

  void drawVerticalReverse(int col, int endRow, int startRow, int[][] matrix, List<Integer> output) {
    while (endRow >= startRow) {
      output.add(matrix[endRow][col]);
      endRow--;
    }
  }
}
