package com.ag.word_count;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineCounterProcessCharTest {
	
	@Test
	public void whenProcessingLFCharacterNotPreceededByCRCharacterCountIncrementsByOne() {
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
	public void whenProcessingCRCharacterCountAlwaysIncrementsByOne() {
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
	
	// helper method
	private void assertCountIncrementsByNumber(char[] arrayOfChars, int countIncrement) {
		LineCounter lineCounter = new LineCounter();
		
		for (int i = 0; i < arrayOfChars.length; i++) {
			lineCounter.processChar(arrayOfChars[i]);
		}

		assertEquals(countIncrement, lineCounter.getCount());
	}
}
