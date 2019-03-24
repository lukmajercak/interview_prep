package leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class AddBoldTag {

  /**
   * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b>
   * to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap
   * them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive,
   * you need to combine them.
   */
  public static void main(String[] args) {
    AddBoldTag solution = new AddBoldTag();

    // Example 1:
    //Input:
    //s = "abcxyz123"
    //dict = ["abc","123"]
    //Output:
    //"<b>abc</b>xyz<b>123</b>"
    //
    // bold = [t, t, t, f, f, f, t, t, t]
    System.out.println(solution.addBoldTag("abcxyz123", new String[]{"abc", "123"}));

    // Example 2:
    //Input:
    //s = "aaabbcc"
    //dict = ["aaa","aab","bc"]
    //Output:
    //"<b>aaabbc</b>c"
    //
    // bold = [t, t, t, t, t, t, f]
    System.out.println(solution.addBoldTag("aaabbcc", new String[]{"aaa", "aab", "bc"}));
  }

  public String addBoldTag(String s, String[] dict) {
    boolean[] bold = new boolean[s.length()];

    for (int i = 0; i < s.length(); i++) {
      for (String word : dict) {
        if (i + word.length() > s.length()) {
          continue;
        }
        if (s.substring(i, i + word.length()).equals(word)) {
          for (int j = i; j < i + word.length(); j++) {
            bold[j] = true;
          }
        }
      }
    }

    StringBuilder output = new StringBuilder();
    boolean inBold = false;
    boolean closed = true;
    for (int i = 0; i < bold.length; i++) {
      if (bold[i] && !inBold) {
        // add <b>
        output.append("<b>" + s.charAt(i));
        closed = false;
      } else if (!bold[i] && inBold) {
        // add </b>
        output.append("</b>" + s.charAt(i));
        closed = true;
      } else {
        output.append(s.substring(i, i + 1));
      }
      inBold = bold[i];
    }
    if (!closed) {
      output.append("</b>");
    }
    return output.toString();
  }
}
