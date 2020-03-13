package leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

  /**
   * Given n pairs of parentheses, write a function to generate all combinations of well-formed
   * parentheses.
   */
  public static void main(String[] args) {
    GenerateParentheses solution = new GenerateParentheses();

    System.out.println(solution.generateParenthesis(0));
    System.out.println(solution.generateParenthesis(1));
    System.out.println(solution.generateParenthesis(2));

    // For example, given n = 3, a solution set is:
    //
    //[
    //  "((()))",
    //  "(()())",
    //  "(())()",
    //  "()(())",
    //  "()()()"
    //]
    System.out.println(solution.generateParenthesis(3));
  }


  public List<String> generateParenthesis(int n) {
    List<String> combinations = new ArrayList<>();
    generateParenthesis(n, 0, 0, new StringBuilder(), combinations);
    return combinations;
  }

  void generateParenthesis(
      int n, int openSoFar, int closedSoFar, StringBuilder soFar,
      List<String> combinations) {
    if (openSoFar == n && closedSoFar == n) {
      combinations.add(soFar.toString());
      return;
    }
    // Can open?
    if (openSoFar < n) {
      StringBuilder soFarCopy = new StringBuilder(soFar);
      soFarCopy.append('(');
      generateParenthesis(n, openSoFar + 1, closedSoFar, soFarCopy, combinations);
    }
    // Can close?
    if (openSoFar > closedSoFar) {
      soFar.append(')');
      generateParenthesis(n, openSoFar, closedSoFar + 1, soFar, combinations);
    }
  }
}
