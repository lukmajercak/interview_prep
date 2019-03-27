package leetcode.queue_and_stack;

import java.util.*;

public class BinaryTreeInorderTraversal {

  /**
   * Given a binary tree, return the inorder traversal of its nodes' values.
   *
   * Follow up: Recursive solution is trivial, could you do it iteratively?
   */
  public static void main(String[] args) {
    BinaryTreeInorderTraversal solution = new BinaryTreeInorderTraversal();

    //    1
    //     \
    //      2
    //     /
    //    3
    //
    // Output: [1,3,2]
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);

    n1.right = n2;
    n2.left = n3;

    System.out.println(solution.inorderTraversal(n1));

    //       1
    //     /   \
    //    2     3
    //  /   \  /  \
    // 4    5  6   7
    //
    // 4, 2, 5, 1, 6, 3, 7
  }

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> traversal = new ArrayList<>();
    inorderTraversalIterative(root, traversal);
    return traversal;
  }

  public void inorderTraversalIterative(TreeNode node, List<Integer> traversal) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = node;

    while (current != null || !stack.isEmpty()) {
      // Reach the left most Node of the current Node
      while (current != null) {
        // Place pointer to a tree node on the stack before traversing
        // the node's left subtree
        stack.push(current);
        current = current.left;
      }
      // Current must be null this point
      current = stack.pop();

      traversal.add(current.val);

      // We have visited the node and its left subtree.  Now, it's right subtree's turn
      current = current.right;
    }
  }

  public void inorderTraversalRecursive(TreeNode node, List<Integer> traversal) {
    if (node == null) {
      return;
    }
    inorderTraversalRecursive(node.left, traversal);
    traversal.add(node.val);
    inorderTraversalRecursive(node.right, traversal);
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
