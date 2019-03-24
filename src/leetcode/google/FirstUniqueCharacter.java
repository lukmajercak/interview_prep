package leetcode.google;

public class FirstUniqueCharacter {

  /**
   * Given a string, find the first non-repeating character in it and return it's index.
   * If it doesn't exist, return -1.
   */
  public static void main(String[] args) {
    FirstUniqueCharacter solution = new FirstUniqueCharacter();
    // Examples:
    //
    //s = "leetcode"
    //return 0.
    System.out.println(solution.firstUniqChar("leetcode"));

    //s = "loveleetcode",
    //return 2.
    System.out.println(solution.firstUniqChar("loveleetcode"));
    System.out.println(solution.firstUniqChar("z"));
    System.out.println(solution.firstUniqChar(""));
  }

  public int firstUniqChar(String s) {
    int[] occurences = new int['z' - 'a' + 1];

    for (int i = 0; i < s.length(); i++) {
      occurences[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < s.length(); i++) {
      if (occurences[s.charAt(i) - 'a'] == 1) {
        return i;
      }
    }
    return -1;
  }
}
