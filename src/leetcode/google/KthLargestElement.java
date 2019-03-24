package leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class KthLargestElement {

  /**
   * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
   */
  public static void main(String[] args) {
    KthLargestElement solution = new KthLargestElement();

    // Example 1:
    //
    //Input: [3,2,1,5,6,4] and k = 2
    //Output: 5
    int[] nums = new int[]{3,2,1,5,6,4};
    System.out.println(solution.findKthLargest(nums, 2));

    // Example 2:
    //
    //Input: [3,2,3,1,2,4,5,5,6] and k = 4
    //Output: 4
    nums = new int[]{3,2,3,1,2,4,5,5,6};
    System.out.println(solution.findKthLargest(nums, 4));

    nums = new int[]{1};
    System.out.println(solution.findKthLargest(nums, 1));
  }

  public int findKthLargest(int[] nums, int k) {
    MaxHeap maxHeap = new MaxHeap();
    for (int num : nums) {
      maxHeap.insert(num);
    }

    Integer kthLargest = null;
    while (k > 0) {
      kthLargest = maxHeap.remove();
      k--;
    }
    return kthLargest;
  }


  /**
   *             15
   *           /    \
   *         10      9
   *       /   \   /   \
   *      8    9   6   3
   *     / \
   *    4   2
   *
   * tree = [15, 10, 9, 8, 9, 6, 3, 4, 2]
   *         0    1  2  3  4  5  6  7  8
   */
  static class MaxHeap {
    List<Integer> tree = new ArrayList<>();

    void insert(Integer num) {
      tree.add(num);
      Integer currIndex = getLastIndex();
      Integer parentIndex = getParentIndex(currIndex);
      Integer parent = getIfInBounds(parentIndex);

      while (parent != null && parent < num) {
        // swap
        swap(currIndex, parentIndex);

        currIndex = parentIndex;
        parentIndex = getParentIndex(currIndex);
        parent = getIfInBounds(parentIndex);
      }
    }

    Integer remove() {
      Integer toRemove = getRoot();

      // 1. swap root with last element
      swap(0, getLastIndex());

      // 2. remove last element
      tree.remove(tree.size() - 1);

      if (tree.isEmpty()) {
        return toRemove;
      }

      // 3. sift down last element from the root
      Integer currentIndex = 0;
      Integer current = getRoot();
      Integer leftChild = getLeft(0);
      Integer rightChild = getRight(0);

      while (true) {
        Integer max = Integer.MIN_VALUE;
        Integer maxIndex = null;

        // Find max from the children
        if (leftChild != null && leftChild > current) {
          if (leftChild > max) {
            max = leftChild;
            maxIndex = getLeftIndex(currentIndex);
          }
        }
        if (rightChild != null && rightChild > current) {
          if (rightChild > max) {
            max = rightChild;
            maxIndex = getRightIndex(currentIndex);
          }
        }
        if (maxIndex == null) {
          break;
        }
        swap(currentIndex, maxIndex);

        currentIndex = maxIndex;
        current = getIfInBounds(currentIndex);
        leftChild = getLeft(currentIndex);
        rightChild = getRight(currentIndex);
      }

      return toRemove;
    }

    void swap(Integer i, Integer j) {
      if (!isInBounds(i) || !isInBounds(j)) {
        return;
      }
      Integer tmp = tree.get(i);
      tree.set(i, tree.get(j));
      tree.set(j, tmp);
    }

    Integer getParent(int index) {
      return getIfInBounds(getParentIndex(index));
    }

    Integer getLeft(int index) {
      return getIfInBounds(getLeftIndex(index));
    }

    Integer getRight(int index) {
      return getIfInBounds(getRightIndex(index));
    }

    Integer getRoot() {
      return tree.get(0);
    }

    Integer getLast() {
      return tree.get(getLastIndex());
    }

    Integer getParentIndex(int index) {
      return (index - 1) / 2;
    }

    Integer getLeftIndex(int index) {
      return index * 2 + 1;
    }

    Integer getRightIndex(int index) {
      return index * 2 + 2;
    }

    Integer getLastIndex() {
      return tree.size() - 1;
    }

    Integer getIfInBounds(int index) {
      if (!isInBounds(index)) {
        return null;
      }
      return tree.get(index);
    }

    boolean isInBounds(int index) {
      return index >= 0 && index < tree.size();
    }
  }
}
