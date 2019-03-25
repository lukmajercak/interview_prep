package daily;

public class RegExpMatching {

  /**
   * This problem was asked by Facebook.
   *
   * Implement regular expression matching with the following special characters:
   *
   * . (period) which matches any single character
   * * (asterisk) which matches zero or more of the preceding element
   * That is, implement a function that takes in a string and a valid regular
   * expression and returns whether or not the string matches the regular expression.
   *
   * For example, given the regular expression "ra." and the string "ray", your function should return true.
   * The same regular expression on the string "raymond" should return false.
   *
   * Given the regular expression ".*at" and the string "chat", your function should return true.
   * The same regular expression on the string "chats" should return false.
   */
  public static void main(String[] args) {
    RegExpMatching solution = new RegExpMatching();

    System.out.println(solution.matches("ra.", "ray"));     // true
    System.out.println(solution.matches("ra.", "raymond")); // false

    System.out.println(solution.matches(".*at", "chat"));   // true
    System.out.println(solution.matches(".*at", "chats"));  // false

    System.out.println(solution.matches("r*t", "t"));       // true
    System.out.println(solution.matches("r*t", "rt"));      // true
    System.out.println(solution.matches("r*t", "rrrrrt"));  // true

    System.out.println(solution.matches("r*rt", "t"));      // false
    System.out.println(solution.matches("r*rt", "rt"));     // true
    System.out.println(solution.matches("r*rt", "rrrt"));   // true

    // Example 1:
    //Input:
    //s = "aa"
    //p = "a"
    //Output: false
    //Explanation: "a" does not match the entire string "aa".
    System.out.println(solution.matches("a", "aa"));

    //Example 2:
    //Input:
    //s = "aa"
    //p = "a*"
    //Output: true
    //Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
    System.out.println(solution.matches("a*", "aa"));

    //Example 3:
    //Input:
    //s = "ab"
    //p = ".*"
    //Output: true
    //Explanation: ".*" means "zero or more (*) of any character (.)".
    System.out.println(solution.matches(".*", "ab"));

    //Example 4:
    //Input:
    //s = "aab"
    //p = "c*a*b"
    //Output: true
    //Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
    System.out.println(solution.matches("c*a*b", "aab"));

    //Example 5:
    //Input:
    //s = "a"
    //p = "ab*"
    //Output: true
    System.out.println(solution.matches("ab*", "a"));

    //Example 6:
    //Input:
    //s = "a"
    //p = "ab*a"
    //Output: false
    System.out.println(solution.matches("ab*a", "a"));

    //Example 7:
    //Input:
    //s = "ab"
    //p = ".*c"
    //Output: false
    System.out.println(solution.matches(".*c", "ab"));

    //Example 8:
    //Input:
    //s = ""
    //p = "c*c*"
    //Output: true
    System.out.println(solution.matches("c*c*", ""));

    //Example 9:
    //Input:
    //s = "a"
    //p = ".*..a*"
    //Output: false
    System.out.println(solution.matches(".*..a*", "a"));
  }


  boolean matches(String regexp, String s) {
    return matches(regexp, s, 0, 0);
  }

  boolean matches(String regexp, String s, int regexpIndex, int sIndex) {
    if (sIndex == s.length() && regexpIndex == regexp.length()) {
      return true;
    }
    if (sIndex >= s.length()) {
      while (regexpIndex < regexp.length() && (regexp.charAt(regexpIndex) == '*' ||
          regexpIndex < regexp.length() - 1 && regexp.charAt(regexpIndex + 1) == '*')) {
        regexpIndex++;
      }
      return regexpIndex == regexp.length() && regexp.charAt(regexpIndex - 1) == '*';
    }
    if (regexpIndex >= regexp.length()) {
      return false;
    }

    char regexpChar = regexp.charAt(regexpIndex);
    char sChar = s.charAt(sIndex);

    if (regexpIndex < regexp.length() - 1) {
      char regexpCharAfter = regexp.charAt(regexpIndex + 1);
      if (regexpCharAfter == '*') {
        int nextNonAsterisk = regexpIndex + 1;
        while (nextNonAsterisk < regexp.length() && regexp.charAt(nextNonAsterisk) == '*') {
          nextNonAsterisk++;
        }
        if (matches(regexp, s, nextNonAsterisk, sIndex)) {
          return true;
        }
        while (sIndex < s.length() && match(regexpChar, s.charAt(sIndex))) {
          sIndex++;
          if (matches(regexp, s, nextNonAsterisk, sIndex)) {
            return true;
          }
        }
        return false;
      }
    }

    if (match(regexpChar, sChar)) {
      return matches(regexp, s, regexpIndex + 1, sIndex + 1);
    }
    return false;
  }

  boolean match(char regChar, char sChar) {
    return regChar == '.' || regChar == sChar;
  }
}
