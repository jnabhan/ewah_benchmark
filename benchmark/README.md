##To build the benchmark project##
from ./benchmark
	mkdir Release
	cd Release
	cmake -DCMAKE_BUILD_TYPE=Release ..
	make

##To run the benchmarks##
from ./benchmark/Release
	./test/ewah_test
