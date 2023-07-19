package ie.gmit.dip;

import java.io.File;
import java.util.Scanner;

/**
 * This is a UI menu class for the word cloud generator. It presents users with options that are 
 * stored prior to creating a word cloud and are used during the word cloud creation. 
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 * 
 */

public class Menu implements Interactible{
	
	// Runtime: O(n) - some user input is validated with slowest method (intPrompt), this method has run time O(n)
	/**
	 * 
	 * @author PJ
	 * 
	 *  Main menu UI. A user can change settings for the source of input text, number of words in the word cloud,
	 *  threading settings
	 *  
	 * @return Settings An object for storing all the parameters for the word cloud operation
	 */
	public Settings menu() {
				
		// Setting Object(String sourceName, String outputName int wordCount, String isURL, boolean quit, int threads)
		Settings settings = new Settings(null, null, 0, false, true, 4); 
		Scanner scanner = new Scanner(System.in);
		
		int input; 					// Stores user's menu input and is used to direct the menu's conditional statements
		int resetMenu = 555; 		// Directs switch statement to the case: display main menu (readability)
		Boolean isQuit = false; 	// Main menu loop control variable
		String fileExt = ".html";	// Default file extension for input file name
		
		displayIntro(); 			// Method displays the program introduction
		mainMenu(); 				// Method displays the main menu options
		
		// First prompt for main menu options. Variable "input" is expression for menu switch statement
		input = intPrompt("\nSelect Option [1-5]>", scanner); 				

		/*
		 * Main menu switch statement
		 */
		do {
			switch (input) {
			// Run Program Options
			case 1 -> {
				String outFile = strPrompt(
						"\nPlease type the name of the output png file >", scanner); 				// Prompts user for input image file name
				while (outFile.isBlank() || outFile.length() > 15) {
					outFile = strPrompt(
							"\nPlease type valid name [Max length: 15 characters] >", scanner);
				}
				settings.setOutputName(outFile);
				
				// Prompts a user to select the number of words to output in the word cloud
				if (settings.getWordCount() == 0) {
					setWCSize(settings, scanner); 													// Method prompts user for number of words to output
					System.out.println("Number of Words Set To: " + settings.getWordCount());
				}
				
				String imgFile = strPrompt("\nPlease type source of input text >", scanner); 		// Prompts user for input image file name
				// Stores user's input file name to Setting class object
				if (fileExt == "url") {
					settings.setURLSource(imgFile);
				} else {
					settings.setSource(imgFile, fileExt);			
				}
				// Valid that the input is either a url or a file type (html/txt)
				boolean inputExists = checkValidInput(settings, fileExt);
				 
				while (!inputExists && input != resetMenu) { 
					// Triggers when incorrect file name entered
					if (input== 1) {
						System.out.println("File does not exist!\n");
					}
					// User prompted to enter file name or return back to main menu
					input = intPrompt("To continue select either:\n1) Enter file name\n2) Return to Main Menu\n\nSelect an option [1-2]>", scanner); 
					if (input == 1) { 																// Ends menu loop and executes the rest of program
						imgFile = strPrompt(
								"\nPlease type image file name (" + fileExt + ") >", scanner); 		// Prompts user for input file source
						settings.setSource(imgFile, fileExt); 										// Stores the input file source to the Settings Object
						inputExists = checkValidInput(settings, fileExt); 							// Set variable to true if input source is a valid file path or url
					} else if (input == 2)  {
						settings.nullSource(); 														// Resets the input file name
						input = resetMenu; 															// Sets input to return to the main menu
					} else {
						System.out.println("Please try again.\n"); 									// Incorrect input message
					}
				}
				
				// Code block is final prompt to execute the program and exit from the menu loop
				if (inputExists && input != resetMenu) {
					showAttributes(settings, fileExt);												// Displays selected settings (i.e. words count, threads)
					while (input != resetMenu && !isQuit) {
						input = intPrompt("\nDo you wish to execute program?\n1) Run\n2) Return to Main Menu\n\nSelect Option [1-2]>", scanner);
						if (input == 1) { 															// Ends menu loop and executes the rest of program
							isQuit = true; 															// Sets main menu loop control variable to exit
						} else if (input == 2)  {
							input = resetMenu; 														// Sets input to return to the main menu
						} else {
							System.out.println("Please try again."); 								// Incorrect input message
						}
					}
				}
			}
				
			// Multi-Threading Options
			case 2 -> {
				System.out.println("\n********   Threading Options   ********\n");
				System.out.println("Current Thread Count Set To: " + settings.getThreads());
				input = intPrompt("\n1) Unthreaded Mode\n2) Set Thread Number\n3) Optimised Mode\n4) Return to Main Menu\n\nPlease enter a valid input[1-4]>",
						scanner);
				if (input == 1) { 										// Sets mode to not using threading
					settings.setThreads(0); 							// Set the number of threads to zero
					input = resetMenu; 									// Input set to display current main menu again
				} else if (input == 2) {
					setThreads(settings, scanner);						// Set the number of threads to for multi-threading process				
					input = resetMenu;									// Sets input to return to the main menu
				} else if (input == 3) { 
					int coreCount = Runtime.getRuntime().availableProcessors();
					settings.setThreads(coreCount); 					// Sets the threads based on the number of available processors
					input = resetMenu;									// Sets input to return to the main menu
				} else if (input == 4) {
					input = resetMenu; 									// Sets input to return to the main menu
				} else {
					input = 2; 											// Input set to display current sub menu again
					System.out.println("Please choose from the following threading options.");
				}
				System.out.println("Number of Threads Set To: " + settings.getThreads());
			}
			// Input Source Options
			case 3 -> { 
				System.out.println("\n********   Input Source Options   ********\n");
				System.out.println("Current File Type Set To: " + fileExt);
				// Prompt the user to select from one of three input source types
				input = intPrompt("\n1) .html file\n2) .txt file\n3) URL address\n4) Return to Main Menu\n\nPlease enter a valid input[1-4]>", scanner);
				if (input == 1) { 										
					fileExt = setSourceType(".html");					// Set input source file to html and display console message
					input = resetMenu; 									// Sets input to return to the main menu
				} else if (input == 2) { 								
					fileExt = setSourceType(".txt");					// Set input source file to txt and display console message
					input = resetMenu;									// Sets input to return to the main menu
				} else if (input == 3) { 								
					fileExt = setSourceType("url");						// Set input source to url and display console message
					input = resetMenu;									// Sets input to return to the main menu
				} else if (input == 4) {
					input = resetMenu; 									// Sets input to return to the main menu
				} else {
					input = 3; 											// Input set to display current sub menu again
					System.out.println("Please choose from the following input source options.");
				}
			}
			// Output Word Count Options
			case 4 -> {
				System.out.println("\n********   Output Word Count Options   ********\n");
				System.out.println("Current Word Count Set To: " + settings.getWordCount());
				// Prompt the user to select from one of three image file types
				input = intPrompt("\n1) Set word count\n2) Return to Main Menu\n\nPlease enter a valid input[1-2]>", scanner);
				if (input == 1) { 										
					setWCSize(settings, scanner); 						// Method prompts user for number of words to output
					System.out.println("Number of Words Set To: " + settings.getWordCount());
					input = resetMenu; 									// Sets input to return to the main menu
				} else if (input == 2) {
					input = resetMenu; 									// Sets input to return to the main menu
				} else {
					input = 4; 											// Input set to display current sub menu again
					System.out.println("Please choose from the following Word Count options.");
				}
			}
			// Quit the program
			case 5 -> { 
				isQuit = true; 											// Set the menu loop variable to exit loop
				settings.setQuit(false); 								// Set the execute program trigger to false
			}
			// Display the main menu again
			case 555 -> { 												// Case used to return back to main menu
				mainMenu();
				input = intPrompt("\nSelect Option [1-4]>", scanner);
			}
			// Incorrect input display
			default -> {
				System.out.println("Please choose from the following menu options.");
				mainMenu();
				input = intPrompt("\nPlease enter a valid input [1-4]>", scanner); 
			}
			}

		} while (isQuit != true);

		scanner.close(); 												// Close the resource
		return settings; 												// settings fields will be used to create word cloud
	}
	
