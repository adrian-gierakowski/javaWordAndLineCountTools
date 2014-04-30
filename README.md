current version: 0.0.1

## DESCRIPTION
Classes for counting of words and lines in a stream of characters (file, keyboard input etc.). They are designed to be:  
1. flexible in terms of how words and lines are detected
2. able to process files of any size and content

The first goal is achieved by providing and Interface, which can be used to inject custom word and line counting mechanisms. The word and line counting was also made independent, since in some cases we might not want to threat a new line as word separator.

The second goal is achieved by using buffered reading. Also, assuming that a very large file might not contain any new line characters, an approach, which stores content of each line in memory had to be avoided.

**Main class:**  
```WordAndLineCounter``` - consumes a stream wrapped with ```CharacterReader``` interface. number of words and lines can be retrieved using its ```getWordCount()``` and ```getLineCount()``` methods. This class was designed to be able to read file of any size: characters are read into a buffer of a 

**Public Interfaces:**  
```CharacterReader``` - can be used to inject a custom mechanism for reading characters from a stream 
```CharacterPatternCounter``` - can be used to inject custom word and line counting mechanisms

**Default implementations of public interfaces:**  
```BufferedReaderAdaptor``` - implementation of ```CharacterReader```, which internally wraps a ```java.io.BufferedReader``` around a Reader (provided to the constructor) to enable efficient buffered reading of characters from a stream  
```WordCounter``` - implementation of ```CharacterPatternCounter``` interface, which uses ```Character.isWhitespace(char)``` to determine if a character is a word delimiters  
```LineCounter``` - implementation of ```CharacterPatternCounter``` interface, which matches any of the three following character patterns as new line: \r, \n, \r\n  

### Usage
to use default word and line counting implementation initialise the WordAndLineCounter with BufferedReaderAdaptor as the only argument and then call getWordCount() and getLineCount() to retrieve line and word counts.

```java
// set buffer size
int bufferSize = 4096;
// initialise BufferedReaderAdaptor with a FileReader as argument
BufferedReaderAdaptor reader = new BufferedReaderAdaptor(new FileReader(fileName), bufferSize);

// initialise WordAndLineCounter with a BufferedReaderAdaptor as argument
WordAndLineCounter wordAndLineCounter = new WordAndLineCounter(reader);

// retrieve line and word counts
long lineCount = wordAndLineCounter.getWordCount();
long wordCount = wordAndLineCounter.getWordCount();
```

also check the WordCountAppRunner for real life example

### Compiling
this repository if a maven project. To compile (as well as run all the tests):  
1. download and install and maven: http://maven.apache.org/  
2. run: ```mvn package``` in the top level folder  

the product of compilation will be an example command line app: word-count-0.0.1-SNAPSHOT.jar in the /target folder 