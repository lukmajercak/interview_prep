package sorting;

import java.util.Arrays;

import static sorting.QuickSort.quicksort;

public class BinarySearch {

  public static void main(String[] args) {
    int[] arr = new int[]{};
    System.out.println(binarySearch(arr, 1));

    arr = new int[]{1};
    System.out.println(binarySearch(arr, 1));
    System.out.println(binarySearch(arr, 2));

    arr = new int[]{1, 2};
    System.out.println(binarySearch(arr, 1));
    System.out.println(binarySearch(arr, 2));
    System.out.println(binarySearch(arr, 3));

    arr = new int[]{5, 4, 8, 11, 2, 1, 21, 15, 14, 33, 17};
    quicksort(arr);
    System.out.println(binarySearch(arr, 5));
    System.out.println(binarySearch(arr, 1));
    System.out.println(binarySearch(arr, 17));
    System.out.println(binarySearch(arr, 21));

    arr = new int[]{5, 4, 8, 11, 2, 1, 21, 15, 14, 33, 12, 16};
    quicksort(arr);
    System.out.println(binarySearch(arr, 5));
    System.out.println(binarySearch(arr, 1));
    System.out.println(binarySearch(arr, 14));
    System.out.println(binarySearch(arr, 21));
    System.out.println(binarySearch(arr, 25));
  }

  static Integer binarySearch(int[] arr, int x) {
    return binarySearch(arr, x, 0, arr.length - 1);
  }

  static Integer binarySearch(int[] arr, int x, int start, int end) {
    if (start > end) {
      return null;
    }
    int middle = start + ((end - start) / 2);

    if (arr[middle] == x) {
      return x;
    }
    if (arr[middle] < x) {
      return binarySearch(arr, x, middle + 1, end);
    }
    return binarySearch(arr, x, start, middle - 1);
  }
}
