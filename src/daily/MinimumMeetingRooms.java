package daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumMeetingRooms {

  /**
   * This problem was asked by Snapchat.
   *
   * Given an array of time intervals (start, end) for classroom lectures (possibly overlapping),
   * find the minimum number of rooms required.
   *
   * For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
   */
  public static void main(String[] args) {
    MinimumMeetingRooms solution = new MinimumMeetingRooms();

    // [(30, 75), (0, 50), (60, 150)]
    // return 2
    int[][] intervals = new int[][] {{30, 75}, {0, 50}, {60, 150}};
    System.out.println(solution.numRooms(intervals));

    // [(30, 75), (0, 50), (60, 150), (40, 170)]
    // return 3
    intervals = new int[][] {{30, 75}, {0, 50}, {60, 150}, {40, 170}};
    System.out.println(solution.numRooms(intervals));

    // [(30, 55), (0, 20), (60, 150), (160, 170)]
    // return 1
    intervals = new int[][] {{30, 55}, {0, 20}, {60, 150}, {160, 170}};
    System.out.println(solution.numRooms(intervals));
  }

  int numRooms(int[][] intervals) {
    List<Integer> starts = new ArrayList<>();
    List<Integer> ends = new ArrayList<>();
    for (int numIntervals = 0; numIntervals < intervals.length; numIntervals++) {
      starts.add(intervals[numIntervals][0]);
      ends.add(intervals[numIntervals][1]);
    }

    Collections.sort(starts);
    Collections.sort(ends);

    int numOverlapping = 0;
    int maxOverlapping = 0;
    int start = 0;
    int end = 0;
    while(start < starts.size() && end < ends.size()) {
      if (starts.get(start) < ends.get(end)) {
        numOverlapping++;
        maxOverlapping = Math.max(maxOverlapping, numOverlapping);
        start++;
      } else {
        numOverlapping--;
        end++;
      }
    }
    return maxOverlapping;
  }
}
