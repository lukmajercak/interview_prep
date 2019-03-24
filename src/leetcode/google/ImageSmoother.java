package leetcode.google;

import java.util.Arrays;

public class ImageSmoother {

  /**
   * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother
   * to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8
   * surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
   */
  public static void main(String[] args) {
    ImageSmoother solution = new ImageSmoother();

    // Example 1:
    //Input:
    //[[1,1,1],
    // [1,0,1],
    // [1,1,1]]
    //Output:
    //[[0, 0, 0],
    // [0, 0, 0],
    // [0, 0, 0]]
    //Explanation:
    //For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
    //For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
    //For the point (1,1): floor(8/9) = floor(0.88888889) = 0
    int[][] m = new int[][]{
        {1, 1, 1},
        {1, 0, 1},
        {1, 1, 1}
    };
    System.out.println(Arrays.deepToString(solution.imageSmoother(m)));

    m = new int[][]{
        {2,3,4},
        {5,6,7},
        {8,9,10},
        {11,12,13},
        {14,15,16}
    };
    System.out.println(Arrays.deepToString(solution.imageSmoother(m)));
  }

  public int[][] imageSmoother(int[][] M) {
    if (M.length == 0) {
      return new int[][]{};
    }
    int rows = M.length;
    int cols = M[0].length;
    int[][] smooth = new int[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        int sum = 0;
        int num = 0;

        for (int i = Math.max(row - 1, 0); i < Math.min(row + 2, rows); i++) {
          for (int j = Math.max(col - 1, 0); j < Math.min(col + 2, cols); j++) {
            num++;
            sum+= M[i][j];
          }
        }
        smooth[row][col] = (int) Math.floor(sum / num);
      }
    }
    return smooth;
  }
}
