package leetcode.google;

public class InvertBinaryTree {

  /**
   * Invert a binary tree.
   */
  public static void main(String[] args) {
    InvertBinaryTree solution = new InvertBinaryTree();

    // Example
    // Input:
    //
    //     4
    //   /   \
    //  2     7
    // / \   / \
    //1   3 6   9
    //
    // Output:
    //
    //     4
    //   /   \
    //  7     2
    // / \   / \
    //9   6 3   1
    TreeNode root = new TreeNode(4);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);
    root.right = new TreeNode(7);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(9);

    TreeNode inverted = solution.invertTree(root);
  }

  public TreeNode invertTree(TreeNode node) {
    if (node == null) {
      return null;
    }
    TreeNode newLeft = invertTree(node.left);
    TreeNode newRight = invertTree(node.right);
    node.left = newRight;
    node.right = newLeft;
    return node;
  }

  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
