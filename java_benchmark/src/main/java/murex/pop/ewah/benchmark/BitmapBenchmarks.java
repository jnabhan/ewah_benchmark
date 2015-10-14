package murex.pop.ewah.benchmark;

import com.googlecode.javaewah.EWAHCompressedBitmap;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
public class BitmapBenchmarks {
   EWAHCompressedBitmap[] ewahs;
   EWAHCompressedBitmap container = new EWAHCompressedBitmap();

   @Param({})
   String filename;

   @Setup(Level.Iteration)
   public void setup() {
      ewahs = BitmapExtractor.readFileFromPath("../bitmap_dumps/" + filename);
   }

   @Benchmark
   public EWAHCompressedBitmap bitmap_benchmark_and() {
      ewahs[0].andToContainer(ewahs[1], container);
      return container;
   }

   @Benchmark
   public EWAHCompressedBitmap bitmap_benchmark_or() {
      ewahs[0].orToContainer(ewahs[1], container);
      return container;
   }

   public static void main(String[] args) throws Exception {
      String[] filenames = new String[208];
      for (int i = 0; i < filenames.length; ++i) {
         filenames[i] = String.valueOf(i * 1000);
      }
      Options opt = new OptionsBuilder().include(BitmapBenchmarks.class.getSimpleName()).param("filename", filenames).shouldDoGC(true).build();
      new Runner(opt).run();
   }
}
