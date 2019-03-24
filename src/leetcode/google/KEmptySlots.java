package leetcode.google;

public class KEmptySlots {

  /**
   * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days.
   * In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
   *
   * Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
   *
   * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.
   *
   * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming,
   * and also the number of flowers between them is k and these flowers are not blooming.
   *
   * If there isn't such day, output -1.
   */
  public static void main(String[] args) {
    KEmptySlots solution = new KEmptySlots();

    //Example 1:
    //Input:
    //flowers: [1,3,2]
    //k: 1
    //Output: 2
    //Explanation: In the second day, the first and the third flower have become blooming.
    int[] flowers = new int[]{1, 3, 2};
    System.out.println(solution.kEmptySlots(flowers, 1));

    //Example 2:
    //Input:
    //flowers: [1,2,3]         0, 1, 2
    //k: 1
    //Output: -1
    flowers = new int[]{1, 2, 3};
    System.out.println(solution.kEmptySlots(flowers, 1));

    // 1 3 4 2 , k = 2 ,  -> 2      0, 3, 1, 2
    flowers = new int[]{1, 3, 4, 2};
    System.out.println(solution.kEmptySlots(flowers, 2));

    // 1 4 3 2 , k = 2 ,  -> 2      0, 3, 2, 1
    flowers = new int[]{1, 4, 3, 2};
    System.out.println(solution.kEmptySlots(flowers, 2));

    // 1 3 4 2 , k = 1 ,  -> -1     0, 3, 1, 2
    flowers = new int[]{1, 3, 4, 2};
    System.out.println(solution.kEmptySlots(flowers, 1));

    // [1,2,3,4,5,7,6] , k = 1 ,  -> -1     0, 3, 1, 2
    flowers = new int[]{1, 3, 4, 2};
    System.out.println(solution.kEmptySlots(flowers, 1));
  }

  // [1, 4, 3, 2, 5]        0, 3, 2, 1, 4
  // k = 3
  //
  public int kEmptySlots(int[] flowers, int k) {
    if (k + 2 > flowers.length) {
      return -1;
    }
    int minBetween = Integer.MAX_VALUE;
    for (int i = 1; i <= k; i++) {
      minBetween = Math.min(flowers[i], minBetween);
    }
    if (flowers[k + 1] < minBetween && flowers[0] < minBetween) {
      if (Math.abs(flowers[k + 1] - flowers[0]) == k) {
        return Math.max(flowers[k + 1], flowers[0]);
      }
    }

    return 0;
  }
}
