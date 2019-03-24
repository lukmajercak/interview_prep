package daily;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Daily_03_01_2019 {

  /**
   * Implement a job scheduler which takes in a function f and an integer n,
   * and calls f after n milliseconds.
   */
  public static void main(String[] args) {
    Scheduler scheduler = new Scheduler();
    scheduler.schedule(new Function() {
      @Override
      public Object apply(Object o) {
        System.out.println("Hello!");
        return null;
      }
    }, 5000);

    scheduler.schedule((x) -> {
      System.out.println("Hello2");
      return null;
      }, 1000);
  }

  static class Scheduler {

    ScheduledExecutorService executorService =
        Executors.newSingleThreadScheduledExecutor();

    void schedule(final Function f, long scheduleAfterMs) {
      executorService.schedule(() -> f.apply(null),
          scheduleAfterMs, TimeUnit.MILLISECONDS);
    }
  }
}
