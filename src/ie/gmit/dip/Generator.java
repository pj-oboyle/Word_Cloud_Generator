package ie.gmit.dip;

/**
 * 
 * A builder interface that has one method that is used as a builder class to create complex objects 
 * in a step by step process.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */
public interface Generator {

	/**
	 * This can can create different representations and types of objects depending on the
	 * the modules that it has access to.
	 */
	void generate();
}
