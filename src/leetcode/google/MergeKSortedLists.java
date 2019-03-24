package leetcode.google;

public class MergeKSortedLists {

  /**
   * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
   */
  public static void main(String[] args) {
    MergeKSortedLists solution = new MergeKSortedLists();

    // Example:
    //
    //Input:
    //[
    //  1->4->5,
    //  1->3->4,
    //  2->6
    //]
    //Output: 1->1->2->3->4->4->5->6
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(5);

    ListNode l2 = new ListNode(1);
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(4);

    ListNode l3 = new ListNode(2);
    l3.next = new ListNode(6);

    System.out.println(printList(solution.mergeKLists(new ListNode[]{l1, l2, l3})));

    l1 = new ListNode(1);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(5);
    System.out.println(printList(solution.mergeKLists(new ListNode[]{l1})));
    System.out.println(printList(solution.mergeKLists(new ListNode[]{})));
  }

  /**
   *
   // merge pairs of lists
   // [L1, L2, L3, L4, L5, L6]
   // merge L1, L6, store
   // [L1+L6, L2, L3, L4, L5, L6]
   // merge L2, L5
   // [L1+L6, L2+L5, L3, L4, L5, L6]
   // merge L3, L4
   // [L1+L6, L2+L5, L3+L4, L4, L5, L6]
   // do the same, start = 0, end = 2
   */
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) {
      return null;
    }
    int end = lists.length - 1;

    while (end != 0) {

      int start = 0;
      int j = end;

      while (start < j) {
        lists[start] = mergeTwoLists(lists[start], lists[j]);

        start++;
        j--;

        if (start >= j) {
          end = j;
        }
      }
    }
    return lists[0];
  }

  ListNode mergeTwoLists(ListNode a, ListNode b) {
    ListNode result = null;
    if (a == null) {
      return b;
    }
    if (b == null) {
      return a;
    }
    if (a.val < b.val) {
      result = a;
      result.next = mergeTwoLists(a.next, b);
    } else {
      result = b;
      result.next = mergeTwoLists(a, b.next);
    }
    return result;
  }

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  static String printList(ListNode node) {
    StringBuilder str = new StringBuilder();
    while (node != null) {
      str.append(node.val).append(", ");
      node = node.next;
    }
    return str.toString();
  }
}
