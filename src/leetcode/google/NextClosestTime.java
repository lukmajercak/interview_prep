package leetcode.google;

import java.util.HashSet;
import java.util.Set;

public class NextClosestTime {

  /**
   * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
   * There is no limit on how many times a digit can be reused.
   *
   * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
   */
  public static void main(String[] args) {
    NextClosestTime solution = new NextClosestTime();

    // Example 1:
    //
    //Input: "19:34"
    //Output: "19:39"
    //Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
    //             It is not 19:33, because this occurs 23 hours and 59 minutes later.
    System.out.println(solution.nextClosestTime("19:34"));

    // Example 2:
    //
    //Input: "23:59"
    //Output: "22:22"
    //Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned
    //             time is next day's time since it is smaller than the input time numerically.
    System.out.println(solution.nextClosestTime("23:59"));

    System.out.println(solution.nextClosestTime("13:24")); // 13:31
    System.out.println(solution.nextClosestTime("19:39")); // 11:11
    System.out.println(solution.nextClosestTime("18:24")); // 18:28
    System.out.println(solution.nextClosestTime("19:24")); // 19:29
    System.out.println(solution.nextClosestTime("20:14")); // 20:20
    System.out.println(solution.nextClosestTime("14:15")); // 14:41
    System.out.println(solution.nextClosestTime("13:55")); // 15:11
    System.out.println(solution.nextClosestTime("22:23")); // 22:32
    System.out.println(solution.nextClosestTime("20:48")); // 22:00
  }

  static final int MINUTES_IN_DAY = 24 * 60;

  public String nextClosestTime(String time) {
    int minutes = Integer.parseInt(time.substring(0, 2)) * 60;
    minutes += Integer.parseInt(time.substring(3));

    Set<Integer> digits = new HashSet<>();
    for (char c : time.toCharArray()) {
      digits.add(c - '0');
    }

    while (true) {
      minutes = (minutes + 1) % MINUTES_IN_DAY;

      int[] nextTime = {minutes / 60 / 10, minutes / 60 % 10, minutes % 60 / 10, minutes % 60 % 10};

      boolean isValid = true;
      for (int digit : nextTime) {
        if (!digits.contains(digit)) {
          isValid = false;
          break;
        }
      }

      if (isValid) {
        return toString(nextTime);
      }
    }
  }

  String toString(int[] times) {
    return String.format("%d%d:%d%d", times[0], times[1], times[2], times[3]);
  }
}
