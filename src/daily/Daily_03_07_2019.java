package daily;

public class Daily_03_07_2019 {

  /**
   * This problem was asked by Twitter.
   *
   * You run an e-commerce website and want to record the last N order ids in a log.
   * Implement a data structure to accomplish this, with the following API:
   *
   * record(order_id): adds the order_id to the log
   * get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.
   * You should be as efficient with time and space as possible.
   */
  public static void main(String[] args) {
    CircularQueue lastIds = new CircularQueue(3);
    lastIds.add(2);
    lastIds.add(3);
    lastIds.add(5);
    lastIds.add(4);
    System.out.println(lastIds.getLast(1));
    System.out.println(lastIds.getLast(2));
    System.out.println(lastIds.getLast(3));
    System.out.println("----------");
    lastIds.add(6);
    System.out.println(lastIds.getLast(1));
    System.out.println(lastIds.getLast(2));
    System.out.println(lastIds.getLast(3));
    System.out.println("----------");
    lastIds.add(7);
    System.out.println(lastIds.getLast(1));
    System.out.println(lastIds.getLast(2));
    System.out.println(lastIds.getLast(3));
    System.out.println("----------");
    lastIds.add(8);
    System.out.println(lastIds.getLast(1));
    System.out.println(lastIds.getLast(2));
    System.out.println(lastIds.getLast(3));
  }

  static class CircularQueue {
    int[] elements;
    int index = -1;

    CircularQueue(int size) {
      this.elements = new int[size];
    }

    void add(int i) {
      index = (index + 1) % getSize();
      elements[index] = i;
    }

    int getLast(int i) {
      if (i > getSize()) {
        return -1;
      }
      int toRetrieve = ((index + 1) - i);
      if (toRetrieve < 0) {
        toRetrieve = getSize() - Math.abs(toRetrieve);
      }
      return elements[toRetrieve];
    }

    private int getSize() {
      return elements.length;
    }
  }
}
