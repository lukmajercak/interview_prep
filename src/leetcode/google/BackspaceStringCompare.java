package leetcode.google;

public class BackspaceStringCompare {

  /**
   * Given two strings S and T, return if they are equal when both are typed into empty text editors.
   * # means a backspace character.
   */
  public static void main(String[] args) {
    BackspaceStringCompare solution = new BackspaceStringCompare();

    // Input: S = "ab#c", T = "ad#c"
    // Output: true
    // Explanation: Both S and T become "ac".
    System.out.println(solution.backspaceCompare("ab#c", "ad#c"));
    System.out.println(solution.backspaceCompareConstantSpace("ab#c", "ad#c"));

    // Input: S = "ab##", T = "c#d#"
    // Output: true
    // Explanation: Both S and T become "".
    System.out.println(solution.backspaceCompare("ab##", "c#d#"));
    System.out.println(solution.backspaceCompareConstantSpace("ab##", "c#d#"));

    // Input: S = "a##c", T = "#a#c"
    // Output: true
    // Explanation: Both S and T become "c"
    System.out.println(solution.backspaceCompare("a##c", "#a#c"));
    System.out.println(solution.backspaceCompareConstantSpace("a##c", "#a#c"));

    // Input: S = "a#c", T = "b"
    // Output: false
    // Explanation: S becomes "c" while T becomes "b".
    System.out.println(solution.backspaceCompare("a#c", "b"));
    System.out.println(solution.backspaceCompareConstantSpace("a#c", "b"));
  }

  public boolean backspaceCompare(String S, String T) {
    String removeCharsS = removeChars(S);
    String removeCharsT = removeChars(T);
    return removeCharsS.equals(removeCharsT);
  }

  String removeChars(String s) {
    StringBuilder res = new StringBuilder();

    int numBackspaces = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      char c = s.charAt(i);
      if (c == '#') {
        numBackspaces++;
      } else {
        if (numBackspaces == 0) {
          res.append(c);
        } else {
          numBackspaces--;
        }
      }
    }
    return res.toString();
  }


  public boolean backspaceCompareConstantSpace(String S, String T) {
    int sIndex = S.length() - 1;
    int tIndex = T.length() - 1;

    int sBackspaces = 0;
    int tBackspaces = 0;

    while (sIndex >= 0 && tIndex >= 0) {
      char sChar = S.charAt(sIndex);
      char tChar = T.charAt(tIndex);

      if (sChar == '#') {
        sBackspaces++;
        sIndex--;
        continue;
      }
      if (tChar == '#') {
        tBackspaces++;
        tIndex--;
        continue;
      }
      if (sBackspaces > 0) {
        sBackspaces--;
        sIndex--;
        continue;
      }
      if (tBackspaces > 0) {
        tBackspaces--;
        tIndex--;
        continue;
      }

      if (sChar != tChar) {
        return false;
      }
      sIndex--;
      tIndex--;
    }

    while (sIndex >= 0) {
      if (S.charAt(sIndex--) != '#') {
        if (sBackspaces-- <= 0) {
          return false;
        }
      } else {
        sBackspaces++;
      }
    }

    while (tIndex >= 0) {
      if (T.charAt(tIndex--) != '#') {
        if (tBackspaces-- <= 0) {
          return false;
        }
      } else {
        tBackspaces++;
      }
    }

    return true;
  }
}
