package leetcode.google;

public class InsertIntoCyclicSortedList {

  /**
   * Given a node from a cyclic linked list which is sorted in ascending order,
   * write a function to insert a value into the list such that it remains a cyclic sorted list.
   * The given node can be a reference to any single node in the list, and may not be necessarily
   * the smallest value in the cyclic list.
   *
   * If there are multiple suitable places for insertion, you may choose any place to insert the
   * new value.
   * After the insertion, the cyclic list should remain sorted.
   *
   * If the list is empty (i.e., given node is null), you should create a new single cyclic list
   * and return the reference to that single node. Otherwise, you should return the original
   * given node.
   *
   * The following example may help you understand the problem better:
   */
  public static void main(String[] args) {
    InsertIntoCyclicSortedList solution = new InsertIntoCyclicSortedList();
    //
    //  1  >  .
    //  ^     |
    //  4  <  3
    //
    //
    // result:
    //  1  >  2
    //  ^     |
    //  4  <  3
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n1 = new Node(1);
    n3.next = n4;
    n4.next = n1;
    n1.next = n3;
    solution.insert(n3, 2);

    n1 = new Node(1);
    n1.next = n1;
    solution.insert(n1, 2);

    n1 = new Node(1);
    n3 = new Node(3);
    Node n5 = new Node(5);
    n1.next = n3;
    n3.next = n5;
    n5.next = n1;
    solution.insert(n1, 2);

    n1 = new Node(1);
    n3 = new Node(3);
    n5 = new Node(5);
    n1.next = n3;
    n3.next = n5;
    n5.next = n1;
    solution.insert(n1, 0);

    n1 = new Node(1);
    n3 = new Node(3);
    n5 = new Node(5);
    n1.next = n3;
    n3.next = n5;
    n5.next = n1;
    solution.insert(n1, 6);

    n1 = new Node(1);
    n3 = new Node(3);
    Node n3_2 = new Node(3);
    n1.next = n3;
    n3.next = n3_2;
    n3_2.next = n1;
    solution.insert(n1, 4);
  }

  public Node insert(Node head, int insertVal) {
    if (head == null) {
      return new Node(insertVal, null);
    }

    Node maximum = findMaxNode(head);

    Node previous = maximum;
    Node next = maximum.next;

    while (next.val < insertVal && next != maximum) {
      previous = next;
      next = next.next;
    }

    if (next.val < insertVal) {
      next.next = new Node(insertVal, next.next);
    } else {
      previous.next = new Node(insertVal, next);
    }

    return head;
  }

  Node findMaxNode(Node n) {
    Node maxNode = n;
    Node next = n.next;
    while (next != n) {
      if (next.val >= maxNode.val) {
        maxNode = next;
      }
      next = next.next;
    }
    return maxNode;
  }

  static class Node {
    public int val;
    public Node next;

    public Node(int val) {
      this.val = val;
    }

    public Node(int _val, Node _next) {
      val = _val;
      next = _next;
    }
  }
}
