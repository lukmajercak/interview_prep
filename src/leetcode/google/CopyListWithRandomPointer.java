package leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

  /**
   * A linked list is given such that each node contains an additional random
   * pointer which could point to any node in the list or null.
   *
   * Return a deep copy of the list.
   */
  public static void main(String[] args) {
    CopyListWithRandomPointer solution = new CopyListWithRandomPointer();

    // Input:
    // {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
    //
    //Explanation:
    // Node 1's value is 1, both of its next and random pointer points to Node 2.
    // Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
    Node n1 = new Node();
    n1.val = 1;
    Node n2 = new Node();
    n1.next = n2;
    n2.val = 2;
    n2.next = null;
    n2.random = n2;

    Node res = solution.copyRandomList(n1);
  }

  public Node copyRandomList(Node head) {
    return copyRandomList(head, new HashMap<>());
  }

  public Node copyRandomList(Node head, Map<Node, Node> copies) {
    if (head == null) {
      return null;
    }
    if (copies.containsKey(head)) {
      return copies.get(head);
    }

    Node copy = new Node();
    copy.val = head.val;
    copies.put(head, copy);
    copy.next = copyRandomList(head.next, copies);
    copy.random = copyRandomList(head.random, copies);
    return copy;
  }

  static class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {
    }

    public Node(int _val, Node _next, Node _random) {
      val = _val;
      next = _next;
      random = _random;
    }
  }
}
