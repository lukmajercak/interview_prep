package daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Daily_03_13_2019 {

  /**
   * This problem was asked by Microsoft.
   *
   * Given a dictionary of words and a string made up of those words (no spaces),
   * return the original sentence in a list. If there is more than one possible reconstruction, return any of them.
   * If there is no possible reconstruction, then return null.
   *
   * For example, given the set of words 'quick', 'brown', 'the', 'fox',
   * and the string "thequickbrownfox", you should return ['the', 'quick', 'brown', 'fox'].
   *
   * Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the string "bedbathandbeyond",
   * return either ['bed', 'bath', 'and', 'beyond] or ['bedbath', 'and', 'beyond'].
   */
  public static void main(String[] args) {
    Daily_03_13_2019 solution = new Daily_03_13_2019();

    Set<String> words = new HashSet<>();
    words.add("quick");
    words.add("brown");
    words.add("the");
    words.add("fox");
    System.out.println(solution.reconstruct(words, "thequick"));
    System.out.println(solution.reconstruct(words, "thequickbrownfox"));

    words = new HashSet<>();
    words.add("bed");
    words.add("bath");
    words.add("bedbath");
    words.add("and");
    words.add("beyond");
    System.out.println(solution.reconstruct(words, "bedbathandbeyond"));
    System.out.println(solution.reconstruct(words, "bezdbathandbeyond"));

    // "catsandog"
    //["cats","dog","sand","and","cat"]
    words = new HashSet<>();
    words.add("cats");
    words.add("dog");
    words.add("sand");
    words.add("and");
    words.add("cat");
    System.out.println(solution.reconstruct(words, "catsandog"));
    System.out.println(solution.reconstruct(words, "zzzcats"));

    // "aaaaaaa"
    //["aaaa","aa"]
    words = new HashSet<>();
    words.add("aaaa");
    words.add("aa");
    System.out.println(solution.reconstruct(words, "aaaaaaa"));

    // "leetcode"
    //["leet","code"]
    words = new HashSet<>();
    words.add("leet");
    words.add("code");
    System.out.println(solution.reconstruct(words, "leetcode"));

    // "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
    //["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
    words = new HashSet<>();
    words.add("a");
    words.add("aa");
    words.add("aaa");
    words.add("aaaa");
    words.add("aaaaa");
    words.add("aaaaaa");
    words.add("aaaaaaa");
    words.add("aaaaaaaa");
    words.add("aaaaaaaaa");
    words.add("aaaaaaaaaa");
    System.out.println(solution.reconstruct(words,
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"));
  }

  Set<String> reconstruct(Set<String> words, String s) {
    TrieNode trie = new TrieNode();
    for (String word : words) {
      trie.add(word);
    }

    Set<String> result = reconstruct(trie, trie, s, 0);
    return result;
  }

  Set<String> reconstruct(TrieNode root, TrieNode node, String s, int i) {
    return reconstruct(root, node, s, i, new HashMap<>());
  }

  Set<String> reconstruct(TrieNode root, TrieNode node, String s, int i, Map<Memo, Set<String>> memo) {
    Memo memoKey = new Memo(node, i);
    if (memo.containsKey(memoKey)) {
      return memo.get(memoKey);
    }

    if (i == s.length()) {
      return null;
    }
    TrieNode child = node.children.get(s.charAt(i));
    if (child == null) {
      return null;
    }

    if (child.word == null) {
      if (i == s.length() - 1) {
        return null;
      }
      Set<String> continued = reconstruct(root, child, s, i + 1, memo);
      memo.put(new Memo(node, i), continued);
      return continued;
    } else {
      // is a word
      if (i == s.length() - 1) {
        Set<String> result = new HashSet<>();
        result.add(child.word);
        memo.put(memoKey, result);
        return result;
      }
      Set<String> addWord = reconstruct(root, root, s, i + 1, memo);
      if (addWord != null) {
        addWord.add(child.word);
        memo.put(memoKey, addWord);
        return addWord;
      }
      Set<String> notAdding = reconstruct(root, child, s, i + 1, memo);
      memo.put(memoKey, notAdding);
      return notAdding;
    }
  }

  static class Memo {
    TrieNode node;
    int i;

    public Memo(TrieNode node, int i) {
      this.node = node;
      this.i = i;
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Memo)) {
        return false;
      }
      Memo b = (Memo) o;
      return this == b || (this.node == b.node && this.i == b.i);
    }

    @Override
    public int hashCode() {
      return node.hashCode() + Integer.valueOf(i).hashCode();
    }
  }

  static class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    String word;

    void add(String s) {
      add(s, 0);
    }

    void add(String s, int i) {
      if (i == s.length()) {
        return;
      }
      TrieNode child = children.get(s.charAt(i));
      if (child == null) {
        child = new TrieNode();
        children.put(s.charAt(i), child);
      }
      if (i == s.length() - 1) {
        child.word = s;
      }
      child.add(s, i + 1);
    }
  }
}
