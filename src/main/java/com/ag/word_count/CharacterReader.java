package com.ag.word_count;

/**
 * CharacterReader is a a source of characters. 
 * Characters from a CharacterReader are made available to callers of the {@code read} method via {@code char[]}.
 * If an implementation of CharacterReader uses an internal buffer, its size can be queried by the used using {@code getBufferSize()} method
 * 
 * @author      Adrian Gierakowski <agierakowski@gmail.com>
 * @version     0.0.1
 * @since       2014-04-10
 */

import java.io.IOException;

public interface CharacterReader {
	
	/**
	 * Attempts to read characters into the specified character array.
	 * 
	 * @param	charBuffer	the char[] to read characters into
	 * 
	 * @return	The number of char values copied to the array, or -1 if this source of characters is at its end
	 * 
	 * @throws	IOException if an I/O error occurs
	 */
	public int read(char[] charBuffer) throws IOException;
	
	/**
	 * Returns size of the internal buffer used by the implementation.
	 * 
	 * @return	size of the internal buffer if implementation uses such, or -1 if it doesn't
	 */
	public int getBufferSize();
}
