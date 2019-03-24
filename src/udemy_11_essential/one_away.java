package udemy_11_essential;

public class one_away {

  public static void main(String[] args) {
    // NOTE: The following input values will be used for testing your solution.
    isOneAway2("abcde", "abcd");  // should return true
    isOneAway2("abde", "abcde");  // should return true
    isOneAway2("a", "a");  // should return true
    isOneAway2("abcdef", "abqdef");  // should return true
    isOneAway2("abcdef", "abccef");  // should return true
    isOneAway2("abcdef", "abcde");  // should return true
    isOneAway2("aaa", "abc");  // should return false
    isOneAway2("abcde", "abc");  // should return false
    isOneAway2("abc", "abcde");  // should return false
    isOneAway2("abc", "bcc");  // should return false
  }

  public static Boolean isOneAway2(String s1, String s2) {
    boolean ret = isOneAway(s1, s2);
    System.out.println(ret);
    return ret;
  }

  // Implement your solution below.
  public static Boolean isOneAway(String s1, String s2) {
    int lengthS1 = s1.length();
    int lengthS2 = s2.length();

    if (Math.abs(lengthS1 - lengthS2) > 1) {
      return false;
    }

    if (lengthS1 == lengthS2) {
      // replace
      return differsInOneChar(s1, s2, true);
    } else if (lengthS1 < lengthS2) {
      // add
      return differsInOneChar(s1, s2, false);
    }
    // remove
    return differsInOneChar(s2, s1, false);
  }

  // go through s1 and s2 simultaneously
  // only 1 character is allowed to be different
  static Boolean differsInOneChar(String s1, String s2, boolean canReplace) {
    int i = 0;
    int j = 0;
    boolean charsDiffered = false;

    while (i < s1.length() && j < s2.length()) {
      boolean charsSame = s1.charAt(i) == s2.charAt(j);

      if (!charsSame) {
        if (charsDiffered) {
          return false;
        }
        charsDiffered = true;
      }
      if (charsSame || canReplace) {
        i++;
      }
      j++;
    }
    return true;
  }
}
