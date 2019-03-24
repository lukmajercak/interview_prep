package udemy_11_essential;

public class non_repeating {
  public static void main(String[] args) {
    // NOTE: The following input values will be used for testing your solution.
    nonRepeating("abcab"); // should return 'c'
    nonRepeating("abab"); // should return null
    nonRepeating("aabzzbbc"); // should return 'c'
    nonRepeating("aabbdbc"); // should return 'd'
  }

  // Implement your solution below.
  public static Character nonRepeating(String s) {
    int[] seen = new int[toInt('z') + 1];

    for (int i = 0; i < s.length(); i++) {
      seen[toInt(s.charAt(i))]++;
    }

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (seen[toInt(c)] == 1) {
        System.out.println(c);
        return c;
      }
    }
    System.out.println("null");
    return null;
  }

  static int toInt(char c) {
    return c - 'a';
  }
}
