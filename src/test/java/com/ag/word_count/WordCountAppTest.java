package com.ag.word_count;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Unit test for simple App.
 */
public class WordCountAppTest
{
	private PrintStream originalOutStream;
	private PrintStream originalErrStream;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpTestStream() {
		this.originalOutStream = System.out;
	    System.setOut(new PrintStream(outContent));
	    
	    this.originalErrStream = System.err;
	    System.setErr(new PrintStream(errContent));
	}
	
	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	
	@Test
    public void whenOnlyACorrectFilePathIsProvidedThenNumberOfWordsInThisFileIsPrinted()
    {
		// get absolute file path of a file residing in classpath 
		String filePath = getClass().getClassLoader().getResource("sample.txt").getPath();
		String[] args = {filePath};
		
		WordCountAppRunner app = new WordCountAppRunner();
		app.run(args);
		
		assertEquals("9\n", outContent.toString());
    }
	
	@Test
    public void whenCorrectFilePathIsProvidedAnd_l_ParameterIsSpecifiedThenNumberOfWordsAndLinesInThisFileIsPrinted()
    {
		// get absolute file path of a file residing in classpath 
		String filePath = getClass().getClassLoader().getResource("sample.txt").getPath();
		String[] args = {"-l", filePath};
		
		WordCountAppRunner app = new WordCountAppRunner();
		app.run(args);
		
		assertEquals("9\n3\n", outContent.toString());
    }
	
	@Test
    public void whenBadFilePathIsProvidedAnErrorMessageIsPrinted()
    {
		String[] args = {""};
		
		WordCountAppRunner app = new WordCountAppRunner();
		app.run(args);
		
		assertEquals("Error: can't open file.\n", errContent.toString());
    }
	
	@Test 
    public void whenProcessingSampleFileSavedWithUTF16EncodingResutsAreWrong()
    {
		String filePath = getClass().getClassLoader().getResource("sample_UTF-16.txt").getPath();
		String[] args = {filePath};
		
		WordCountAppRunner app = new WordCountAppRunner();
		app.run(args);
		
		assertNotEquals("9\n", outContent.toString());
    }
	
	@Test (expected = IllegalArgumentException.class) 
    public void whenUserProvides3ArgumensIllegalArgumentExceptionIsThrown()
    {
		String[] args = {"a","b","c"};
		WordCountAppRunner app = new WordCountAppRunner();
		app.run(args);
    }
	
	@After
	public void cleanUpTestStream() {
		System.setOut(this.originalOutStream);
		System.setErr(this.originalErrStream);
	}
}
