package leetcode.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers {

  /**
   * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
   * <p>
   * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers,
   * we must pay them according to the following rules:
   * <p>
   * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
   * Every worker in the paid group must be paid at least their minimum wage expectation.
   * Return the least amount of money needed to form a paid group satisfying the above conditions.
   *
   * TODO: check again
   */
  public static void main(String[] args) {
    MinimumCostToHireKWorkers solution = new MinimumCostToHireKWorkers();

    // Input: quality = [10,20,5], wage = [70,50,30], K = 2
    // Output: 105.00000
    // Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
    int[] quality = new int[]{10, 20, 5};
    int[] wage = new int[]{70, 50, 30};
    System.out.println(solution.mincostToHireWorkers(quality, wage, 2));

    // Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
    // Output: 30.66667
    // Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
    quality = new int[]{3, 1, 10, 10, 1};
    wage = new int[]{4, 8, 2, 2, 7};
    System.out.println(solution.mincostToHireWorkers(quality, wage, 3));
  }

  public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    ArrayList<Worker> list = new ArrayList<>();
    for (int i = 0; i < wage.length; i++) {
      list.add(new Worker(quality[i], wage[i]));
    }
    Comparator<Worker> comp = Comparator.comparing((Worker w) -> w.ratio);
    Collections.sort(list, comp);

    PriorityQueue<Integer> q = new PriorityQueue<>();
    int sum = 0;
    double result = Integer.MAX_VALUE;

    for (Worker w : list) {
      sum += w.quality;
      q.offer(-w.quality);

      if (q.size() > K) {
        int extra = q.poll();
        sum += extra;
      }

      if (q.size() == K) {
        result = Math.min(result, sum * w.ratio);
      }
    }
    return result;
  }

  class Worker {
    int quality;
    int wage;
    double ratio;

    public Worker(int q, int w) {
      this.quality = q;
      this.wage = w;
      this.ratio = (double) w / q;
    }
  }
}
