package daily;

public class Daily_03_10_2019 {

  /**
   * This problem was asked by Facebook.
   *
   * A builder is looking to build a row of N houses that can be of K different colors.
   * He has a goal of minimizing cost while ensuring that no two neighboring houses are of the same color.
   *
   * Given an N by K matrix where the nth row and kth column represents the cost to build
   * the nth house with kth color, return the minimum cost which achieves this goal.
   */
  public static void main(String[] args) {
    Daily_03_10_2019 solution = new Daily_03_10_2019();

    int[][] prices = new int[][]{
        {1, 3, 2, 4},
        {3, 1, 0, 1},
        {6, 3, 1, 5}
    };
    System.out.println(solution.minimumCost(prices));
    System.out.println(solution.minimumCostIterative(prices));
  }

  int minimumCostIterative(int[][] prices) {
    int numColors = prices.length;
    int[] previousLowestCost = new int[numColors];

    for (int houseNum = 0; houseNum < prices[0].length; houseNum++) {
      int[] nextHousesLowestCosts = new int[numColors];

      for (int color = 0; color < numColors; color++) {
        int minimum = Integer.MAX_VALUE;
        for (int colorPicked = 0; colorPicked < numColors; colorPicked++) {
          if (color != colorPicked) {
            minimum = Math.min(minimum, previousLowestCost[colorPicked]);
          }
        }
        nextHousesLowestCosts[color] = minimum + prices[color][houseNum];
      }
      previousLowestCost = nextHousesLowestCosts;
    }

    int minimum = Integer.MAX_VALUE;
    for (int price : previousLowestCost) {
      minimum = Math.min(minimum, price);
    }

    return minimum;
  }

  /**
   * [[1, 3, 2, 4],
   *  [3, 1, 0, 1],
   *  [6, 3, 1, 5]]
   *  = 4
   *
   */

  int minimumCost(int[][] prices) {
    return minimumCost(prices, 0, -1);
  }

  int minimumCost(int[][] prices, int houseNumber, int previouslyPickedColor) {
    if (houseNumber == prices[0].length) {
      return 0;
    }

    Integer minimum = Integer.MAX_VALUE;
    for (int color = 0; color < prices.length; color++) {
      if (color != previouslyPickedColor) {
        int cost = prices[color][houseNumber] + minimumCost(prices, houseNumber + 1, color);
        minimum = Math.min(minimum, cost);
      }
    }
    return minimum;
  }
}
