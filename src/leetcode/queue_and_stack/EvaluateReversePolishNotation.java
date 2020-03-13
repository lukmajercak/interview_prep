package leetcode.queue_and_stack;

import java.util.Stack;

public class EvaluateReversePolishNotation {

  /**
   * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
   *
   * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
   *
   * Note:
   *
   * Division between two integers should truncate toward zero.
   * The given RPN expression is always valid. That means the expression would always
   * evaluate to a result and there won't be any divide by zero operation.
   */
  public static void main(String[] args) {
    EvaluateReversePolishNotation solution = new EvaluateReversePolishNotation();

    // Example 1:
    //Input: ["2", "1", "+", "3", "*"]
    //Output: 9
    //Explanation: ((2 + 1) * 3) = 9
    String[] input = new String[]{"2", "1", "+", "3", "*"};
    System.out.println(solution.evalRPN(input));
    System.out.println(solution.evalRPNStack(input));

    // Example 2:
    //Input: ["4", "13", "5", "/", "+"]
    //Output: 6
    //Explanation: (4 + (13 / 5)) = 6
    input = new String[]{"4", "13", "5", "/", "+"};
    System.out.println(solution.evalRPN(input));
    System.out.println(solution.evalRPNStack(input));

    //Example 3:
    //Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
    //Output: 22
    //Explanation:
    //  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
    //= ((10 * (6 / (12 * -11))) + 17) + 5
    //= ((10 * (6 / -132)) + 17) + 5
    //= ((10 * 0) + 17) + 5
    //= (0 + 17) + 5
    //= 17 + 5
    //= 22
    input = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
    System.out.println(solution.evalRPN(input));
    System.out.println(solution.evalRPNStack(input));
  }

  public int evalRPN(String[] tokens) {
    return evalRPN(tokens, tokens.length - 1, new LastIndex(tokens.length - 1));
  }

  static class LastIndex {
    int i;

    LastIndex(int i) {
      this.i = i;
    }
  }

  public int evalRPN(String[] tokens, int i, LastIndex lastIndex) {
    if (!isSign(tokens[i])) {
      lastIndex.i = i;
      return Integer.valueOf(tokens[i]);
    }
    char c = tokens[i].charAt(0);

    int right = evalRPN(tokens, i - 1, lastIndex);
    int left = evalRPN(tokens, lastIndex.i - 1, lastIndex);

    if (c == '+') {
      return left + right;
    }
    if (c == '*') {
      return left * right;
    }
    if (c == '-') {
      return left - right;
    }
    if (c == '/') {
      return left / right;
    }

    return -1;
  }

  boolean isSign(String s) {
    char c = s.charAt(0);
    return s.length() == 1 && (c == '*' || c == '+' || c == '-' || c == '/');
  }

  public int evalRPNStack(String[] tokens) {
    Stack<Integer> results = new Stack<>();

    for (String token : tokens) {
      if (!isSign(token)) {
        results.push(Integer.valueOf(token));
      } else {
        int right = results.pop();
        int left = results.pop();
        int result = 0;

        char c = token.charAt(0);
        if (c == '+') {
          result = left + right;
        }
        if (c == '*') {
          result = left * right;
        }
        if (c == '-') {
          result = left - right;
        }
        if (c == '/') {
          result = left / right;
        }

        results.push(result);
      }
    }
    return results.peek();
  }
}
