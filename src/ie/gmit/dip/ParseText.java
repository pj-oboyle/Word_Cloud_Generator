package ie.gmit.dip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * This class contains methods to normalise the raw input text. This is necessary to remove abbreviations, 
 * symbols, specified ignore words and isolate each word in the input text. The text parsing is broken into three stages:
 * <ol>
 * <li>Isolate each word in the input text</li>
 * <li>Convert words to lower-case and remove symbols and abbreviations</li>
 * <li>Compare and remove words that are present in a common word list</li>
 * </ol>
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 */
public class ParseText implements Parseable {
	private String normalisedText;
	
	
	/**
	 *  Class constructor
	 * @param text Input text as a String for parsing.
	 */
	public ParseText(String text) {
		super();
		this.normalisedText = text;
	}

	// Runtime: O(n^2) - normalise method has a run time of O(n^2)
	/**
	 * Method filters input text in preparation for word frequency mapping.
	 * The process isolates words, converts to lowercase, removes symbols, abbreviations and specified ignore words.
	 * 
	 * @return Each word as a string stored in a List
	 */
	public List<String> parse() {
		File ignoreWords = new File("ignorewords.txt");
		// Create an list of ignore words stored in a list from a text file
		ArrayList<String> ignoreList = getIgnoreWords(ignoreWords); // METHOD SHORTERNED
		
		// Runtime: O(n)
		String[] words = normalisedText.replaceAll("[^a-zA-Z\\s]", "").split("\\s+");

		// Runtime: O(n^2)
		List<String> normalisedText = normalise(words); // METHOD SHORTERNED
		
		// Runtime: O(n) - O(nm) for m size of ignoreList, uses contains() on ArrayList
		normalisedText.removeAll(ignoreList); // Remove all the words from the ignore list		

		return normalisedText;

	}

	// Runtime: O(n^2) - String.matches run time O(n) inside another for loop
	// This method filters and converts the words and places them in an arrayList
	private List<String> normalise(String[] words) {
		List<String> filteredText = new ArrayList<String>();

		for (String string : words) {
			// Convert each word to lower case, ignoring abbreviations and symbols
			if (!string.matches("\\b(?:[A-Z][a-z]*){2,}|\\b\\w\\b")) { // Remove all the abbreviations
				filteredText.add(string.toLowerCase());
			}
		}
		return filteredText;

	}
	
	// Runtime: O(n) - scan all of input and add to a new ArrayList
	// Method loads a File, scans each element and adds it to an ArrayList of Strings
	private ArrayList<String> getIgnoreWords(File file) {
		Scanner scanner;
		ArrayList<String> ignoreList = new ArrayList<>();
		try {
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				ignoreList.add(scanner.next());
			}
			scanner.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return ignoreList;
	}
	
}
