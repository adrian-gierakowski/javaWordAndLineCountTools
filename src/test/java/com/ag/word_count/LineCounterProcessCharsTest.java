package com.ag.word_count;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineCounterProcessCharsTest {

	@Test (expected = IllegalArgumentException.class)
	public void whenProcessCountArgumentIsGreaterThanLengthOfProvidedArrayThrowsIllegalArgumentException() {
		LineCounter lineCounter= new LineCounter();
		
		char[] arrayOfChars = new char[]{'\n'};
		
		lineCounter.processChars(arrayOfChars, 2);
	}
	
	@Test
	public void whenProcessingArrayOfCharactersEachOccurenceOfLFCharacterNotPreceededByCRCharacterIncrementsCountByOne() {
		// single LF character
		assertCountIncrementsByNumber(new char[]{'\n'}, 1);
		
		// non new line character followed by single LF character
		assertCountIncrementsByNumber(new char[]{'\n', 'a'}, 1);
		
		// single LF character followed by non new line character 
		assertCountIncrementsByNumber(new char[]{'a', '\n'}, 1);
		
		// two LF characters in a row
		assertCountIncrementsByNumber(new char[]{'\n', '\n'}, 2);
	}
	
	@Test
	public void whenProcessingArrayOfCharactersEachOccurenceOfCRCharacterIncrementsCountByOne() {
		// single CR character
		assertCountIncrementsByNumber(new char[]{'\r'}, 1);
		
		// non new line character followed by single LF character
		assertCountIncrementsByNumber(new char[]{'a', '\r'}, 1);
		
		// single CR character followed by non new line character 
		assertCountIncrementsByNumber(new char[]{'\r', 'a'}, 1);
		
		// two CR characters in a row
		assertCountIncrementsByNumber(new char[]{'\r', '\r'}, 2);
	}
	
	
	@Test
	public void whenProcessingNonCRorLFCharactersCountDoesNotIncrement() {
		assertCountIncrementsByNumber(new char[]{'a', '1', '.', '?', '\t'}, 0);
	}
	
	@Test
	public void whenProcessingCRCharacterFollowedByLFCharacterCountIncrementsByOne() {
		assertCountIncrementsByNumber(new char[]{'\r', '\n'}, 1);
	}
	
	@Test
	public void processCountArgumentDeterminesHowManyCharactersFromTheArrayWillBeProcessed() {
		LineCounter lineCounter= new LineCounter();
		char[] arrayOfChars = new char[]{'\n','\n','\n','\n'};
		long initalCount = lineCounter.getCount();
		
		// processing 3 LF out of 4
		lineCounter.processChars(arrayOfChars, 3);
		assertEquals(initalCount + 3, lineCounter.getCount());
		
		// processing 2 LF out of 4
		initalCount = lineCounter.getCount();
		
		lineCounter.processChars(arrayOfChars, 2);
		assertEquals(initalCount + 2, lineCounter.getCount());
	}
	
	@Test
	public void processingSingleArrayOfCharactersGivesTheSameResultsAsProcessingMultipleArraysWhichWhenConcatenatedHaveEquivalentContent() {
		LineCounter lineCounterLong = new LineCounter();
		LineCounter lineCounterSubArrays = new LineCounter();
		long lineCountInLongArray;
		long lineCountInSubArrays;
		long expectedLineCount = 3;
		
		char[] oneLongArrayOfChars = new char[]{'\n',' ','\n',' ', 'a','\n','a',' '};
		char[] subArray1 = new char[]{'\n',' ','\n',' '};
		char[] subArray2 = new char[]{'a','\n','a',' '};
		
		// check that the arrays are equivalent first
		assertEquals(new String(oneLongArrayOfChars), new String(subArray1) + new String(subArray2));
		
		// process one long array
		lineCounterLong.processChars(oneLongArrayOfChars, oneLongArrayOfChars.length);
		lineCountInLongArray = lineCounterLong.getCount();
		
		// process two arrays with equivalent content
		lineCounterSubArrays.processChars(subArray1, subArray1.length);
		lineCounterSubArrays.processChars(subArray2, subArray2.length);
		lineCountInSubArrays = lineCounterSubArrays.getCount();
		
		assertEquals(expectedLineCount, lineCountInLongArray);
		assertEquals(expectedLineCount, lineCountInSubArrays);
	}
	
	// helper method
	private void assertCountIncrementsByNumber(char[] arrayOfChars, int countIncrement) {
		LineCounter lineCounter = new LineCounter();
		
		lineCounter.processChars(arrayOfChars, arrayOfChars.length);
		
		assertEquals(countIncrement, lineCounter.getCount());
	}
}
