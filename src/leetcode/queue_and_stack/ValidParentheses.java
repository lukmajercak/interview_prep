package leetcode.queue_and_stack;

import java.util.Stack;

public class ValidParentheses {

  /**
   * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
   *
   * An input string is valid if:
   *
   * Open brackets must be closed by the same type of brackets.
   * Open brackets must be closed in the correct order.
   * Note that an empty string is also considered valid.
   *
   */
  public static void main(String[] args) {
    ValidParentheses solution = new ValidParentheses();

    // Example 1:
    //Input: "()"
    //Output: true
    System.out.println(solution.isValid("()"));

    //Example 2:
    //Input: "()[]{}"
    //Output: true
    System.out.println(solution.isValid("()[]{}"));

    //Example 3:
    //Input: "(]"
    //Output: false
    System.out.println(solution.isValid("(]"));

    //Example 4:
    //Input: "([)]"
    //Output: false
    System.out.println(solution.isValid("([)]"));

    //Example 5:
    //Input: "{[]}"
    //Output: true
    System.out.println(solution.isValid("{[]}"));

    // false
    System.out.println(solution.isValid("["));
  }

  public boolean isValid(String s) {
    Stack<Character> seen = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (isOpen(c)) {
        seen.push(c);
      } else if (isClose(c)) {
        if (seen.isEmpty()) {
          return false;
        }
        char prev = seen.pop();
        if (counter(c) != prev) {
          return false;
        }
      } else {
        return false;
      }
    }
    return seen.isEmpty();
  }

  boolean isOpen(char c) {
    return c == '{' || c == '[' || c == '(';
  }

  boolean isClose(char c) {
    return c == '}' || c == ']' || c == ')';
  }

  Character counter(char c) {
    switch(c) {
      case '}':
        return '{';
      case ']':
        return '[';
      case ')':
        return '(';
      default:
        return null;
    }
  }
}
