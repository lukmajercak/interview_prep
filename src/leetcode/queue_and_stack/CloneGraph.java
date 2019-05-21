package leetcode.queue_and_stack;

import java.util.*;

public class CloneGraph {

  /**
   * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
   * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
   *
   * Example:
   *
   *   1 --- 2
   *   |     |
   *   |     |
   *   4 --- 3
   * Input:
   * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
   *
   * Explanation:
   * Node 1's value is 1, and it has two neighbors: Node 2 and 4.
   * Node 2's value is 2, and it has two neighbors: Node 1 and 3.
   * Node 3's value is 3, and it has two neighbors: Node 2 and 4.
   * Node 4's value is 4, and it has two neighbors: Node 1 and 3.
   */
  public static void main(String[] args) {
    CloneGraph solution = new CloneGraph();

    Node n1 = new Node(1, new ArrayList<>());
    Node n2 = new Node(2, new ArrayList<>());
    Node n3 = new Node(3, new ArrayList<>());
    Node n4 = new Node(4, new ArrayList<>());

    n1.neighbors.add(n2);
    n1.neighbors.add(n4);

    n2.neighbors.add(n1);
    n2.neighbors.add(n3);

    n3.neighbors.add(n2);
    n3.neighbors.add(n4);

    n4.neighbors.add(n3);
    n4.neighbors.add(n1);

    Node copy = solution.cloneGraph(n1);
  }

  public Node cloneGraph(Node node) {
    return cloneGraph(node, new HashMap<>());
  }

  public Node cloneGraph(Node node, Map<Node, Node> visited) {
    Node previousCopy = visited.get(node);
    if (previousCopy != null) {
      return previousCopy;
    }
    Node copy = new Node(node.val, new ArrayList<>());
    visited.put(node, copy);
    for (Node neighbor : node.neighbors) {
      copy.neighbors.add(cloneGraph(neighbor, visited));
    }
    return copy;
  }

  static class Node {
    public int val;
    public List<Node> neighbors;

    public Node(int val, List<Node> neighbors) {
      this.val = val;
      this.neighbors = neighbors;
    }
  };
}
