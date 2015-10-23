#include "benchmark/benchmark_api.h"
#include <string>
#include "ewah/ewah.h"
#include "util/JavaEWAHReader.hpp"

static void BM_iterator_access_sum(benchmark::State& state) {
	string filename = to_string(state.range_x() * 1000);
	EWAHBoolArray<uint64_t>** ewahs = JavaEWAHReader::readFile("../../bitmap_dumps/" +filename);
	uint64_t *columnData;
	columnData = new uint64_t[7000000];
	for (int i=0; i<7000000; ++i) {
		columnData[i] = rand() % 1000;
	}

	while (state.KeepRunning()) {
		uint64_t x = 0;
		EWAHBoolArray<uint64_t>::const_iterator end = ewahs[0]->end();
		for(EWAHBoolArray<uint64_t>::const_iterator i = ewahs[0]->begin(); i!=end; ++i) {
			x += columnData[*i];
		}
	}

	delete[] columnData;
	delete ewahs[0];
	delete ewahs[1];
	delete[] ewahs;
}
BENCHMARK(BM_iterator_access_sum)->DenseRange(0, 207);

BENCHMARK_MAIN()
