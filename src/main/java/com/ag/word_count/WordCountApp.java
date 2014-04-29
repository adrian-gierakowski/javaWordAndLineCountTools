package com.ag.word_count;

public class WordCountApp {
	public static void main(String[] args) 
    {	
		try {
			new WordCountAppRunner().run(args);			
		}
		catch (IllegalArgumentException e) {
			// just quit quietly
		}
    }
}
