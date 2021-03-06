package leetcode.google;

import java.util.HashSet;
import java.util.Set;

public class CrackingTheSafe {

  /**
   * There is a box protected by a password. The password is n digits,
   * where each letter can be one of the first k digits 0, 1, ..., k-1.
   *
   * You can keep inputting the password, the password will automatically be
   * matched against the last n digits entered.
   *
   * For example, assuming the password is "345", I can open it when I type "012345",
   * but I enter a total of 6 digits.
   *
   * Please return any string of minimum length that is guaranteed to open the box
   * after the entire string is inputted.
   *
   *
   * Note:
   * n will be in the range [1, 4].
   * k will be in the range [1, 10].
   * k^n will be at most 4096.
   */
  public static void main(String[] args) {
    CrackingTheSafe solution = new CrackingTheSafe();

    // Input: n = 1, k = 2
    // Output: "01"
    // Note: "10" will be accepted too.
    System.out.println(solution.crackSafe(1, 2));

    // Input: n = 2, k = 2
    // Output: "00110"
    // Note: "01100", "10011", "11001" will be accepted too.
    System.out.println(solution.crackSafe(2, 2));
  }

  HashSet seen;
  StringBuilder ans;

  public String crackSafe(int n, int k) {
    if (n == 1 && k == 1) return "0";
    seen = new HashSet();
    ans = new StringBuilder();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n-1; ++i)
      sb.append("0");
    String start = sb.toString();

    dfs(start, k);
    ans.append(start);
    return new String(ans);
  }

  public void dfs(String node, int k) {
    for (int x = 0; x < k; ++x) {
      String nei = node + x;
      if (!seen.contains(nei)) {
        seen.add(nei);
        dfs(nei.substring(1), k);
        ans.append(x);
      }
    }
  }
}
