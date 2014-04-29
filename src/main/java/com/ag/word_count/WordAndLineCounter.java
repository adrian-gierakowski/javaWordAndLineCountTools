package com.ag.word_count;

/**
 * Counts number of words and lines in text read from a character-input stream.
 * Custom objects implementing CharacterPatternDetector interface can be injected to facilitate different rules for counting lines and words.
 * A constructor using default CharacterPatternDetector implementations is provided.
 * 
 * Reading from the character-input stream and counting of words and line happens on object instantiation.
 * User can query word and line count with provided getter methods.
 * 
 * @author      Adrian Gierakowski <agierakowski@gmail.com>
 * @version     0.0.1
 * @since       2014-04-10
 */

import java.io.IOException;

public class WordAndLineCounter {

	private long wordCount;
	private long lineCount;
	private CharacterReader reader;
	private CharacterPatternCounter lineCounter;
	private CharacterPatternCounter wordCounter;
	private char[] buffer;
	
	/**
     * Creates an instance WordAndLineNumberCounter, with default LineCounter and WordCounter implementations
     * 
     * @param	reader	a CharacterReader, which wraps a character-input stream from which characters are to be read
     */
	public WordAndLineCounter(CharacterReader reader) throws IOException {
		this(reader, new LineCounter(), new WordCounter());
	}
	
	/**
     * Creates an instance WordAndLineNumberCounter, with LineCounter and WordCounter injected as paramters
     * 
     * @param	reader	a CharacterReader, which wraps a character-input stream from which characters are to be read
     * 
     * @param	lineCounter	CharacterPatternCounter instance used for line counting
     * 
     * @param	wordCounter	CharacterPatternCounter instance used for word counting
     */
	public WordAndLineCounter(CharacterReader reader, CharacterPatternCounter lineCounter, CharacterPatternCounter wordCounter) throws IOException {
		this.reader = reader;
		this.lineCounter = lineCounter;
		this.wordCounter = wordCounter;
		
		int bufferSize = reader.getBufferSize();
		if (bufferSize == -1)
			bufferSize = 4096;
			
		buffer = new char[bufferSize];
	
		// provide default implementations for detecting new line and word separators
		this.lineCounter = new LineCounter();
		this.wordCounter = new WordCounter();
		
		count();
	}


	/**
     * Get total word count
     * 
     * @return	number of words in the processed stream
     */
	public long getWordCount() {
		return wordCount;
	}

	/**
     * Get total line count
     * 
     * @return	number of lines in the processed stream
     */
	public long getLineCount() {
		return lineCount;
	}
	
	/**
     * Internal method used to read from the stream and forward characters to {@code lineCounter} and {@code wordCounter}
     */
	private void count() throws IOException {
		int readCount = reader.read(buffer);
		
		while (readCount != -1) {
			lineCounter.processChars(buffer, readCount);
			wordCounter.processChars(buffer, readCount);
			readCount = reader.read(buffer);
		}
		
		// account for any special conditions, for example: last line in non-empty text not finishing with new line pattern etc.
		lineCount = lineCounter.getFinalCount();
		wordCount = wordCounter.getFinalCount();
	}
}

