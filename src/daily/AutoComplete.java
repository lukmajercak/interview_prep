package daily;

import java.util.*;

public class AutoComplete {

  /**
   * This problem was asked by Twitter.
   *
   * Implement an autocomplete system. That is, given a query string s and a set of all possible query strings,
   * return all strings in the set that have s as a prefix.
   *
   * For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].
   */
  public static void main(String[] args) {
    List<String> words = Arrays.asList(new String[]{"dog", "deer", "deal"});
    System.out.println(query("de", words));
    System.out.println(query("d", words));

    words = Arrays.asList(new String[]{"dog", "deer", "deal", "rab", "rob", "rabs"});
    System.out.println(query("de", words));
    System.out.println(query("d", words));
    System.out.println(query("r", words));
    System.out.println(query("ra", words));
    System.out.println(query("rob", words));
  }

  static List<String> query(String query, List<String> words) {
    TrieNode trie = new TrieNode();
    for (String word : words) {
      trie.add(word);
    }
    return trie.query(query);
  }

  static class TrieNode {
    String value;
    Map<Character, TrieNode> children = new HashMap<>();

    void add(String word) {
      add(word, 0);
    }

    void add(String word, int i) {
      if (i == word.length()) {
        return;
      }
      char next = word.charAt(i);
      TrieNode child = children.get(next);
      if (child == null) {
        child = new TrieNode();
        children.put(next, child);
      }
      if (i == word.length() - 1 && child.value == null) {
        child.value = word;
      }
      child.add(word, i + 1);
    }

    List<String> query(String word) {
      List<String> results = new ArrayList<>();
      query(word, 0, results);
      return results;
    }

    void query(String word, int i, List<String> results) {
      if (i == word.length()) {
        // find all children
        findAllChildren(results);
        return;
      }
      char next = word.charAt(i);
      TrieNode child = children.get(next);
      if (child == null) {
        return;
      }
      child.query(word, i + 1, results);
    }

    void findAllChildren(List<String> results) {
      if (this.value != null) {
        results.add(this.value);
      }
      for (TrieNode child : this.children.values()) {
        child.findAllChildren(results);
      }
    }
  }
}
