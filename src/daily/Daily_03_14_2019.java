package daily;

import java.util.*;

public class Daily_03_14_2019 {

  /**
   * This problem was asked by Google.
   *
   * You are given an M by N matrix consisting of booleans that represents a board.
   * Each True boolean represents a wall. Each False boolean represents a tile you can walk on.
   *
   * Given this matrix, a start coordinate, and an end coordinate, return the minimum number of
   * steps required to reach the end coordinate from the start. If there is no possible path,
   * then return null. You can move up, left, down, and right. You cannot move through walls.
   * You cannot wrap around the edges of the board.
   *
   * For example, given the following board:
   *
   * [[f, f, f, f],
   * [t, t, f, t],
   * [f, f, f, f],
   * [f, f, f, f]]
   * and start = (3, 0) (bottom left) and end = (0, 0) (top left), the minimum number of steps
   * required to reach the end is 7, since we would need to go through (1, 2) because there is a wall everywhere else on the second row.
   */
  public static void main(String[] args) {
    Daily_03_14_2019 solution = new Daily_03_14_2019();

    // return 7
    boolean[][] board = new boolean[][] {
        {false, false, false, false},
        {true, true, false, true},
        {false, false, false, false},
        {false, false, false, false}
    };
    Position start = new Position(3, 0);
    Position end = new Position(0, 0);
    System.out.println(solution.minSteps(board, start, end));
    System.out.println(solution.minStepsPath(board, start, end));

    // return null
    board = new boolean[][] {
        {false, false, false, false},
        {true, true, true, true},
        {false, false, false, false},
        {false, false, false, false}
    };
    start = new Position(3, 0);
    end = new Position(0, 0);
    System.out.println(solution.minSteps(board, start, end));
    System.out.println(solution.minStepsPath(board, start, end));

    // return 16
    board = new boolean[][] {
        {false, false, false, false, false},
        {true, true, true, false, true},
        {false, false, false, false, true},
        {false, true, true, true, true},
        {false, false, true, false, false},
        {true, false, false, false, true}
    };
    start = new Position(0, 0);
    end = new Position(4, 4);
    System.out.println(solution.minSteps(board, start, end));
    System.out.println(solution.minStepsPath(board, start, end));
  }

  Integer minSteps(boolean[][] board, Position start, Position end) {
    if (start.equals(end)) {
      return 0;
    }
    Set<Position> visited = new HashSet<>();
    Queue<Position> toVisit = new LinkedList<>();
    toVisit.offer(start);

    Integer numSteps = -1;
    while (!toVisit.isEmpty()) {
      int check = toVisit.size();
      numSteps++;
      while (check > 0) {
        check--;
        Position nextPos = toVisit.poll();
        if (visited.contains(nextPos)) {
          continue;
        }

        if (nextPos.equals(end)) {
          return numSteps;
        }
        visited.add(nextPos);

        List<Position> nextPositions = nextPos.getNext(board.length, board[0].length);
        for (Position child : nextPositions) {
          if (!isWall(board, child)) {
            toVisit.offer(child);
          }
        }
      }
    }
    return null;
  }

  List<Position> minStepsPath(boolean[][] board, Position start, Position end) {
    List<Position> startPosPath = Arrays.asList(new Position[]{start});
    if (start.equals(end)) {
      return startPosPath;
    }
    Set<Position> visited = new HashSet<>();
    Queue<List<Position>> toVisit = new LinkedList<>();
    toVisit.offer(startPosPath);

    while (!toVisit.isEmpty()) {
      int check = toVisit.size();
      while (check > 0) {
        check--;
        List<Position> nextPosPath = toVisit.poll();
        Position lastNode = nextPosPath.get(nextPosPath.size() - 1);
        if (visited.contains(lastNode)) {
          continue;
        }

        if (lastNode.equals(end)) {
          return nextPosPath;
        }
        visited.add(lastNode);

        List<Position> nextPositions = lastNode.getNext(board.length, board[0].length);
        for (Position child : nextPositions) {
          if (!isWall(board, child)) {
            List<Position> childPath = new ArrayList<>(nextPosPath);
            childPath.add(child);
            toVisit.offer(childPath);
          }
        }
      }
    }
    return null;
  }

  boolean isWall(boolean[][] board, Position position) {
    return board[position.row][position.col];
  }

  static class Position {
    int row;
    int col;

    Position(int row, int col) {
      this.row = row;
      this.col = col;
    }

    List<Position> getNext(int numRows, int numCols) {
      List<Position> next = new ArrayList<>(4);
      if (col - 1 >= 0) {
        next.add(new Position(row, col - 1));
      }
      if (col + 1 < numCols) {
        next.add(new Position(row, col + 1));
      }
      if (row - 1 >= 0) {
        next.add(new Position(row - 1, col));
      }
      if (row + 1 < numRows) {
        next.add(new Position(row + 1, col));
      }

      return next;
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Position)) {
        return false;
      }
      Position that = (Position) o;
      return this.row == that.row && this.col == that.col;
    }

    @Override
    public int hashCode() {
      return new String(String.valueOf(this.row) + "," +
          String.valueOf(this.col)).hashCode();
    }

    @Override
    public String toString() {
      return "(row: " + this.row + ", col: " + this.col + ")";
    }
  }
}
