package daily;

public class MinParenthesesToRemove {


  /**
   * This problem was asked by Google.
   *
   * Given a string of parentheses, write a function to compute the minimum number of parentheses
   * to be removed to make the string valid (i.e. each open parenthesis is eventually closed).
   *
   * For example, given the string "()())()", you should return 1. Given the string ")(",
   * you should return 2, since we must remove all of them.
   *
   * We will be sending the solution tomorrow, along with tomorrow's question. As always,
   * feel free to shoot us an email if there's anything we can help with.
   *
   *
   *
   * TODO:
   * https://leetcode.com/problems/remove-invalid-parentheses/
   */
  public static void main(String[] args) {
    System.out.println(minToRemove("()())()")); // 1
    System.out.println(minToRemove(")("));      // 2
    System.out.println(minToRemove("((("));     // 3
    System.out.println(minToRemove("(()("));    // 2
    System.out.println(minToRemove("(())"));    // 0
  }


  // keep number of "("  = numOpen
  // if ) seen:
  //      if numOpen = 0
  //          numToRemove++
  //      else
  //         numOpen--
  //return numToRemove + numOpen
  static int minToRemove(String s) {
    int numOpen = 0;
    int numToRemove = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        numOpen++;
      } else if (c == ')') {
        if (numOpen > 0) {
          numOpen--;
        } else {
          numToRemove++;
        }
      }
    }

    return numOpen + numToRemove;
  }

  //   "()())()"
}
