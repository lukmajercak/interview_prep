package daily;

public class PickNumberUsingBitOperations {

  /**
   * Given three 32-bit integers x, y, and b, return x if b is 1 and y if b is 0,
   * using only mathematical or bit operations. You can assume b can only be 1 or 0.
   *
   * We will be sending the solution tomorrow, along with tomorrow's question.
   * As always, feel free to shoot us an email if there's anything we can help with.
   */
  public static void main(String[] args) {
    System.out.println(pick(2, 3, 1));
    System.out.println(pick(2, 3, 0));
    System.out.println(pick(432, 55232, 1));
    System.out.println(pick(432, 55232, 0));
  }

  static int pick(int x, int y, int b) {
    return (x * b) + (y * (1 - b));
  }
}
