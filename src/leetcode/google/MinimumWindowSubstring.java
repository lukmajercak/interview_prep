package leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

  /**
   * Given a string S and a string T, find the minimum window in S which will contain all the
   * characters in T in complexity O(n).
   *
   * Note:
   * If there is no such window in S that covers all characters in T, return the empty string "".
   * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
   */
  public static void main(String[] args) {
    MinimumWindowSubstring solution = new MinimumWindowSubstring();

    // Example:
    //
    //Input: S = "ADOBECODEBANC", T = "ABC"
    //Output: "BANC"
    System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    System.out.println(solution.minWindow("ADOBECODEBANC", "DCO"));
    System.out.println(solution.minWindow("ADOBECODEBANC", "OCD"));
    System.out.println(solution.minWindow("A", "AA"));
    System.out.println(solution.minWindow("cabwefgewcwaefgcf", "cae")); //"cwae"
  }

  public String minWindow(String s, String t) {
    Map<Character, Integer> occurrences = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      Integer thisOccurrences = occurrences.get(t.charAt(i));
      if (thisOccurrences == null) {
        thisOccurrences = 1;
      }
      thisOccurrences--;
      occurrences.put(t.charAt(i), thisOccurrences);
    }
    int minWindowLength = Integer.MAX_VALUE;
    Integer windowLeft = null;

    int missing = occurrences.size();
    int left = 0;
    int right = 0;

    while (right < s.length()) {

      // move right side of the sliding window until we match everything
      while (missing > 0 && right < s.length()) {
        char rightChar = s.charAt(right);
        Integer seenThisChar = occurrences.get(rightChar);
        if (seenThisChar == null) {
          right++;
          continue;
        }
        if (seenThisChar == 0) {
          missing--;
        }
        occurrences.put(rightChar, seenThisChar + 1);
        right++;
      }

      // shrink sliding window from the left while we are matching everything
      while (missing == 0 && left < right) {
        if (right - left < minWindowLength) {
          windowLeft = left;
          minWindowLength = right - left;
        }
        char leftChar = s.charAt(left);
        Integer seenThisChar = occurrences.get(leftChar);
        if (seenThisChar == null) {
          left++;
          continue;
        }
        if (seenThisChar == 1) {
          missing++;
        }
        occurrences.put(leftChar, seenThisChar - 1);
        left++;
      }
    }
    if (windowLeft == null) {
      return "";
    }
    return s.substring(windowLeft, windowLeft + minWindowLength);
  }
}
