package com.ag.word_count;

/**
 * Counts new lines in a sequence of characters
 * 
 * Matches 3 most common new line patterns: \r, \n, \r\n
 * 
 * The getCountAsIfSequenceFinished() can be used to get the correct number of lines in a text file 
 * when last line does not end with one of the above new line patterns
 * 
 * @author      Adrian Gierakowski <agierakowski@gmail.com>
 * @version     0.0.1
 * @since       2014-04-10
 */

public class LineCounter implements CharacterPatternCounter {
	private boolean previousIsCR = false;
	private boolean lastCharWasNonNewLine = false;

	long lineCount = 0;
	 
	/**
	 * Counts lines in a sequence of characters, with the characters provided one by one in subsequent calls to this method.
	 * Characters should be provided in order from first to last.
	 * 
	 * This method is useful when the characters are read from a stream and become available one at a time.
	 * 
	 * lineCount is incremented when one of the following patterns is encountered: \r, \n, \r\n
	 * \r\n causes increment by one (the lineCount is incremented as soon as \r occurs and subsequent \n is ignored)
	 * 
	 * @param	character	the character to be processed
	 */
	public void processChar(char character) {
		
		switch (character) {
		// when CR is encountered, lineCount is incremented immediately
		case '\r':
			this.lineCount++;
			previousIsCR = true;
			lastCharWasNonNewLine = false;
			break;
		case '\n':
			lastCharWasNonNewLine = false;
			if (previousIsCR) {
				// new line has already been counted when processing CR	
				previousIsCR = false;
			}
			else {
				this.lineCount++;
			}
			break;
		default:
			lastCharWasNonNewLine = true;
			previousIsCR = false;
		}
	}
	
	/**
	 * Counts lines in a sequence of characters, with the characters provided in an array. 
	 * The sequence can be provided all at once, or in chunks, providing new characters in subsequent calls to this method. 
	 * 
	 * This method is useful when the characters are read from a stream into a buffer.
	 * 
	 * lineCount is incremented when one of the following patterns is encountered: \r, \n, \r\n
	 * \r\n causes increment by one (the lineCount is incremented as soon as \r occurs and subsequent \n is ignored)
	 * 
	 * @param	character	the character to be processed
	 * @param	processCount	number of characters in the array to be processed (starting from index 0)
	 */
	public void processChars(char[] arrayOfChars, int processCount) {
		if (processCount > arrayOfChars.length) {
			throw new IllegalArgumentException("Provided processCount: " + processCount + ", is greater the the length of processed array: " + arrayOfChars.length);
		}
		
		for (int i = 0; i < processCount; i++) {
			switch (arrayOfChars[i]) {
			// when CR is encountered, lineCount is incremented immediately
			case '\r':
				this.lineCount++;
				previousIsCR = true;
				lastCharWasNonNewLine = false;
				break;
			case '\n':
				lastCharWasNonNewLine = false;
				if (previousIsCR) {
					// new line has already been counted when processing CR	
					previousIsCR = false;
				}
				else {
					this.lineCount++;
				}
				break;
			default:
				lastCharWasNonNewLine = true;
				previousIsCR = false;
			}
		}
	}

	 /**
     * resets the instance to initial state  
     */
	public void reset() {
		this.previousIsCR = false;
		this.lastCharWasNonNewLine = false;
		this.lineCount = 0;
	}

	/**
	 * Get count of lines in processed character sequence, accounting for cases when last processed character was not part of new line pattern:
	 * when non-empty text file does not end with new line, the number of lines should be 1 more then number of new line character patterns
	 * 
	 * @return	number of lines in the processed text
	 */
	public long getFinalCount() {
		if (lastCharWasNonNewLine)
			return this.lineCount + 1;
		else
			return this.lineCount;
	}

	/**
	 * Getter for lineCount
	 * 
	 * @return	count of new line character patterns encountered in the processed character sequence so far
	 */
	public long getCount() {
		return this.lineCount;
	}
	
	/**
	 * Getter for previousIsCR field - used for testing
	 * 
	 * @return	true if previous processed character was CR
	 */
	public boolean getPreviousIsCR() {
		return previousIsCR;
	}

	/**
	 * Getter for lastCharWasNonNewLine field - used for testing
	 * 
	 * @return	true if last character was not part of new line pattern
	 */
	public boolean getLastCharWasNonNewLine() {
		return lastCharWasNonNewLine;
	}
}
