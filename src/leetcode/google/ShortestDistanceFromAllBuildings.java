package leetcode.google;

import java.util.*;

public class ShortestDistanceFromAllBuildings {

  /**
   * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
   * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
   *
   * Each 0 marks an empty land which you can pass by freely.
   * Each 1 marks a building which you cannot pass through.
   * Each 2 marks an obstacle which you cannot pass through.
   *
   * Note:
   * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
   */
  public static void main(String[] args) {
    ShortestDistanceFromAllBuildings solution = new ShortestDistanceFromAllBuildings();

    // Example:
    //
    //Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
    //
    //1 - 0 - 2 - 0 - 1
    //|   |   |   |   |
    //0 - 0 - 0 - 0 - 0
    //|   |   |   |   |
    //0 - 0 - 1 - 0 - 0
    //
    //Output: 7
    //
    //Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
    //             the point (1,2) is an ideal empty land to build a house, as the total
    //             travel distance of 3+3+1=7 is minimal. So return 7.
    int[][] grid = new int[][] {
        {1,0,2,0,1},
        {0,0,0,0,0},
        {0,0,1,0,0}
    };
    System.out.println(solution.shortestDistance(grid));

    grid = new int[][] {
        {1,2,0}
    };
    System.out.println(solution.shortestDistance(grid));

    grid = new int[][] {
        {0,2,0,0,2,2,2,2,2,2,2,0,0,0,0,2,2,1,0,0,2,0,2,0,2,0,0,2,2,2,0,0,2,0,2,0,2,2,2,0,2},
        {0,0,0,0,0,2,2,0,2,0,0,0,0,0,2,0,0,2,2,0,2,2,2,2,0,0,2,2,0,0,2,2,1,0,0,2,2,0,2,0,0},
        {0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,2,2,0,0,0,2,2,2,2,0,0,0,0,2,2,0,0,0,0,0,2,0,0,2,2,0},
        {2,2,0,2,0,0,2,0,0,0,0,0,2,2,2,0,2,2,2,0,0,0,0,0,0,1,2,2,0,0,0,0,2,0,0,2,0,0,0,0,2},
        {0,0,2,2,0,0,2,1,2,0,0,0,0,2,1,0,2,2,0,2,0,0,0,2,1,0,2,2,0,0,0,2,0,0,0,2,2,0,2,0,0},
        {0,0,2,2,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,2,2,2,2,2,0,2,2,1,2,0,2,0,0,0,2,0,2,2,0},
        {2,0,0,0,2,2,0,0,0,2,1,0,2,0,0,0,0,2,0,2,0,2,2,2,0,2,2,2,0,0,0,0,0,0,2,0,0,0,0,0,2},
        {1,0,2,2,0,0,2,0,0,0,2,0,0,0,2,2,2,2,0,0,0,0,2,0,0,2,0,2,1,2,2,0,0,2,0,0,0,0,0,0,0},
        {0,2,0,0,0,1,0,0,2,2,0,0,0,0,2,0,0,2,0,2,2,2,0,0,2,2,0,2,2,2,2,0,0,0,0,2,0,2,0,0,2},
        {0,0,2,2,0,2,2,0,2,0,1,0,0,0,0,0,2,0,0,2,0,2,0,0,0,0,0,0,0,2,2,0,0,0,0,0,2,2,0,0,1},
        {1,2,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,2,0,0,2,0},
        {0,1,2,2,2,2,0,2,0,0,2,2,0,0,0,0,0,2,0,2,0,0,0,0,2,0,0,0,0,0,0,2,2,2,0,0,2,2,0,0,0},
        {2,0,2,0,2,0,0,2,0,0,0,2,0,0,2,2,0,0,0,2,0,0,2,2,2,0,2,2,0,2,2,1,2,0,0,2,0,0,0,2,0},
        {0,0,2,0,0,0,2,2,2,0,2,2,2,0,0,2,0,0,0,2,1,2,2,0,2,0,2,0,0,2,2,0,0,0,2,2,0,0,0,0,0},
        {0,0,0,0,0,2,2,0,2,0,0,2,0,2,0,0,0,2,0,0,0,0,0,0,0,1,2,0,0,0,0,1,0,2,0,0,2,0,1,0,0},
        {1,2,0,2,0,0,0,2,0,2,0,0,0,0,0,2,1,2,0,0,0,0,0,2,0,0,0,0,0,2,2,0,0,0,2,0,0,1,0,2,2},
        {1,0,0,2,0,2,0,2,0,2,0,0,0,0,1,0,0,0,0,0,2,2,0,2,0,2,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
        {0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,2,0,0,0,0,1,2,2,0,0,2,2,0,0,0,0,0,0,2,0,2,0,2,2,0,1},
        {2,0,0,1,1,0,1,0,2,0,0,2,1,0,2,0,0,2,2,0,2,0,2,2,0,1,2,0,2,0,0,0,0,0,0,2,0,0,2,0,0},
        {2,2,0,0,0,2,2,0,2,2,0,0,0,2,0,0,0,2,0,0,2,0,0,2,0,0,0,2,0,0,0,2,2,2,0,2,0,0,2,0,2}
    };
    System.out.println(solution.shortestDistance(grid));

    grid = new int[][] {
        {1,1,1,1,1,0},
        {0,0,0,0,0,1},
        {0,1,1,0,0,1},
        {1,0,0,1,0,1},
        {1,0,1,0,0,1},
        {1,0,0,0,0,1},
        {0,1,1,1,1,0}
    };
    System.out.println(solution.shortestDistance(grid));
  }

  int[][] numReach;
  int[][] distances;

  public int shortestDistance(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int numRows = grid.length;
    int numCols = grid[0].length;
    numReach = new int[numRows][numCols];
    distances = new int[numRows][numCols];

    int numBuildings = 0;
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        if (grid[row][col] != 1) {
          continue;
        }
        bfs(grid, row, col);
        numBuildings++;
      }
    }

