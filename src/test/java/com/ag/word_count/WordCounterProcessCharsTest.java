package com.ag.word_count;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordCounterProcessCharsTest {

	@Test (expected = IllegalArgumentException.class)
	public void whenProcessCountArgumentIsGreaterThanLengthOfProvidedArrayThrowsIllegalArgumentException() {
		WordCounter wordCounter= new WordCounter();
		
		char[] arrayOfChars = new char[]{'\n'};
		
		wordCounter.processChars(arrayOfChars, 2);
	}
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
	
	@Test
	public void processCountArgumentDeterminesHowManyCharactersFromTheArrayWillBeProcessed() {
		WordCounter wordCounter= new WordCounter();
		char[] arrayOfChars = new char[]{'a',' ','a',' '};
		long initalCount = wordCounter.getCount();
		
		// processing 2 out of 4
		wordCounter.processChars(arrayOfChars, 2);
		assertEquals(initalCount + 1, wordCounter.getCount());
		
		// processing 3 out of 4
		initalCount = wordCounter.getCount();
		wordCounter.processChars(arrayOfChars, 3);
		assertEquals(initalCount + 2, wordCounter.getCount());
	}
	
	@Test
	public void processingSingleArrayOfCharactersGivesTheSameResultsAsProcessingMultipleArraysWhichWhenConcatenatedHaveEquivalentContent() {
		WordCounter wordCounterLong = new WordCounter();
		WordCounter wordCounterSubArrays = new WordCounter();
		long wordCountInLongArray;
		long wordCountInSubArrays;
		long expectedWordCount = 3;
		
		char[] oneLongArrayOfChars = new char[]{'a',' ','a',' ', 'a','b','a',' '};
		char[] subArray1 = new char[]{'a',' ','a',' '};
		char[] subArray2 = new char[]{'a','b','a',' '};
		
		// process one long array
		wordCounterLong.processChars(oneLongArrayOfChars, oneLongArrayOfChars.length);
		wordCountInLongArray = wordCounterLong.getCount();
		
		// process two arrays with equivalent content
		wordCounterSubArrays.processChars(subArray1, subArray1.length);
		wordCounterSubArrays.processChars(subArray2, subArray2.length);
		wordCountInSubArrays = wordCounterSubArrays.getCount();
		
		assertEquals(expectedWordCount, wordCountInLongArray);
		assertEquals(expectedWordCount, wordCountInSubArrays);
	}
	
	// helper method
	private void assertCountIncrementsByNumber(char[] arrayOfChars, int countIncrement) {
		WordCounter wordCounter = new WordCounter();
		
		wordCounter.processChars(arrayOfChars, arrayOfChars.length);

		assertEquals(countIncrement, wordCounter.getCount());
	}
}
