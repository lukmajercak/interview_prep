package leetcode.google;

public class FlipEquivalentBinaryTrees {

  /**
   * For a binary tree T, we can define a flip operation as follows: choose any node,
   * and swap the left and right child subtrees.
   *
   * A binary tree X is flip equivalent to a binary tree Y if and only if we can make
   * X equal to Y after some number of flip operations.
   *
   * Write a function that determines whether two binary trees are flip equivalent.
   * The trees are given by root nodes root1 and root2.
   */
  public static void main(String[] args) {
    FlipEquivalentBinaryTrees solution = new FlipEquivalentBinaryTrees();

    // Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
    // Output: true
    // Explanation: We flipped at nodes with values 1, 3, and 5.
    TreeNode root1 = new TreeNode(1);
    root1.left = new TreeNode(2);
    root1.left.left = new TreeNode(4);
    root1.left.right = new TreeNode(5);
    root1.left.right.left = new TreeNode(7);
    root1.left.right.right = new TreeNode(8);
    root1.right = new TreeNode(3);
    root1.right.left = new TreeNode(6);

    TreeNode root2 = new TreeNode(1);
    root2.left = new TreeNode(3);
    root2.left.right = new TreeNode(6);
    root2.right = new TreeNode(2);
    root2.right.left = new TreeNode(4);
    root2.right.right = new TreeNode(5);
    root2.right.right.left = new TreeNode(8);
    root2.right.right.right = new TreeNode(7);

    System.out.println(solution.flipEquiv(root1, root2));


    // [0,4,1,9,17,3,2,15,11,18,null,5,10,null,14,null,22,null,12,null,null,6,null,null,16,24,null,null,null,null,null,null,7,null,null,null,null,8,null,21,13,null,null,23,19,null,null,20]
    // [0,1,4,3,2,9,17,10,5,14,null,19,11,null,18,null,16,null,6,24,null,null,20,null,12,null,null,null,null,7,null,null,null,null,null,null,null,null,8,21,13,null,null,15,23,null,22]
  }

  public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if (root1 == null) {
      return root2 == null;
    }
    if (root2 == null) {
      return root1 == null;
    }

    Integer valLeft1 = root1.left == null ? null : root1.left.val;
    Integer valRight1 = root1.right == null ? null : root1.right.val;

    Integer valLeft2 = root2.left == null ? null : root2.left.val;
    Integer valRight2 = root2.right == null ? null : root2.right.val;

    if (valLeft1 != valLeft2 && valRight1 == valRight2) {
      return false;
    }
    if (valRight1 != valRight2 && valLeft1 == valLeft2) {
      return false;
    }

    if (valLeft1 != valLeft2) {
      return flipEquiv(root1.left, root2.right) &&
          flipEquiv(root1.right, root2.left);
    }

    return flipEquiv(root1.left, root2.left) &&
        flipEquiv(root1.right, root2.right);
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
