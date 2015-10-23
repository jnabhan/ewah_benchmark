package murex.pop.ewah.benchmark;

import com.googlecode.javaewah.EWAHCompressedBitmap;
import com.googlecode.javaewah.IntIterator;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Random;

@State(Scope.Thread)
public class IteratorAccessSumBenchmark {
   EWAHCompressedBitmap[] ewahs;
   long[] dataColumn = new long[7000000];

   @Param({})
   String filename;

   @Setup(Level.Iteration)
   public void setup() {
      Random random = new Random(17);
      for (int j = 0; j < 7000000; ++j) {
         dataColumn[j] = random.nextInt(1000);
      }
      ewahs = BitmapExtractor.readFileFromPath(EWAHBenchmarks.BITMAPS_PATH + filename);
   }

   @Benchmark
   public int ewah_test_iterator_access_sum() {
      IntIterator iterator = ewahs[0].intIterator();
      int sum = 0;
      while (iterator.hasNext()) {
         sum += dataColumn[iterator.next()];
      }
      return sum;
   }
}