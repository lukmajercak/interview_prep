package daily;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class ApproximatePI {

  static Random r = new Random();

  /**
   * This problem was asked by Google.
   *
   * The area of a circle is defined as πr^2. Estimate π to 3 decimal places using a Monte Carlo method.
   */
  public static void main(String[] args) {
    System.out.println(approximatePi());
  }

  static double approximatePi() {
    int numInQuadrant = 0;
    int n = 0;

    double pi = 1;

    while (pi != 3.14159) {
      n++;
      double x = r.nextDouble();
      double y = r.nextDouble();

      double distanceFromOrigin = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
      if (distanceFromOrigin < 1) {
        numInQuadrant++;
      }
      pi = round(4 * (numInQuadrant / (double) n), 5);
    }

    System.out.println("Took " + n + " points to approximate pi.");

    return pi;
  }

  private static double round(double value, int places) {
    BigDecimal bd = new BigDecimal(Double.toString(value));
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
