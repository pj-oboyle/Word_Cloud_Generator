package ie.gmit.dip;


/**
 * An interface for creating classes that draw objects that can be returned as a BufferedImage.
 *  
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */
public interface Drawable {
	
	
	/**
	 * A class that draws an object and returns it as a BufferedImage.
	 * @return A BufferedImage of the Drawable object.
	 */
	void draw();

}
