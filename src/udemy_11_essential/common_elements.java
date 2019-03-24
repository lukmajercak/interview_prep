package udemy_11_essential;

import java.util.ArrayList;
import java.util.List;

public class common_elements {

  public static void main(String[] args) {
    // NOTE: The following input values are used for testing your solution.

    int[] array1A = {1, 3, 4, 6, 7, 9};
    int[] array2A = {1, 2, 4, 5, 9, 10};
    // should return [1, 4, 9] (an array).
    commonElements(array1A, array2A);

    int[] array1B = {1, 2, 9, 10, 11, 12};
    int[] array2B = {0, 1, 2, 3, 4, 5, 8, 9, 10, 12, 14, 15};
    // should return [1, 2, 9, 10, 12] (an array).
    commonElements(array1B, array2B);

    int[] array1C = {0, 1, 2, 3, 4, 5};
    int[] array2C = {6, 7, 8, 9, 10, 11};
    //  should return [] (an empty array).
    commonElements(array1C, array2C);
  }

  // Implement your solution below.
  // NOTE: Remember to return an Integer array, not an int array.
  public static Integer[] commonElements(int[] array1, int[] array2) {
    int i = 0;
    int j = 0;

    List<Integer> commonElements = new ArrayList<>();

    while (i < array1.length && j < array2.length) {
      int elem1 = array1[i];
      int elem2 = array2[j];
      if (elem1 == elem2) {
        commonElements.add(array1[i]);
        i++;
        j++;
      } else if (elem1 > elem2) {
        j++;
      } else {
        i++;
      }
    }

    Integer[] resultInArray = new Integer[commonElements.size()];
    commonElements.toArray(resultInArray);
    System.out.println(commonElements);
    return resultInArray;
  }
}
