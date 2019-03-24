package leetcode.google;

public class ValidPalindrome {

  /**
   * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
   *
   * Note: For the purpose of this problem, we define empty string as valid palindrome.
   */
  public static void main(String[] args) {
    ValidPalindrome solution = new ValidPalindrome();
    // Example 1:
    //
    //Input: "A man, a plan, a canal: Panama"
    //Output: true
    System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));

    // Example 2:
    //
    //Input: "race a car"
    //Output: false
    System.out.println(solution.isPalindrome("race a car"));
  }

  public boolean isPalindrome(String s) {
    int start = 0;
    int end = s.length() - 1;

    while (start <= end) {
      char cStart = s.charAt(start);
      char cEnd = s.charAt(end);

      if (!isValid(cStart)) {
        start++;
        continue;
      }
      if (!isValid(cEnd)) {
        end--;
        continue;
      }
      if (!(Character.toLowerCase(cStart) == Character.toLowerCase(cEnd))) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }

  boolean isValid(char c) {
    return Character.isDigit(c) || Character.isLetter(c);
  }
}
