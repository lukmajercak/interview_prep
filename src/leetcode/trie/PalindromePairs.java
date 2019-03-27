package leetcode.trie;

import java.util.*;

public class PalindromePairs {

  /**
   * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
   * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome
   *
   * Example 1:
   *
   * Input: ["abcd","dcba","lls","s","sssll"]
   * Output: [[0,1],[1,0],[3,2],[2,4]]
   * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
   *
   *
   * Example 2:
   *
   * Input: ["bat","tab","cat"]
   * Output: [[0,1],[1,0]]
   * Explanation: The palindromes are ["battab","tabbat"]
   *
   */
  public static void main(String[] args) {
    System.out.println(palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"})); // [[0,1],[1,0],[3,2],[2,4]]
    System.out.println(palindromePairs(new String[]{"bat","tab","cat"})); // [[0,1],[1,0]]
    System.out.println(palindromePairs(new String[]{"a", ""})); // [[0,1],[1,0]]
    System.out.println(palindromePairs(new String[]{"a","abc","aba",""})); // [[3, 0], [3, 2], [0, 3], [2, 3]]
    System.out.println(palindromePairs(new String[]{"a","b","c","ab","ac","aa"})); // [[3,0],[1,3],[4,0],[2,4],[5,0],[0,5]]
    System.out.println(palindromePairs(new String[]{"ab","ba","abc","cba"})); // [[0,1],[1,0],[2,1],[2,3],[0,3],[3,2]]
    System.out.println(palindromePairs(new String[]{"aba","ba","a","caba"})); // [[0,1],[2,1],[0,3]]
  }

  static List<List<Integer>> palindromePairs(String[] words) {
    TrieNode trie = new TrieNode();
    for (int i = 0; i < words.length; i++) {
      trie.add(words[i], i);
    }

    Set<List<Integer>> result = new HashSet<>();
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      if (word.isEmpty()) {
        for (int j = 0; j < words.length; j++) {
          if (j != i && isPalindrome(words[j])) {
            result.add(Arrays.asList(new Integer[]{j, i}));
          }
        }
      } else {
        query(trie, word, i, result);
      }
    }
    return new ArrayList<List<Integer>>(result);
  }

  static boolean isPalindrome(String word) {
    if (word.isEmpty()) {
      return true;
    }
    int i = 0;
    int j = word.length() - 1;
    while (i <= j) {
      if (word.charAt(i) != word.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  static void query(TrieNode trie, String word, int index, Set<List<Integer>> result) {
    TrieNode next = trie;

    if (next.index != null && next.index != index && isPalindrome(word)) {
      result.add(Arrays.asList(new Integer[]{next.index, index}));
    }

    // Iterate the word from the end
    int i = word.length() - 1;
    for (; i >= 0; i--) {
      next = next.children.get(word.charAt(i));
      if (next == null) {
        break;
      }
      if (next.index != null && next.index != index && isPalindrome(word.substring(0, i))) {
        result.add(Arrays.asList(new Integer[]{next.index, index}));
      }
    }
    TrieNode previouslyChecked = next;
    if (i == -1 && next != null) {
      for (TrieNode child : next.children.values()) {
        List<TrieNode> allWords = child.findAllWords();
        for (TrieNode wordNode : allWords) {
          String substring = wordNode.value.substring(word.length());
          if (isPalindrome(substring) && wordNode.index != index) {
            result.add(Arrays.asList(new Integer[]{wordNode.index, index}));
          }
        }
      }
    }
  }

  static class TrieNode {
    Integer index;
    String value;
    Map<Character, TrieNode> children = new HashMap<>();

    TrieNode(int index, String value) {
      this.index = index;
      this.value = value;
    }

    TrieNode() {
    }

    void add(String word, int index) {
      if (word.isEmpty()) {
        this.index = index;
        return;
      }
      add(word, index, 0);
    }

    void add(String word, int index, int i) {
      if (i == word.length()) {
        return;
      }
      char nextChar = word.charAt(i);
      TrieNode child = children.get(nextChar);
      if (child == null) {
        child = new TrieNode();
        children.put(nextChar, child);
      }
      if (i == word.length() - 1) {
        child.index = index;
        child.value = word;
      }

      child.add(word, index, i + 1);
    }

    List<TrieNode> findAllWords() {
      List<TrieNode> words = new ArrayList<>();
      findAllWords(words);
      return words;
    }

    void findAllWords(List<TrieNode> words) {
      if (this.value != null) {
        words.add(this);
      }
      for (TrieNode child : this.children.values()) {
        child.findAllWords(words);
      }
    }
  }
}
