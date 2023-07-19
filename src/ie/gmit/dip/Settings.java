package ie.gmit.dip;

import java.io.File;

/**
 * Settings class that creates an object that stores all the parameters for processing a word cloud 
 * (input file name, word count, input text source and number of threads to map word frequency).
 *
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */

public class Settings {

	private String sourceName; 			// Stores input text source
	private String outputName;			// Stores output file name
	private int wordCount; 				// Variable sets the number of most common words to display
	private boolean isURL;				// Stores whether the input source is a url or not
	private boolean isQuit; 			// Program execution trigger (set to false if user selects quits from the menu)
	private int threads; 				// Stores the number of threads that will be used to map word frequency

	public Settings(String srcName, String outName, int wordCount, boolean isURL, boolean isQuit, int threads) {
		this.sourceName = srcName;
		this.outputName = outName;
		this.wordCount = wordCount;
		this.isURL = isURL;
		this.isQuit = isQuit;
		this.threads = threads;
	}

	// Runtime: O(1)
	// Method returns the name output png file chosen by the user.
	public String getOutputName() {
		return outputName;
	}

	// Runtime: O(1)
	// Method sets the name output png file chosen by the user.
	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}


	// Runtime: O(1)
	// Method sets the url address of the input text source from a webpage and set the boolean to flag the url to true.
	public void setURLSource(String url) {
		this.sourceName = url;
		this.isURL = true;
	}
	
	// Runtime: O(1)
	// Method returns the url address of the input text source.
	public String getURLSource() {
		return sourceName;
	}
	
	// Runtime: O(1)
	// Method returns true if the input source is a URL
	public boolean hasURL() {
		return isURL;
	}

	/* Runtime: O(n) - split method matches input with the regular expression
	 * Method sets the name of the input text file path, by concatenating user's input file name 
	 * and file extension. Method also performs a check in case user includes the file extension with the file name input.
	 */	
	public void setSource(String inputFile, String fileExt) {
		// Combine user's input file name and the file extension
		File filePath = new File(inputFile+fileExt); 						// Create a file instance for preliminary error checking
		
		/*
		 * Check if the combined file path exists. The if statement accounts for possibility of duplication of file extension if user
		 * included a file extension during input and removes the file extension again. This approach allows the input of file names
		 * that may coincidentally contain a file name as part of their file name (e.g. testFile.png.png).
		 */
		if (!filePath.exists()) {
			// Split input file name at the last full stop, prevents splitting at possible relative paths names
			String[] splitPath = inputFile.split("(\\.(?=[a-zA-Z]+$))");	// Match a full stop followed by any letters only at end of string
			inputFile = splitPath[0];										// Store string portion that precedes the final full stop
		}
		this.sourceName = inputFile + fileExt; 								// Return combined file name and file type
	}
	
	/* Runtime: O(1)
	 * This method sets the name of the user's input text source name to null.
	 * The application will not execute if the input source name is null (checked via the toExecute() method below)
	 * This method is used to set the image file name to null if user's input file is not recognised.
	 */
	public void nullSource() {
		this.sourceName = null;
	}

	// Runtime: O(1)
	// Method returns the name of the user's input file name
	public String getSource() {
		return sourceName;
	}


	/* Runtime: O(1)
	 * Getters and setters for the number of words to create the word cloud selected by the user which is stored as a integer.
	 */
	public void setWordCount(int input) {
		this.wordCount = input;
	}
	
	// Runtime: O(1)
	// returns the integer value of the number of words to create the word cloud
	public int getWordCount() {
		return wordCount;
	}
	
	// Runtime: O(1)
	// Setter for the isQuit variable that controls the program execution trigger
	public void setQuit(boolean isQuit) {
		this.isQuit = isQuit;
	}

	/* Runtime: O(1)
	 * Get value of boolean variable "isQuit". Checks that a filter is selected and a name for the file is given
	 */
	public boolean toExecute() {
		if (wordCount <= 0 || sourceName == null) {
			isQuit = false;
		} else {
			return isQuit; 
		}
		return isQuit;
	}
	
	// Runtime: O(1)
	// Setter for the number of thread to create for word mapping task
	public void setThreads(int threads) {
		this.threads = threads;
	}
	
	// Runtime: O(1)
	// Getter for the value of number of threads variable
	public int getThreads() { 
		return threads;
	}
}
