package leetcode.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * In English, we have a concept called root, which can be followed by some
 * other words to form another longer word - let's call this word successor.
 * For example, the root an, followed by other, which can form another word another.
 *
 * Now, given a dictionary consisting of many roots and a sentence.
 * You need to replace all the successor in the sentence with the root forming it.
 * If a successor has many roots can form it, replace it with the root with the shortest length.
 *
 * You need to output the sentence after the replacement.
 *
 * Example 1:
 *
 * Input: dict = ["cat", "bat", "rat"]
 * sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 *
 * Note:
 *
 * The input will only have lower-case letters.
 * 1 <= dict words number <= 1000
 * 1 <= sentence words number <= 1000
 * 1 <= root length <= 100
 * 1 <= sentence words length <= 1000
 */
public class ReplaceWords {

  TrieNode trie = new TrieNode();

  private void addToDict(String toAdd) {
    trie.insert(toAdd);
  }

  private int charsMatched(String word) {
    int i = 0;
    TrieNode nextNode = trie.children.get(word.charAt(i));
    while (nextNode != null & i < word.length() - 1) {
      if (nextNode.isWord) {
        return i + 1;
      }
      i++;
      nextNode = nextNode.children.get(word.charAt(i));
    }
    return -1;
  }

  public String replaceWords(List<String> dict, String sentence) {
    for (String dictStr : dict) {
      addToDict(dictStr);
    }

    StringBuilder outStr = new StringBuilder();
    String[] words = sentence.split(" ");

    for (int i = 0; i < words.length; i++) {
      String word = words[i];

      int toKeep = charsMatched(word);
      if (toKeep != -1) {
        outStr.append(word.substring(0, toKeep));
      } else {
        outStr.append(word);
      }
      if (i != words.length - 1) {
        outStr.append(' ');
      }
    }
    return outStr.toString();
  }

  public static void main(String[] args) {
    ReplaceWords replaceWords = new ReplaceWords();
    List<String> dict = new ArrayList<>();
    dict.add("cat");
    dict.add("bat");
    dict.add("rat");

    String replaced = replaceWords.replaceWords(dict,
        "the cattle was rattled by the battery");
    System.out.println(replaced);


    replaceWords = new ReplaceWords();
    dict = new ArrayList<>();
    dict.add("a");
    dict.add("aa");
    dict.add("aaa");
    dict.add("aaaa");

    replaced = replaceWords.replaceWords(dict,
        "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa");
    System.out.println(replaced);
  }
}
