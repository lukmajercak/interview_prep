package daily;

import java.util.Random;

public class Daily_03_06_2019 {

  /**
   * This problem was asked by Facebook.
   *
   * Given a stream of elements too large to store in memory,
   * pick a random element from the stream with uniform probability.
   */
  public static void main(String[] args) {
    RandomPicker picker = new RandomPicker();

    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    for (int i = 0; i < numbers.length; i++) {
      System.out.println(picker.selectRandom(numbers[i]));
    }
  }

  /**
   * Let us now talk about second last element. When second last element processed first time,
   * the probability that it replaced the previous result is 1/(n-1). The probability that
   * previous result stays when nth item is considered is (n-1)/n.
   *
   * So probability that the second last element is picked in last iteration is [1/(n-1)] * [(n-1)/n] which is 1/n.
   */
  static class RandomPicker {
    int count = 0;
    int res = 0;
    Random r = new Random();

    int selectRandom(int x) {
      count++;
      if (count == 1) {
        res = x;
      } else {
        if (r.nextInt(count) == count - 1) {
          res = x;
        }
      }
      return res;
    }
  }
}
