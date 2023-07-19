package ie.gmit.dip;

import java.util.Map;

/**
 * An interface for performing mapping operations concurrently using multi-threading.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */
public interface Threadable {
	
	/**
	 * The method threads a task that can be mapped.
	 * 
	 * @param threads The number of threads to be created for the task.
	 * @return The mappable task as a Map of String keys and Integer values.
	 */
	public Map<String, Integer> threadTask(int threads);

}
