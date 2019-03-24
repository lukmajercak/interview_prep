package leetcode.google;

import java.util.*;

public class GameOfLife {

  /**
   * According to the Wikipedia's article: "The Game of Life, also known simply as Life,
   * is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
   *
   * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
   * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
   *
   * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
   * Any live cell with two or three live neighbors lives on to the next generation.
   * Any live cell with more than three live neighbors dies, as if by over-population..
   * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
   * Write a function to compute the next state (after one update) of the board given its current state.
   * he next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
   *
   * Follow up:
   *
   * Could you solve it in-place? Remember that the board needs to be updated at the same time:
   *  You cannot update some cells first and then use their updated values to update other cells.
   * In this question, we represent the board using a 2D array. In principle, the board is infinite,
   *  which would cause problems when the active area encroaches the border of the array. How would you address these problems?
   */
  public static void main(String[] args) {
    GameOfLife solution = new GameOfLife();

    // Example:
    //
    //Input:
    //[
    //  [0,1,0],
    //  [0,0,1],
    //  [1,1,1],
    //  [0,0,0]
    //]
    //Output:
    //[
    //  [0,0,0],
    //  [1,0,1],
    //  [0,1,1],
    //  [0,1,0]
    //]
    int[][] board = new int[][] {
        {0, 1, 0},
        {0, 0, 1},
        {1, 1, 1},
        {0, 0, 0}
    };
    solution.gameOfLife(board);
    System.out.println(Arrays.deepToString(board));
  }

  public void gameOfLife(int[][] board) {
    if (board.length == 0 || board[0].length == 0) {
      return;
    }
    gameOfLife(board, new Position(0, 0, board[0][0]), new HashSet<>());
  }

  void gameOfLife(int[][] board, Position pos, Set<Position> visited) {
    visited.add(pos);
    List<Position> neighbors = getNeighbors(board, pos);

    int alive = 0;
    for (Position neighbor : neighbors) {
      if (!visited.contains(neighbor)) {
        gameOfLife(board, neighbor, visited);
      }
      if (neighbor.isAlive()) {
        alive++;
      }
    }

    if (alive < 2) {
      board[pos.row][pos.col] = 0;
    } else if (alive > 3) {
      board[pos.row][pos.col] = 0;
    } else if (alive == 3 && pos.isDead()) {
      board[pos.row][pos.col] = 1;
    }
  }

  List<Position> getNeighbors(int[][] board, Position pos) {
    List<Position> neighbors = new ArrayList<>();
    for (int row = pos.row - 1; row <= pos.row + 1; row++) {
      for (int col = pos.col - 1; col <= pos.col + 1; col++) {
        if (row == pos.row && col == pos.col) {
          continue;
        }
        if (!isInBounds(board, row, col)) {
          continue;
        }
        neighbors.add(new Position(row, col, board[row][col]));
      }
    }
    return neighbors;
  }

  boolean isInBounds(int[][] board, int row, int col) {
    return row >= 0 && row < board.length &&
        col >= 0 && col < board[0].length;
  }

  static class Position {
    int row;
    int col;
    Integer value;

    Position(int row, int col, Integer value) {
      this.row = row;
      this.col = col;
      this.value = value;
    }

    boolean isAlive() {
      return this.value != null && this.value == 1;
    }

    boolean isDead() {
      return this.value != null && this.value == 0;
    }

    @Override
    public int hashCode() {
      return new String(this.row + "," + this.col).hashCode();
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Position)) {
        return false;
      }
      Position that = (Position) o;
      return this.row == that.row && this.col == that.col;
    }
  }
}
