package leetcode.google;

public class CountCompleteTreeNodes {

  /**
   * Given a complete binary tree, count the number of nodes.
   *
   * Note:
   *
   * Definition of a complete binary tree from Wikipedia:
   * In a complete binary tree every level, except possibly the last, is completely filled,
   * and all nodes in the last level are as far left as possible.
   * It can have between 1 and 2h nodes inclusive at the last level h.
   */
  public static void main(String[] args) {
    CountCompleteTreeNodes solution = new CountCompleteTreeNodes();

    // Input:
    //    1
    //   / \
    //  2   3
    // /
    //4
    //
    // Output: 4
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(4);
    root.right = new TreeNode(3);
    System.out.println(solution.countNodes(root));

    // Input:
    //    1
    //   / \
    //  2   3
    // / \  /
    //4  5 6
    //
    // Output: 6
    root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(6);
    System.out.println(solution.countNodes(root));
  }

  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = getLeftHeight(root) + 1;
    int right = getRightHeight(root) + 1;

    if (left == right) {
      return (2 << (left-1)) - 1;
    } else {
      return countNodes(root.left) + countNodes(root.right) + 1;
    }
  }

  public int getLeftHeight(TreeNode n){
    if (n == null) {
      return 0;
    }

    int height = 0;
    while (n.left != null) {
      height++;
      n = n.left;
    }
    return height;
  }

  public int getRightHeight(TreeNode n){
    if (n == null) {
      return 0;
    }

    int height = 0;
    while (n.right != null) {
      height++;
      n = n.right;
    }
    return height;
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
