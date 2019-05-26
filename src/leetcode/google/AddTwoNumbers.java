package leetcode.google;

public class AddTwoNumbers {

  /**
   * You are given two non-empty linked lists representing two non-negative integers.
   * The digits are stored in reverse order and each of their nodes contain a single digit.
   * Add the two numbers and return it as a linked list.
   *
   * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
   */
  public static void main(String[] args) {
    AddTwoNumbers solution = new AddTwoNumbers();

    // Input: (2 -> 4 -> 3) +
    //        (5 -> 6 -> 4)
    // Output: 7 -> 0 -> 8
    // Explanation: 342 + 465 = 807.

    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(3);

    ListNode l2 = new ListNode(5);
    l2.next = new ListNode(6);
    l2.next.next = new ListNode(4);

    System.out.println(solution.addTwoNumbers(l1, l2));
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return addTwoNumbers(l1, l2, 0);
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
    if (l1 == null && l2 == null) {
      if (carry == 0) {
        return null;
      }
      return new ListNode(carry);
    }

    int sum = carry;
    if (l1 != null) {
      sum += l1.val;
    }
    if (l2 != null) {
      sum += l2.val;
    }

    ListNode result = new ListNode(sum % 10);
    result.next = addTwoNumbers(l1 != null ? l1.next : null,
        l2 != null ? l2.next : null, sum / 10);
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
