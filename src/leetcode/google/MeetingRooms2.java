package leetcode.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingRooms2 {

  /**
   * Given an array of meeting time intervals consisting of start and end times
   * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
   */
  public static void main(String[] args) {
    MeetingRooms2 solution = new MeetingRooms2();

    // Input: [[0, 30],[5, 10],[15, 20]]
    // Output: 2
    int[][] intervals = new int[][] {
        {0, 30}, {5, 10}, {15, 20}};
    System.out.println(solution.minMeetingRooms(intervals));

    // Input: [[7,10],[2,4]]
    // Output: 1
    intervals = new int[][] {
        {7, 10}, {2, 4}};
    System.out.println(solution.minMeetingRooms(intervals));
  }

  public int minMeetingRooms(int[][] intervals) {
    List<Integer> starts = new ArrayList<>();
    List<Integer> ends = new ArrayList<>();

    for (int[] meeting : intervals) {
      starts.add(meeting[0]);
      ends.add(meeting[1]);
    }

    Collections.sort(starts);
    Collections.sort(ends);

    int minMeetingRooms = 0;
    int numOverlapping = 0;

    int startIndex = 0;
    int endIndex = 0;
    while (startIndex < starts.size() && endIndex < ends.size()) {
      if (starts.get(startIndex) < ends.get(endIndex)) {
        startIndex++;
        minMeetingRooms = Math.max(minMeetingRooms, ++numOverlapping);
      } else {
        numOverlapping--;
        endIndex++;
      }
    }

    return minMeetingRooms;
  }
}
