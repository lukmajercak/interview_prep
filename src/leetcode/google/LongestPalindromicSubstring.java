package leetcode.google;

public class LongestPalindromicSubstring {

  /**
   * Given a string s, find the longest palindromic substring in s.
   * You may assume that the maximum length of s is 1000.
   */
  public static void main(String[] args) {
    LongestPalindromicSubstring solution =
        new LongestPalindromicSubstring();

    // Input: "babad"
    // Output: "bab"
    // Note: "aba" is also a valid answer.
    System.out.println(solution.longestPalindrome("babad"));
    System.out.println(solution.longestPalindrome("babab"));

    // Input: "cbbd"
    // Output: "bb"
    System.out.println(solution.longestPalindrome("cbbd"));

    System.out.println(solution.longestPalindrome(""));
  }

  public String longestPalindrome(String s) {
    int n = s.length();

    boolean[][] palindromes = new boolean[n][n];

    int maxLength = 1;

    // All strings of 1 character are palindromes
    for (int i = 0; i < n; i++) {
      palindromes[i][i] = true;
    }

    int start = 0;

    // Substrings of length 2
    for (int i = 0; i < n - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        palindromes[i][i+1] = true;
        start = i;
        maxLength = 2;
      }
    }

    // Check substrings of length >= 3
    for (int k = 3; k <= n; k++) {
      // Start from beginning
      for (int i = 0; i < n - k + 1; i++) {
        // Ending index of i + k
        int j = i + k - 1;

        if (palindromes[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
          palindromes[i][j] = true;
          if (k > maxLength) {
            start = i;
            maxLength = k;
          }
        }
      }
    }

    return s.substring(start, Math.min(n, start + maxLength));
  }
}
