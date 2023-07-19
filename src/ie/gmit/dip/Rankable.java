package ie.gmit.dip;

import java.util.List;
import java.util.Map;


/**
 *  
 * An interface for organising and ranking objects by some ordering.
 *
 * @author PJ
 * @version 1.0
 * @since 1.0
 */
public interface Rankable {
	
	/**
	 * A method orders a Rankable object and returns them in a List. 
	 * 
	 * @return The ordering in a List of String keys and Integer values.
	 */
	public List<Map.Entry<String, Integer>> rank();

}
