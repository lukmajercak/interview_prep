package udemy_11_essential;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class lowest_common_ancestor {

  public static void main(String[] args) {
    // NOTE: The following input values will be used for testing your solution.
    // The mapping we're going to use for constructing a tree.
    // For example, {0: [1, 2]} means that 0's left child is 1, and its right
    // child is 2.
    HashMap<Integer, int[]> mapping1 = new HashMap<Integer, int[]>();
    int[] childrenA = {1, 2};
    int[] childrenB = {3, 4};
    int[] childrenC = {5, 6};
    mapping1.put(0, childrenA);
    mapping1.put(1, childrenB);
    mapping1.put(2, childrenC);

    TreeNode head1 = createTree(mapping1, 0);

    // This tree is:
    // head1 = 0
    //        / \
    //       1   2
    //      /\   /\
    //     3  4 5  6


    HashMap<Integer, int[]> mapping2 = new HashMap<Integer, int[]>();
    int[] childrenD = {1, 4};
    int[] childrenE = {3, 8};
    int[] childrenF = {9, 2};
    int[] childrenG = {6, 7};
    mapping2.put(5, childrenD);
    mapping2.put(1, childrenE);
    mapping2.put(4, childrenF);
    mapping2.put(3, childrenG);

    TreeNode head2 = createTree(mapping2, 5);
    // This tree is:
    //  head2 = 5
    //        /   \
    //       1     4
    //      /\    / \
    //     3  8  9  2
    //    /\
    //   6  7


    // lca(head1, 1, 5) should return 0
    System.out.println(lca(head1, 1, 5));
    // lca(head1, 3, 1) should return 1
    System.out.println(lca(head1, 3, 1));
    // lca(head1, 1, 4) should return 1
    System.out.println(lca(head1, 1, 4));
    // lca(head1, 0, 5) should return 0
    System.out.println(lca(head1, 0, 5));
    // lca(head2, 4, 7) should return 5
    System.out.println(lca(head2, 4, 7));
    // lca(head2, 3, 3) should return 3
    System.out.println(lca(head2, 3, 3));
    // lca(head2, 8, 7) should return 1
    System.out.println(lca(head2, 8, 7));
    // lca(head2, 3, 0) should return null (0 does not exist in the tree)
    System.out.println(lca(head2, 3, 0));
  }

  public static TreeNode lca(TreeNode root, int j, int k) {
    Stack<TreeNode> pathToJ = pathToNode(root, j);
    if (pathToJ == null) {
      return null;
    }
    Stack<TreeNode> pathToK = pathToNode(root, k);
    if (pathToK == null) {
      return null;
    }

    TreeNode toReturn = null;
    while (!pathToJ.isEmpty() && !pathToK.isEmpty()) {
      TreeNode nextJ = pathToJ.pop();
      TreeNode nextK = pathToK.pop();
      if (nextJ == nextK) {
        toReturn = nextJ;
      } else {
        break;
      }
    }
    return toReturn;
  }

  public static Stack<TreeNode> pathToNode(TreeNode node, int value) {
    if (node == null) {
      return null;
    }

    if (node.value == value) {
      Stack<TreeNode> stack = new Stack<>();
      stack.push(node);
      return stack;
    }

    if (node.left != null) {
      Stack<TreeNode> pathViaLeft = pathToNode(node.left, value);
      if (pathViaLeft != null) {
        pathViaLeft.push(node);
        return pathViaLeft;
      }
    }
    if (node.right != null) {
      Stack<TreeNode> pathViaRight = pathToNode(node.right, value);
      if (pathViaRight != null) {
        pathViaRight.push(node);
        return pathViaRight;
      }
    }
    return null;
  }

  // A function for creating a tree.
  // Input:
  // - mapping: a node-to-node mapping that shows how the tree should be constructed
  // - headValue: the value that will be used for the head ndoe
  // Output:
  // - The head node of the resulting tree
  public static TreeNode createTree(HashMap<Integer, int[]> mapping, int headValue) {
    TreeNode head = new TreeNode(headValue, null, null);
    HashMap<Integer, TreeNode> nodes = new HashMap<>();
    nodes.put(headValue, head);
    for(Integer key : mapping.keySet()) {
      int[] value = mapping.get(key);
      TreeNode leftChild = new TreeNode(value[0], null, null);
      TreeNode rightChild = new TreeNode(value[1], null, null);
      nodes.put(value[0], leftChild);
      nodes.put(value[1], rightChild);
    }
    for(Integer key : mapping.keySet()) {
      int[] value = mapping.get(key);
      nodes.get(key).left = nodes.get(value[0]);
      nodes.get(key).right = nodes.get(value[1]);
    }
    return head;
  }


  public static class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value, TreeNode left, TreeNode right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }

    // The string representation of this node.
    // Will be used for testing.
    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }
}
