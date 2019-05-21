package leetcode.google;

import java.util.*;

public class MergeIntervals {

  /**
   * Given a collection of intervals, merge all overlapping intervals.
   */
  public static void main(String[] args) {
    MergeIntervals solution = new MergeIntervals();

    // Input: [[1,3],[2,6],[8,10],[15,18]]
    // Output: [[1,6],[8,10],[15,18]]
    // Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
    int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    System.out.println(Arrays.deepToString(solution.merge(intervals)));

    // Input: [[2,6],[1,3],[8,10],[15,18]]
    // Output: [[1,6],[8,10],[15,18]]
    // Explanation: Same as above, just not in order
    intervals = new int[][]{{2, 6}, {1, 3}, {8, 10}, {15, 18}};
    System.out.println(Arrays.deepToString(solution.merge(intervals)));

    // Input: [[1,4],[4,5]]
    // Output: [[1,5]]
    // Explanation: Intervals [1,4] and [4,5] are considered overlapping.
    intervals = new int[][]{{1, 4}, {4, 5}};
    System.out.println(Arrays.deepToString(solution.merge(intervals)));

    // Input: [[4,5],[1,4]]
    // Output: [[1,5]]
    // Explanation: Intervals [1,4] and [4,5] are considered overlapping.
    intervals = new int[][]{{4, 5}, {1, 4}};
    System.out.println(Arrays.deepToString(solution.merge(intervals)));

    // Input: [[4,5]]
    // Output: [[4,5]]
    intervals = new int[][]{{4, 5}};
    System.out.println(Arrays.deepToString(solution.merge(intervals)));

    // Input: []
    // Output: []
    intervals = new int[][]{};
    System.out.println(Arrays.deepToString(solution.merge(intervals)));

    // Input: [[1,4][2,3]]
    // Output: [[1,4]]
    intervals = new int[][]{{1, 4}, {2, 3}};
    System.out.println(Arrays.deepToString(solution.merge(intervals)));
  }

  public int[][] merge(int[][] intervals) {
    // sort first
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    // merge
    List<int[]> merged = mergeSortedIntervals(intervals);

    int[][] mergedArr = new int[merged.size()][];
    for (int i = 0; i < merged.size(); i++) {
      mergedArr[i] = merged.get(i);
    }
    return mergedArr;
  }

  public List<int[]> mergeSortedIntervals(int[][] intervals) {
    List<int[]> merged = new ArrayList<>();

    if (intervals.length == 0) {
      return merged;
    }

    int startIndex = intervals[0][0];
    int endIndex = intervals[0][1];

    for (int i = 1; i < intervals.length; i++) {
      int nextStart = intervals[i][0];
      int nextEnd = intervals[i][1];

      if (nextStart <= endIndex) {
        endIndex = Math.max(endIndex, nextEnd);
      } else {
        merged.add(new int[]{startIndex, endIndex});
        startIndex = nextStart;
        endIndex = nextEnd;
      }
    }

    merged.add(new int[]{startIndex, endIndex});

    return merged;
  }
}
