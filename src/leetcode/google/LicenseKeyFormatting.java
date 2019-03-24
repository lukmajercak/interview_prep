package leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class LicenseKeyFormatting {

  /**
   * You are given a license key represented as a string S which consists only alphanumeric character and dashes.
   * The string is separated into N+1 groups by N dashes.
   *
   * Given a number K, we would want to reformat the strings such that each group contains exactly K characters,
   * except for the first group which could be shorter than K, but still must contain at least one character.
   * Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
   *
   * Given a non-empty string S and a number K, format the string according to the rules described above.
   */
  public static void main(String[] args) {
    LicenseKeyFormatting solution = new LicenseKeyFormatting();

    // Example 1:
    //Input: S = "5F3Z-2e-9-w", K = 4
    //
    //Output: "5F3Z-2E9W"
    //
    //Explanation: The string S has been split into two parts, each part has 4 characters.
    //Note that the two extra dashes are not needed and can be removed.
    System.out.println(solution.licenseKeyFormatting("5F3Z-2e-9-w", 4)); // "5F3Z-2E9W"
    System.out.println(solution.licenseKeyFormatting("5F3ZE-2e-9-w", 4)); // "5-F3ZE-2E9W"
    System.out.println(solution.licenseKeyFormatting("5F3Z-2e-9-w", 5)); // "5F3-Z2E9W"

    // Example 2:
    //Input: S = "2-5g-3-J", K = 2
    //
    //Output: "2-5G-3J"
    //
    //Explanation: The string S has been split into three parts,
    // each part has 2 characters except the first part as it could be shorter as mentioned above.
    System.out.println(solution.licenseKeyFormatting("2-5g-3-J", 2)); // "2-5G-3J"
  }

  public String licenseKeyFormatting(String S, int K) {
    List<Character> keyReverse = new ArrayList<>();

    int k = K;
    for (int i = S.length() - 1; i >= 0; i--) {
      char c = S.charAt(i);
      if (c == '-') {
        continue;
      }
      if (k == 0) {
        keyReverse.add('-');
        k = K;
      }
      keyReverse.add(Character.toUpperCase(c));
      k--;
    }

    StringBuilder key = new StringBuilder(keyReverse.size());
    for (int i = keyReverse.size() - 1; i >= 0; i--) {
      key.append(keyReverse.get(i));
    }
    return key.toString();
  }
}
