package leetcode.queue_and_stack;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
class MinStack {

  public static void main(String[] args) {
    MinStack stack = new MinStack();
    stack.push(-2);
    stack.push(0);
    stack.push(-3);
    System.out.println(stack.getMin());
    stack.pop();
    System.out.println(stack.top());
    System.out.println(stack.getMin());
  }

  private Stack<NodeWithMin> stack = new Stack<>();

  public MinStack() {
  }

  public void push(int x) {
    if (stack.isEmpty()) {
      stack.push(new NodeWithMin(x, x));
      return;
    }
    NodeWithMin previous = stack.peek();
    NodeWithMin newNode = new NodeWithMin(x, Math.min(previous.min, x));
    stack.push(newNode);
  }

  public void pop() {
    if (stack.isEmpty()) {
      return;
    }
    stack.pop();
  }

  public int top() {
    if (stack.isEmpty()) {
      return -1;
    }
    return stack.peek().value;
  }

  public int getMin() {
    if (stack.isEmpty()) {
      return -1;
    }
    return stack.peek().min;
  }

  static class NodeWithMin {
    int value;
    int min;

    NodeWithMin(int value, int min) {
      this.value = value;
      this.min = min;
    }
  }
}
