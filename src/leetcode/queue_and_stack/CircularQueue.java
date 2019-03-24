package leetcode.queue_and_stack;

public class CircularQueue {

  int[] values;
  int size;

  int start = -1;
  int end = -1;

  /** Initialize your data structure here. Set the size of the queue to be k. */
  public CircularQueue(int k) {
    this.values = new int[k];
    this.size = k;
  }

  /** Insert an element into the circular queue. Return true if the operation is successful. */
  public boolean enQueue(int value) {
    if (start == -1 && end == -1) {
      start = 0;
      end = 0;
      values[0] = value;
      return true;
    }

    if (!isFull()) {
      end = (end + 1) % size;
      values[end] = value;
      return true;
    }
    return false;
  }

  /** Delete an element from the circular queue. Return true if the operation is successful. */
  public boolean deQueue() {
    if (!isEmpty()) {
      if (start == end) {
        start = -1;
        end = -1;
        return true;
      }
      start = (start + 1) % size;
      return true;
    }
    return false;
  }

  /** Get the front item from the queue. */
  public int Front() {
    if (isEmpty()) {
      return -1;
    }
    return values[start];
  }

  /** Get the last item from the queue. */
  public int Rear() {
    if (isEmpty()) {
      return -1;
    }
    return values[end];
  }

  /** Checks whether the circular queue is empty or not. */
  public boolean isEmpty() {
    return start == -1 && end == -1;
  }

  /** Checks whether the circular queue is full or not. */
  public boolean isFull() {
    return !isEmpty() && ((end + 1) % size) == start;
  }

  public static void main(String args[]) {
    CircularQueue queue = new CircularQueue(1);
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.enQueue(1));
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
    System.out.println(queue.deQueue());
    System.out.println(queue.deQueue());

    //"enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]
    queue = new CircularQueue(3);
    System.out.println(queue.enQueue(1));
    System.out.println(queue.enQueue(2));
    System.out.println(queue.enQueue(3));
    System.out.println(queue.enQueue(4));

    //["MyCircularQueue","enQueue","Rear","Rear","deQueue","enQueue","Rear","deQueue","Front","deQueue","deQueue","deQueue"]
    //[[6],[6],[],[],[],[5],[],[],[],[],[],[]]
    queue = new CircularQueue(6);
    System.out.println(queue.enQueue(6));
    System.out.println(queue.Rear());
    System.out.println(queue.Rear());
    System.out.println(queue.deQueue());
    System.out.println(queue.enQueue(5));
    System.out.println(queue.Rear());
    System.out.println(queue.deQueue());
    System.out.println(queue.Front());
    System.out.println(queue.deQueue());
    System.out.println(queue.deQueue());
    System.out.println(queue.deQueue());

    //["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]
    //[[3],[1],[2],[3],[4],[],[],[],[4],[]]
    //[null,true,true,true,false,3,true,true,true,4]
    System.out.println(".");
    queue = new CircularQueue(3);
    System.out.println(queue.enQueue(1));
    System.out.println(queue.enQueue(2));
    System.out.println(queue.enQueue(3));
    System.out.println(queue.enQueue(4));
    System.out.println(queue.Rear());
    System.out.println(queue.isFull());
    System.out.println(queue.deQueue());
    System.out.println(queue.enQueue(4));
    System.out.println(queue.Rear());
  }

}
