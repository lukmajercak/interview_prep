package leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KClosestPointsToOrigin {

  /**
   * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
   *
   * (Here, the distance between two points on a plane is the Euclidean distance.)
   *
   * You may return the answer in any order.  The answer is guaranteed to be unique
   * (except for the order that it is in.)
   */
  public static void main(String[] args) {
    KClosestPointsToOrigin solution = new KClosestPointsToOrigin();

    // Input: points = [[1,3],[-2,2]], K = 1
    // Output: [[-2,2]]
    // Explanation:
    // The distance between (1, 3) and the origin is sqrt(10).
    // The distance between (-2, 2) and the origin is sqrt(8).
    // Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
    // We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
    int[][] points = new int[][] {
        {1, 3}, {-2, 2}};
    System.out.println(Arrays.deepToString(solution.kClosest(points, 1)));

    // Input: points = [[3,3],[5,-1],[-2,4]], K = 2
    // Output: [[3,3],[-2,4]]
    // (The answer [[-2,4],[3,3]] would also be accepted.)
    points = new int[][] {
        {3, 3}, {5, -1}, {-2, 4}};
    System.out.println(Arrays.deepToString(solution.kClosest(points, 2)));

    // Input: points = [[68,97],[34,-84],[60,100],[2,31],[-27,-38],[-73,-74],
    //                  [-55,-39],[62,91],[62,92],[-57,-67]]
    //        k = 5
    // Output: [[2,31],[-27,-38],[-55,-39],[-57,-67],[34,-84]]
    points = new int[][] {
        {68, 97}, {34, -84}, {60, 100}, {2, 31}, {-27, -38}, {-73, -74},
        {-55, -39}, {62, 91}, {62, 92}, {-57, -67}};
    System.out.println(Arrays.deepToString(solution.kClosest(points, 5)));
  }

  public int[][] kClosest(int[][] points, int K) {
    if (points.length == 0) {
      return new int[][]{};
    }
    MaxHeap maxHeap = new MaxHeap();

    for (int[] point : points) {
      double distance = calculateDistance(point);
      Point newPoint = new Point(point, distance);

      if (maxHeap.size() < K) {
        maxHeap.insert(newPoint);
        continue;
      }

      if (maxHeap.getRoot() != null && maxHeap.getRoot().distance > distance) {
        maxHeap.remove();
        maxHeap.insert(newPoint);
      }
    }

    int[][] result = new int[maxHeap.size()][1];
    int i = 0;
    for (Point point : maxHeap.tree) {
      result[i++] = point.point;
    }
    return result;
  }

  double calculateDistance(int[] point) {
    return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
  }

  class Point {
    int[] point;
    double distance;

    Point(int[] point, double distance) {
      this.point = point;
      this.distance = distance;
    }
  }

  class MaxHeap {
    List<Point> tree = new ArrayList<>();

    //       15
    //    9     4
    //  1   7     3
    // [15, 9, 4, 1, 7, 3]
    //   0  1  2  3  4  5
    public void insert(Point point) {
      tree.add(point);

      // bubble up
      Integer currIndex = getLastIndex();
      Integer parentIndex = getParentIndex(currIndex);
      Point parent = getIfInBounds(parentIndex);

      while (parent != null && parent.distance < point.distance) {
        // swap
        swap(currIndex, parentIndex);

        currIndex = parentIndex;
        parentIndex = getParentIndex(currIndex);
        parent = getIfInBounds(parentIndex);
      }
    }

    public void remove() {
      if (tree.isEmpty()) {
        return;
      }
      // 1. Swap root with the last element
      swap(0, getLastIndex());

      // 2. Remove last element
      tree.remove(tree.size() - 1);
      if (tree.isEmpty()) {
        return;
      }

      // 3. sift down last element from the root
      int currentIndex = 0;
      Point current = getRoot();
      Point leftChild = getLeft(0);
      Point rightChild = getRight(0);

      while (true) {
        double max = Integer.MIN_VALUE;
        Integer maxIndex = null;

        // 4. Find max child
        if (leftChild != null && leftChild.distance > current.distance) {
          if (leftChild.distance > max) {
            max = leftChild.distance;
            maxIndex = getLeftIndex(currentIndex);
          }
        }
        if (rightChild != null && rightChild.distance > current.distance) {
          if (rightChild.distance > max) {
            maxIndex = getRightIndex(currentIndex);
          }
        }

        if (maxIndex == null) {
          break;
        }
        // 5. Swap
        swap(currentIndex, maxIndex);
        currentIndex = maxIndex;
        current = getIfInBounds(currentIndex);
        leftChild = getLeft(currentIndex);
        rightChild = getRight(currentIndex);
      }
    }

    void swap(int indexA, int indexB) {
      if (!isInBounds(indexA) || !isInBounds(indexB)) {
        return;
      }
      Point a = tree.get(indexA);
      Point b = tree.get(indexB);
      tree.set(indexB, a);
      tree.set(indexA, b);
    }

    Point getRoot() {
      return tree.isEmpty() ? null : tree.get(0);
    }

    Point getLeft(int index) {
      return getIfInBounds(getLeftIndex(index));
    }

    Point getRight(int index) {
      return getIfInBounds(getRightIndex(index));
    }

    Integer getLeftIndex(int index) {
      return index * 2 + 1;
    }

    Integer getRightIndex(int index) {
      return index * 2 + 2;
    }

    int getLastIndex() {
      return tree.size() - 1;
    }

    int getParentIndex(int index) {
      return (index - 1) / 2;
    }

    Point getIfInBounds(int index) {
      if (!isInBounds(index)) {
        return null;
      }
      return tree.get(index);
    }

    boolean isInBounds(int index) {
      return index >= 0 && index < tree.size();
    }

    int size() {
      return tree.size();
    }
  }
}
