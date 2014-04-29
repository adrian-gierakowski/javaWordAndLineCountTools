package com.ag.word_count;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordCountAppHandleCommandLineArgumentsTest {

	private PrintStream originalOutStream;
	private PrintStream originalErrStream;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	WordCountAppRunner app;
	
	@Before
	public void setUpTestStream() {
		this.originalOutStream = System.out;
	    System.setOut(new PrintStream(outContent));
	    
	    this.originalErrStream = System.err;
	    System.setErr(new PrintStream(errContent));
	    
	    app = new WordCountAppRunner();
	}
	
	@Test
	public void whenJustFilenNameIsProvidedReturnsFalse() {
		String[] args = {"sample"};
		
		assertFalse(app.handleCommandLineArguments(args));
	}
	
	@Test
	public void when_l_OptionAndFilenNameAreProvidedReturnsFalse() {
		String[] args = {"-l", "sample"};
		assertFalse(app.handleCommandLineArguments(args));
	}
	
	@Test
	public void whenNoArgumentsAreProvidedErrorAndUsageMessagesArePrintedAndTrueIsReturned() {
		String[] args = {};
		
		assertTrue(app.handleCommandLineArguments(args));
		assertEquals("No arguments provided.\n" + WordCountAppRunner.UsageInfo, outContent.toString());
	}

	@Test
	public void whenTooManyArgumentsAreProvidedErrorAndUsageMessagesArePrintedAndTrueIsReturned() {
		String[] args = {"a","b","c"};
		
		assertTrue(app.handleCommandLineArguments(args));
		assertEquals("Too many arguments.\n" + WordCountAppRunner.UsageInfo, outContent.toString());
	}
	
	@Test
	public void whenTooArgumentsAreProvidedButDoNotMatchExpectedPatternErrorAndUsageMessagesArePrintedAndTrueIsReturned() {
		String[] args = {"a", "b"};
		
		assertTrue(app.handleCommandLineArguments(args));
		assertEquals("Illegal arguments.\n" + WordCountAppRunner.UsageInfo, outContent.toString());
	}
	
	@After
	public void cleanUpTestStream() {
		System.setOut(this.originalOutStream);
		System.setErr(this.originalErrStream);
	}
}
