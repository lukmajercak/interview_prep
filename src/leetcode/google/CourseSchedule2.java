package leetcode.google;

import java.util.*;

public class CourseSchedule2 {

  /**
   * There are a total of n courses you have to take, labeled from 0 to n-1.
   *
   * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
   * which is expressed as a pair: [0,1]
   *
   * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses
   * you should take to finish all courses.
   *
   * There may be multiple correct orders, you just need to return one of them.
   * If it is impossible to finish all courses, return an empty array.
   */
  public static void main(String[] args) {
    CourseSchedule2 solution = new CourseSchedule2();

    // Input: 2, [[1,0]]
    //Output: [0,1]
    //Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
    //             course 0. So the correct course order is [0,1] .
    int[][] prerequisites = new int[][]{{1, 0}};
    System.out.println(Arrays.toString(solution.findOrder(2, prerequisites)));

    // Input: 3, [[1,0],[2,0],[0,2]]
    //Output: []
    prerequisites = new int[][]{{1, 0},{2, 0},{0, 2}};
    System.out.println(Arrays.toString(solution.findOrder(3, prerequisites)));

    // Input: 2, [[0,1]]
    //Output: [1,0]
    prerequisites = new int[][]{{0, 1}};
    System.out.println(Arrays.toString(solution.findOrder(2, prerequisites)));

    // Input: 2, [[0,1],[1,0]]
    //Output: []
    prerequisites = new int[][]{{0, 1}, {1, 0}};
    System.out.println(Arrays.toString(solution.findOrder(2, prerequisites)));

    // Input: 4, [[1,0],[2,0],[3,1],[3,2]]
    // Output: [0,1,2,3] or [0,2,1,3]
    // Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
    //             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
    //             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
    prerequisites = new int[][]{
        {1, 0}, {2, 0}, {3, 1}, {3, 2}};
    System.out.println(Arrays.toString(solution.findOrder(4, prerequisites)));

    prerequisites = new int[][]{};
    System.out.println(Arrays.toString(solution.findOrder(1, prerequisites)));
  }

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    if (prerequisites == null) {
      throw new IllegalArgumentException("illegal prerequisites array");
    }

    int len = prerequisites.length;

    // if there is no prerequisites, return a sequence of courses
    if (len == 0){
      int[] courses = new int[numCourses];
      for (int j = 0; j < numCourses; j++){
        courses[j] = j;
      }
      return courses;
    }

    // records the number of prerequisites each course (0,...,numCourses-1) requires
    int[] pCounter = new int[numCourses];
    for (int[] prerequisite : prerequisites) {
      pCounter[prerequisite[0]]++;
    }

    // stores courses that have no prerequisites
    LinkedList<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++){
      if (pCounter[i] == 0){
        queue.add(i);
      }
    }

    int numNoPre = queue.size();

    // initialize result
    int[] result = new int[numCourses];
    int j = 0;

    while (!queue.isEmpty()){
      int c = queue.remove();
      result[j++] = c;

      for (int[] prerequisite : prerequisites) {
        if (prerequisite[1] == c) {
          int nextCourse = prerequisite[0];
          pCounter[nextCourse]--;
          if (pCounter[nextCourse] == 0) {
            queue.add(nextCourse);
            numNoPre++;
          }
        }
      }
    }

    // return result
    if (numNoPre == numCourses){
      return result;
    } else {
      return new int[0];
    }
  }
}
