package ie.gmit.dip;

import java.awt.Font;

/**
 *
 *  An ENUM class of web safe font names that are used for drawing the word cloud.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */
public enum Fonts {
	
	Sans_Serif  (Font.SANS_SERIF),
	Dialog      (Font.DIALOG),
	DialogInput (Font.DIALOG_INPUT),
	Monospaced  (Font.MONOSPACED),
	Serif       (Font.SERIF);

	
	private final String name;
	
	/**
	 * Constructor of the Fonts ENUM
	 * 
	 * @param name The name of the font.
	 */
	Fonts(String name) {
		this.name = name;
	}

	/**
	 *  Get the name of the font.
	 * @return The name of the selected font as a String.
	 */
	public String getName() {
		return this.name;
	}


	

}
