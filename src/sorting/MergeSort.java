package sorting;

import java.util.Arrays;

public class MergeSort {

  public static void main(String[] args) {
    int[] arr = new int[]{};
    mergeSort(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{1};
    mergeSort(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{1, 2};
    mergeSort(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{2, 1};
    mergeSort(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{5, 4, 8, 11, 2, 1, 21, 15, 14, 33, 17};
    mergeSort(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[]{5, 4, 8, 11, 2, 1, 21, 15, 14, 33, 12, 16};
    mergeSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  public static void mergeSort(int[] arr) {
    mergeSort(arr, new int[arr.length], 0, arr.length - 1);
  }

  static void mergeSort(int[] arr, int[] tmp, int leftStart, int rightEnd) {
    if (leftStart >= rightEnd) {
      return;
    }
    int middle = leftStart + ((rightEnd - leftStart) / 2);
    mergeSort(arr, tmp, leftStart, middle);
    mergeSort(arr, tmp, middle + 1, rightEnd);
    merge(arr, tmp, leftStart, rightEnd);
  }

  static void merge(int[] arr, int[] tmp, int leftStart, int rightEnd) {
    int leftEnd = leftStart + ((rightEnd - leftStart) / 2);
    int rightStart = leftEnd + 1;

    int left = leftStart;
    int right = rightStart;

    int index = leftStart;
    while (left <= leftEnd && right <= rightEnd) {
      if (arr[left] <= arr[right]) {
        tmp[index] = arr[left];
        left++;
      } else {
        tmp[index] = arr[right];
        right++;
      }
      index++;
    }

    // Copy remaining
    while (left <= leftEnd) {
      tmp[index++] = arr[left++];
    }
    while (right <= rightEnd) {
      tmp[index++] = arr[right++];
    }

    // Copy back to the array
    while (leftStart <= rightEnd) {
      arr[leftStart] = tmp[leftStart++];
    }
  }
}
