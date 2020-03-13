
// Note: You may implement this in python, Clojure or any other language.

// You may also decide to create class or object to wrap this _ your call!

// Browser history: Build the object to support browser history.
// Browser should support URL bar, forward and back buttons
// No need for network calls - this is a data structure only.

// creates the history object _ use a class, record, or other structure if you want!

// EXAMPLE: 
// IF: 
//  - visit A
//  - visit B
//  - visit C
// THEN:
//   - back yields B, back again yields A.
// THEN:
//   - forward yields B, forward again yields C.
// THEN:
//   - back, from B visit D eliminates C from the history.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowserHistory {

  // abc, dca

  public static void main(String[] args) {
    BrowserHistory browserHistory = new BrowserHistory(3);

    System.out.println(browserHistory.executeForwardMove()); // null
    System.out.println(browserHistory.executeBackMove()); // null

    browserHistory.storeVisit("ABC");
    browserHistory.storeVisit("BCD");
    browserHistory.storeVisit("CAB");

    System.out.println(Arrays.toString(browserHistory.lookup("AB"))); // ABC, CAB
    System.out.println(Arrays.toString(browserHistory.lookup("BC"))); // ABC, BCD
    System.out.println(Arrays.toString(browserHistory.lookup("D"))); // BCD

    System.out.println(browserHistory.executeBackMove()); // B
    System.out.println(browserHistory.executeBackMove()); // A

    System.out.println(browserHistory.executeForwardMove()); // B
    System.out.println(browserHistory.executeForwardMove()); // C

    System.out.println(browserHistory.executeBackMove()); // B

    browserHistory.storeVisit("D");
    System.out.println(browserHistory.executeBackMove()); // B
    System.out.println(browserHistory.executeForwardMove()); // D
    System.out.println(browserHistory.executeForwardMove()); // null
  }

  // A - B - C
  //         ^
  //     ^
  // ^
  //     ^
  //         ^
  //     ^
  // A - B
  // A - B - D

  Node head;
  Node current;
  private final int limit;
  int size;
  
  // creates the history object _ use a class, record, or other structure if you want!
  public BrowserHistory (int limit) {
    this.limit = limit;
    this.size = 0;
  }
  
  // to support "click a link" on the browser
  public void storeVisit(String url) {
    // look at current element
    // remove everything to the right

    Node newCurrent = new Node(url);

    if (current != null) {
      current.next = newCurrent;
      newCurrent.previous = current;
    } else {
      head = newCurrent;
    }

    if (size == limit && head != null) {
      Node newHead = head.next;
      newHead.previous = null;
      head = newHead;
    } else {
      size++;
    }

    current = newCurrent;
  }
  
  // to support "go back" on the browser
  public String executeBackMove() {
    if (current == null || current.previous == null) {
      return null;
    }
    current = current.previous;
    return current.url;
  }
 
  // to support "go forward" on the browser
  public String executeForwardMove() {
    if (current == null || current.next == null) {
      return null;
    }
    current = current.next;
    return current.url;
  }
  
  // Bonus: look up matching URLs by substring
  public String[] lookup(String sub) {
    List<String> found = new ArrayList<>();

    Node check = current;

    while (check != null) {
      if (check.url.contains(sub)) {
        found.add(check.url);
      }

      check = check.previous;
    }

    String[] foundArray = new String[found.size()];
    foundArray = found.toArray(foundArray);

    return foundArray;
  }


  class Node {
    Node previous;
    Node next;
    String url;

    Node(String url) {
      this.url = url;
    }

    @Override
    public String toString() {
      return this.url;
    }
  }
}
