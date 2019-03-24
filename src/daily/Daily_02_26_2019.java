package daily;

import java.util.HashMap;
import java.util.Map;

public class Daily_02_26_2019 {

  /**
   * This problem was asked by Facebook.
   *
   * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
   *
   * For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
   *
   * You can assume that the messages are decodable. For example, '001' is not allowed.
   */
  public static void main(String[] args) {
    System.out.println(numWaysEncoded("01"));
    System.out.println(numWaysEncoded("0"));
    System.out.println(numWaysEncoded("00"));
    System.out.println(numWaysEncoded("111")); // aaa, ka, ak
    System.out.println(numWaysEncoded("261")); // bfa, za
    System.out.println(numWaysEncoded("2")); // b
    System.out.println(numWaysEncoded("26")); // bf, z
    System.out.println(numWaysEncoded("15263"));
    System.out.println(numWaysEncoded(
        "152632516832919834813112321111121221131211313113"));
  }

  static int numWaysEncoded(String message) {
    return numWaysEncoded(message, 0, new HashMap<>());
  }

  static int numWaysEncoded(String message, int i, Map<Integer, Integer> memo) {
    Integer memoized = memo.get(i);
    if (memoized != null) {
      return memoized;
    }

    if (i == message.length()) {
      memo.put(i, 1);
      return 1;
    }

    int thisChar = Character.getNumericValue(message.charAt(i));
    if (i == message.length() - 1) {
      int numWays = isValidNumber(thisChar) ? 1 : 0;
      memo.put(i, numWays);
      return numWays;
    }

    int numWays = 0;
    if (i < message.length() - 1) {
      int nextChar = Character.getNumericValue(message.charAt(i + 1));
      if (isValidNumber(thisChar) && isValidNumber(10 * thisChar + nextChar)) {
        numWays += numWaysEncoded(message, i + 2, memo);
      }
    }

    if (isValidNumber(thisChar)) {
      numWays += numWaysEncoded(message, i + 1, memo);
    }

    memo.put(i, numWays);
    return numWays;
  }

  static boolean isValidNumber(int n) {
    return n >= 1 && n <= 26;
  }
}
