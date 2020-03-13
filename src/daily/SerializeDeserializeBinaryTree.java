package daily;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

/**
 * This problem was asked by Google.
 *
 * Given the root to a binary tree, implement serialize(root),
 * which serializes the tree into a string, and deserialize(s),
 * which deserializes the string back into the tree.
 *
 * For example, given the following Node class
 *
 * class Node:
 *     def __init__(self, val, left=None, right=None):
 *         self.val = val
 *         self.left = left
 *         self.right = right
 * The following test should pass:
 *
 * node = Node('root', Node('left', Node('left.left')), Node('right'))
 * assert deserialize(serialize(node)).left.left.val == 'left.left'
 */
public class SerializeDeserializeBinaryTree {

  public static void main(String[] args) {

    /**
     *              3
     *          1      2
     *      4        5    6
     */
    Node n4 = new Node(4, null, null);
    Node n1 = new Node(1, n4, null);

    Node n5 = new Node(5, null, null);
    Node n6 = new Node(6, null, null);
    Node n2 = new Node(2, n5, n6);

    Node n3 = new Node(3, n1, n2);

    String serialized = serialize(n3);
    System.out.println(serialized);
    Node deserialized = deserialize(serialized);
    System.out.println(deserialized.value);
    System.out.println(deserialized.left.value);
    System.out.println(deserialized.left.left.value);
    System.out.println(deserialized.right.value);
    System.out.println(deserialized.right.left.value);
    System.out.println(deserialized.right.right.value);

    System.out.println("serialize2");
    String serialized2 = serialize2(n3);
    System.out.println(serialized2);
    Node deserialized2 = deserialize2(serialized2);
    System.out.println(deserialized2.value);
    System.out.println(deserialized2.left.value);
    System.out.println(deserialized2.left.left.value);
    System.out.println(deserialized2.right.value);
    System.out.println(deserialized2.right.left.value);
    System.out.println(deserialized2.right.right.value);
  }

  static class Node {
    Node left;
    Node right;
    int value;

    Node(int value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }
  }

  /**
   *              3
   *          1      2
   *      4        5    6
   *
   *
   *      4N 1L 5N 6N 2B 3B
   *
   *      post order
   *      go through, put on stack, pop 1 if L/R, pop 2 if B
   *
   *
   */
  static String serialize(Node n) {
    boolean hasLeft = n.left != null;
    boolean hasRight = n.right != null;

    String valueString = String.valueOf(n.value);

    if (hasLeft) {
      if (hasRight) {
        // both
        return serialize(n.left) + serialize(n.right) + valueString + "B";
      }
      // only left
      return serialize(n.left) + valueString + "L";
    } else if (hasRight) {
      // only right
      return serialize(n.right) + valueString + "R";
    }
    // leaf
    return valueString + "N";
  }

  /**
   * 4N 1L 5N 6N 2B 3B
   */
  static Node deserialize(String s) {

    Stack<Node> nodeStack = new Stack<>();

    int pos = 0;
    while (pos != s.length()) {
      int previousPos = pos;
      char nextChar = s.charAt(pos);

      int valueLength = 0;
      while (nextChar != 'N' && nextChar != 'B' && nextChar != 'R' && nextChar != 'L') {
        pos++;
        nextChar = s.charAt(pos);
      }

      int value = Integer.valueOf(s.substring(
          previousPos, previousPos + (pos - previousPos)));

      Node newNode = new Node(value, null, null);
      if (nextChar == 'L') {
        newNode.left = nodeStack.pop();
      } else if (nextChar == 'R') {
        newNode.right = nodeStack.pop();
      } else if (nextChar == 'B') {
        newNode.right = nodeStack.pop();
        newNode.left = nodeStack.pop();
      }
      nodeStack.push(newNode);

      pos++;
    }

    return nodeStack.pop();
  }


  /**
                  3
   *          1      2
   *      4        5    6
   *
   *
   *   3 1 4 # # # 2 5 # # 6 # #
   * Pre order
   */
  static String serialize2(Node n) {
    if (n == null) {
      return "#";
    }
    return n.value + " " + serialize2(n.left) + " " + serialize2(n.right);
  }

  /**
   * '3 1 4 # # # 2 5 # # 6 # #'
   */
  static Node deserialize2(String s) {
    String[] split = s.split(" ");
    return deserialize2Helper(Arrays.asList(split).iterator());
  }

  static Node deserialize2Helper(Iterator<String> values) {
    String next = values.next();
    if (next.equals("#")) {
      return null;
    }
    int value = Integer.valueOf(next);
    Node node = new Node(value, null, null);
    node.left = deserialize2Helper(values);
    node.right = deserialize2Helper(values);
    return node;
  }
}
