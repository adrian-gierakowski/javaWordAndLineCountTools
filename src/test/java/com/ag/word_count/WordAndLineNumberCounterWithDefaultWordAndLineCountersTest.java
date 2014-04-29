package com.ag.word_count;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class WordAndLineNumberCounterWithDefaultWordAndLineCountersTest {

	
	@Test
	public void whenProcessingEmptyFileWordCountAndLineCountAreZero() throws IOException {
		int bufferSize = 4096;
		// mocking a FileReader reader with StringReader
		CharacterReader characterReader = new BufferedReaderAdaptor(new StringReader(""), bufferSize);
		WordAndLineCounter counter = new WordAndLineCounter(characterReader);
		
		assertEquals(0, counter.getWordCount());
		assertEquals(0, counter.getLineCount());
	}
	
	@Test
	public void whenProcessingTextWithOnlyLFCharacterWordCountIsZeroAndLineCountIsOne() throws IOException {
		int bufferSize = 4096;
		CharacterReader characterReader = new BufferedReaderAdaptor(new StringReader("\n"), bufferSize);
		WordAndLineCounter counter = new WordAndLineCounter(characterReader);
		
		assertEquals(0, counter.getWordCount());
		assertEquals(1, counter.getLineCount());
	}
	
	@Test
	public void when_LF_CR_or_CRLF_arePresentInTheProcessedTextAllAreHandledCorrectly() throws IOException {
		int bufferSize = 4096;
		CharacterReader characterReader = new BufferedReaderAdaptor(new StringReader("\n\r\n\r"), bufferSize);
		WordAndLineCounter counter = new WordAndLineCounter(characterReader);
		
		assertEquals(0, counter.getWordCount());
		assertEquals(3, counter.getLineCount());
	}
	
	@Test
	public void whenProcessedTextIsNotEmptyAndDoesNotEndWithNewLineLineCountIsOneMoreThenTheTotalNumberOfNewLineOccureces() throws IOException {
		int bufferSize = 4096;
		CharacterReader characterReader = new BufferedReaderAdaptor(new StringReader("abc"), bufferSize);
		WordAndLineCounter counter = new WordAndLineCounter(characterReader);
		
		assertEquals(1, counter.getLineCount());
	}
	
	@Test
	public void whenProcessedTextEndsWithNewLineThenLineCountIsEqualToTotalNumberOfNewLineOccureces() throws IOException {
		int bufferSize = 4096;
		CharacterReader characterReader = new BufferedReaderAdaptor(new StringReader("abc\n"), bufferSize);
		WordAndLineCounter counter = new WordAndLineCounter(characterReader);
		
		assertEquals(1, counter.getLineCount());
	}
	
	@Test
	public void whenMultipleWhiteSpaceCharactersOccurInARowTheyAreHandledCorrectly() throws IOException {
		int bufferSize = 4096;
		CharacterReader characterReader = new BufferedReaderAdaptor(new StringReader("a  b\f\tc"), bufferSize);
		WordAndLineCounter counter = new WordAndLineCounter(characterReader);
		
		assertEquals(3, counter.getWordCount());
	}
	
	@Test
	public void whenLineOfTextContainsOnlyWhiteSpaceCharactersItIsTreatedAsNonEmptyLine() throws IOException {
		int bufferSize = 4096;
		CharacterReader characterReader = new BufferedReaderAdaptor(new StringReader("some   strange\f\tlayout"), bufferSize);
		WordAndLineCounter counter = new WordAndLineCounter(characterReader);
		
		assertEquals(3, counter.getWordCount());
	}
}
