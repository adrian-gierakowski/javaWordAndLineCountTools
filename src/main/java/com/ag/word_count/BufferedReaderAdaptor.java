package com.ag.word_count;

/**
 * Wraps java.io.BufferedReader, implementing CharacterReader interface.
 * User has to explicitly specify buffer size to be used by BufferedReader.
 * Buffer size is saved in local variable, which can be then queried by clients of character reader.
 * 
 * @author      Adrian Gierakowski <agierakowski@gmail.com>
 * @version     0.0.1
 * @since       2014-04-10
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class BufferedReaderAdaptor implements CharacterReader {
	BufferedReader bufferedReader;
	int bufferSize;
	
	/**
     * Creates an instance BufferedReaderAdaptor, which wraps a java.io.BufferedReader using an input buffer of the specified size 
     * 
     * @param	reader	java.io.Reader to be wrapped in BufferedReader
     * @param	bufferSize	size of the internal buffer of the BufferedReader
     */
	public BufferedReaderAdaptor(Reader reader, int bufferSize) {
		bufferedReader = new BufferedReader(reader, bufferSize);
		this.bufferSize = bufferSize;
	}
	
	 /**
     * Reads characters into a portion of an array using {@code read(char[])} method of the underlying BufferedReader
     * 
     * @exception	IOException	If an I/O error occurs
     */
	public int read(char[] charBuffer) throws IOException {
		return bufferedReader.read(charBuffer);
	}
	
	 /**
     * close the underlying BufferedReader
     * 
     * @exception	IOException	If an I/O error occurs
     */
	public void close() throws IOException  {
		bufferedReader.close();
	}
	
	 /**
     * Get buffer size of the underlying BufferedReader
     * 
     * @exception IOException  If an error occurs while closing
     */
	public int getBufferSize() {
		return this.bufferSize;
	}
}
