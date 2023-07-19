package ie.gmit.dip;

import java.util.List;

/**
 * 
 * A Runnable class for finding the frequency of the words that extends HashMapObject.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */
public class MapByHashRunnable extends MapByHash implements Runnable {
	
	/**
	 * Constructs a MapByHashRunnable object for a List of Strings that can to be mapped concurrently.
	 * @param wordList A list of input words to be mapped stored in a List of Strings.
	 */
	public MapByHashRunnable(List<String> wordList) {
		super(wordList);
	}

	// Runtime: O(n^2) - mapFrequency method is inherited from MapByHash class
	/**
	 * The method that will be called when MapByHashRunnable is used to create a thread.
	 */
	@Override
	public void run() {
		mapFrequency();

	}


}
