package daily;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestAbsoluteFilePath {

  /**
   * This problem was asked by Google.
   *
   * Suppose we represent our file system by a string in the following manner:
   *
   * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
   *
   * dir
   *     subdir1
   *     subdir2
   *         file.ext
   * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
   *
   * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
   *
   * dir
   *     subdir1
   *         file1.ext
   *         subsubdir1
   *     subdir2
   *         subsubdir2
   *             file2.ext
   * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and
   * an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2
   * containing a file file2.ext.
   *
   * We are interested in finding the longest (number of characters) absolute path to a file within our file system.
   * For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext",
   * and its length is 32 (not including the double quotes).
   *
   * Given a string representing the file system in the above format, return the length of the longest absolute
   * path to a file in the abstracted file system. If there is no file in the system, return 0.
   *
   * Note:
   *
   * The name of a file contains at least a period and an extension.
   *
   * The name of a directory or sub-directory will not contain a period.
   */
  public static void main(String[] args) {
    LongestAbsoluteFilePath solution = new LongestAbsoluteFilePath();

    System.out.println(solution.findLongest(
      "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));

    System.out.println(solution.findLongest(
        "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));

    System.out.println(solution.findLongest(
        "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext\n\tverylongfileyopickmeplease.txt"));
  }

  String findLongest(String filesystem) {
    String longestSoFar = null;

    Deque<String> pathSoFar = new ArrayDeque<>();

    String[] dirsAndFiles = filesystem.split("\n");
    for (String dirOrFile : dirsAndFiles) {
      if (isFile(dirOrFile)) {
        String fullPath = getFullPath(dirOrFile, pathSoFar);
        longestSoFar = getLonger(longestSoFar, fullPath);
      } else {
        int level = getLevel(dirOrFile);

        while (!pathSoFar.isEmpty() && getLevel(pathSoFar.peek()) >= level) {
          pathSoFar.pop();
        }
        pathSoFar.push(dirOrFile);
      }
    }
    return longestSoFar;
  }

  boolean isFile(String dirOrFile) {
    return dirOrFile.contains(".");
  }

  String getFullPath(String file, Deque<String> pathSoFar) {
    StringBuilder fullPath = new StringBuilder(file.replaceAll("\t", ""));
    for (String dir : pathSoFar) {
      fullPath.insert(0, dir.replaceAll("\t", "") + "/");
    }
    return fullPath.toString();
  }

  String getLonger(String a, String b) {
    if (a == null) {
      return b;
    }
    if (b == null) {
      return a;
    }
    return a.length() > b.length() ? a : b;
  }

  int getLevel(String dirOrFile) {
    int i = 0;
    while (dirOrFile.charAt(i) == '\t') {
      i++;
    }
    return i;
  }
}
