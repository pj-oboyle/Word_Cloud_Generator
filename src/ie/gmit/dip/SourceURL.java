package ie.gmit.dip;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * An class that implements the Sourceable interface and extends the Source class.
 * It loads a Source object that contains a url as the input source.  It uses the
 * Java library JSoup HTML parser to fetch and parse the input url webpage.
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */
public class SourceURL extends Source {
	private String source;
	
	// Runtime: O(1)
	/**
	 * Class constructor that accepts a URL as a Source object.
	 * 
	 * @param source A string that contains url for a website input source.
	 */
	public SourceURL(String source) {
		this.source = source;
	}
	
	// Runtime: O(n) - returnText method has run time of O(n)
	/**
	 * Method uses the input string as a URL to fetch and parse text to a String. The input url
	 * must be http or https protocol.
	 * 
	 * @return A string of the text parsed from the URL.
	 */
	@Override
	public String loadSource() {
		Document doc = new Document(null);
		try {
			doc = Jsoup.connect(source).get(); // 
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		// Runtime: O(n) - returnText has for loop that traverses each element inside the body tags of the Document object input
		String textPage = returnText(doc); // Method parses all text with the html body tags
		return textPage;
	}

}
