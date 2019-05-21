package leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

  /**
   * Given a set of non-overlapping intervals, insert a new interval
   * into the intervals (merge if necessary).
   *
   * You may assume that the intervals were initially sorted according
   * to their start times.
   */
  public static void main(String[] args) {
    InsertInterval solution = new InsertInterval();

    // Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
    // Output: [[1,5],[6,9]]
    int[][] intervals = new int[][]{{1, 3}, {6, 9}};
    int[] newInterval = new int[]{2, 5};
    System.out.println(Arrays.deepToString(solution.insert(intervals, newInterval)));

    // Input: intervals = [[1,3],[6,9]], newInterval = [4,5]
    // Output: [[1,3],[4,5],[6,9]]
    intervals = new int[][]{{1, 3}, {6, 9}};
    newInterval = new int[]{4, 5};
    System.out.println(Arrays.deepToString(solution.insert(intervals, newInterval)));

    // Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    // Output: [[1,2],[3,10],[12,16]]
    // Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
    intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
    newInterval = new int[]{4, 8};
    System.out.println(Arrays.deepToString(solution.insert(intervals, newInterval)));

    // Input: intervals = [[6,9]], newInterval = [2,4]
    // Output: [[2, 4], [6, 9]]
    intervals = new int[][]{{6, 9}};
    newInterval = new int[]{2, 4};
    System.out.println(Arrays.deepToString(solution.insert(intervals, newInterval)));

    // Input: intervals = [[6,9]], newInterval = [2,8]
    // Output: [[2, 9]]
    intervals = new int[][]{{6, 9}};
    newInterval = new int[]{2, 8};
    System.out.println(Arrays.deepToString(solution.insert(intervals, newInterval)));

    // Input: intervals = [[6,9]], newInterval = [5,20]
    // Output: [[5, 20]]
    intervals = new int[][]{{6, 9}};
    newInterval = new int[]{5, 20};
    System.out.println(Arrays.deepToString(solution.insert(intervals, newInterval)));

    // Input: intervals = [[6,9]], newInterval = [8,13]
    // Output: [[6,13]]
    intervals = new int[][]{{6, 9}};
    newInterval = new int[]{8, 13};
    System.out.println(Arrays.deepToString(solution.insert(intervals, newInterval)));

    // Input: intervals = [[6,9]], newInterval = [11, 13]
    // Output: [[6,9],[11,13]]
    intervals = new int[][]{{6, 9}};
    newInterval = new int[]{11, 13};
    System.out.println(Arrays.deepToString(solution.insert(intervals, newInterval)));

    // Input: intervals = [], newInterval = [11, 13]
    // Output: [[11,13]]
    intervals = new int[][]{};
    newInterval = new int[]{11, 13};
    System.out.println(Arrays.deepToString(solution.insert(intervals, newInterval)));

    // Input: intervals = [[1,5]], newInterval = [0,0]
    // Output: [[0,0],[1,5]]
    intervals = new int[][]{{1, 5}};
    newInterval = new int[]{0, 0};
    System.out.println(Arrays.deepToString(solution.insert(intervals, newInterval)));
  }

  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> newIntervals = new ArrayList<>();

    // 1. Add intervals from the beginning that cant be merged
    int i = 0;
    while (i < intervals.length &&
        intervals[i][0] < newInterval[0] &&
        intervals[i][1] < newInterval[0]) {
      newIntervals.add(intervals[i]);
      i++;
    }

    // 2. Merge intervals if any
    if (i >= intervals.length || newInterval[1] < intervals[i][0]) {
      // No merging necessary, just add the new interval
      newIntervals.add(newInterval);
    } else {
      // Merge intervals with the new interval
      Integer mergeStart = newInterval[0];
      Integer mergeEnd = newInterval[1];
      if (i < intervals.length) {
        mergeStart = Math.min(intervals[i][0], newInterval[0]);
        mergeEnd = Math.max(intervals[i][1], newInterval[1]);
      }

      while (i < intervals.length &&
          intervals[i][0] <= newInterval[1]) {
        mergeEnd = Math.max(intervals[i][1], mergeEnd);
        i++;
      }
      newIntervals.add(new int[]{mergeStart, mergeEnd});
    }

    // 3. Add remaining intervals that arent being merged
    while (i < intervals.length) {
      newIntervals.add(intervals[i++]);
    }

    // Convert back to array
    int[][] newArr = new int[newIntervals.size()][];
    for (i = 0; i < newIntervals.size(); i++) {
      newArr[i] = newIntervals.get(i);
    }
    return newArr;
  }
}
