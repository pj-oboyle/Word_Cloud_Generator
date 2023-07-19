package ie.gmit.dip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This is the worker class for running a Runnable Mappable object created using the MapByHashRunnable class.
 * <br><br>
 * The class workflow includes: 
 * <ol>
 * <li>Divide the input List of Strings to be mapped into partitions and store them in a List</li>
 * <li>Create a MapByHashRunnable object using each of the partitions in the List</li>
 * <li>Creates a thread pool of the specified thread count</li>
 * <li>Perform the mapping task and store the output into a List of HashMap for each partition</li>
 * <li>Merge all the output frequency HashMaps into a single HashMap adding values from duplicate keys</li>
 * </ol>
 *  
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */
public class ThreadedMap implements Threadable {
	private List<String> wordList;
	
	/**
	 * Constructs a ThreadMap object using a List of input strings.
	 * 
	 * @param inputList An List of strings to be mapped.
	 */
	public ThreadedMap(List<String> inputList) {
		this.wordList = inputList;
	}
	
	// Runtime: O(n^2) - method mergeAllPartitions has a runtime of O(n^2)
	/**
	 * This method performs the multi-threading of a Runnable Mappable object. 
	 * 
	 * @param threads The number of threads to be created for multi-threading.
	 * @return The combined output of the threaded mapping stored in a Map of String keys and Integer values.
	 */
	@Override
	public Map<String, Integer> threadTask(int threads) {
		HashMap<String, Integer> wordMapFinal = new HashMap<String, Integer>();
		
		// Partition the text based on the number of threads set by the user
		List<List<String>> partitions = partitionTextList(wordList, threads);
	
		// Create a Runnable Task for each partition and store in a List
		List<MapByHashRunnable> taskList = createTaskList(partitions);

		// Add each task (word frequency count) to a List of Hash Maps
		List<HashMap<String, Integer>> wordMaps = threadWordFrequency(taskList, threads); 

		// Merge the all the HashMaps but combine the duplicate key's values
		wordMapFinal = mergeAllPartitions(wordMaps);

		return wordMapFinal;
	}
	
	// Runtime: O(1) - The for loop does not traverse the entire input List (i =+ partitionLength)
	// Method divides a List of Strings into specified partitions and stores these partitions in a new List of Lists
	private List<List<String>> partitionTextList(List<String> inputTextList, int requirePartitions) {
		int size = inputTextList.size(); // Value used to divide the list

		/* 
		* The length of each partition is determined by dividing the size of the input List,rounding the
		* value up and then if the size of the input is odd increase value by one. This will ensure
		* that the partition length does not result in an extra partition due remainders from division.
		*/
		int partitionLength = (int) (Math.ceil(size / (double) requirePartitions ) + size % 2);
		List<List<String>> partitionList = new ArrayList<>();

		// Partition the List of Strings
		for (int i = 0; i < size; i += partitionLength) {
			// Create a partition of the partition length and add to the List
			partitionList.add(inputTextList.subList(i, Math.min(i + partitionLength, size)));
		}

		return partitionList;
	}

	// Runtime: O(n) - Traverses the input and create a new Runnable for each element
	// Method creates an List of Runnable task objects so that the tasks can be run concurrently
	private List<MapByHashRunnable> createTaskList(List<List<String>> partitions) {
		// Create an ArrayList of MapByHashRunnable to create a Runnable task list for each partition
		List<MapByHashRunnable> taskList = new ArrayList<MapByHashRunnable>();
		for (List<String> list : partitions) {	
			taskList.add(new MapByHashRunnable(list));
		}
		return taskList;

	}
	
	/* Runtime: O(n) - single for loops. This does not take into account the run time of the Runnable task in run() which in this case is mapping the frequency
	 * using mapFrequency which as a runtime of O(n^2)
	* This method creates a thread pool using Executor to manage the fixed thread pool
	*
	*/
	private List<HashMap<String, Integer>> threadWordFrequency(List<MapByHashRunnable> taskList, int threadCount) {
		// Thread pool generation
		ExecutorService service = Executors.newFixedThreadPool(threadCount);
		List<HashMap<String, Integer>> wordMaps = new ArrayList<>();
		int numOfTasks = taskList.size(); // The number of Runnable task

		// Execute each task, add it to the thread task list
		for (int i = 0; i < numOfTasks; i++) {
			service.execute(taskList.get(i));
		}
		service.shutdown();
		// Wait for each thread to complete (or timeout) before closing the thread pool
		try {
			service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // Sets timeout to extremely large value (~300 years)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 
		for (int i = 0; i < numOfTasks; i++) {
			wordMaps.add(taskList.get(i).getFrequency());
		}
		return wordMaps;
	}
	
	// Runtime: O(1) - All operations (put, remove, get, remappingFunction) of Map.merge are O(1) 
	// Method merges two HahsMaps of String keys and Integer values, adding the values together if the maps have duplicate keys.
	private HashMap<String, Integer> mergeMaps(HashMap<String, Integer> hMap1, HashMap<String, Integer> hMap2) {
		// Overrides the the overwriting of values of duplicate keys when merging
		hMap1.forEach((key, value) -> hMap2.merge(key, value, (v1, v2) -> v1 + v2));

		return hMap2;
	}
	
	// Runtime: O(n) - method mergeMaps has a run time of O(1) inside another for loop
	// Method merges all HashMaps in a List using a the mergeMap method.
	private HashMap<String, Integer> mergeAllPartitions(List<HashMap<String, Integer>> wordMaps) {
		HashMap<String, Integer> wordMapFinal = null;
		for (int i = 0; i < wordMaps.size() - 1; i++) {
			wordMapFinal = mergeMaps(wordMaps.get(i), wordMaps.get(i + 1));
		}

		return wordMapFinal;

	}
}
