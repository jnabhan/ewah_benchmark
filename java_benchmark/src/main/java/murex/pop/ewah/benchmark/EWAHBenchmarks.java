package murex.pop.ewah.benchmark;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class EWAHBenchmarks {
   static String BITMAPS_PATH = "../bitmap_dumps/";

   private static String getBenchmarkClass(String[] args) {
      if (args.length != 0) {
         if (args[0].equals("and")) {
            return AndBenchmark.class.getSimpleName();
         }
         if (args[0].equals("or")) {
            return OrBenchmark.class.getSimpleName();
         }
         if (args[0].equals("iterator")) {
            return IteratorBenchmark.class.getSimpleName();
         }
         if (args[0].equals("iterator_access_sum")) {
            return IteratorAccessSumBenchmark.class.getSimpleName();
         }
      }
      return ".*";
   }

   public static void main(String[] args) throws Exception {
      String[] filenames = new String[2];
      for (int i = 0; i < filenames.length; ++i) {
         filenames[i] = String.valueOf(i * 1000);
      }
      ChainedOptionsBuilder optBldr = new OptionsBuilder()
            .include(getBenchmarkClass(args))
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .warmupIterations(3).warmupTime(TimeValue.seconds(1L))
            .measurementIterations(3).measurementTime(TimeValue.seconds(1L))
            .forks(1)
            .param("filename", filenames)
            .shouldDoGC(true);
      new Runner(optBldr.build()).run();
   }
}
