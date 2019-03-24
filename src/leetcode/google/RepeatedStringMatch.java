package leetcode.google;

public class RepeatedStringMatch {

  /**
   * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it.
   * If no such solution, return -1.
   *
   * For example, with A = "abcd" and B = "cdabcdab".
   *
   * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it;
   * and B is not a substring of A repeated two times ("abcdabcd").
   *
   * Note:
   * The length of A and B will be between 1 and 10000.
   */
  public static void main(String[] args) {
    RepeatedStringMatch solution = new RepeatedStringMatch();

    System.out.println(solution.repeatedStringMatch("abcd", "cdabcdab"));
    System.out.println(solution.repeatedStringMatch("a", "aa"));
    System.out.println(solution.repeatedStringMatch("abc", "cabcabca"));
    System.out.println(solution.repeatedStringMatch("aaaaaaaaaaaaaaaaaaaaaab", "ba"));
  }

  // bcdabcdabcda cdabcdab
  public int repeatedStringMatch(String A, String B) {
    StringBuilder aStretched = new StringBuilder(A);
    while (aStretched.length() <= 2 * B.length()) {
      aStretched.append(A);
    }
    String a = aStretched.toString();
    int index = a.indexOf(B);
    if (index == -1) {
      index = new String(a + a).indexOf(B);
      if (index == -1) {
        return -1;
      }
    }
    int endIndex = index + B.length();
    int oneMore = endIndex % A.length() == 0 ? 0 : 1;
    return (endIndex / A.length()) + oneMore;
  }
}
