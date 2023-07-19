package ie.gmit.dip;

import java.util.List;
import java.util.Map;

/**
 * This is a class contains a method that runs the word cloud workflow. 
 * <br><br>
 * There is six steps in the word cloud workflow:
 * <ol>
 * <li> Load the input from text/url/html </li>
 * <li> Parse/normalise the input text</li>
 * <li> Map the frequency of each word and store its value</li>
 * <li> Sort the input text based on highest word frequency</li>
 * <li> Draw a specified number of the most common words to a BufferedIamge</li>
 * <li> Output the BufferedImage to a image file</li>
 * </ol>
 *
 * @author PJ
 * @version 1.0
 * @since 1.0
 * 
 */

public class GenerateCloud implements Generator {
	
	private Settings settings;
	

	public GenerateCloud(Settings settings) {
		this.settings = settings;
	}
	
	// Runtime: O(n^2) - 
	/**
	 * This method creates and outputs a word cloud using settings stored in a Settings object.
	 * 
	 * @param settings Object contains parameters from UI input for word count, input source and threading options.
	 */
	@Override
	public void generate() {	
		int numWords = settings.getWordCount();		// The number of words to display in the word cloud
		int threads = settings.getThreads();		// The number of threads to use in mapping word frequency
		long startTime = System.nanoTime();			// Start a timer to track execution of each step
		String inputPath = settings.getSource();	// The path for the input text
		String outName = settings.getOutputName();	// Sets the name of the output .png file
				
		// Conditional for input source
		Sourceable source;							// Interface for loading the input text
		if (settings.hasURL()) {
			source = new SourceURL(inputPath);
		} else {
			source = new SourceTxt(inputPath);
		}

		// ****** Loading Source Method ******
		System.out.print("Loading Source: ..... ");
		String textPage = source.loadSource();  	// Loads the Source object and returns it as a String
		timer("Compeleted ", startTime);

		// ****** Parsing Method ******
		System.out.print("Parsing Input Text: ..... ");
		Parseable parse = new ParseText(textPage);	// Class for raw text normalisation
		List<String> normalised = parse.parse(); 	// Performs the parse operation on the input textPage
		timer("Compeleted ", startTime);;
		
		
		// ****** Rank/Mapping Method ******
		System.out.print("Mapping Word Frequency: ..... ");
		Rankable rankWords = new RankWords(normalised, threads);		
		List<Map.Entry<String, Integer>> arr = rankWords.rank();
		timer("Compeleted ", startTime);
		
		// ****** Cloud Drawing Method Here ******
		System.out.print("Drawing Word Cloud: ..... ");
		Drawable cloud = new DrawCloud(arr, outName, numWords);
		timer("Compeleted ", startTime);
		cloud.draw(); 								// Draw the cloud object
		System.out.println("Word Cloud Image Created");
		

		timer("Total Running Time: ", startTime);
		System.out.println("Closing Program");
	}
	
	// Timer and console message method to display process execution time
	private long timer(String msg, long startTime) {
		long endTime = System.nanoTime();
		long totalTime = (endTime - startTime) / 1000000;
		System.out.print(msg + totalTime + " (ms)\n");
		return totalTime;

	}

}
