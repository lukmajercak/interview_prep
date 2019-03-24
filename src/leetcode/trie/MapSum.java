package leetcode.trie;

import java.util.HashMap;
import java.util.Map;

public class MapSum {

  TrieNodeSum trie = new TrieNodeSum();

  /**
   * you'll be given a pair of (string, integer).
   * The string represents the key and the integer represents the value.
   * If the key already existed, then the original key-value pair will be overridden to the new one.
   */
  public void insert(String key, int val) {
    TrieNodeSum node = trie.search(key);
    if (node != null && node.isWord) {
      trie.remove(key, 0);
    }

    trie.insert(key, val);
  }

  /**
   * you'll be given a string representing the prefix,
   * and you need to return the sum of all the pairs' value whose key starts with the prefix.
   */
  public int sum(String prefix) {
    TrieNodeSum node = trie.search(prefix);
    if (node == null) {
      return 0;
    }
    return node.sum;
  }

  static class TrieNodeSum {

    boolean isWord = false;
    int sum = 0;
    Map<Character, TrieNodeSum> children = new HashMap<>();

    /** Inserts a word into the trie. */
    public void insert(String word, int value) {
      insertImpl(word, value, 0);
    }

    private void insertImpl(String word, int value, int i) {
      if (word == null || word.isEmpty() || i == word.length()) {
        return;
      }
      char nextChar = word.charAt(i);
      TrieNodeSum child = this.children.get(nextChar);
      if (child == null) {
        child = new TrieNodeSum();
        this.children.put(nextChar, child);
      }

      child.isWord |= i == word.length() - 1;
      child.sum += value;
      child.insertImpl(word, value, i + 1);
    }

    /** Returns if the word is in the leetcode.trie. */
    public TrieNodeSum search(String word) {
      return searchNode(word, 0);
    }

    public void remove(String word, int i) {
      if (word == null || word.isEmpty()) {
        return;
      }
      if (i == word.length()) {
        return;
      }

      char nextChar = word.charAt(i);
      TrieNodeSum child = this.children.get(nextChar);

      if (child != null) {
        this.children.remove(nextChar);
        child.remove(word, i + 1);
      }
    }

    private TrieNodeSum searchNode(String word, int i) {
      if (word == null || word.isEmpty()) {
        return null;
      }
      if (i == word.length()) {
        return this;
      }
      char nextChar = word.charAt(i);
      TrieNodeSum child = this.children.get(nextChar);
      if (child == null) {
        return null;
      }
      return child.searchNode(word, i + 1);
    }
  }

  public static void main(String[] args) {
    MapSum mapSum = new MapSum();
    mapSum.insert("aa", 3); // Output: Null
    System.out.println(mapSum.sum("a")); // Output: 3
    mapSum.insert("aa", 2); // Output: Null
    System.out.println(mapSum.sum("a")); // output 2
  }
}



