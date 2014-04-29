package com.ag.word_count;

/**
 * Counts words in a sequence of characters
 *
 * Word count is incremented whenever a transition from white-space character to non white-space is detected.
 * A white space character is detected by calling Character.isWhitespace(char)
 * 
 * @author      Adrian Gierakowski <agierakowski@gmail.com>
 * @version     0.0.1
 * @since       2014-04-10
 */

public class WordCounter implements CharacterPatternCounter {
	private boolean previousIsWhiteSpace = true;
	long wordCount = 0;
	
	/**
	 * Counts number of words in a sequence of characters, with the characters provided one by one in subsequent calls to this method.
	 * Characters should be provided in order from first to last.
	 * 
	 * This method is useful when the characters are read from a stream and become available one at a time.
	 * 
	 * Word count is incremented whenever a transition from white-space character to non white-space is detected.
	 * 
	 * @param	character	the character to be processed
	 */
	public void processChar(char character) {
		boolean currentIsWhiteSpace = Character.isWhitespace(character);
		if (previousIsWhiteSpace && !currentIsWhiteSpace)
			this.wordCount++;
		
		previousIsWhiteSpace = currentIsWhiteSpace;
	}
	
	/**
	 * Counts number of words in a sequence of characters, with the characters provided in an array. 
	 * The sequence can be provided all at once, or in chunks, providing new characters in subsequent calls to this method. 
	 * 
	 * This method is useful when the characters are read from a stream into a buffer.
	 * 
	 * Word count is incremented whenever a transition from white-space character to non white-space is detected.
	 * 
	 * @param	character	the character to be processed
	 * @param	processCount	number of characters in the array to be processed (starting from index 0)
	 */
	public void processChars(char[] arrayOfChars, int processCount) {
		if (processCount > arrayOfChars.length) {
			throw new IllegalArgumentException("Provided processCount: " + processCount + ", is greater the the length of processed array: " + arrayOfChars.length);
		}
		
		for (int i = 0; i < processCount; i++) {
			boolean currentIsWhiteSpace = Character.isWhitespace(arrayOfChars[i]);
			if (previousIsWhiteSpace && !currentIsWhiteSpace)
				this.wordCount++;
			
			previousIsWhiteSpace = currentIsWhiteSpace;
		}
	}

	 /**
     * resets the instance to initial state  
     */
	public void reset() {
		this.wordCount = 0;
		previousIsWhiteSpace = true;
	}

	 /**
     * wordCount getter
     * 
     * @return	word count so far (same as calling getCount())
     */
	public long getFinalCount() {
		return this.getCount();
	}

	 /**
     * wordCount getter
     * 
     * @return	word count so far
     */
	public long getCount() {
		return this.wordCount;
	}

	 /**
     * Getter for previousIsWhiteSpace field - used for testing 
     * 
     * @return	true previous characted was a white-space
     */
	public boolean getPreviousIsWhiteSpace() {
		return previousIsWhiteSpace;
	}
}
