package murex.pop.ewah.benchmark;

import com.googlecode.javaewah.EWAHCompressedBitmap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BitmapExtractor {
   private static final String PATH = "./target/bitmap_dumps";
   private static int serialNumber;

   static {
      File directory = new File(PATH);
      directory.mkdirs();
   }

   public static void selectBitmaps(EWAHCompressedBitmap bitmap1, EWAHCompressedBitmap bitmap2) {
      if ((serialNumber++ % 1000) == 0) {
         store(bitmap1, bitmap2, "" + (serialNumber - 1));
      }
   }

   static void store(EWAHCompressedBitmap bitmap1, EWAHCompressedBitmap bitmap2, String filename) {
      DataOutputStream fileStream = null;
      try {
         deleteFile(filename);

         fileStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(PATH + "/" + filename)));

         writeBitmapToFile(bitmap1, fileStream);
         writeBitmapToFile(bitmap2, fileStream);

         fileStream.flush();
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         safeClose(fileStream);
      }
   }

   static void deleteFile(String filename) {
      File file = new File(PATH + "/" + filename);
      file.delete();
   }

   private static void writeBitmapToFile(EWAHCompressedBitmap bitmap, DataOutputStream fileStream) throws IOException {
      int[] ones = bitmap.toArray();

      fileStream.writeInt(bitmap.sizeInBits());
      fileStream.writeInt(ones.length);
      for (int i : ones) {
         fileStream.writeInt(i);
      }
   }

   static EWAHCompressedBitmap[] readFile(String filename) {
      return readFileFromPath(PATH + "/" + filename);
   }

   public static EWAHCompressedBitmap[] readFileFromPath(String filepath) {
      DataInputStream fileStream = null;
      EWAHCompressedBitmap[] bitmaps = new EWAHCompressedBitmap[2];
      try {
         fileStream = new DataInputStream(new BufferedInputStream(new FileInputStream(filepath)));

         bitmaps[0] = readBitmapFromFile(fileStream);
         bitmaps[1] = readBitmapFromFile(fileStream);

      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } finally {
         safeClose(fileStream);
         return bitmaps;
      }
   }

   private static EWAHCompressedBitmap readBitmapFromFile(DataInputStream fileStream) throws IOException {
      int sizeInBits = fileStream.readInt();
      int numberOfOnes = fileStream.readInt();

      EWAHCompressedBitmap bitmap = new EWAHCompressedBitmap();
      for (int i = 0; i<numberOfOnes; ++i) {
         bitmap.set(fileStream.readInt());
      }
      bitmap.setSizeInBits(sizeInBits, false);

      return bitmap;
   }

   private static void safeClose(OutputStream out) {
      try {
         if (out != null) {
            out.close();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static void safeClose(InputStream in) {
      try {
         if (in != null) {
            in.close();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
