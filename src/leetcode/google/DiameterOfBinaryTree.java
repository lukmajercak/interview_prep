package leetcode.google;

public class DiameterOfBinaryTree {

  /**
   * Given a binary tree, you need to compute the length of the diameter of the tree.
   * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
   * This path may or may not pass through the root.
   */
  public static void main(String[] args) {
    DiameterOfBinaryTree solution = new DiameterOfBinaryTree();

    // Given a binary tree
    //          1
    //         / \
    //        2   3
    //       / \
    //      4   5
    // Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
    // Note: The length of path between two nodes is represented by the number of edges between them.
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right = new TreeNode(3);

    System.out.println(solution.diameterOfBinaryTree(root));
  }

  int max = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    helper(root);
    return max;
  }

  public int helper(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = helper(root.left);
    int right = helper(root.right);

    max = Math.max(max, left + right);

    return Math.max(left, right) + 1;
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