	// Runtime: O(n) - intPrompt has run time O(n)
	// Method set the number of threads to be used during the word mapping operation
	private static void setThreads(Settings settings, Scanner scanner) {
		// int 1003 used as an error code in method intPrompt
		int input = 1003;
		// Input validation limit set to 50 threads max
		while (input < 1 || input > 50 ) {
			input = intPrompt("How many threads do you want to use? [Max:50]>", scanner);
		}
		settings.setThreads(input);
	}
	
	// Runtime: O(n) - intPrompt has run time O(n)
	// Method set the number of the most frequent words that will be placed in the world cloud
	private static void setWCSize(Settings settings, Scanner scanner) {
		// int 1003 used as an error code in method intPrompt
		int input = 1003;
		// Input validation limit set to 100 words max
		while (input < 1 || input > 100 ) {
			input = intPrompt("How many words to output? [Max:100]>", scanner);
		}
		settings.setWordCount(input);
	}
	
	// Runtime: O(1)
	// Method validates the input path that it is either a valid url or File
	private static boolean checkValidInput(Settings settings, String fileExt) {
		boolean inputExists = false;
		
		if (fileExt == "url") {
			// Code path must contain the Protocol identifier https or http
			if (checkURL(settings.getURLSource())) {
				inputExists = true;
			}
		} else {
			// Code block checks if the input image file exists
			File f = new File(settings.getSource());
			if (f.exists()) {
				inputExists = true;
			}				
		}
		return inputExists;
	}
	
