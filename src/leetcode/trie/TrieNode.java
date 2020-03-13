package leetcode.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {

  String word = null;
  Map<Character, TrieNode> children = new HashMap<>();

  /** Inserts a word into the leetcode.trie. */
  public void insert(String word) {
    insertImpl(word, 0);
  }

  private void insertImpl(String word, int i) {
    if (word == null || word.isEmpty() || i == word.length()) {
      return;
    }
    char nextChar = word.charAt(i);
    TrieNode child = this.children.get(nextChar);
    if (child == null) {
      child = new TrieNode();
      this.children.put(nextChar, child);
    }
    if (i == word.length() - 1) {
      child.word = word;
    }

    child.insertImpl(word, i + 1);
  }

  /** Returns if the word is in the leetcode.trie. */
  public boolean search(String word) {
    TrieNode searchNode = searchNode(word, 0);
    return searchNode != null && searchNode.word != null;
  }

  /** Returns if there is any word in the leetcode.trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    TrieNode searchNode = searchNode(prefix, 0);
    return searchNode != null;
  }

  public TrieNode searchNode(String word) {
    return searchNode(word, 0);
  }

  private TrieNode searchNode(String word, int i) {
    if (word == null || word.isEmpty()) {
      return null;
    }
    if (i == word.length()) {
      return this;
    }
    char nextChar = word.charAt(i);
    TrieNode child = this.children.get(nextChar);
    if (child == null) {
      return null;
    }
    return child.searchNode(word, i + 1);
  }

  public List<String> getChildren() {
    List<String> children = new ArrayList<>();
    getChildren(children);
    return children;
  }

  public void getChildren(List<String> children) {
    if (this.word != null) {
      children.add(this.word);
    }
    for (TrieNode child : this.children.values()) {
      child.getChildren(children);
    }
  }


  public static void main(String[] args) {
    TrieNode trie = new TrieNode();

    trie.insert("app");
    trie.insert("apple");
    trie.insert("beer");
    trie.insert("add");
    trie.insert("jam");
    trie.insert("rental");
    System.out.println(trie.search("apps"));
    System.out.println(trie.search("app"));
  }
}
