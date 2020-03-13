package daily;

public class RemoveKthLastFromLinkedList {

  /**
   * This problem was asked by Google.
   *
   * Given a singly linked list and an integer k, remove the kth last element from the list.
   * k is guaranteed to be smaller than the length of the list.
   *
   * The list is very long, so making more than one pass is prohibitively expensive.
   *
   * Do this in constant space and in one pass.
   */
  public static void main(String[] args) {
    RemoveKthLastFromLinkedList solution = new RemoveKthLastFromLinkedList();

    // 1 -> null
    Node n1 = new Node(1);
    System.out.println(solution.removeKth(n1, 1)); // null

    // 1 -> 2 -> null
    n1 = new Node(1);
    Node n2 = new Node(2);
    n1.next = n2;
    System.out.println(solution.removeKth(n1, 1)); // 1

    // 1 -> 2 -> 3 -> 4 -> 5 -> null
    n1 = new Node(1);
    n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    Node res = solution.removeKth(n1, 3);
    System.out.println(res); // 1, 2, 4, 5

    res = solution.removeKth(res, 4);
    System.out.println(res); // 2, 4, 5

    res = solution.removeKth(res, 1);
    System.out.println(res); // 2, 4

    res = solution.removeKth(res, 2);
    System.out.println(res); // 4
  }

  Node removeKth(Node n, int k) {
    Node dummy = new Node(0, n);

    Node findLast = dummy;
    Node findBeforeKth = dummy;
    while (k >= 0 && findLast != null) {
      findLast = findLast.next;
      k--;
    }

    while (findLast != null) {
      findLast = findLast.next;
      findBeforeKth = findBeforeKth.next;
    }

    findBeforeKth.next = findBeforeKth.next.next;

    return dummy.next;
  }

  static class Node {
    Node next;
    int value;

    Node(int value) {
      this(value, null);
    }

    Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }

    @Override
    public String toString() {
      StringBuilder str = new StringBuilder(Integer.toString(value));
      Node n = next;
      while (n != null) {
        str.append(",").append(n.value);
        n = n.next;
      }
      return str.toString();
    }
  }
}
