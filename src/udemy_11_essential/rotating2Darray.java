package udemy_11_essential;

import java.util.Arrays;

public class rotating2Darray {

  public static void main(String[] args) {
    // NOTE: The following input values will be used for testing your solution.
    int[][] a1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}};
    // rotate(a1, 3) should return:
    // [[7, 4, 1],
    //  [8, 5, 2],
    //  [9, 6, 3]]
    System.out.println(Arrays.deepToString(rotate(a1, 3)));

    int[][] a2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}};
    // rotate(a2, 4) should return:
    // [[13, 9, 5, 1],
    //  [14, 10, 6, 2],
    //  [15, 11, 7, 3],
    //  [16, 12, 8, 4]]
    System.out.println(Arrays.deepToString(rotate(a2, 4)));
  }

  // Implement your solution below.
  public static int[][] rotate(int[][] a, int n) {
    rotate(a, 0, n - 1);
    return a;
  }

  public static void rotate(int[][] a, int startIndex, int endIndex) {
    // 1 element subsquare
    if (startIndex >= endIndex) {
      return;
    }

    int offset = 0;

    // rotate edges of startIndex -> endIndex
    while (offset + startIndex < endIndex) {
      int topLeftTmp = a[startIndex][startIndex + offset];

      // top left is bottom left
      a[startIndex][startIndex + offset] = a[endIndex - offset][startIndex];
      // bottom left is bottom right
      a[endIndex - offset][startIndex] = a[endIndex][endIndex - offset];
      // bottom right is top right
      a[endIndex][endIndex - offset] = a[startIndex + offset][endIndex];
      // top right is top left
      a[startIndex + offset][endIndex] = topLeftTmp;

      offset++;
    }
    rotate(a, startIndex + 1, endIndex -1);
  }
}
