package leetcode.google;

public class MergeTwoSortedLists {

  /**
   * Merge two sorted linked lists and return it as a new list. T
   * he new list should be made by splicing together the nodes of the first two lists.
   */
  public static void main(String[] args) {
    MergeTwoSortedLists solution = new MergeTwoSortedLists();

    // Input: 1->2->4, 1->3->4
    // Output: 1->1->2->3->4->4
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(4);

    ListNode l2 = new ListNode(1);
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(4);

    ListNode result = solution.mergeTwoLists(l1, l2);
    System.out.println(result);

    System.out.println(solution.mergeTwoLists(l1, null));
    System.out.println(solution.mergeTwoLists(null, l2));
    System.out.println(solution.mergeTwoLists(result, l2));
    System.out.println(solution.mergeTwoLists(result, result));
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode result = null;
    ListNode tail = null;

    while (l1 != null || l2 != null) {
      int next;
      if (l1 == null) {
        next = l2.val;
        l2 = l2.next;
      } else if (l2 == null) {
        next = l1.val;
        l1 = l1.next;
      } else {
        next = Math.min(l1.val, l2.val);
        if (l1.val < l2.val) {
          l1 = l1.next;
        } else {
          l2 = l2.next;
        }
      }

      if (result == null) {
        result = new ListNode(next);
        tail = result;
      } else {
        tail.next = new ListNode(next);
        tail = tail.next;
      }
    }
    return result;
  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }

    @Override
    public String toString() {
      return val + " -> " + next;
    }
  }
}
