package ie.gmit.dip;

import java.util.Map;

/**
 * 
 * Interface for mapping operations. The interface has two methods for mapping the frequency 
 * and returning the frequency to a Map type.
 *
 * @author PJ
 * @version 1.0
 * @since 1.0
 */

public interface Mappable {
	
	/**
	 * Method for mapping the frequency of a Mappable object.
	 */
	public void mapFrequency();
	
	/**
	 * Method that returns the frequency of the mapping of the Mappable object.
	 * 
	 * @return The frequency of the items is return as K,V of a Map type.
	 */
	public Map<String, Integer> getFrequency();
}
