package leetcode.google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class InOrderSuccessorInBST {

  /**
   * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
   *
   * The successor of a node p is the node with the smallest key greater than p.val.
   */
  public static void main(String[] args) {
    InOrderSuccessorInBST solution = new InOrderSuccessorInBST();

    // Example 1:
    // Input: root = [2,1,3], p = 1
    //Output: 2
    //Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
    TreeNode root = new TreeNode(2);
    TreeNode n1 = new TreeNode(1);
    root.left = n1;
    root.right = new TreeNode(3);
    System.out.println(solution.inorderSuccessor(root, n1));

    // Example 2:
    //
    //
    //Input: root = [5,3,6,2,4,null,null,1], p = 6
    //Output: null
    //Explanation: There is no in-order successor of the current node, so the answer is null.

    TreeNode n5 = new TreeNode(5);
    TreeNode n3 = new TreeNode(3);
    TreeNode n2 = new TreeNode(2);
    TreeNode n4 = new TreeNode(4);
    TreeNode n6 = new TreeNode(6);
    n1 = new TreeNode(1);
    n5.left = n3;
    n5.right = n6;
    n3.left = n2;
    n3.right = n4;
    n2.left = n1;
    System.out.println(solution.inorderSuccessor(n5, n1));
    System.out.println(solution.inorderSuccessor(n5, n2));
    System.out.println(solution.inorderSuccessor(n5, n3));
    System.out.println(solution.inorderSuccessor(n5, n4));
    System.out.println(solution.inorderSuccessor(n5, n5));
    System.out.println(solution.inorderSuccessor(n5, n6));
  }

  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (p.right != null) {
      return minNode(p.right);
    }

    TreeNode previous = null;
    while (root != null) {
      if (root.val > p.val) {
        previous = root;
        root = root.left;
      } else if (root.val < p.val) {
        root = root.right;
      } else {
        break;
      }
    }
    return previous;
  }

  TreeNode minNode(TreeNode n) {
    TreeNode next = n.left;
    while (next != null) {
      n = next;
      next = next.left;
    }
    return n;
  }

  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }

    @Override
    public String toString() {
      return new Integer(val).toString();
    }
  }
}
