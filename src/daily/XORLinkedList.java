package daily;

public class XORLinkedList {

  /**
   * This problem was asked by Google.
   *
   * An XOR linked list is a more memory efficient doubly linked list.
   * Instead of each node holding next and prev fields, it holds a field named both,
   * which is an XOR of the next node and the previous node.
   *
   * Implement an XOR linked list; it has an add(element) which adds the element to the end,
   * and a get(index) which returns the node at index.
   *
   * If using a language that has no pointers (such as Python),
   * you can assume you have access to get_pointer and dereference_pointer
   * functions that converts between nodes and memory addresses.
   */
  public static void main(String[] args) {

    // 1st.address = 1 0 1 1 0 1 0 1 1
    // 2nd.address = 1 0 0 0 1 0 1 0 1
    // 3rd.address = 0 1 1 0 1 1 0 1 0


    // 1st.both = 0 XOR 2nd
    //          = 1 0 0 0 1 0 1 0 1
    // 2nd.both = 1st XOR 3rd
    //          = 1 1 0 1 1 0 0 0 1
    // 3rd.both = 2nd XOR 0
    //          = 1 0 0 0 1 0 1 0 1

    // when iterating
    // address of 2nd = 1st.both
    //    pass 1st.address to 2nd
    //       address of 3rd = 2nd.both XOR 1st.address
    //                      = 1 1 0 1 1 0 0 0 1
    //                    XOR 1 0 1 1 0 1 0 1 1
    //                      = 0 1 1 0 1 1 0 1 0 == 3rd.address


    // KEY TAKEAWAY :
    // if  A XOR B = C  then  C XOR B = A  and  C XOR A = B
  }
}
