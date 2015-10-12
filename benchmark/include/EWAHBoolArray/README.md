#Compressed bitset in C++
[![Build Status](https://travis-ci.org/lemire/EWAHBoolArray.png)](https://travis-ci.org/lemire/EWAHBoolArray)




## What is this? 

The class EWAHBoolArray is a compressed bitset data structure.

It also provides a basic BoolArray class which can serve as a traditional
bitmap.

This library is used by database and information retrieval engines
such as Hustle -- A column oriented, embarrassingly distributed relational 
event database (see https://github.com/chango/hustle).

## Licensing 

Apache License 2.0. 

Update (May 20th, 2013): though by default I use the Apache License 2.0 (which is compatible with GPL 3.0), you can *also* consider this library licensed under GPL 2.0. 


## Dependencies 

None. (Will work under MacOS, Windows or Linux.)

Compilers tested: clang++, g++, Intel compiler, Microsoft Visual Studio

# Usage 

    make
    ./unit
    make example
    ./example

# Example 

Please see example.cpp.
For an example with tabular data, please see example2.cpp.

# Ruby wrapper 

Josh Ferguson wrote a wrapper for Ruby. 
The implementation is packaged and installable as a ruby gem.

You can install it by typing:

        gem install ewah-bitset

# Further reading 

Please see

* Daniel Lemire, Owen Kaser, Kamel Aouiche, Sorting improves
word-aligned bitmap indexes. Data & Knowledge Engineering 69 (1),
pages 3-28, 2010.
http://arxiv.org/abs/0901.3751


## Persistent storage 

If you save the bitmap to disk using "write" and then try to read it again with "read" on a different machine, it *may* crash. The file format is specific to the machine you are using. E.g., using two 64-bit Windows PC might work, but if you send the same data to a 32-bit Linux box it may fail.

You can get better persistence with the readBuffer and writeBuffer methods. They will be consistent across machines except for bit endianess: you may need to manually check bit endianess. Moreover, these methods require you to save some metadata (i.e., buffersize and sizeinbits) yourself in some portable way.


For saving in a persistent way:

     size_t sb = mybitmap.sizeInBits(); // save sb somewhere safe, your responsability!
     size_t bs = mybitmap.bufferSize(); // save bs somewhere safe, your responsability!
     mybitmap.writeBuffer(out); // this writes the internal buffer of the bitmap


For loading:

     mybitmap.readBuffer(in,bs); // recover the saved internal buffer, you are responsable for endianess
     mybitmap.setSizeInBits(sb); // you have to do this to get a proper bitmap size
