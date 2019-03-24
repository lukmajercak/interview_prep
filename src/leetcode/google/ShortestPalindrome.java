package leetcode.google;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPalindrome {

  /**
   * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it.
   * Find and return the shortest palindrome you can find by performing this transformation.
   */
  public static void main(String[] args) {
    ShortestPalindrome solution = new ShortestPalindrome();

    // Example 1:
    //
    //Input: "aacecaaa"
    //Output: "aaacecaaa"
    System.out.println(solution.shortestPalindrome("aacecaaa"));

    // Example 2:
    //
    //Input: "abcd"
    //Output: "dcbabcd"
    System.out.println(solution.shortestPalindrome("abcd"));

    System.out.println(solution.shortestPalindrome("aabba")); // "abbaabba"

    System.out.println(solution.shortestPalindrome("a")); // "a"
    System.out.println(solution.shortestPalindrome("aa")); // "aa"
    System.out.println(solution.shortestPalindrome("aaa")); // "aaa"

    System.out.println(solution.shortestPalindrome("abb")); // "bbabb"

    System.out.println(solution.shortestPalindrome("adcba")); // "abcdadcba"
  }

  public String shortestPalindrome(String s) {
    // rev_s + s
    String reversed = new StringBuilder(s).reverse().toString();

    // TODO
    // abcd
    // dcba
    int i = 0;
    while (s.charAt(i) == reversed.charAt(i)) {
      i++;
    }
    // find suffix(rev_s) which is longest_prefix(s)
    // remove the longest suffix from rev_s
    //return s + reversed.substring
    return null;
  }
}
