package leetcode.google;

public class BinaryTreeMaximumPathSum {

  /**
   * Given a non-empty binary tree, find the maximum path sum.
   *
   * For this problem, a path is defined as any sequence of nodes from some starting
   * node to any node in the tree along the parent-child connections.
   * The path must contain at least one node and does not need to go through the root.
   */
  public static void main(String[] args) {
    BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum();

    // Input: [1,2,3]
    //
    //       1
    //      / \
    //     2   3
    //
    // Output: 6
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    System.out.println(solution.maxPathSum(root));

    // Input: [-10,9,20,null,null,15,7]
    //
    //   -10
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //
    // Output: 42
    root = new TreeNode(-10);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    System.out.println(solution.maxPathSum(root));


    // [9,6,-3,null,null,-6,2,null,null,2,null,-6,-6,-6]
    root = new TreeNode(9);
    root.left = new TreeNode(6);
    root.right = new TreeNode(-3);
    root.right.left = new TreeNode(-6);
    root.right.right = new TreeNode(2);
    root.right.right.left = new TreeNode(2);
    root.right.right.left.left = new TreeNode(-6);
    root.right.right.left.left.left = new TreeNode(-6);
    root.right.right.left.right = new TreeNode(-6);
    System.out.println(solution.maxPathSum(root));
  }

  int max;

  public int maxPathSum(TreeNode root) {
    max = root.val;
    maxPathSumH(root);
    return max;
  }

  public int maxPathSumH(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int maxSumLeft = maxPathSumH(root.left);
    int maxSumRight = maxPathSumH(root.right);

    max = Math.max(max, root.val);
    max = Math.max(max, root.val + maxSumLeft);
    max = Math.max(max, root.val + maxSumRight);
    max = Math.max(max, root.val + maxSumLeft + maxSumRight);

    // Pick either right or left (if non negative) and add current
    return root.val + Math.max(0, Math.max(maxSumLeft, maxSumRight));
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
