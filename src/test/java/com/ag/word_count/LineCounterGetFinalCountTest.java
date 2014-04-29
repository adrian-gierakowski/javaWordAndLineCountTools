package com.ag.word_count;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineCounterGetFinalCountTest {

	@Test
	public void whenGetFinalCountIsCalledAfterProcessingNonWhiteSpaceItsReturnValueIsGreaterByOneThenReturnedByGetCount() {
		LineCounter lineCounter = new LineCounter();
		
		lineCounter.processChar('a');
		lineCounter.processChar('\n');
		lineCounter.processChar('a');
		
		assertEquals(1, lineCounter.getCount());
		assertEquals(2, lineCounter.getFinalCount());
	}
}
