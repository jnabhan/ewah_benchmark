##To build the benchmark project##
from ./benchmark<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mkdir Release<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cd Release<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cmake -DCMAKE_BUILD_TYPE=Release ..<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;make<br/>

##To run the benchmarks##
from ./benchmark/Release<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;./test/ewah_test<br/>

##dump_files##
Each dump file contains two bitmaps written consecutively in the following manner<br/>
<ul>
  <li>an int representing the size in bits</li>
  <li>followed by an int representing the number of ones</li>
  <li>followed by int representations of the positions of all the ones</li>
</ul>
All ints in the dump files are in a big-endian format.<br/>
The benchmark checks the endianness of the machine and does the conversion automatically.
