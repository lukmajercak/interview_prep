package leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtmostKDistinct {

  /**
   * Given a string, find the length of the longest substring T that contains at most k distinct characters.
   */
  public static void main(String[] args) {
    LongestSubstringWithAtmostKDistinct solution =
        new LongestSubstringWithAtmostKDistinct();

    // Example 1:
    //
    //Input: s = "eceba", k = 2
    //Output: 3
    //Explanation: T is "ece" which its length is 3.
    System.out.println(solution.lengthOfLongestSubstringKDistinct("eceba", 2));

    // Example 2:
    //
    //Input: s = "aa", k = 1
    //Output: 2
    //Explanation: T is "aa" which its length is 2.
    System.out.println(solution.lengthOfLongestSubstringKDistinct("aa", 1));
  }

  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int start = 0;
    int end = 0;

    Integer maxLength = 0;

    HashMap<Character, Integer> numOccurrences = new HashMap<>();

    while (end < s.length()) {
      char next = s.charAt(end);
      numOccurrences.put(next, numOccurrences.getOrDefault(next, 0) + 1);

      while (numOccurrences.size() > k) {
        char previous = s.charAt(start);
        Integer occurrencesPrevious = numOccurrences.get(previous);
        if (occurrencesPrevious == 1) {
          numOccurrences.remove(previous);
        } else {
          numOccurrences.put(previous, occurrencesPrevious - 1);
        }
        start++;
      }

      maxLength = Math.max(maxLength, end - start + 1);
      end++;
    }
    return maxLength;
  }
}
