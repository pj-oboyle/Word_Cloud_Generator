###########################################################
##                                                       ##
##             Word Cloud Generator V1.0                 ##
##                                                       ##
##        H.Dip in Science (Software Development)        ##
##    GMIT - Dept. Computer Science & Applied Physics    ##
##                Student #: G00398242                   ##
##                                                       ##
###########################################################
					
		Table of Contents:
		1. Description
		2. How to Run Program
		3. Features
		
*************    Description    *************

This is a command line menu-driven Java application that counts the word frequecny of input text and returns a word cloud image of the most common words.

The workflow for creating a word cloud is broken into five modules. Each module's behaviour is placed in an interface which is then used by a Generator interface to build the word cloud step by step.

The five modules with their interfaces are:
	1. Sourceable: 	Load the input text.
	2. Parseable: 	Prepare the input text for mapping the word frequency (e.g.isolate words, remove symbols/abbreviations/ignore words)
	3. Mappable: 	Map the frequency of each word contained within the normalised text and save their value.
	4. Rankable: 	Rank the words based on decreasing occurrence.
	5. Drawable: 	Draw/output a word cloud image of the most common words to an image file.

Other specialised classes include a Runner class along with:
	1. The Menu class creates the user interface that controls the command line menu. This class takes input from the user and stores these options (i.e. number of output words, multi-threading settings and input text source) in the Settings object.
	2. The slowest step of creating the word cloud is mapping the frequency of all the words. This step can be expedited through dividing the input text and performing each part concurrently using threads. A specialised class called ThreadedMapByHash inherits behaviour from the MapByHash class and creates a Runnable task for concurrent execution of a portion of the input.
	3. The application can accept as an input source as a url or a file (.html or .txt). There is a class for processing each of these input types; SourceURL and SourceTxt respectively.

There are two ENUM classes that store constant data for fonts and font colours.


*************   How to Run Program   *************

To run the program:
	1. Select option 1 (Run program).
	2. (If not already set by user) User will be prompted to input the number of most common words to display in the word cloud image.
	3. User will then be prompted to type the name of the input source.
	4. If the source is found, source and current attributes are displayed with a prompt to execute the program.
	5. After execution, output messages are displayed and the program closes.
	
*************      Features       *************

Multi-Threading :-
There are three modes in the Threading Options (main menu option 2):
	1. Unthreaded mode - Do not use any multithreading for the word frequency mapping.	
	2. Set Thread Number - The user can set the number of threads to utilse.
	3. Optimised Mode - The program will based the number of the number of processors that the user's device has available.
 
Application menu keeps running until a word cloud is created (main menu option 1) or the user selects to quit the program (main menu option 5).
User can navigate between all the option sub menus and the main menu using the "Return to main menu option" in each sub-menu.

File Extension Options :-
	Application can switch between different input source types. Three input text sources to select from:
		1. .html
		2. .txt
		3. url (address must contain protocol identifer http or https to be valid)
	Input file names can be entered with or without the file extension (i.e. .html/.txt) when the corresponding mode is selected in sub-menu 3 "Input Source Options". 

Input/Output Features :-	
	1. User input checks, 
		There are two methods for integer and string inputs. 
		When prompted for menu input (integers only menu options), application can detect illegal inputs from out of range integers, strings and empty return entries (custom error message for each).
				
	2. Input image file check
		Program checks that input source exists before execution and prompts user to enter a valid file name if the check fails.
		Program can check if a user has entered a file name without the file extension (i.e. html/txt) and automatically add the currently selected file type if omitted before searching for the file.
		Program can accept input from image file names and folders/directories that have the file extension as part of the their name (e.g. ./.html/testFile.html.ntml).
		
	3. Output file naming
		The name of the output image name is auto-generated from concatenating the input file name, filter name and edge mode used.
		The output image will automatically be of the same file type as the input file.
		
		
*************      References       *************
R. W. K. Sedgewick, 2001. Algorithms, 4th ed., Boston, Addison-Wesley.

Runtime complexity of Map.merge:
https://docs.oracle.com/javase/8/docs/api/java/util/Map.html#merge-K-V-java.util.function.BiFunction-

Runtime complexity of Collections.sort:
https://docs.oracle.com/javase/6/docs/api/java/util/Collections.html#sort(java.util.List)

Set the timing to forever with ExecutorService.awaitTermination :
https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/package-summary.html

JSoup Documentation -----
parse method:
https://jsoup.org/apidocs/org/jsoup/parser/Parser.html#parse(java.lang.String,java.lang.String)

connect method:
https://jsoup.org/apidocs/org/jsoup/Jsoup.html#connect(java.lang.String)


Some of my own work is reused from a previous project that was submitted for Object Oriented Software Development (COMP08026) in 2021.