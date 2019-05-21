package leetcode.google;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * - the difference in height between left and right is always <= 1
 *
 *
 *
 * Rotations:
 * After-LL    -> rotate right
 * After-LR    -> 2 rotations, first rotate left child to the left, then rotate to the right
 * After-RR    -> rotate left
 * After-RL    -> 2 rotations, first rotate right child to the right, then rotate to the left
 */
public class AVLTree {

  public static void main(String[] args) {
    AVLNode tree = new AVLNode(30);
    tree = add(tree, 10, new AtomicInteger());
    tree = add(tree, 20, new AtomicInteger());
  }


  static AVLNode add(AVLNode node, int value, AtomicInteger numSmaller) {
    // 1. Add as in a regular BST
    if (node == null) {
      return new AVLNode(value);
    }

    if (value <= node.value) {
      // insert left
      node.left = add(node.left, value, numSmaller);
    } else {
      // insert right
      node.right = add(node.right, value, numSmaller);

      numSmaller.set(numSmaller.get() + size(node.left));
      if (node.value != value) {
        numSmaller.incrementAndGet();
      }
    }

    // 4. Update height of this node
    node.height = Math.max(height(node.left), height(node.right)) + 1;
    node.size = size(node.left) + size(node.right) + 1;

    // 3. Calculate balance, check if imbalanced
    int balance = balance(node);

    if (balance > 1 && value <= node.left.value) {
      // LL
      return rightRotate(node);
    }
    if (balance < -1 && value > node.right.value) {
      // RR
      return leftRotate(node);
    }
    if (balance > 1 && value > node.left.value) {
      // LR
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }
    if (balance < -1 && value <= node.right.value) {
      // RL
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }
    return node;
  }

  /**
   *              5235
   *                    5263
   *                          5263
   *
   */
  static AVLNode rightRotate(AVLNode node) {
    //     N
    //   B   nr
    // C  br

    //     B
    //  C     N
    //      br  nr
    AVLNode newRootRight= null;
    AVLNode newRoot = node.left;
    try {
      newRootRight = newRoot.right;
    } catch (NullPointerException e) {
      throw e;
    }

    newRoot.right = node;
    node.left = newRootRight;

    // Update height
    node.height = Math.max(height(node.left), height(node.right)) + 1;
    newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

    // Update size
    node.size = size(node.left) + size(node.right) + 1;
    newRoot.size = size(newRoot.left) + size(newRoot.right) + 1;

    return newRoot;
  }

  static AVLNode leftRotate(AVLNode node) {
    //      N
    //   nl   B
    //      bl  C

    //      B
    //   N     C
    // nl  bl
    AVLNode newRoot = node.right;
    AVLNode newRootLeft = newRoot.left;

    newRoot.left = node;
    node.right = newRootLeft;

    // Update height
    node.height = Math.max(height(node.left), height(node.right)) + 1;
    newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

    // Update size
    node.size = size(node.left) + size(node.right) + 1;
    newRoot.size = size(newRoot.left) + size(newRoot.right) + 1;

    return newRoot;
  }

  static int balance(AVLNode node) {
    return height(node.left) - height(node.right);
  }

  static int height(AVLNode node) {
    return node != null ? node.height : 0;
  }

  static int size(AVLNode node) {
    return node != null ? node.size : 0;
  }

  static class AVLNode {
    final int value;
    AVLNode left;
    AVLNode right;

    int height;
    int size;

    AVLNode(int value) {
      this.value = value;
      this.height = 1;
      this.size = 1;
    }
  }
}
