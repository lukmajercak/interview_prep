package leetcode.google;

public class ReverseInteger {

  /**
   * Given a 32-bit signed integer, reverse digits of an integer.
   */
  public static void main(String[] args) {
    ReverseInteger solution = new ReverseInteger();

    // Input: 123
    // Output: 321
    System.out.println(solution.reverse(123));

    // Input: -123
    // Output: -321
    System.out.println(solution.reverse(-123));

    // Input: 120
    // Output: 21
    System.out.println(solution.reverse(120));

    System.out.println(solution.reverse(1534236469));
    //                                  2147483647
  }

  public int reverse(int x) {
    int res = 0;
    int prev_res = 0;

    while (x != 0) {
      res *= 10;
      int remainder = x % 10;
      res += remainder;

      if ((res - remainder) / 10 != prev_res) {
        return 0;
      }

      x /= 10;

      prev_res = res;
    }

    return res;
  }
}
