package ie.gmit.dip;


/**
 * Interface is for loading a Source object and returning a String. A source object can contain input from
 * a specific source e.g. a url, text file, html file, etc.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */

public interface Sourceable {
	
	/**
	 * Method to returns the loaded input Source as a String. 
	 *  
	 * @return Returns the input source as a String.
	 */
	public String loadSource ();
	
	

}