	// Runtime: O(1)
	// Method check and returns true if the input string contains the protocol identifiers for http/https
	private static boolean checkURL(String url) {
		if (url.contains("https://") || url.contains("http://")) {
			return true;
		}
		return false;
	}
	
	// Runtime: O(1)
	// Displays users settings to the console before final prompt to create word cloud
	private static void showAttributes(Settings settings, String fileExt) {
		StringBuilder base = new StringBuilder("Retrieve Input From: ");
		// Append the base string with the currently selected attributes
		base.append(settings.getSource());
		base.append("\nInput Source: ");
		base.append(fileExt);
		base.append("\nNumber of Words to Display Set To: ");
		base.append(settings.getWordCount());
		base.append("\nNumber of Threads to Use: ");
		base.append(settings.getThreads());
		String output = base.toString();
		
		System.out.println(output);
	}
	
	/* Runtime: O(1)
	 * This method takes the user's menu selection and sets the corresponding file extension 
	 * and then displays the selection to the console.
	 */
	private static String setSourceType(String fileType) {
		System.out.println("File Type Set To: " + fileType); 	// Display the current mode
		return fileType;
	}
	
	/* Runtime: O(n) - Integer.parseInt has O(n) time
	 * I/O method prompts and checks user input is an integer. A string argument is
	 * passed that will be displayed as the prompt message for the user. Invalid
	 * inputs (out of range integers, empty inputs and strings) return an error
	 * message to console.
	 */
	private static int intPrompt(String subM, Scanner scanner) {
		String nums = "^(1000|-?[1-9]?[0-9]?[0-9])$"; // Regex for numbers between 0-1000
		System.out.println(subM);

		String input = scanner.nextLine();
		if (input.isEmpty()) {
			System.out.print("Blank entry. ");
			return 1001; // Used as a blank entry trigger
		}

		// Check input integer is within a range (i.e. nums: 0-1000)
		if (input.matches(nums)) {
			return Integer.parseInt(input); // Converts input to a integer
		} else {
			// Else block catches strings and integers outside of regex nums's range
			System.out.print("Invalid input! ");
			return 1003; // Used as a invalid trigger
		}
	}
	
	/* Runtime: O(1)
	 * I/O method prompts user for input that is returned as a String.
	 * A string argument is passed that will be displayed as the prompt message for the user.
	 */
	private static String strPrompt(String subM, Scanner scanner) {	
		String nextStr = null;
		System.out.println(subM);
		nextStr = scanner.nextLine();
		
		return nextStr;
	}
	
	/* Runtime: O(1)
	 * Method displays the application's main menu options to the console
	 */
	private static void mainMenu() {
		System.out.println("\n********   Main Menu   ********\n");
		System.out.println("1) Run program"); 				// Enter the input text source name and executes the application
		System.out.println("2) Threading Options"); 		// Set the number of threads used in word mapping
		System.out.println("3) Input Source Options"); 		// Change the text input type to html/text/url
		System.out.println("4) Word Output Options"); 		// Set the number of words in the word cloud
		System.out.println("5) Quit"); 						// Terminate the program

	}

	/* Runtime: O(1)
	 * Method displays the application's preface message to the console
	 */
	private static void displayIntro() {
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*           Word Cloud Generator V1.1             *");
		System.out.println("*     H.Dip in Science (Software Development)     *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");
	}


}
