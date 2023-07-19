package ie.gmit.dip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * This class implements the Rankable interface. 
 * The class has a method rank that counts the frequency of words in a List of Strings and returns the words and
 * and their frequency in a sorted ArrayList by decreasing frequency.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 */
public class RankWords implements Rankable{
	
	private List<String> normalisedText; 
	private int threads;
	
	/**
	 * Constructs a RankWord object with a List of words to be ranked and the number of threads to use
	 * for multi-threading of the mapping process.
	 * 
	 * @param normalisedText The input text that is to be mapped.
	 * @param threads The number of threads to use, if set to zero then operation will no use multi-threading.
	 */
	public RankWords(List<String> normalisedText, int threads) {
		super();
		this.normalisedText = normalisedText;
		this.threads = threads;
	}
	
	// Runtime: O(n^2) - the mapFrequency method has a run time of O(n^2)
	/**
	 * This method ranks words by highest frequency and stores the word and its frequency count in a sorted ArrayList.
	 * The method can utilise threading to reduce the time to map the frequency of each word in the input text.
	 * @return An ArrayList of Map of String keys and Integer values.
	 */
	@Override
	public ArrayList<Map.Entry<String, Integer>> rank() {

		// Create a new HashMap to store words and their frequency
		Map<String, Integer> wordMapFinal = new HashMap<String, Integer>();

		// Based on the value of threads, will perform the frequency mapping with threading
		if (threads > 0) {
			Threadable threading = new ThreadedMap(normalisedText);
			wordMapFinal = threading.threadTask(threads);
		} else {
			Mappable newMapping = new MapByHash(normalisedText);
			newMapping.mapFrequency();
			wordMapFinal = newMapping.getFrequency();
			System.out.println("Size of mapped array: " + wordMapFinal.size());
		}

		// Convert the HashMap into a ArrayList for sorting
		ArrayList<Map.Entry<String, Integer>> arr = maptoArrayList(wordMapFinal);

		// Method overrides Collections.sort to sort an arrayList by highest keys with highest values
		sortList(arr);

		return arr;
	}

	// Runtime: O(n) - Map each entry set of the input to a new ArrayList
	// Method maps an input Map of <String, Integers> to an ArrayList
	private ArrayList<Map.Entry<String, Integer>> maptoArrayList(Map<String, Integer> wordMap) {
		ArrayList<Map.Entry<String, Integer>> arr = new ArrayList<>();

		// Convert the HashMap into a ArrayList for sorting
		for (Map.Entry<String, Integer> e : wordMap.entrySet()) {
			arr.add(e);
		}
		return arr;
	}
	
	// Runtime: O(n log n) - Collections.sort has a run time of O(n log n)
	// Method uses a comparator to sort a List of Map<String, Integers> based on increasing values of the keys.
	private List<Entry<String, Integer>> sortList(List<Map.Entry<String, Integer>> list) {
		Comparator<Map.Entry<String, Integer>> valueCompare = new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				Integer i1 = o1.getValue();
				Integer i2 = o2.getValue();
				return i2.compareTo(i1); 		// Compare i1 to i2 to sort higher to lower
			}
		};
		// Sort the input list using the created Comparator valueCompare
		Collections.sort(list, valueCompare);

		return list;
	}
	
}
