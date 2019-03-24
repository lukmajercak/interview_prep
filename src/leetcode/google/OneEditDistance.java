package leetcode.google;

public class OneEditDistance {

  /**
   * Given two strings s and t, determine if they are both one edit distance apart.
   *
   * Note:
   *
   * There are 3 possiblities to satisify one edit distance apart:
   *
   * Insert a character into s to get t
   * Delete a character from s to get t
   * Replace a character of s to get t
   */
  public static void main(String[] args) {
    OneEditDistance solution = new OneEditDistance();

    // Example 1:
    //
    //Input: s = "ab", t = "acb"
    //Output: true
    //Explanation: We can insert 'c' into s to get t.
    System.out.println(solution.isOneEditDistance("ab", "acb"));

    // Example 2:
    //
    //Input: s = "cab", t = "ad"
    //Output: false
    //Explanation: We cannot get t from s by only one step.
    System.out.println(solution.isOneEditDistance("cab", "ad"));

    // Example 3:
    //
    //Input: s = "1203", t = "1213"
    //Output: true
    //Explanation: We can replace '0' with '1' to get t.
    System.out.println(solution.isOneEditDistance("1203", "1213"));

    // Example 4:
    //
    //Input: s = "a", t = ""
    //Output: true
    //Explanation: We can replace '0' with '1' to get t.
    System.out.println(solution.isOneEditDistance("a", ""));

    // Example 5:
    //
    //Input: s = "ab", t = "cab"
    //Output: true
    //Explanation: We can replace '0' with '1' to get t.
    System.out.println(solution.isOneEditDistance("ab", "cab"));
  }

  public boolean isOneEditDistance(String s, String t) {
    int sLen = s.length();
    int tLen = t.length();

    if (sLen == tLen) {
      return checkReplace(s, t);
    }
    if (Math.abs(sLen - tLen) > 1) {
      return false;
    }
    if (sLen > tLen) {
      return checkAdd(t, s);
    }
    return checkAdd(s, t);
  }

  boolean checkReplace(String a, String b) {
    boolean foundDiff = false;
    for (int i = 0; i < a.length(); i++) {
      boolean match = a.charAt(i) == b.charAt(i);
      if (!match && foundDiff) {
        return false;
      }
      foundDiff |= !match;
    }
    return foundDiff;
  }

  boolean checkAdd(String a, String b) {
    boolean added = false;
    int bIndex = 0;
    int aIndex = 0;

    while (aIndex < a.length() && bIndex < b.length()) {
      boolean match = a.charAt(aIndex) == b.charAt(bIndex);
      if (!match && added) {
        return false;
      }
      if (match) {
        aIndex++;
      }
      added |= !match;
      bIndex++;
    }
    return added || (!added && aIndex == a.length());
  }
}
