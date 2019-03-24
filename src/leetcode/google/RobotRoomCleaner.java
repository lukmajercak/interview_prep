package leetcode.google;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner {

  /**
   * Given a robot cleaner in a room modeled as a grid.
   *
   * Each cell in the grid can be empty or blocked.
   *
   * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
   *
   * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
   *
   * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
   *
   * interface Robot {
   *   // returns true if next cell is open and robot moves into the cell.
   *   // returns false if next cell is obstacle and robot stays on the current cell.
   *   boolean move();
   *
   *   // Robot will stay on the same cell after calling turnLeft/turnRight.
   *   // Each turn will be 90 degrees.
   *   void turnLeft();
   *   void turnRight();
   *
   *   // Clean the current cell.
   *   void clean();
   * }
   */


  int[] dx = {-1, 0, 1, 0};
  int[] dy = {0, 1, 0, -1};
  public void cleanRoom(Robot robot) {
    // use 'x,y' mark visited nodes, where x,y are integers tracking the coordinates
    dfs(robot, new HashSet<>(), 0, 0, 0); // 0: up, 90: right, 180: down, 270: left
  }

  public void dfs(Robot robot, Set<String> visited, int x, int y, int curDir) {
    String key = x + "," + y;
    if (visited.contains(key)) {
      return;
    }
    visited.add(key);
    robot.clean();

    for (int i = 0; i < 4; i++) { // 4 directions
      if (robot.move()) {
        // can go directly. Find the (x, y) for the next cell based on current direction
        dfs(robot, visited, x + dx[curDir], y + dy[curDir], curDir);
        backtrack(robot);
      }

      // turn to next direction
      robot.turnRight();
      curDir += 1;
      curDir %= 4;
    }
  }

  // go back to the starting position
  private void backtrack(Robot robot) {
    robot.turnLeft();
    robot.turnLeft();
    robot.move();
    robot.turnRight();
    robot.turnRight();
  }

  static interface Robot {
    void turnLeft();
    void turnRight();
    boolean move();
    void clean();
  }
}
