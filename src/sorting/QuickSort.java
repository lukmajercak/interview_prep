package sorting;

import java.util.Arrays;

public class QuickSort {

  public static void main(String[] args) {
    int[] arr = new int[]{};
    quicksort(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{1};
    quicksort(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{1, 2};
    quicksort(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{2, 1};
    quicksort(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{5, 4, 8, 11, 2, 1, 21, 15, 14, 33, 17};
    quicksort(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{5, 4, 8, 11, 2, 1, 21, 15, 14, 33, 12, 16};
    quicksort(arr);
    System.out.println(Arrays.toString(arr));
  }

  static void quicksort(int[] arr) {
    quicksort(arr, 0, arr.length -1);
  }

  static void quicksort(int[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    // pi is partitioning index, arr[pi] is now at right place
    int pi = partition(arr, low, high);

    // Recursively sort elements before partition and after partition
    quicksort(arr, low, pi - 1);
    quicksort(arr, pi + 1, high);
  }

  /**
   *  This function takes last element as pivot, places the pivot element at its correct
   *  position in sorted array, and places all smaller (smaller than pivot) to left of
   *  pivot and all greater elements to right of pivot */
  static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];

    int smallerIndex = low;
    for (int i = low; i < high; i++) {
      if (arr[i] < pivot) {
        // swap arr[i] with arr[numSmaller]
        swap(arr, smallerIndex++, i);
      }
    }

    // put pivot into the correct position
    swap(arr, smallerIndex, high);
    return smallerIndex;
  }

  static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
