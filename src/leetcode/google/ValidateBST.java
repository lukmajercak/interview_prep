package leetcode.google;

public class ValidateBST {

  /**
   * Given a binary tree, determine if it is a valid binary search tree (BST).
   *
   * Assume a BST is defined as follows:
   *
   * The left subtree of a node contains only nodes with keys less than the node's key.
   * The right subtree of a node contains only nodes with keys greater than the node's key.
   * Both the left and right subtrees must also be binary search trees.
   */
  public static void main(String[] args) {

  }

  public boolean isValidBST(TreeNode root) {
    return isValidBST(root, null, null);
  }

  boolean isValidBST(TreeNode n, Integer min, Integer max) {
    if (n == null) {
      return true;
    }
    if (min != null && n.val <= min) {
      return false;
    }
    if (max != null && n.val >= max) {
      return false;
    }
    return isValidBST(n.left, min, n.val) && isValidBST(n.right, n.val, max);
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