    int result = Integer.MAX_VALUE;
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        if (grid[row][col] == 0 && numReach[row][col] == numBuildings){
          result = Math.min(result, distances[row][col]);
        }
      }
    }
    return result == Integer.MAX_VALUE ? -1 : result;
  }

  private void bfs(int[][] grid, int row, int col) {
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int distance = -1;
    Queue<Position> toCheck = new LinkedList<>();
    toCheck.add(new Position(row, col));

    while (!toCheck.isEmpty()) {
      distance++;
      int checkThisRound = toCheck.size();
      while (checkThisRound-- > 0) {
        Position next = toCheck.remove();

        if (visited[next.row][next.col]) {
          continue;
        }

        if (grid[next.row][next.col] == 0) {
          visited[next.row][next.col] = true;
          numReach[next.row][next.col]++;
          distances[next.row][next.col] += distance;
        }

        toCheck.addAll(getNeighbors(next, grid));
      }
    }
  }

  List<Position> getNeighbors(Position pos, int[][] grid) {
    List<Position> neighbors = new ArrayList<>();
    if (inBounds(pos.row + 1, pos.col, grid) && grid[pos.row + 1][pos.col] == 0) {
      neighbors.add(new Position(pos.row + 1, pos.col));
    }
    if (inBounds(pos.row - 1, pos.col, grid) && grid[pos.row - 1][pos.col] == 0) {
      neighbors.add(new Position(pos.row - 1, pos.col));
    }
    if (inBounds(pos.row, pos.col + 1, grid) && grid[pos.row][pos.col + 1] == 0) {
      neighbors.add(new Position(pos.row, pos.col + 1));
    }
    if (inBounds(pos.row, pos.col - 1, grid) && grid[pos.row][pos.col - 1] == 0) {
      neighbors.add(new Position(pos.row, pos.col - 1));
    }
    return neighbors;
  }

  boolean inBounds(int row, int col, int[][] grid) {
    return row >= 0 && row < grid.length &&
        col >= 0 && col < grid[0].length;
  }

  static class Position {
    int row;
    int col;

    Position(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public int hashCode() {
      return (this.row + "," + this.col).hashCode();
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
    public String toString() {
      return "row: " + this.row + ", col: " + this.col;
    }
  }
}
