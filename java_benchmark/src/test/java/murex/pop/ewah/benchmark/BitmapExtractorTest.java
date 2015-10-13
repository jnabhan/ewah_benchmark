package murex.pop.ewah.benchmark;

import com.googlecode.javaewah.EWAHCompressedBitmap;
import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class BitmapExtractorTest {
   EWAHCompressedBitmap bitmap1 = EWAHCompressedBitmap.bitmapOf(0, 1);
   EWAHCompressedBitmap bitmap2 = EWAHCompressedBitmap.bitmapOf(2, 3, 5);

   @Test
   public void
   it_should_write_two_bitmaps_to_file() {
      BitmapExtractor.store(bitmap1, bitmap2, "test_file");
      EWAHCompressedBitmap[] readBitmaps = BitmapExtractor.readFile("test_file");

      Assertions.assertThat(readBitmaps[0]).isEqualTo(bitmap1);
      Assertions.assertThat(readBitmaps[1]).isEqualTo(bitmap2);
   }

   @Test
   public void
   it_should_conserve_the_size_in_bits() {
      bitmap1.setSizeInBits(20, false);
      bitmap2.setSizeInBits(40, false);

      BitmapExtractor.store(bitmap1, bitmap2, "test_file");
      EWAHCompressedBitmap[] readBitmaps = BitmapExtractor.readFile("test_file");


      Assertions.assertThat(readBitmaps[0].sizeInBits()).isEqualTo(bitmap1.sizeInBits());
      Assertions.assertThat(readBitmaps[1].sizeInBits()).isEqualTo(bitmap2.sizeInBits());
   }
}
