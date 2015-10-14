#pragma once

#include "ewah/ewah.h"
#include <fstream>
#include <iostream>

class JavaEWAHReader {
public:
	JavaEWAHReader() {

	}

	static EWAHBoolArray<uint64_t>** readFile(string filename) {
		EWAHBoolArray<uint64_t>** ewahs = new EWAHBoolArray <uint64_t>*[2];

		ifstream inputStream;
		inputStream.open(filename, ios::binary);

		ewahs[0] = readOneBitmap(&inputStream);
		ewahs[1] = readOneBitmap(&inputStream);

		inputStream.close();

		return ewahs;
	}

	virtual ~JavaEWAHReader() {

	}

	static EWAHBoolArray<uint64_t>* readOneBitmap(ifstream* inputStream) {
		EWAHBoolArray<uint64_t>* ewah = new EWAHBoolArray<uint64_t>;

		uint32_t sizeInBits = 0;
		inputStream->read((char *)&sizeInBits, 4);
		sizeInBits = swapBytesIfNecessary(sizeInBits);

		uint32_t numberOfOnes = 0;
		inputStream->read((char *)&numberOfOnes, 4);
		numberOfOnes = swapBytesIfNecessary(numberOfOnes);

		uint32_t tmp = 0;
		for (unsigned long i = 0; i<numberOfOnes; ++i) {
			inputStream->read((char *)&tmp, 4);
			tmp = swapBytesIfNecessary(tmp);
			ewah->set(tmp);
		}

		ewah->setSizeInBits(sizeInBits);
		return ewah;
	}
private:
	static uint32_t swapBytesIfNecessary(uint32_t num) {
		int i = 1;
		if (*(char *)&i == 1) {
			// little endian machine
			return ((num>>24)&0xff) | // move byte 3 to byte 0
		            ((num<<8)&0xff0000) | // move byte 1 to byte 2
		            ((num>>8)&0xff00) | // move byte 2 to byte 1
		            ((num<<24)&0xff000000); // byte 0 to byte 3
		} else {
			// big endian machine
			return num;
		}
	}
};
