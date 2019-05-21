package leetcode.queue_and_stack;

import java.util.*;

public class NumberOfIslands {

  /**
   * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
   * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
   * You may assume all four edges of the grid are all surrounded by water.
   *
   * Example 1:
   *
   * Input:
   * 11110
   * 11010
   * 11000
   * 00000
   *
   * Output: 1
   *
   *
   * Example 2:
   *
   * Input:
   * 11000
   * 11000
   * 00100
   * 00011
   *
   * Output: 3
   * @param args
   */
  public static void main(String[] args) {
    NumberOfIslands numIslands = new NumberOfIslands();

    System.out.println(numIslands.numIslands(new char[][]{{}}));

    char[][] grid = new char[][]{
        {'1', '1', '1', '1', '0'},
        {'1', '1', '1', '1', '0'},
        {'1', '1', '1', '1', '0'},
        {'1', '1', '1', '1', '0'}};
    System.out.println(numIslands.numIslands(grid));

    // Example 1
    grid = new char[][]{
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}};
    System.out.println(numIslands.numIslands(grid));

    // Example 2
    grid = new char[][]{
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'}};
    System.out.println(numIslands.numIslands(grid));
  }

  public int numIslands(char[][] grid) {
    int numIslands = 0;

    if (grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    Queue<Point> toCheck = new LinkedList<>();
    Set<Point> visited = new HashSet<>();

    toCheck.offer(new Point(0, 0));

    while(!toCheck.isEmpty()) {
      Point point = toCheck.poll();

      if (visited.contains(point)) {
        continue;
      }

      // Process water
      if (isWater(grid, point)) {
        visited.add(point);
        List<Point> neighbors = getNeighbors(grid, point);
        for (Point neighbor : neighbors) {
          toCheck.offer(neighbor);
        }
        continue;
      }

      // Process land
      numIslands++;
      Queue<Point> wholeIsland = new LinkedList<>();
      wholeIsland.offer(point);

      while (!wholeIsland.isEmpty()) {
        Point islandPoint = wholeIsland.poll();
        if (visited.contains(islandPoint)) {
          continue;
        }
        visited.add(islandPoint);

        List<Point> neighbors = getNeighbors(grid, islandPoint);
        for (Point neighbor : neighbors) {
          if (isWater(grid, neighbor)) {
            toCheck.offer(neighbor);
          } else {
            wholeIsland.offer(neighbor);
          }
        }
      }
    }
    return numIslands;
  }

  List<Point> getNeighbors(char[][] grid, Point point) {
    List<Point> neighbors = new ArrayList<>();
    if (point.x + 1 < grid.length) {
      neighbors.add(new Point(point.x + 1, point.y));
    }
    if (point.x - 1 >= 0) {
      neighbors.add(new Point(point.x - 1, point.y));
    }
    if (point.y + 1 < grid[point.x].length) {
      neighbors.add(new Point(point.x, point.y + 1));
    }
    if (point.y - 1 >= 0) {
      neighbors.add(new Point(point.x, point.y - 1));
    }
    return neighbors;
  }

  boolean isWater(char[][] grid, Point point) {
    return grid[point.x][point.y] == '0';
  }

  boolean isLand(char[][] grid, Point point) {
    return !isWater(grid, point);
  }

  static class Point {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Point)) {
        return false;
      }
      Point other = (Point) o;
      return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
      return new String(String.valueOf(x) + "," + String.valueOf(y)).hashCode();
    }
  }
}
