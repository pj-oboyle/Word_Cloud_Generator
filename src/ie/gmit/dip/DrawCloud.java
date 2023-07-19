package ie.gmit.dip;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 *   
 * The DrawString class contains a method to creates a BufferedImage of the word cloud using a list of input words.
 *  
 * @author PJ
 * @version 1.0
 * @since 1.0
 * 
 */
public class DrawCloud implements Drawable{
	
	private List<Map.Entry<String, Integer>> rankedWords;
	String outputName;
	private int numWords;
	
	/**
	 * Class constructor creates a DrawCloud with a specified word count and a List of ranked words.
	 * 
	 * @param rankedWords An list of input words based on an ordering.
	 * @param numWords The number of words to be displayed in the output word cloud.
	 */
	public DrawCloud(List<Map.Entry<String, Integer>> rankedWords, String outputName, int numWords) {
		this.numWords = numWords;
		this.outputName = outputName;
		this.rankedWords = rankedWords;
	}

	// Runtime: O(n) - for loop of the number of words to be drawn to the word cloud image
	/**
	 * Method generates a word cloud from a list of input words based on their ordering.
	 * 
	 * @param arr List of words from input source sorted by most frequent
	 * @param numWords The number of most common input words to be displayed in the word cloud
	 * @return BuffererdImage of the word cloud containing the specified number of input words
	 */
	@Override
	public void draw() {
		int canvasWidth = 800;
		int canvasHeight = 400;
		BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = image.getGraphics();
		
		// Loop through the specified elements in the sorted array
		for (int i = 0; i < numWords; i++) {
			String text;
			
			// A check if the input is too small and there is no text found
			try {
				text = rankedWords.get(i).getKey();
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Error: Not enough input words. Total Words Drawn: " + rankedWords.size());
				break;
			}
			
			// Get all the Color/Font objects stored in the ENUM class Colours/Fonts
			Colours[] colours = Colours.values();
			Fonts[] fonts = Fonts.values();
			// Loop back over the arrays again when all colours/fonts used
			int colourIndex = i % colours.length; 
			int fontIndex = i % fonts.length;
			
			// Some variables to make the display more dynamic
			int fontSize = 24;		
			int xCord = 10+(20*i) % canvasWidth;
			int yCord = 5+(25*i) % canvasHeight;
			
			// Set the colour/font settings
			Font font = new Font(fonts[fontIndex].getName(), Font.BOLD, fontSize);
			graphics.setFont(font);
			graphics.setColor(colours[colourIndex].getColor());
			// Draw the word using at the specified co-ordinates
			graphics.drawString(text, xCord, yCord);

		}
		graphics.dispose();
		
		// Output To File Method
		imageToFile(image, outputName); // 
		
	}
	
	// Runtime: O(n) - Has write the input BufferedImage to a new File
	// Method outputs the BufferedImage to as a png file
	private static void imageToFile(BufferedImage image, String fileName) {
		try {
			//TODO: The name of the output file needs to be changed!!!!
			ImageIO.write(image, "png", new File(fileName + ".png"));
			System.out.println("Image Saved as: " + fileName + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
