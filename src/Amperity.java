import java.util.Map;

public class Amperity {


  public static void main(String[] args) {
    float[] denoms = new float[]{0.50f, 0.25f, 0.01f};
  }


  // 0.25  10, 3, 0 -- 10, 0, 30, -- 10, 0, 0
  //           1              25
  //       10, 2, 0    10, 0, 5      10, 0, 0

  // 0.50
  // 1.26
  void findChange(float amount, float[] denominations, Map<Float, Integer> remaining) {
    int denomBeingUsed = 0;

    while (amount > 0 || denomBeingUsed == denominations.length) {
      float denom = denominations[denomBeingUsed];

      int remainingDenom = remaining.get(denom);
      int quantity = (int) (amount / denom);

      if (quantity >= 1 && remainingDenom > 0) {
        quantity = Math.min(quantity, remainingDenom);
        System.out.println(denom + " x" + quantity);
        amount -= quantity * denom;

        remaining.put(denom, remainingDenom - quantity);
      } else {
        denomBeingUsed++;
      }
    }


  }

}
