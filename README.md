##C++##
###To build the benchmark project###
from ./benchmark<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mkdir Release<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cd Release<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cmake -DCMAKE_BUILD_TYPE=Release ..<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;make<br/>

###To run the benchmarks###
from ./benchmark/Release (necessary because of hard-coded path for bitmaps)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;./test/ewah_test<br/>

##Java##
###To build the benchmark project###
from ./java_benchmark<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mvn clean install<br/>

###To run the benchmarks###
from ./java_benchmark<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;java -cp target/benchmarks.jar murex.pop.ewah.benchmark.BitmapBenchmarks

##dump_files##
Each dump file contains two bitmaps written consecutively in the following manner<br/>
<ul>
  <li>an int representing the size in bits</li>
  <li>followed by an int representing the number of ones</li>
  <li>followed by int representations of the positions of all the ones</li>
</ul>
All ints in the dump files are in a big-endian format.<br/>
The benchmark checks the endianness of the machine and does the conversion automatically.
