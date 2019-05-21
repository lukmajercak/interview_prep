package leetcode.google;

public class ValidAnagram {

  /**
   * Given two strings s and t , write a function to determine if t is an anagram of s.
   */
  public static void main(String[] args) {
    ValidAnagram solution = new ValidAnagram();

    // Input: s = "anagram", t = "nagaram"
    // Output: true
    System.out.println(solution.isAnagram("anagram", "nagaram"));

    // Input: s = "rat", t = "car"
    // Output: false
    System.out.println(solution.isAnagram("rat", "car"));

    // Input: s = "zlap", t = "kcqx"
    // Output: false
    System.out.println(solution.isAnagram("zlap", "kcqx"));

    // Input: s = "aacc", t = "ccac"
    // Output: false
    System.out.println(solution.isAnagram("aacc", "ccac"));
  }

  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    int[] counts = new int['z' - 'a' + 1];

    for (int i = 0; i < s.length(); i++) {
      counts[s.charAt(i) - 'a']++;
    }

    for (int i = 0; i < t.length(); i++) {
      if (counts[t.charAt(i) - 'a']-- < 1) {
        return false;
      }
    }
    return true;
  }
}
