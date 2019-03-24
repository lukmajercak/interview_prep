package leetcode.queue_and_stack;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostorderTraversal {

  /**
   * Given a binary tree, return the postorder traversal of its nodes' values.
   *
   * Follow up: Recursive solution is trivial, could you do it iteratively?
   */
  public static void main(String[] args) {
    BinaryTreePostorderTraversal solution = new BinaryTreePostorderTraversal();

    //    1
    //     \
    //      2
    //     / \
    //    3   4
    //
    // Output: [3,4,2,1]
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);

    n1.right = n2;
    n2.left = n3;
    n2.right = n4;

    System.out.println(solution.postorderTraversal(n1));
  }

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> traversal = new ArrayList<>();
    postorderTraversalIterative(root, traversal);
    return traversal;
  }

  public void postorderTraversalIterative(TreeNode node, List<Integer> traversal) {
    // TODO:
  }

  static public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
