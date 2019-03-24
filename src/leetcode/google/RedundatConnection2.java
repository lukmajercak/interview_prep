package leetcode.google;

import java.util.*;

public class RedundatConnection2 {

  /**
   * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root)
   * for which all other nodes are descendants of this node, plus every node has exactly one parent,
   * except for the root node which has no parents.
   *
   * The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N),
   * with one additional directed edge added. The added edge has two different vertices chosen from 1 to N,
   * and was not an edge that already existed.
   *
   * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents
   * a directed edge connecting nodes u and v, where u is a parent of child v.
   *
   * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple
   * answers, return the answer that occurs last in the given 2D-array.
   */
  public static void main(String[] args) {
    RedundatConnection2 solution = new RedundatConnection2();

    // Example 1:
    //Input: [[1,2], [1,3], [2,3]]
    //Output: [2,3]
    //Explanation: The given directed graph will be like this:
    //  1
    // / \
    //v   v
    //2-->3
    int[][] edges = new int[][] {
        {1, 2},
        {1, 3},
        {2, 3}
    };
    System.out.println(Arrays.toString(solution.findRedundantDirectedConnection(edges)));

    // Example 2:
    //Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
    //Output: [4,1]
    //Explanation: The given directed graph will be like this:
    //5 <- 1 -> 2
    //     ^    |
    //     |    v
    //     4 <- 3
    edges = new int[][] {
        {1, 2},
        {2, 3},
        {3, 4},
        {4, 1},
        {1, 5}
    };
    System.out.println(Arrays.toString(solution.findRedundantDirectedConnection(edges)));
  }

  public int[] findRedundantDirectedConnection(int[][] edges) {
    //TODO:
   /* Map<Integer, EdgeCounter> edgeCounter = new HashMap<>();

    for (int[] edge : edges) {
      int first = edge[0];
      int second = edge[1];

      EdgeCounter edgeCounterFirst = edgeCounter.get(first);
      if (edgeCounterFirst == null) {
        edgeCounterFirst = new EdgeCounter();
      }
      edgeCounterFirst.outgoing++;
      edgeCounter.put(first, edgeCounterFirst);


      EdgeCounter edgeCounterSecond = edgeCounter.get(second);
      if (edgeCounterSecond == null) {
        edgeCounterSecond = new EdgeCounter();
      }
      edgeCounterSecond.incoming++;
      edgeCounter.put(second, edgeCounterSecond);
    }

    for (int j = edges.length - 1; j >= 0; j--) {
      int[] edge = edges[j];
      boolean canRemove = checkRemove(edgeCounter, edge[0], edge[1]);
      if (canRemove) {
        return edge;
      }
      canRemove = checkRemove(edgeCounter, edge[1], edge[0]);
      if (canRemove) {
        return edge;
      }
    }*/
    return null;
  }

  boolean checkRemove(Map<Integer, EdgeCounter> edgeCounter, int toBeRoot, int child) {
    EdgeCounter rootEdges = edgeCounter.get(toBeRoot);
    EdgeCounter childEdges = edgeCounter.get(child);
    return rootEdges.incoming == 1 && rootEdges.outgoing > 0 &&
        childEdges.incoming > 0 && childEdges.outgoing > 1;
  }

  static class EdgeCounter {
    int outgoing = 0;
    int incoming = 0;
  }
}
