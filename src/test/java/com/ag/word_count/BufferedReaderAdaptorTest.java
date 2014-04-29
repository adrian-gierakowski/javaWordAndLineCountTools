package com.ag.word_count;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class BufferedReaderAdaptorTest {

	@Test
	public void test() throws IOException {
		char[] buffer = new char[2];
		BufferedReaderAdaptor bufferedReaderAdapter = new BufferedReaderAdaptor(new StringReader("abc"), 4);
		
		assertEquals(4, bufferedReaderAdapter.getBufferSize());
		
		int numCharsRead = bufferedReaderAdapter.read(buffer);
		
		assertEquals(2, numCharsRead);
		assertEquals('a', buffer[0]);
		assertEquals('b', buffer[1]);
		
		numCharsRead = bufferedReaderAdapter.read(buffer);

		assertEquals(1, numCharsRead);
		assertEquals('c', buffer[0]);
		assertEquals('b', buffer[1]);
		
		numCharsRead = bufferedReaderAdapter.read(buffer);

		assertEquals(-1, numCharsRead);
		assertEquals('c', buffer[0]);
		assertEquals('b', buffer[1]);
	}

}
