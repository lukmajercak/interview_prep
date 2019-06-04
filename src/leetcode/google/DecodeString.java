package leetcode.google;

public class DecodeString {

  /**
   * Given an encoded string, return it's decoded string.
   *
   * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets
   * is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
   *
   * You may assume that the input string is always valid; No extra white spaces,
   * square brackets are well-formed, etc.
   *
   * Furthermore, you may assume that the original data does not contain any digits
   * and that digits are only for those repeat numbers, k.
   * For example, there won't be input like 3a or 2[4].
   */
  public static void main(String[] args) {
    DecodeString solution = new DecodeString();

    // s = "abc", return "abc".
    System.out.println(solution.decodeString("abc"));

    // s = "3[a]", return "aaa".
    System.out.println(solution.decodeString("3[a]"));

    // s = "3[a]2[bc]", return "aaabcbc".
    System.out.println(solution.decodeString("3[a]2[bc]"));

    // s = "3[a2[c]]", return "accaccacc".
    System.out.println(solution.decodeString("3[a2[c]]"));

    // s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
    System.out.println(solution.decodeString("2[abc]3[cd]ef"));
  }

  public String decodeString(String s) {
    StringBuilder result = new StringBuilder();
    decodeString(s, 0, result);
    return result.toString();
  }

  public int decodeString(String s, int i, StringBuilder soFar) {
    if (i >= s.length()) {
      return i;
    }
    char c = s.charAt(i);
    if (c == ']') {
      return i;
    }
    if (!Character.isDigit(c)) {
      soFar.append(c);
      return decodeString(s, i + 1, soFar);
    }
    int start = i;
    while (s.charAt(i++) != '[') {}
    int multiplier = Integer.parseInt(s.substring(start, i - 1));

    StringBuilder toRepeat = new StringBuilder();
    int end = decodeString(s, i, toRepeat);
    while (multiplier-- > 0) {
      soFar.append(toRepeat);
    }

   return decodeString(s, end + 1, soFar);
  }
}
