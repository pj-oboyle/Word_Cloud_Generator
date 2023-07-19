package ie.gmit.dip;

import java.util.List;

/**
 * 
 * A interface for filtering, normalising or parsing objects.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 */
public interface Parseable {
	
	
	/**
	 * A method perform a parsing operation on the Parseable object.
	 * 
	 * @return A list of strings containing text from the Parseable object.
	 */
	public List<String> parse();

}
