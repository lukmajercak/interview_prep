package leetcode.trie;

public class MaximumXOR {

  private static final short INT_SIZE = 32;

  /**
   * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
   *
   * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
   *
   * Could you do this in O(n) runtime?
   *
   * Example:
   *
   * Input: [3, 10, 5, 25, 2, 8]
   *
   * Output: 28
   *
   * Explanation: The maximum result is 5 ^ 25 = 28.
   *
   *
   * 3 ->  0 0 0 1 1
   * 10 -> 0 1 0 1 0
   * 5 ->  0 0 1 0 1
   * 25 -> 1 1 0 0 1
   * 2 ->  0 0 0 1 0
   * 8 ->  0 1 0 0 0
   *
   * 0 xor 3  = 0 0 0 1 1    in tree  0 0 0 1 0
   *   xor 10 = 0 1 0 0 1    in tree  0 1 0 0 1
   *   xor 5  = 0 1 1 0 0    in tree  0 1 1 0 0
   *   xor 25 = 1 0 1 0 1    in tree
   */
  public static void main(String[] args) {
    int pre_xor = 0 ^ 3;
    System.out.println(pre_xor);
    pre_xor = pre_xor ^ 10;
    //System.out.println(pre_xor);
    pre_xor = pre_xor ^ 5;
    //System.out.println(pre_xor);
    pre_xor = pre_xor ^ 25;
    //System.out.println(pre_xor);

    for (int i=INT_SIZE-1; i>=0; i--) {
      int val = (pre_xor & (1 << INT_SIZE - i)) >= 1 ? 1 : 0;
      System.out.println(val);
    }

  }

  static class TrieNode {
    int value;
    TrieNode[] children = new TrieNode[2];

    TrieNode(int value) {
      this.value = 0;
    }

    void insert(int prefix_xor) {

    }

    int query(int prefix_xor) {
      return this.value;
    }
  }



  // track prefix_xor as we go
  // add prefix_xor to the tree
  // query in trie using opposite bits
  static int maxXor(int[] numbers) {

    TrieNode trie = new TrieNode(0);
    trie.insert(0);

    int result = Integer.MIN_VALUE;
    int prefix_xor = 0;

    for (int n : numbers) {
      prefix_xor = prefix_xor ^ n;
      trie.insert(prefix_xor);

      int max = trie.query(prefix_xor);
      result = Math.max(result, max);
    }

    return result;
  }
}
