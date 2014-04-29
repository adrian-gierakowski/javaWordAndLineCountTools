package com.ag.word_count;

/**
 * Detects and counts character patterns in character sequences
 * Characters from the sequences can be provided one by one via {@code processChar(char)} method 
 * or in bulk via {@code processChars(char[], int)}
 * 
 * @author      Adrian Gierakowski <agierakowski@gmail.com>
 * @version     0.0.1
 * @since       2014-04-10
 */

public interface CharacterPatternCounter {
	
	 /**
     * Processes a single character of a character sequences, in which one of the desired character patters is to be detected
     * Characters should be provided in the same order as they occur in the sequences in subsequent calls to this method 
     * 
     * @param	character	A character to be processed
     */
	public void processChar(char character);
	
	 /**
     * Allows to process the characters from the sequences in chunks, if the sequences can be read into a character buffer
     * If the whole sequences doesn't fit into the buffer, subsequent calls to this method can be called to provide further characters
     * 
     * @param	array of characters	An array of character to be processed
     * @param	processCount	number of characters in the array to be processed (starting from index 0)
     */
	public void processChars(char[] arrayOfChars, int processCount);
	
	 /**
     * get number of occurrences of the pattern so far, assuming the the sequence of chars has finished (for example when reached EOF)
     * can be different then the what is returned by {@code getCount()}, depending on the counting rules of the implementation
     * 
     * @return	number of total occurrences of the pattern in the stream
     */
	public long getFinalCount();
	
	 /**
     * get number of occurrences of the pattern so far, assuming that the sequence of chars might continue
     * can be different then the what is returned by {@code getFinalCount()}, depending on the counting rules of the implementation
     * 
     * @return	number of total occurrences of the pattern in the stream
     */
	public long getCount();
	
	 /**
     * resets the instance to initial state  
     */
	public void reset();
}
