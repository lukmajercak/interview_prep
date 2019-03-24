package daily;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Daily_02_24_2019 {

  /**
   * This problem was asked by Jane Street.
   *
   * cons(a, b) constructs a pair, and car(pair) and cdr(pair) returns the first and last element of that pair.
   * For example, car(cons(3, 4)) returns 3, and cdr(cons(3, 4)) returns 4.
   *
   * Given this implementation of cons:
   *
   * def cons(a, b):
   *     def pair(f):
   *         return f(a, b)
   *     return pair
   * Implement car and cdr.
   */
  public static void main(String[] args) {
    System.out.println(car(cons(3, 4)));
    System.out.println(cdr(cons(3, 4)));
  }

  static Function<Void , Pair> cons(final int a, final int b) {
    return new Function<Void, Pair>() {
      public Pair apply(Void v) {
        return new Pair(a, b);
      }
    };
  }

  static int car(Function<Void, Pair> fun) {
    return fun.apply(null).a;
  }

  static int cdr(Function<Void, Pair> fun) {
    return fun.apply(null).b;
  }

  static class Pair {
    int a;
    int b;

    Pair(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }
}
