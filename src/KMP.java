public class KMP {

  public static void main(String[] args) {
    KMP solution = new KMP();
    String txt = "ABABDABACDABABCABAB";
    String pat = "ABABCABAB";

    System.out.println(solution.search(txt , pat));
  }

  private int search(String text, String pattern) {
    // Create lps[] that will hold the longest prefix suffix values for pattern
    int[] lps = computeLPSArray(pattern);

    int i = 0; // index for text
    int j = 0; // index for pattern

    while (i < text.length()) {
      if (pattern.charAt(j) == text.charAt(i)) {
        j++;
        i++;
      }
      if (j == pattern.length()) {
        return i - j;
      } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
        // Mismatch after j matches
        // Do not match lps[0..lps[j-1]] characters, they will match anyway
        if (j != 0) {
          j = lps[j - 1];
        } else {
          i = i + 1;
        }
      }
    }
    return -1;
  }

  private int[] computeLPSArray(String pattern) {
    int[] lps = new int[pattern.length()];

    // Length of the previous longest prefix suffix
    int len = 0;
    int i = 1;
    lps[0] = 0;

    while (i < pattern.length()) {
      if (pattern.charAt(i) == pattern.charAt(len)) {
        len++;
        lps[i] = len;
        i++;
      } else {
        // (pattern[i] != pattern[len])
        if (len != 0) {
          // This is tricky. Consider the example. AAACAAAA and i = 7.
          // The idea is similar to search step.
          // Also, note that we do not increment i here
          len = lps[len - 1];
        } else {
          // if (len == 0)
          lps[i] = 0;
          i++;
        }
      }
    }
    return lps;
  }
}
