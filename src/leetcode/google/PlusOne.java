package leetcode.google;

import java.util.Arrays;

public class PlusOne {

  /**
   * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
   *
   * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
   *
   * You may assume the integer does not contain any leading zero, except the number 0 itself.
   */
  public static void main(String[] args) {
    PlusOne solution = new PlusOne();

    // Example 1:
    //
    //Input: [1,2,3]
    //Output: [1,2,4]
    //Explanation: The array represents the integer 123.
    int[] digits = new int[]{1, 2, 3};
    System.out.println(Arrays.toString(solution.plusOne(digits)));

    // Example 2:
    //
    //Input: [4,3,2,1]
    //Output: [4,3,2,2]
    //Explanation: The array represents the integer 4321.
    digits = new int[]{4, 3, 2, 1};
    System.out.println(Arrays.toString(solution.plusOne(digits)));

    // Example 3:
    //
    //Input: [2, 9, 9]
    //Output: [3, 0 , 0]
    //Explanation: The array represents the integer 4321.
    digits = new int[]{2, 9, 9};
    System.out.println(Arrays.toString(solution.plusOne(digits)));

    // Example 4:
    //
    //Input: [9, 9, 9]
    //Output: [1, 0, 0 , 0]
    //Explanation: The array represents the integer 4321.
    digits = new int[]{9, 9, 9};
    System.out.println(Arrays.toString(solution.plusOne(digits)));

    // Example 5:
    //
    //Input: [0]
    //Output: [1]
    //Explanation: The array represents the integer 4321.
    digits = new int[]{0};
    System.out.println(Arrays.toString(solution.plusOne(digits)));

    // Example 6:
    //
    //Input: [1,0,0,0,0]
    //Output: [1,0,0,0,1]
    //Explanation: The array represents the integer 4321.
    digits = new int[]{1,0,0,0,0};
    System.out.println(Arrays.toString(solution.plusOne(digits)));
  }

  public int[] plusOne(int[] digits) {
    int[] output = new int[digits.length];
    if (output.length == 0) {
      return output;
    }

    DigitAndCarry digitAndCarry = getDigitAndCarry(digits[digits.length - 1], 1);
    output[digits.length - 1] = digitAndCarry.digit;

    for (int i = digits.length - 2; i >= 0; i--) {
      digitAndCarry = getDigitAndCarry(digits[i], digitAndCarry.carry);
      output[i] = digitAndCarry.digit;
    }

    if (digitAndCarry.carry == 1) {
      int[] outputCopy = new int[output.length + 1];
      outputCopy[0] = 1;
      System.arraycopy(output, 0, outputCopy, 1, output.length);
      return outputCopy;
    }

    return output;
  }

  DigitAndCarry getDigitAndCarry(int digit, int carry) {
    int newDigit = (digit + carry) % 10;
    int newCarry = digit == 9 && carry == 1 ? 1 : 0;
    return new DigitAndCarry(newDigit, newCarry);
  }

  static class DigitAndCarry {
    int digit;
    int carry;

    DigitAndCarry(int digit, int carry) {
      this.digit = digit;
      this.carry = carry;
    }
  }
}
