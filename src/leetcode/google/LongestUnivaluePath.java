package leetcode.google;

public class LongestUnivaluePath {

  /**
   * Given a binary tree, find the length of the longest path where each node in the path has the same value.
   * This path may or may not pass through the root.
   *
   * Note: The length of path between two nodes is represented by the number of edges between them.
   */
  public static void main(String[] args) {
    LongestUnivaluePath solution = new LongestUnivaluePath();
    // Example 1:
    //Input:
    //
    //              5
    //             / \
    //            4   5
    //           / \   \
    //          1   1   5
    //Output: 2
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(4);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(1);
    root.right = new TreeNode(5);
    root.right.right = new TreeNode(5);
    System.out.println(solution.longestUnivaluePath(root));

    // Example 2:
    //Input:
    //
    //              1
    //             / \
    //            4   5
    //           / \   \
    //          4   4   5
    //Output: 2
    root = new TreeNode(1);
    root.left = new TreeNode(4);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(4);
    root.right = new TreeNode(5);
    root.right.right = new TreeNode(5);
    System.out.println(solution.longestUnivaluePath(root));


    //          1
    //       2      2
    //   2     2       2
    // 2
    // output: 3
    root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(2);
    root.left.left.left = new TreeNode(2);
    root.left.right = new TreeNode(2);
    root.right = new TreeNode(2);
    root.right.right = new TreeNode(2);
    System.out.println(solution.longestUnivaluePath(root));

    // 1
    root = new TreeNode(1);
    System.out.println(solution.longestUnivaluePath(root));

    // empty
    root = new TreeNode(1);
    System.out.println(solution.longestUnivaluePath(null));


    //          1
    //       3      2
    //   3     2       2
    // 2        2
    //        2   2
    //      4      2
    // output: 4
    root = new TreeNode(1);
    root.left = new TreeNode(3);
    root.left.left = new TreeNode(3);
    root.left.left.left = new TreeNode(2);
    root.left.right = new TreeNode(2);
    root.left.right.right = new TreeNode(2);
    root.left.right.right.left = new TreeNode(2);
    root.left.right.right.left.left = new TreeNode(4);
    root.left.right.right.right = new TreeNode(2);
    root.left.right.right.right.right = new TreeNode(2);
    root.right = new TreeNode(2);
    root.right.right = new TreeNode(2);
    System.out.println(solution.longestUnivaluePath(root));

    //Input :
    //              2
    //             / \
    //            7   2
    //           / \   \
    //          1   1   2
    //Output : 2
    root = new TreeNode(2);
    root.left = new TreeNode(7);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(1);
    root.right = new TreeNode(2);
    root.right.right = new TreeNode(2);
    System.out.println(solution.longestUnivaluePath(root));

    //
    //Input :
    //              4
    //             / \
    //            4   4
    //           / \   \
    //          4   9   5
    //Output : 3
    root = new TreeNode(4);
    root.left = new TreeNode(4);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(9);
    root.right = new TreeNode(4);
    root.right.right = new TreeNode(5);
    System.out.println(solution.longestUnivaluePath(root));


    //
    //Input :
    //              4
    //             / \
    //            4   4
    //           / \   \
    //          4   9   5
    //                /   \
    //               5     5
    //Output : 3
    root = new TreeNode(4);
    root.left = new TreeNode(4);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(9);
    root.right = new TreeNode(4);
    root.right.right = new TreeNode(5);
    root.right.right.left = new TreeNode(5);
    root.right.right.right = new TreeNode(5);
    System.out.println(solution.longestUnivaluePath(root));
  }

  //Global max
  int max;

  public int longestUnivaluePath(TreeNode root) {
    max = 0;
    longestPath(root);
    return max;
  }

  public int longestPath(TreeNode node) {
    // Base case: if null node, return 0
    if (node == null) {
      return 0;
    }
    // Get the max length from left and right
    int maxLeft = longestPath(node.left);
    int maxRight = longestPath(node.right);
    // Calculate the current max length
    int maxLeftSoFar = 0;
    int maxRightSoFar = 0;
    // Compare parent node with child node
    // If they are the same, extend the max length by one
    if (node.left != null && node.left.val == node.val) {
      maxLeftSoFar = maxLeft + 1;
    }
    if(node.right != null && node.right.val == node.val) {
      maxRightSoFar = maxRight + 1;
    }
    // Update the max with the sum of left and right length
    max = Math.max(max, maxLeftSoFar + maxRightSoFar);
    // Return the max from left and right to upper node
    // since only one side path is valid
    return Math.max(maxLeftSoFar,maxRightSoFar);
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
