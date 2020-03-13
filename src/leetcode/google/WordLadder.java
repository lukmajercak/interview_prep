package leetcode.google;

import java.util.*;

public class WordLadder {

  /**
   * Given two words (beginWord and endWord), and a dictionary's word list, find the length
   * of shortest transformation sequence from beginWord to endWord, such that:
   *
   * Only one letter can be changed at a time.
   * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
   * Note:
   *
   * Return 0 if there is no such transformation sequence.
   * All words have the same length.
   * All words contain only lowercase alphabetic characters.
   * You may assume no duplicates in the word list.
   * You may assume beginWord and endWord are non-empty and are not the same.
   */
  public static void main(String[] args) {
    WordLadder solution = new WordLadder();

    // Input:
    // beginWord = "hit",
    // endWord = "cog",
    // wordList = ["hot","dot","dog","lot","log","cog"]
    //
    // Output: 5
    //
    // Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    // return its length 5.
    String beginWord = "hit";
    String endWord = "cog";
    List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
    System.out.println(solution.ladderLength(beginWord, endWord, wordList));

    // Input:
    // beginWord = "hit"
    // endWord = "cog"
    // wordList = ["hot","dot","dog","lot","log"]
    //
    // Output: 0
    //
    // Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
    beginWord = "hit";
    endWord = "cog";
    wordList = Arrays.asList("hot","dot","dog","lot","log");
    System.out.println(solution.ladderLength(beginWord, endWord, wordList));
  }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    List<GraphNode> graph = new ArrayList<>();
    boolean hasEndWord = false;

    for (String word : wordList) {
      hasEndWord |= word.equals(endWord);
      addToGraph(graph, word);
    }
    if (!hasEndWord) {
      return 0;
    }

    GraphNode beginWordGraphNode = addToGraph(graph, beginWord);

    Set<GraphNode> visited = new HashSet<>();
    Queue<GraphNode> toVisit = new LinkedList<>();
    toVisit.add(beginWordGraphNode);
    int steps = 1;

    while (!toVisit.isEmpty()) {
      int toCheck = toVisit.size();

      for (int i = 0; i < toCheck; i++) {
        GraphNode next = toVisit.poll();
        if (next.word.equals(endWord)) {
          return steps;
        }
        visited.add(next);

        for (GraphNode neighbor : next.neighbors) {
          if (!visited.contains(neighbor)) {
            toVisit.offer(neighbor);
          }
        }
      }
      steps++;
    }
    return 0;
  }

  GraphNode addToGraph(List<GraphNode> graph, String word) {
    GraphNode graphNode = new GraphNode();
    graphNode.word = word;

    for (GraphNode prevGraphNode : graph) {
      if (oneAway(graphNode, prevGraphNode)) {
        graphNode.neighbors.add(prevGraphNode);
        prevGraphNode.neighbors.add(graphNode);
      }
    }
    graph.add(graphNode);
    return graphNode;
  }

  boolean oneAway(GraphNode a, GraphNode b) {
    boolean diff = false;
    for (int i = 0; i < a.word.length(); i++) {
      if (a.word.charAt(i) != b.word.charAt(i)) {
        if (diff) {
          return false;
        }
        diff = true;
      }
    }
    return true;
  }

  static class GraphNode {
    String word;
    List<GraphNode> neighbors = new ArrayList<>();
  }
}
