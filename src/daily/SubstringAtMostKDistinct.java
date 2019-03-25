package daily;

import java.util.HashSet;
import java.util.Set;

public class SubstringAtMostKDistinct {

  /**
   * This problem was asked by Amazon.
   *
   * Given an integer k and a string s, find the length of the longest substring
   * that contains at most k distinct characters.
   *
   * For example, given s = "abcba" and k = 2,
   * the longest substring with k distinct characters is "bcb".
   *
   * a: 0, 4
   * b: 1, 3
   * c: 2
   *
   */
  public static void main(String[] args) {
    System.out.println(longestSubstring("abcba", 1));
    System.out.println(longestSubstring2("abcba", 1));
    System.out.println(longestSubstring("abcba", 2));
    System.out.println(longestSubstring2("abcba", 2));
    System.out.println(longestSubstring("abcba", 3));
    System.out.println(longestSubstring2("abcba", 3));

    System.out.println(longestSubstring("abcbdcadba", 3));
    System.out.println(longestSubstring2("abcbdcadba", 3));
    System.out.println(longestSubstring("abcbdcaaaadba", 2));
    System.out.println(longestSubstring2("abcbdcaaaadba", 2));
    System.out.println(longestSubstring("abcbdcaaaadba", 3));
    System.out.println(longestSubstring2("abcbdcaaaadba", 3));
  }

  static String longestSubstring(String s, int k) {
    return longestSubstring(s, k, new HashSet<>(k), 0, new StringBuilder());
  }

  static String longestSubstring(String s, int k, Set<Character> seenChars, int index, StringBuilder soFar) {
    if (index == s.length()) {
      return soFar.toString();
    }

    char thisChar = s.charAt(index);
    seenChars.add(thisChar);

    if (seenChars.size() == k + 1) {
      return soFar.toString();
    }

    soFar.append(thisChar);
    String withAdding = longestSubstring(s, k, seenChars, index + 1, soFar);
    String withoutAdding = longestSubstring(s, k, new HashSet<>(), index + 1, new StringBuilder());

    if (withAdding.length() > withoutAdding.length()) {
      return withAdding;
    }
    return withoutAdding;
  }

  static String longestSubstring2(String s, int k) {
    Range range = longestSubstring2(s, k, new HashSet<>(k), 0, 0);
    return s.substring(range.start, range.end);
  }

  static Range longestSubstring2(String s, int k, Set<Character> seenChars, int index, int startIndex) {
    if (index == s.length()) {
      return new Range(startIndex, s.length());
    }

    char thisChar = s.charAt(index);
    seenChars.add(thisChar);
    if (seenChars.size() == k + 1) {
      return new Range(startIndex, index);
    }

    Range withAdding = longestSubstring2(s, k, seenChars, index + 1, startIndex);
    Range withoutAdding = longestSubstring2(s, k, new HashSet<>(k), index + 1, index + 1);

    if (withAdding.length() > withoutAdding.length()) {
      return withAdding;
    }
    return withoutAdding;
  }

  static class Range {
    int start;
    int end;

    Range(int start, int end) {
      this.start = start;
      this.end = end;
    }

    int length() {
      return end - start;
    }
  }

}
