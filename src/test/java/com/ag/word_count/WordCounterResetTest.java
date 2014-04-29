package com.ag.word_count;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordCounterResetTest {

	@Test
	public void whenResetIsCalledFieldsOfTheInstanceAreResetToInitialValues() {
		WordCounter whiteSpaceDetector = new WordCounter();
		
		long initialWordCount = whiteSpaceDetector.getCount();
		boolean initialPreviousIsWhiteSpace = whiteSpaceDetector.getPreviousIsWhiteSpace();
		
		// changing the state
		whiteSpaceDetector.processChar('a');
		assertNotEquals(initialWordCount, whiteSpaceDetector.getCount());
		assertNotEquals(initialPreviousIsWhiteSpace, whiteSpaceDetector.getPreviousIsWhiteSpace());
		
		// calling reset
		whiteSpaceDetector.reset();
		assertEquals(initialWordCount, whiteSpaceDetector.getCount());
		assertEquals(initialPreviousIsWhiteSpace, whiteSpaceDetector.getPreviousIsWhiteSpace());
	}
}
