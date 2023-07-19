package ie.gmit.dip;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * An abstract class that implements the Sourceable interface and implements a returnText method.
 * The return text method can be shared among child classes.
 *
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */

public abstract class Source implements Sourceable {
	
	
	/**
	 * Abstract method for loading a Source object and returning a String.
	 * @return A string of the 
	 */
	@Override
	public abstract String loadSource();
	
	// Runtime: O(n) - For loop traverses each element inside the body tags of the Document object input
	/**
	 * This method parses a Document for html body tags and and returns all the text contained within it. 
	 * 
	 * @param doc A Document object that contains input text from a Source.
	 * @return A string of text from the input Document.
	 */
	protected String returnText(Document doc) {
		Elements body = doc.getElementsByTag("Body");
		String textPage = "";
		for (Element bs1 : body) {
			textPage = bs1.text();
		}
		return textPage;
	}
}
