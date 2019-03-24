package udemy_11_essential;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class minesweeper {

  public static void main(String[] args) {
    // NOTE: The following input values will be used for testing your solution.
    int[][] bombs1 = {{0, 2}, {2, 0}};
    // ) should return:
    System.out.println(Arrays.deepToString(mineSweeper(bombs1, 3, 3)));
    // [[0, 1, -1],
    //  [1, 2, 1],
    //  [-1, 1, 0]]

    int[][] bombs2 = {{0, 0}, {0, 1}, {1, 2}};
    //  should return:
    // [[-1, -1, 2, 1],
    //  [2, 3, -1, 1],
    //  [0, 1, 1, 1]]
    System.out.println(Arrays.deepToString(mineSweeper(bombs2, 3, 4)));

    int[][] bombs3 = {{1, 1}, {1, 2}, {2, 2}, {4, 3}};
    // mineSweeper(bombs3, 5, 5) should return:
    // [[1, 2, 2, 1, 0],
    //  [1, -1, -1, 2, 0],
    //  [1, 3, -1, 2, 0],
    //  [0, 1, 2, 2, 1],
    //  [0, 0, 1, -1, 1]]
    System.out.println(Arrays.deepToString(mineSweeper(bombs3, 5, 5)));


    /**
     * Expanding
     */
    // NOTE: The following input values will be used for testing your solution.
    int[][] field1 = {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 1, 0},
        {0, 1, -1, 1, 0}};
    // click(field1, 3, 5, 2, 2) should return:
    // [[0, 0, 0, 0, 0],
    //  [0, 1, 1, 1, 0],
    //  [0, 1, -1, 1, 0]]
    System.out.println("click tests");
    System.out.println(Arrays.deepToString(click(field1, 3, 5, 2, 2)));
    System.out.println(Arrays.deepToString(clickBFS(field1, 3, 5, 2, 2)));

    // click(field1, 3, 5, 1, 4) should return:
    // [[-2, -2, -2, -2, -2],
    //  [-2, 1, 1, 1, -2],
    //  [-2, 1, -1, 1, -2]]
    System.out.println(Arrays.deepToString(click(field1, 3, 5, 1, 4)));
    System.out.println(Arrays.deepToString(clickBFS(field1, 3, 5, 1, 4)));


    int[][] field2 = {
        {-1, 1, 0, 0},
        {1, 1, 0, 0},
        {0, 0, 1, 1},
        {0, 0, 1, -1}};

    // click(field2, 4, 4, 0, 1) should return:
    // [[-1, 1, 0, 0],
    //  [1, 1, 0, 0],
    //  [0, 0, 1, 1],
    //  [0, 0, 1, -1]]
    System.out.println(Arrays.deepToString(click(field2, 4, 4, 0, 1)));
    System.out.println(Arrays.deepToString(clickBFS(field2, 4, 4, 0, 1)));

    // click(field2, 4, 4, 1, 3) should return:
    // [[-1, 1, -2, -2],
    //  [1, 1, -2, -2],
    //  [-2, -2, 1, 1],
    //  [-2, -2, 1, -1]]
    System.out.println(Arrays.deepToString(click(field2, 4, 4, 1, 3)));
    System.out.println(Arrays.deepToString(clickBFS(field2, 4, 4, 1, 3)));
  }

  public static int[][] click(int[][] field, int numRows, int numCols, int givenI, int givenJ) {
    if (!isInField(field, givenI, givenJ)) {
      return field;
    }

    int value = field[givenI][givenJ];
    if (value > 0 || value == -1 || value == -2) {
      return field;
    }

    field[givenI][givenJ] = -2;

    for (int row = givenI - 1; row <= givenI + 1; row++) {
      for (int col = givenJ - 1; col <= givenJ + 1; col++) {
        field = click(field, numRows, numCols, row, col);
      }
    }

    return field;
  }

  public static int[][] clickBFS(int[][] field, int numRows, int numCols, int givenI, int givenJ) {
    Queue<Position> toCheck = new LinkedList<>();

    toCheck.offer(new Position(givenI, givenJ));

    while(!toCheck.isEmpty()) {
      Position position = toCheck.poll();

      if (field[position.row][position.col] != 0) {
        continue;
      }
      field[position.row][position.col] = -2;

      for (int row = givenI - 1; row <= givenI + 1; row++) {
        for (int col = givenJ - 1; col <= givenJ + 1; col++) {
          if (isInField(field, row, col)) {
            toCheck.offer(new Position(row, col));
          }
        }
      }
    }

    return field;
  }

  static class Position {
    int row;
    int col;

    Position(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  // Implement your solution below.
  public static int[][] mineSweeper(int[][] bombs, int numRows, int numCols) {
    int[][] field = new int[numRows][numCols];

    for (int i = 0; i < bombs.length; i++) {
      int[] bomb = bombs[i];

      int row = bomb[0];
      int col = bomb[1];

      field[row][col] = -1;

      for (int row_i = row - 1; row_i <= row + 1; row_i++) {
        for (int col_i = col - 1; col_i <= col + 1; col_i++) {
          if (!isBomb(field, numRows, numCols, row_i, col_i) &&
              isInField(field, row_i, col_i)) {
            field[row_i][col_i] += 1;
          }
        }
      }
    }

    return field;
  }

  static boolean isInField(int[][] field, int row, int col) {
    return row >= 0 && row < field.length && col >= 0 && col < field[0].length;
  }

  static boolean isBomb(int[][] field, int numRows, int numCols, int row, int col) {
    if (!isInField(field, row, col)) {
      return false;
    }
    return field[row][col] == -1;
  }
}
