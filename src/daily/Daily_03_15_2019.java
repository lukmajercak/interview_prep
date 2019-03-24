package daily;

public class Daily_03_15_2019 {

  /**
   * This problem was asked by Google.
   *
   * Implement locking in a binary tree. A binary tree node can be locked or unlocked only if all of its descendants or ancestors are not locked.
   *
   * Design a binary tree node class with the following methods:
   *
   * is_locked, which returns whether the node is locked
   * lock, which attempts to lock the node. If it cannot be locked, then it should return false. Otherwise, it should lock it and return true.
   * unlock, which unlocks the node. If it cannot be unlocked, then it should return false. Otherwise, it should unlock it and return true.
   * You may augment the node to add parent pointers or any other property you would like.
   * You may assume the class is used in a single-threaded program, so there is no need for actual locks or mutexes.
   * Each method should run in O(h), where h is the height of the tree.
   */
  public static void main(String[] args) {
    //           1
    //       2      3
    //    4    5   6  7
    //  8     9   10   11
    Node n1 = new Node();
    Node n2 = new Node();
    Node n3 = new Node();
    Node n4 = new Node();
    Node n5 = new Node();
    Node n6 = new Node();
    Node n7 = new Node();
    Node n8 = new Node();
    Node n9 = new Node();
    Node n10 = new Node();
    Node n11 = new Node();

    n1.left = n2;
    n1.right = n3;

    n2.parent = n1;
    n2.left = n4;
    n2.right = n5;

    n4.parent = n2;
    n4.left = n8;

    n5.parent = n2;
    n5.left = n9;

    n9.parent = n5;

    n3.parent = n1;
    n3.left = n6;
    n3.right = n7;

    n6.parent = n3;
    n6.left = n10;

    n7.parent = n3;
    n7.right = n11;

    n11.parent = n7;


    System.out.println("lock n3: " + n3.lock());
    System.out.println("lock n6: " + n6.lock());
    System.out.println("lock n11: " + n6.lock());
    System.out.println("lock n1: " + n1.lock());
    System.out.println("lock n5: " + n5.lock());
  }

  static class Node {
    Node left;
    Node right;
    Node parent;
    boolean is_locked;
    int numLockedDescendants = 0;

    boolean isLocked() {
      return is_locked;
    }

    boolean canLockOrUnlock() {
      if (numLockedDescendants > 0) {
        return false;
      }

      Node current = this;
      while (current != null) {
        if (current.is_locked) {
          return false;
        }
        current = current.parent;
      }
      return true;
    }

    boolean lock() {
      if (is_locked) {
        return false;
      }
      if (!canLockOrUnlock()) {
        return false;
      }
      this.is_locked = true;

      Node current = this;
      while (current != null) {
        current.numLockedDescendants += 1;
        current = current.parent;
      }
      return true;
    }

    boolean unlock() {
      if (!is_locked) {
        return false;
      }
      if (!canLockOrUnlock()) {
        return false;
      }
      this.is_locked = false;

      Node current = this;
      while (current != null) {
        current.numLockedDescendants -= 1;
        current = current.parent;
      }
      return true;
    }
  }
}
