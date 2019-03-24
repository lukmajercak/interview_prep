package udemy_11_essential;

import java.util.ArrayList;
import java.util.List;

public class is_rotation {

  public static void main(String[] args) {
    // NOTE: The following input values will be used for testing your solution.
    int[] array1 = {1, 2, 3, 4, 5, 6, 7};
    int[] array2a = {4, 5, 6, 7, 8, 1, 2, 3};
    //  should return false.
    System.out.println(isRotation(array1, array2a));

    int[] array2b = {4, 5, 6, 7, 1, 2, 3};
    //  should return true.
    System.out.println(isRotation(array1, array2b));

    int[] array2c = {4, 5, 6, 9, 1, 2, 3};
    //  should return false.
    System.out.println(isRotation(array1, array2c));

    int[] array2d = {4, 6, 5, 7, 1, 2, 3};
    // ) should return false.
    System.out.println(isRotation(array1, array2d));

    int[] array2e = {4, 5, 6, 7, 0, 2, 3};
    //  should return false.
    System.out.println(isRotation(array1, array2e));

    int[] array2f = {1, 2, 3, 4, 5, 6, 7};
    //  should return true.
    System.out.println(isRotation(array1, array2f));
  }

  // Implement your solution below.
  public static Boolean isRotation(int[] array1, int[] array2) {
    if (array1.length != array2.length) {
      return false;
    }

    int j = 0;
    for (int number : array2) {
      if (number == array1[0]) {
        break;
      }
      j++;
    }

    if (j == array2.length) {
      return false;
    }

    for (int i = 0; i < array1.length; i++) {
      if (array1[i] != array2[j]) {
        return false;
      }
      j = (j + 1) % array2.length;
    }
    return true;
  }
}
