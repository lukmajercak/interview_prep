package leetcode.google;

public class MostStonesRemovedWithSameRowOrColumn {

  /**
   * On a 2D plane, we place stones at some integer coordinate points.
   * Each coordinate point may have at most one stone.
   *
   * Now, a move consists of removing a stone that shares a column or row
   * with another stone on the grid.
   *
   * What is the largest possible number of moves we can make?
   */
  public static void main(String[] args) {
    MostStonesRemovedWithSameRowOrColumn solution =
        new MostStonesRemovedWithSameRowOrColumn();

    // Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
    //Output: 5

    //Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
    //Output: 3

    //Input: stones = [[0,0]]
    //Output: 0

  }

  int[] root = new int[1000]; //value is index
  int result = 0;


  // Use disjoint sets
  public int removeStones(int[][] stones) {
    result = stones.length;
    for (int i = 0; i < stones.length; i++) {
      root[i] = i;
    }

    for (int i = 0; i < stones.length; i++) {
      for (int j = i + 1; j < stones.length; j++) {
        if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
          union(i, j);
        }
      }
    }

    return stones.length - result;
  }

  public void union(int i, int j) {
    int ri = getRoot(i);
    int rj = getRoot(j);

    if (ri == rj) {
      // Same set
      return;
    }

    root[ri] = rj;
    result--;
  }

  public int getRoot(int i){
    while (i != root[i]) {
      i = root[i];
    }
    return i;
  }
}
