package ie.gmit.dip;

/**
 * An interface that allows the user to interact with the application.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */
public interface Interactible {

	
	/**
	 * Method for a console menu-driven UI for setting application parameters before execution.
	 * 
	 * @return A Settings object that stores all the user's menu choices.
	 */
	public Settings menu();

}
