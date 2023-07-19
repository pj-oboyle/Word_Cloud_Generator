package ie.gmit.dip;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/** 
 * The HashMapObject implements the Mappable interface and implements its two methods. 
 * The class maps the frequency of the words in a List of Strings and stores the key/values in a HashMap.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 * 
 */
public class MapByHash implements Mappable {
	
	private HashMap<String, Integer> frequencyMap;
	protected List<String> wordList;
	
	/**
	 * Constructs a MapByHash object for a List of Strings that are to be mapped.
	 * @param wordList A list of input words to be mapped stored in a List of Strings.
	 */
	public MapByHash(List<String> wordList) {
		super();
		this.wordList = wordList;
	}
	
	// Runtime: O(n^2) - Collections.frequency run time O(n) inside another for loop
	/**
	 *  This method maps the frequency of words stored as Strings in a List to a HashMap with each unique word a key and
	 *  and it's frequency the value pair.
	 */
	@Override
	public void mapFrequency() {
		// Create a new HashMap to store words and their frequency
		HashMap<String, Integer> wordMap = new HashMap<>();

		// Loop through each word in normalised words
		for (String string : wordList) {
			// Find the frequency of the word using the store the value as an integer
			int freq = Collections.frequency(wordList, string);
			// Assign the the word to as the key to the hashmap and assign it the pair value the word frequency
			wordMap.put(string, freq);
		}
		this.frequencyMap = wordMap;
		
	}
	
	// Runtime: O(1)
	/**
	 * Returns the HashMap of the mapped word frequency of the input text.
	 * 
	 * @return A HashMap containing strings of words with their pair value that words frequency.
	 */
	@Override
	public HashMap<String, Integer> getFrequency() {
		 return this.frequencyMap;
	}



}
