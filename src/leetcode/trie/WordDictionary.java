package leetcode.trie;

/**
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 *
 * search(word) can search a literal word or a regular expression string
 * containing only letters a-z or '.' . A '.' means it can represent any one letter.
 *
 * Example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 */
public class WordDictionary {

  TrieNode trie;

  /** Initialize your data structure here. */
  public WordDictionary() {
    trie = new TrieNode();
  }

  /** Adds a word into the data structure. */
  public void addWord(String word) {
    trie.insert(word);
  }

  /**
   * Returns if the word is in the data structure.
   * A word could contain the dot character '.' to represent any one letter.
   * */
  public boolean search(String word) {
    return search(word, trie, 0);
  }

  private boolean search(String word, TrieNode node, int i) {
    if (i == word.length()) {
      return node.isWord;
    }

    Character nextChar = word.charAt(i);
    if (nextChar == '.') {
      for (TrieNode child : node.children.values()) {
        if (search(word, child, i + 1)) {
          return true;
        }
      }
      return false;
    }
    // Simple char
    TrieNode child = node.children.get(nextChar);
    if (child == null) {
      return false;
    }
    return search(word, child, i + 1);
  }


  public static void main(String[] args) {
    WordDictionary wordDict = new WordDictionary();
    wordDict.addWord("bad");
    wordDict.addWord("dad");
    wordDict.addWord("mad");

    System.out.println(wordDict.search("pad"));
    System.out.println(wordDict.search("bad"));
    System.out.println(wordDict.search(".ad"));
    System.out.println(wordDict.search("b.."));

    System.out.println("----------- EXAMPLE 2 --------------");

    /**
     * ["WordDictionary","addWord","addWord","addWord","addWord","search","search","addWord","search","search","search","search","search","search"]
     * [[],               ["at"]  ,["and"]  ,["an"]   ,["add"]  ,["a"]   ,[".at"] ,["bat"]  ,[".at"] ,["an."] ,["a.d."],["b."] ,["a.d"]  ,["."]]
     * Expected output:
     * [null,             null    ,null     ,null     ,null     ,false   ,false   ,null     ,true    ,true    ,false   ,false  ,true     ,false]
     */
    wordDict = new WordDictionary();
    wordDict.addWord("at");
    wordDict.addWord("and");
    wordDict.addWord("an");
    wordDict.addWord("add");

    System.out.println(wordDict.search("a"));
    System.out.println(wordDict.search(".at"));
    wordDict.addWord("bat");
    System.out.println(wordDict.search(".at"));
    System.out.println(wordDict.search("an."));
    System.out.println(wordDict.search("a.d."));
    System.out.println(wordDict.search("b."));
    System.out.println(wordDict.search("a.d"));
    System.out.println(wordDict.search("."));
  }
}
