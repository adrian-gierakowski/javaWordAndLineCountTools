package com.ag.word_count;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class WordCountAppRunner 
{
	boolean printLineCount = false;
	String fileName;
	
	final static String UsageInfo = "Usage:\n"
			+ "\tjava -jar word-count-0.0.1-SNAPSHOT.jar [-l] filename\n"
			+ "Description:\n"
			+ "\tCounts the number of words in a file and prints out the result to console.\n"
			+ "Options:\n"
			+ "\t-l\tAlso counts and prints the number of lines in the file.\n";
	
    public void run(String[] args) 
    {	
    	if (handleCommandLineArguments(args))
    		throw new IllegalArgumentException();
    	
    	BufferedReaderAdaptor reader = null;
    	try {
    		// using FileReader since we assume default character encoding
    		int bufferSize = 4096;
			reader = new BufferedReaderAdaptor(new FileReader(this.fileName), bufferSize);
			try {
				WordAndLineCounter wordCounter = new WordAndLineCounter(reader);
				System.out.println(wordCounter.getWordCount());
				if (this.printLineCount) {
					System.out.println(wordCounter.getLineCount());
				}
			} catch (IOException e) {
				System.err.println("Error: can't read from file.");
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						System.err.println("Error: can't close file.");
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error: can't open file.");
		}
    }
    
    public boolean handleCommandLineArguments(String[] args) { // throws IllegalArgumentException
    	boolean badArguments = true;
    	if (args.length == 0) {
    		usage("No arguments provided.");
    	}
    	else if (args.length == 1) {
    		this.fileName = args[0];
    		badArguments = false;
    	}
    	else if (args.length == 2) {
    		if (args[0].equals("-l")) {
    			this.printLineCount = true;
    			this.fileName = args[1];
    			badArguments = false;
    		} 
    		else {
    			usage("Illegal arguments.");
    		}
    	}
    	else {
    		usage("Too many arguments.");
    	}
    	
    	return badArguments;
    }
    
    static void usage(String message) 
    {
    	System.out.println(message);
    	System.out.print(UsageInfo);
    }
}
