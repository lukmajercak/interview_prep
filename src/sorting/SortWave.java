package sorting;

import java.util.Arrays;

public class SortWave {

  public static void main(String[] args) {
    int[] arr = new int[]{};
    sortWave(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{1};
    sortWave(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{1, 2};
    sortWave(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{2, 1};
    sortWave(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{5, 4, 8, 11, 2, 1, 21, 15, 14, 33, 17};
    sortWave(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{5, 4, 8, 11, 2, 1, 21, 15, 14, 33, 12, 16};
    sortWave(arr);
    System.out.println(Arrays.toString(arr));
  }

  public static void sortWave(int[] arr) {
    for (int i = 0; i < arr.length - 1; i += 2) {
      if (i > 0 && arr[i] < arr[i - 1]) {
        swap(arr, i , i - 1);
      }
      if (i < arr.length - 1 && arr[i] < arr[i + 1]) {
        swap(arr, i, i + 1);
      }
    }
  }

  static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
