package leetcode.google;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

  /**
   * Given a string, find the length of the longest substring without repeating characters.
   */
  public static void main(String[] args) {
    LongestSubstringWithoutRepeatingCharacters solution =
        new LongestSubstringWithoutRepeatingCharacters();

    //Input: "abcabcbb"
    //Output: 3
    //Explanation: The answer is "abc", with the length of 3.
    System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));

    //Input: "bbbbb"
    //Output: 1
    //Explanation: The answer is "b", with the length of 1.
    System.out.println(solution.lengthOfLongestSubstring("bbbbb"));

    //Input: "pwwkew"
    //Output: 3
    //Explanation: The answer is "wke", with the length of 3.
    //             Note that the answer must be a substring,
    //             "pwke" is a subsequence and not a substring.
    System.out.println(solution.lengthOfLongestSubstring("pwwkew"));

    System.out.println(solution.lengthOfLongestSubstring(""));
    System.out.println(solution.lengthOfLongestSubstring("abcdefgh"));

    // 2
    System.out.println(solution.lengthOfLongestSubstring("abba"));
  }

  public int lengthOfLongestSubstring(String s) {
    int maxLength = 0;
    Set<Character> seenChars = new HashSet<>();
    Queue<Character> seen = new LinkedList<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (seenChars.contains(c)) {
        while (seen.peek() != c) {
          seenChars.remove(seen.remove());
        }
        seen.remove();
      }
      seen.offer(c);
      seenChars.add(c);

      maxLength = Math.max(maxLength, seen.size());
    }

    return maxLength;
  }
}
