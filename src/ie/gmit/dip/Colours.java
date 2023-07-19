package ie.gmit.dip;

import java.awt.Color;


/**
 * 
 * An ENUM class of Color objects that are used for drawing the word cloud fonts.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */
public enum Colours {
	
	BLUE(Color.blue),
	CYAN(Color.cyan), 
	GREEN(Color.green), 
	YELLOW(Color.yellow),
	BLACK(Color.black), 
	DARKGRAY(Color.darkGray),
	MAGENTA(Color.magenta),
	ORANGE(Color.orange),
	PINK(Color.pink),
	RED(Color.red);

	private final Color color;
	
	/**
	 * Constructor of the Colours ENUM
	 * 
	 * @param color A Color object used to encapsulate colours in the default sRGB colour space
	 */
	Colours(Color color) {
		this.color = color;
	}
	
	/**
	 * Return the Color object assign to the ENUM 
	 * @return The Color object.
	 */
	public Color getColor() {
		return this.color;
	}

}
