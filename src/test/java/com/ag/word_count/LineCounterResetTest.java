package com.ag.word_count;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineCounterResetTest {
	
	@Test
	public void whenResetIsCalledFieldsOfTheInstanceAreResetToInitialValues() {
		LineCounter lineCounter = new LineCounter();
		
		long initialLineCount = lineCounter.getCount();
		boolean initialLastCharWasNonNewLine = lineCounter.getLastCharWasNonNewLine();
		boolean initialPreviousIsCR = lineCounter.getPreviousIsCR();
		
		// incrementing number of new lines
		lineCounter.processChar('\n');
		assertNotEquals(initialLineCount, lineCounter.getCount());
		// reseting
		lineCounter.reset();
		assertEquals(initialLineCount, lineCounter.lineCount);
		
		// changing lastCharWasNonNewLine
		lineCounter.processChar('a');
		assertNotEquals(initialLastCharWasNonNewLine, lineCounter.getLastCharWasNonNewLine());
		// reseting
		lineCounter.reset();
		assertEquals(initialPreviousIsCR, lineCounter.getPreviousIsCR());
		
		// changing previousIsCR
		lineCounter.processChar('\r');
		assertNotEquals(initialPreviousIsCR, lineCounter.getPreviousIsCR());
		// reseting
		lineCounter.reset();
		assertEquals(initialPreviousIsCR, lineCounter.getPreviousIsCR());
	}

}
