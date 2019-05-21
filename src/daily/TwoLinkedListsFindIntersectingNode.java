package daily;

public class TwoLinkedListsFindIntersectingNode {

  /**
   * This problem was asked by Google.
   *
   * Given two singly linked lists that intersect at some point, find the intersecting node. The lists are non-cyclical.
   *
   * For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return the node with value 8.
   *
   * In this example, assume nodes with the same value are the exact same node objects.
   *
   * Do this in O(M + N) time (where M and N are the lengths of the lists) and constant space.
   */
  public static void main(String[] args) {
    TwoLinkedListsFindIntersectingNode solution = new TwoLinkedListsFindIntersectingNode();

    /**
     * A = 3 -> 7 -> 8 -> 10
     * B = 99 -> 1 -> 8 -> 10
     * return 8
     */
    Node eight = new Node(8, new Node(10, null));
    Node a = new Node(3, new Node(7, eight));
    Node b = new Node(99, new Node(1, eight));

    System.out.println(solution.findIntersecting(a, b).value);

    /**
     * A = 0 -> 2 -> 3 -> 7 -> 8 -> 10
     * B =          99 -> 1 -> 8 -> 10
     * return 8
     */
    a = new Node(0, new Node(2, new Node(3, new Node(7, eight))));
    System.out.println(solution.findIntersecting(a, b).value);

    /**
     * A =      0 -> 2 -> 3  -> 7 -> 8 -> 10
     * B = 1 -> 4 -> 5 -> 99 -> 7 -> 8 -> 10
     * return 7
     */
    Node seven = new Node(7, eight);
    a = new Node(0, new Node(2, new Node(3, seven)));
    b = new Node(1, new Node(4, new Node(5, new Node(99, seven))));
    System.out.println(solution.findIntersecting(a, b).value);
  }

  Node findIntersecting(Node a, Node b) {
    int lengthA = getLength(a);
    int lengthB = getLength(b);

    if (lengthA > lengthB) {
      a = advanceBy(a, lengthA - lengthB);
    } else if (lengthB > lengthA) {
      b = advanceBy(b, lengthB - lengthA);
    }

    while (a != b) {
      a = a.next;
      b = b.next;
    }

    return a;
  }

  Node advanceBy(Node list, int index) {
    while (index-- > 0) {
      list = list.next;
    }
    return list;
  }

  int getLength(Node a) {
    int length = 0;
    while (a != null) {
      a = a.next;
      length++;
    }
    return length;
  }

  static class Node {
    Node next;
    int value;

    Node(int value, Node next) {
      this.next = next;
      this.value = value;
    }
  }
}
