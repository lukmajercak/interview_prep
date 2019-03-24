package leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrobogrammaticNumber2 {

  /**
   * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
   *
   * Find all strobogrammatic numbers that are of length = n.
   */
  public static void main(String[] args) {
    StrobogrammaticNumber2 solution = new StrobogrammaticNumber2();

    // Example:
    //
    //Input:  n = 2
    //Output: ["11","69","88","96"]
    System.out.println(solution.findStrobogrammatic(2));

    System.out.println(solution.findStrobogrammatic(1));
    System.out.println(solution.findStrobogrammatic(3));
    System.out.println(solution.findStrobogrammatic(4));
    System.out.println(solution.findStrobogrammatic(5));
    System.out.println(solution.findStrobogrammatic(10));
  }

  char[] same = new char[]{'1', '8', '0'};
  char[] all = new char[]{'0', '1', '6', '8', '9'};

  public List<String> findStrobogrammatic(int n) {
    List<String> results = new ArrayList<>();

    char[] soFar = new char[n];
    findStrobogrammatic(n, 0, soFar, results);
    return results;
  }

  void findStrobogrammatic(int n, int index, char[] soFar, List<String> results) {
    if (index >= Math.ceil(n / 2.0f)) {
      results.add(new String(soFar));
      return;
    }

    if (index == n - index - 1) {
      // middle
      for (char c : same) {
        soFar[index] = c;
        findStrobogrammatic(n, index + 1, soFar, results);
      }
    } else {
      for (char c : all) {
        if (index == 0 && c == '0') {
          continue;
        }
        soFar[index] = c;
        soFar[n - index - 1] = getOpposite(c);
        findStrobogrammatic(n, index + 1, soFar, results);
      }
    }
  }

  char getOpposite(char c) {
    if (c == '6') {
      return '9';
    } else if (c == '9') {
      return '6';
    }
    return c;
  }
}
