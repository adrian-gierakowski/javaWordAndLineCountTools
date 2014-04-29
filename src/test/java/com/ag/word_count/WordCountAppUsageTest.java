package com.ag.word_count;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordCountAppUsageTest {
	private PrintStream originalPrintStream;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpTestStream() {
		this.originalPrintStream = System.out;
	    System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void printsErrorMessageAndUsageInformation() {
		final String message = "error message";
		
		WordCountAppRunner.usage(message);
		assertEquals(message + '\n' + WordCountAppRunner.UsageInfo, outContent.toString());
	}

	@After
	public void cleanUpTestStream() {
		System.setOut(this.originalPrintStream);
	}
}
