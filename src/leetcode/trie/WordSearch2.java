package leetcode.trie;

import java.util.*;

public class WordSearch2 {

  /**
   * Given a 2D board and a list of words from the dictionary, find all words in the board.
   *
   * Each word must be constructed from letters of sequentially adjacent cell,
   * where "adjacent" cells are those horizontally or vertically neighboring.
   * The same letter cell may not be used more than once in a word.
   *
   * Example:
   *
   * Input:
   * words = ["oath","pea","eat","rain"] and board =
   * [
   *   ['o','a','a','n'],
   *   ['e','t','a','e'],
   *   ['i','h','k','r'],
   *   ['i','f','l','v']
   * ]
   *
   * Output: ["eat","oath"]
   * Note:
   * You may assume that all inputs are consist of lowercase letters a-z.
   */
  public static void main(String[] args) {
    char[][] board = new char[][]{
        {'o','a','a','n'},
        {'e','t','a','e'},
        {'i','h','k','r'},
        {'i','f','l','v'}
    };
    String[] words = new String[]{"oath","pea","eat","rain"};

    System.out.println(findWords(board, words));

    board = new char[][]{{'a'}};
    words = new String[]{"a"};
    System.out.println(findWords(board, words));

    board = new char[][]{{'a', 'a'}};
    words = new String[]{"aaa"};
    System.out.println(findWords(board, words));

    board = new char[][]{{'a', 'b'}};
    words = new String[]{"ab"};
    System.out.println(findWords(board, words));

    board = new char[][]{
        {'a', 'b'},
        {'a', 'a'}
    };
    words = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
    System.out.println(findWords(board, words));

    board = new char[][]{
        {'b','a','a','b','a','b'},
        {'a','b','a','a','a','a'},
        {'a','b','a','a','a','b'},
        {'a','b','a','b','b','a'},
        {'a','a','b','b','a','b'},
        {'a','a','b','b','b','a'},
        {'a','a','b','a','a','b'}
    };
    words = new String[]{
        "bbaabaabaaaaabaababaaaaababb",
        "aabbaaabaaabaabaaaaaabbaaaba",
        "babaababbbbbbbaabaababaabaaa",
        "bbbaaabaabbaaababababbbbbaaa",
        "babbabbbbaabbabaaaaaabbbaaab",
        "bbbababbbbbbbababbabbbbbabaa",
        "babababbababaabbbbabbbbabbba",
        "abbbbbbaabaaabaaababaabbabba",
        "aabaabababbbbbbababbbababbaa",
        "aabbbbabbaababaaaabababbaaba",
        "ababaababaaabbabbaabbaabbaba",
        "abaabbbaaaaababbbaaaaabbbaab",
        "aabbabaabaabbabababaaabbbaab",
        "baaabaaaabbabaaabaabababaaaa",
        "aaabbabaaaababbabbaabbaabbaa",
        "aaabaaaaabaabbabaabbbbaabaaa",
        "abbaabbaaaabbaababababbaabbb",
        "baabaababbbbaaaabaaabbababbb",
        "aabaababbaababbaaabaabababab",
        "abbaaabbaabaabaabbbbaabbbbbb",
        "aaababaabbaaabbbaaabbabbabab",
        "bbababbbabbbbabbbbabbbbbabaa",
        "abbbaabbbaaababbbababbababba",
        "bbbbbbbabbbababbabaabababaab",
        "aaaababaabbbbabaaaaabaaaaabb",
        "bbaaabbbbabbaaabbaabbabbaaba",
        "aabaabbbbaabaabbabaabababaaa",
        "abbababbbaababaabbababababbb",
        "aabbbabbaaaababbbbabbababbbb",
        "babbbaabababbbbbbbbbaabbabaa"
    };
    System.out.println(findWords(board, words));
  }

  static class TrieNode {
    String value;
    Map<Character, TrieNode> children = new HashMap<>();

    void insert(String word) {
      TrieNode next = this;
      int i = 0;

      while (i < word.length()) {
        char nextChar = word.charAt(i);
        TrieNode child = next.children.get(nextChar);
        if (child == null) {
          child = new TrieNode();
          next.children.put(nextChar, child);
        }

        next = child;
        i++;
      }
      next.value = word;
    }
  }

  public static List<String> findWords(char[][] board, String[] words) {
    List<String> result = new ArrayList<>();

    TrieNode trie = new TrieNode();
    for (String word : words) {
      trie.insert(word);
    }

    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        TrieNode child = trie.children.get(board[row][col]);
        if (child == null) {
          continue;
        }
        boolean[][] visited = new boolean[board.length][board[row].length];
        checkTrieContains(child, board, row, col, visited, result);
      }
    }

    return result;
  }

  static void checkTrieContains(
      TrieNode trie, char[][] board, int row, int col,
      boolean[][] visited, List<String> result) {
    if (trie.value != null) {
      if (!result.contains(trie.value)) {
        result.add(trie.value);
      }
    }

    visited[row][col] = true;
    checkPossibleStep(trie, board, row - 1, col, visited, result);
    checkPossibleStep(trie, board, row + 1, col, visited, result);
    checkPossibleStep(trie, board, row, col - 1, visited, result);
    checkPossibleStep(trie, board, row, col + 1, visited, result);
    visited[row][col] = false;
  }

  static void checkPossibleStep(
      TrieNode trie, char[][] board, int row, int col,
      boolean[][] visited, List<String> result) {
    if (!inBounds(board, row, col)) {
      return;
    }

    TrieNode next = trie.children.get(board[row][col]);
    if (next != null && !visited[row][col]) {
      checkTrieContains(next, board, row, col, visited, result);
    }
  }

  static boolean inBounds(char[][] board, int row, int col) {
    return row >= 0 && row < board.length &&
        col >= 0 && col < board[row].length;
  }
}
