package leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {

  /**
   * In a row of trees, the i-th tree produces fruit with type tree[i].
   *
   * You start at any tree of your choice, then repeatedly perform the following steps:
   *
   * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
   * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
   * Note that you do not have any choice after the initial choice of starting tree:
   *  you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
   *
   * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
   *
   * What is the total amount of fruit you can collect with this procedure?
   */
  public static void main(String[] args) {
    FruitIntoBaskets solution = new FruitIntoBaskets();

    // Input: [1,2,1]
    //Output: 3
    //Explanation: We can collect [1,2,1].
    int[] trees = new int[]{1, 2, 1};
    System.out.println(solution.totalFruit(trees));

    // Input: [0,1,2,2]
    //Output: 3
    //Explanation: We can collect [1,2,2].
    //If we started at the first tree, we would only collect [0, 1].
    trees = new int[]{0, 1, 2, 2};
    System.out.println(solution.totalFruit(trees));

    //Input: [1,2,3,2,2]
    //Output: 4
    //Explanation: We can collect [2,3,2,2].
    //If we started at the first tree, we would only collect [1, 2].
    trees = new int[]{1, 2, 3, 2, 2};
    System.out.println(solution.totalFruit(trees));

    //Input: [3,3,3,1,2,1,1,2,3,3,4]
    //Output: 5
    //Explanation: We can collect [1,2,1,1,2].
    //If we started at the first tree or the eighth tree, we would only collect 4 fruits.
    trees = new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
    System.out.println(solution.totalFruit(trees));

    // output: 3
    trees = new int[]{1, 0, 3, 4, 3};
    System.out.println(solution.totalFruit(trees));

    // output: 5
    trees = new int[]{1, 0, 1, 4, 1, 4, 1, 2, 3};
    System.out.println(solution.totalFruit(trees));
  }

  public int totalFruit(int[] tree) {
    if (tree.length == 0) {
      return 0;
    }
    int max = 1;
    Map<Integer, Integer> fruitTypeIndices = new HashMap<>();

    int i = 0;
    int j = 0;

    while (j < tree.length) {
      if (fruitTypeIndices.size() <= 2) {
        fruitTypeIndices.put(tree[j], j++);
      }
      if (fruitTypeIndices.size() > 2) {
        // find min
        int min = tree.length - 1;
        for (int lastIndex : fruitTypeIndices.values()) {
          min = Math.min(min, lastIndex);
        }
        i = min + 1;

        // Remove the fruit that we are throwing away
        fruitTypeIndices.remove(tree[min]);
      }
      max = Math.max(max, j - i);
    }

    return max;
  }
}
