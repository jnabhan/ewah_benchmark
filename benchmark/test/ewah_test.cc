#include "benchmark/benchmark_api.h"
#include <string>
#include "ewah/ewah.h"
#include "util/JavaEWAHReader.hpp"

static void BM_logicaland(benchmark::State& state) {
	string filename = to_string(state.range_x() * 1000);
	EWAHBoolArray<uint64_t>** ewahs = JavaEWAHReader::readFile("../../bitmap_dumps/" +filename);
	EWAHBoolArray<uint64_t>* container = new EWAHBoolArray<uint64_t>;

	while (state.KeepRunning()) {
		ewahs[0]->logicaland(*ewahs[1], *container);
	}

	delete container;
	delete ewahs[0];
	delete ewahs[1];
	delete[] ewahs;
}
BENCHMARK(BM_logicaland)->DenseRange(0, 207);


BENCHMARK_MAIN()
