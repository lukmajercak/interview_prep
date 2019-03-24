package leetcode.google;

import java.util.*;

public class EvaluateDivision {

  /**
   * Equations are given in the format A / B = k, where A and B are variables represented as strings,
   * and k is a real number (floating point number). Given some queries, return the answers.
   * If the answer does not exist, return -1.0.
   */
  public static void main(String[] args) {
    EvaluateDivision solution = new EvaluateDivision();

    // Example:
    //Given a / b = 2.0, b / c = 3.0.
    //queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
    //return [6.0, 0.5, -1.0, 1.0, -1.0 ].
    //
    //The input is: vector<pair<string, string>> equations,
    // vector<double>& values,
    // vector<pair<string, string>> queries ,
    // where equations.size() == values.size(), and the values are positive.
    // This represents the equations. Return vector<double>

    String[][] equations = new String[][] {
        {"a","b"},
        {"b","c"}
    };
    double[] values = new double[] {
        2.0, 3.0
    };
    String[][] queries = new String[][] {
        {"a","c"},
        {"b","c"},
        {"a","e"},
        {"a","a"},
        {"x","x"}
    };
    System.out.println(Arrays.toString(solution.calcEquation(equations, values, queries)));

    equations = new String[][] {
        {"x1","x2"},
        {"x2","x3"},
        {"x3","x4"},
        {"x4","x5"}
    };
    values = new double[] {
        3.0,
        4.0,
        5.0,
        6.0
    };
    queries = new String[][] {
        {"x1","x5"},
        {"x5","x2"},
        {"x2","x4"},
        {"x2","x2"},
        {"x2","x9"},
        {"x9","x9"},
    };
    // [360.0,0.00833,20.0,1.0,-1.0,-1.0]
    System.out.println(Arrays.toString(solution.calcEquation(equations, values, queries)));



    equations = new String[][] {
        {"x1","x2"},
        {"x2","x3"},
        {"x3","x4"},
        {"x4","x5"}
    };
    values = new double[] {
        3.0,
        4.0,
        5.0,
        6.0
    };
    queries = new String[][] {
        {"x1","x5"},
        {"x5","x2"},
        {"x2","x4"},
        {"x2","x2"},
        {"x2","x9"},
        {"x9","x9"},
    };
    // [360.0,0.00833,20.0,1.0,-1.0,-1.0]
    System.out.println(Arrays.toString(solution.calcEquation(equations, values, queries)));
  }

  // x1 / x2 = 3
  // x2 / x3 = 4
  // x1 -> x2 * 3, x3 * 12
  // x2 -> x3 * 4
  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    // create graph
    // x1 *3 -> x2 / 3 -> x1
    //             \
    //              /3 -> x4
    //
    //(
    Map<String, GraphNode> nodes = new HashMap<>();
    for (int i = 0; i < equations.length; i++) {
      String left = equations[i][0];
      String right = equations[i][1];
      double result = values[i];

      GraphNode leftNode = checkOrAdd(nodes, left);
      GraphNode rightNode = checkOrAdd(nodes, right);
      leftNode.addChild(rightNode, result);
      rightNode.addChild(leftNode, 1 / result);
    }

    double[] results = new double[queries.length];
    for (int i = 0; i < queries.length; i++) {
      String left = queries[i][0];
      String right = queries[i][1];

      GraphNode leftNode = nodes.get(left);
      GraphNode rightNode = nodes.get(right);
      if (leftNode == null || rightNode == null) {
        results[i] = -1;
        continue;
      }
      Double result = findPath(leftNode, rightNode, new HashSet<>());
      results[i] = result;
    }
    return results;
  }

  private Double findPath(GraphNode nextNode, GraphNode toFind, Set<GraphNode> visited) {
    if (nextNode.equals(toFind)) {
      return new Double(1);
    }
    visited.add(nextNode);
    for (GraphNode child : nextNode.children.keySet()) {
      if (!visited.contains(child)) {
        Double result = findPath(child, toFind, visited);
        if (result > 0) {
          return result * nextNode.children.get(child);
        }
      }
    }
    return new Double(-1);
  }

  GraphNode checkOrAdd(Map<String, GraphNode> map, String key) {
    if (!map.containsKey(key)) {
      map.put(key, new GraphNode(key));
    }
    return map.get(key);
  }

  static class GraphNode {
    String variable;
    Map<GraphNode, Double> children;

    GraphNode(String variable) {
      this.variable = variable;
      this.children = new HashMap<>();
    }

    void addChild(GraphNode child, Double factor) {
      children.put(child, factor);
    }

    @Override
    public int hashCode() {
      return variable.hashCode();
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof GraphNode)) {
        return false;
      }
      GraphNode that = (GraphNode) o;
      return this.variable == that.variable;
    }
  }
}
