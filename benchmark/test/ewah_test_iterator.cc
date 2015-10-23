#include "benchmark/benchmark_api.h"
#include <string>
#include "ewah/ewah.h"
#include "util/JavaEWAHReader.hpp"

static void BM_iterator(benchmark::State& state) {
	string filename = to_string(state.range_x() * 1000);
	EWAHBoolArray<uint64_t>** ewahs = JavaEWAHReader::readFile("../../bitmap_dumps/" +filename);

	while (state.KeepRunning()) {
		EWAHBoolArray<uint64_t>::const_iterator end = ewahs[0]->end();
		for(EWAHBoolArray<uint64_t>::const_iterator i = ewahs[0]->begin(); i!=end; ++i) {
		}
	}

	delete ewahs[0];
	delete ewahs[1];
	delete[] ewahs;
}
BENCHMARK(BM_iterator)->DenseRange(0, 207);

BENCHMARK_MAIN()
