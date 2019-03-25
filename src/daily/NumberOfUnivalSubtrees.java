package daily;

import java.util.HashSet;
import java.util.Set;

public class NumberOfUnivalSubtrees {

  static class Node {
    Node left;
    Node right;
    int value;

    Node(int value) {
      this(null, null, value);
    }

    Node(Node left, Node right, int value) {
      this.left = left;
      this.right = right;
      this.value = value;
    }

    boolean isLeaf() {
      return !hasLeft() && !hasRight();
    }

    boolean hasLeft() {
      return this.left != null;
    }

    boolean hasRight() {
      return this.right != null;
    }
  }

  /**
   * This problem was asked by Google.
   *
   * A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
   *
   * Given the root to a binary tree, count the number of unival subtrees.
   *
   * For example, the following tree has 5 unival subtrees:
   *
   *    0
   *   / \
   *  1   0
   *     / \
   *    1   0
   *   / \
   *  1   1
   */
  public static void main(String[] args) {
    Node root = new Node(0);
    root.left = new Node(1);
    root.right = new Node(0);
    root.right.left = new Node(1);
    root.right.left.left = new Node(1);
    root.right.left.right = new Node(1);
    root.right.right = new Node(0);

    System.out.println("example1");
    System.out.println(numUnivalSubtrees(root));
    System.out.println(numUnivalSubtrees1(root));

    /**
     *    1
     *   / \
     *  1   1
     *     / \
     *    1   1
     *   / \
     *  1   1
     *  should be 7
     */
    root = new Node(1);
    root.left = new Node(1);
    root.right = new Node(1);
    root.right.left = new Node(1);
    root.right.left.left = new Node(1);
    root.right.left.right = new Node(1);
    root.right.right = new Node(1);
    System.out.println("example2");
    System.out.println(numUnivalSubtrees(root));
    System.out.println(numUnivalSubtrees1(root));

    /**
                5
              /   \
            4      5
          /  \      \
         4    4      5

     should be 5
     */
    root = new Node(5);
    root.left = new Node(4);
    root.right = new Node(5);
    root.left.left = new Node(4);
    root.left.right = new Node(4);
    root.right.right = new Node(5);
    System.out.println("example3");
    System.out.println(numUnivalSubtrees(root));
    System.out.println(numUnivalSubtrees1(root));



    /**
     *        5
     *       / \
     *      4   5
     *     / \   \
     *    1   1   5
     *        \
     *         2
     *          \
     *           1
     *  should be 4 distinct
     */
    root = new Node(5);
    root.left = new Node(4);
    root.left.left = new Node(1);
    root.left.right = new Node(1);
    root.left.right.right = new Node(2);
    root.left.right.right.right = new Node(1);
    root.right = new Node(5);
    root.right.right = new Node(5);
    System.out.println("exampleDistinct");
    System.out.println(numDistinctSubtrees(root));

    /**
     *        5
     *       / \
     *      4   5
     *     / \   \
     *    1   3   5
     *        \
     *         2
     *          \
     *           1
     *  should be 5 distinct
     */
    root = new Node(5);
    root.left = new Node(4);
    root.left.left = new Node(1);
    root.left.right = new Node(3);
    root.left.right.right = new Node(2);
    root.left.right.right.right = new Node(1);
    root.right = new Node(5);
    root.right.right = new Node(5);
    System.out.println("exampleDistinct2");
    System.out.println(numDistinctSubtrees(root));
  }

  public static int numUnivalSubtrees(Node node) {
    return numUnivalSubtrees(node, new HashSet<>());
  }

  public static int numUnivalSubtrees(Node node, Set<Integer> valuesSeen) {
    if (node == null) {
      return 0;
    }

    if (node.isLeaf()) {
      valuesSeen.add(node.value);
      return 1;
    }

    int numUnivalSubtrees = 0;
    Set<Integer> seenSubtree = new HashSet<>();
    if (node.hasLeft()) {
      numUnivalSubtrees += numUnivalSubtrees(node.left, seenSubtree);
    }
    if (node.hasRight()) {
      numUnivalSubtrees += numUnivalSubtrees(node.right, seenSubtree);
    }

    seenSubtree.add(node.value);
    valuesSeen.add(node.value);
    if (seenSubtree.size() == 1) {
      numUnivalSubtrees++;
    }
    return numUnivalSubtrees;
  }


  /**
   * Second solution using a counter that's passed around and
   * returning bool from ancestors saying whether they are
   * unival or not;
   * @param node
   * @return
   */
  public static int numUnivalSubtrees1(Node node) {
    Count c = new Count();
    numUnivalSubtrees1(node, c);
    return c.count;
  }

  static class Count {
    int count = 0;
  }

  public static boolean numUnivalSubtrees1(Node node, Count c) {
    if (node == null) {
      return false;
    }

    if (node.isLeaf()) {
      c.count++;
      return true;
    }

    boolean left = true;
    boolean right = true;

    int value = node.value;

    if (node.hasLeft()) {
      left = numUnivalSubtrees1(node.left, c) && node.left.value == value;
    }
    if (node.hasRight()) {
      right = numUnivalSubtrees1(node.right, c) && node.right.value == value;
    }

    if (left && right) {
      c.count++;
      return true;
    }
    return false;
  }




  public static int numDistinctSubtrees(Node node) {
    return numDistinctSubtrees(node, new HashSet<>());
  }

  public static int numDistinctSubtrees(Node node, Set<Integer> valuesSeen) {
    if (node == null) {
      return 0;
    }

    if (node.isLeaf()) {
      valuesSeen.add(node.value);
      return 1;
    }

    int numUnivalSubtrees = 0;
    Set<Integer> seenSubtree = new HashSet<>();
    Set<Integer> seenLeft = new HashSet<>();
    Set<Integer> seenRight = new HashSet<>();
    if (node.hasLeft()) {
      numUnivalSubtrees += numDistinctSubtrees(node.left, seenLeft);
    }
    if (node.hasRight()) {
      numUnivalSubtrees += numDistinctSubtrees(node.right, seenRight);
    }

    seenSubtree.addAll(seenLeft);
    seenSubtree.addAll(seenRight);
    int seenSubtreeSize = seenSubtree.size();
    seenSubtree.add(node.value);
    valuesSeen.addAll(seenSubtree);
    if (seenSubtree.size() > seenSubtreeSize) {
      numUnivalSubtrees++;
    }
    return numUnivalSubtrees;
  }
}
