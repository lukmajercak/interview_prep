package leetcode.queue_and_stack;

import java.util.*;

public class OpenTheLock {

  /**
   * You have a lock in front of you with 4 circular wheels.
   * Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
   * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
   * Each move consists of turning one wheel one slot.
   *
   * The lock initially starts at '0000', a string representing the state of the 4 wheels.
   *
   * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
   * the wheels of the lock will stop turning and you will be unable to open it.
   *
   * Given a target representing the value of the wheels that will unlock the lock,
   * return the minimum total number of turns required to open the lock, or -1 if it is impossible.
   *
   *
   * Example 1:
   * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
   * Output: 6
   * Explanation:
   * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
   * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
   * because the wheels of the lock become stuck after the display becomes the dead end "0102".
   *
   * Example 2:
   * Input: deadends = ["8888"], target = "0009"
   * Output: 1
   * Explanation:
   * We can turn the last wheel in reverse to move from "0000" -> "0009".
   *
   * Example 3:
   * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
   * Output: -1
   * Explanation:
   * We can't reach the target without getting stuck.
   *
   * Example 4:
   * Input: deadends = ["0000"], target = "8888"
   * Output: -1
   *
   * Note:
   * The length of deadends will be in the range [1, 500].
   * target will not be in the list deadends.
   * Every string in deadends and the string target will be a string of 4 digits
   * from the 10,000 possibilities '0000' to '9999
   *
   */
  public static void main(String[] args) {
    OpenTheLock openLock = new OpenTheLock();

    // Example 1:
    String[] deadends = new String[]{"0201","0101","0102","1212","2002"};
    System.out.println(openLock.openLock(deadends, "0202"));

    // Example 2:
    deadends = new String[]{"8888"};
    System.out.println(openLock.openLock(deadends, "0009"));

    // Example 3:
    deadends = new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"};
    System.out.println(openLock.openLock(deadends, "8888"));

    // Example 4:
    deadends = new String[]{"0000"};
    System.out.println(openLock.openLock(deadends, "8888"));
  }

  public int openLock(String[] deadends, String target) {
    Set<String> deadendsSet = new HashSet<>(Arrays.asList(deadends));

    String startingPosition = "0000";
    if (deadendsSet.contains(startingPosition)) {
      return -1;
    }

    Queue<String> positionsToCheck = new LinkedList<>();
    positionsToCheck.offer(startingPosition);

    Set<String> visited = new HashSet<>();
    visited.add(startingPosition);

    int stepsTaken = 0;

    while (!positionsToCheck.isEmpty()) {
      // Check all on this level
      int size = positionsToCheck.size();
      for (int i = 0; i < size; i++) {
        String next = positionsToCheck.poll();
        if (next.equals(target)) {
          return stepsTaken;
        }

        // Get all for the next level and add to the queue
        List<String> nextPositions = getNextPositions(next);
        for (String nextPosition : nextPositions) {
          if (!visited.contains(nextPosition) && !deadendsSet.contains(nextPosition)) {
            visited.add(nextPosition);
            positionsToCheck.offer(nextPosition);
          }
        }
      }
      stepsTaken++;
    }
    return -1;
  }

  List<String> getNextPositions(String position) {
    List<String> positions = new ArrayList<>();
    for (int i = 0; i < position.length(); i++) {
      int wheelPos = Character.getNumericValue(position.charAt(i));

      String before = position.substring(0, i);
      String after = position.substring(i + 1);

      positions.add(before + (wheelPos == 9 ? 0 : wheelPos + 1) + after);
      positions.add(before + (wheelPos == 0 ? 9 : wheelPos - 1) + after);
    }
    return positions;
  }
}
