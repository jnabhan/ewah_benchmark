package murex.pop.ewah.benchmark;

import com.googlecode.javaewah.EWAHCompressedBitmap;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class AndBenchmark {
   EWAHCompressedBitmap[] ewahs;
   EWAHCompressedBitmap container = new EWAHCompressedBitmap();

   @Param({})
   String filename;

   @Setup(Level.Iteration)
   public void setup() {
      ewahs = BitmapExtractor.readFileFromPath(EWAHBenchmarks.BITMAPS_PATH + filename);
   }

   @Benchmark
   public EWAHCompressedBitmap ewah_test_and() {
      ewahs[0].andToContainer(ewahs[1], container);
      return container;
   }
}
