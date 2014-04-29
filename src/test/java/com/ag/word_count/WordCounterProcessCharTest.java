package com.ag.word_count;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordCounterProcessCharTest {
	
	@Test
	public void whenFirstCharacterProcessedAfterInstantiationIsNonWhiteSpaceCountIncrementsByOne() {
		assertCountIncrementsByNumber(new char[]{'a'}, 1);
	}
	
	@Test
	public void whenNonWhiteSpaceCharacterIsProcessedAfterWhiteSpaceCharacterCountIncrementsByOne() {
		// Space
		assertCountIncrementsByNumber(new char[]{' ', 'a'}, 1);		
		// Tab
		assertCountIncrementsByNumber(new char[]{'\t', 'a'}, 1);		
		// FormFeed
		assertCountIncrementsByNumber(new char[]{'\f', 'a'}, 1);
		// LF
		assertCountIncrementsByNumber(new char[]{'\n', 'a'}, 1);
		// CR
		assertCountIncrementsByNumber(new char[]{'\r', 'a'}, 1);
	}
	
	@Test
	public void whenWhiteSpaceCharacterIsProcessedAfterWhiteSpaceCharacterCountDoesNotIncrement() {
		assertCountIncrementsByNumber(new char[]{' ', '\t', '\n', '\r', '\n', '\t'}, 0);
	}
	
	@Test
	public void whenProcessingAnyCharacterAfterNonWhiteSpaceCharacterCountDoesNotIncrement() {	
		assertCountIncrementsByNumber(new char[]{'a', ' '}, 1);
		assertCountIncrementsByNumber(new char[]{'a', '1', '?', '\n'}, 1);
	}
	
	// helper method
	private void assertCountIncrementsByNumber(char[] arrayOfChars, int countIncrement) {
		WordCounter wordCounter = new WordCounter();
		
		for (int i = 0; i < arrayOfChars.length; i++) {
			wordCounter.processChar(arrayOfChars[i]);
		}

		assertEquals(countIncrement, wordCounter.getCount());
	}
}