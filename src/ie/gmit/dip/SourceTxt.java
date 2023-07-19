package ie.gmit.dip;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
  * An class that implements the Sourceable interface and extends the Source class.
 * It loads a Source object that contains a file type as an input source. It uses the
 * Java library JSoup HTML parser to parse the input text from the html elements. 
 * 
 * @author PJ
 * @version 1.0
 * @since 1.0
 *
 */
public class SourceTxt extends Source {
	private String source;
	
	// Runtime: O(1)
	/**
	 * Class constructor that accepts a file type as a Source object.
	 * 
	 * @param source A string contains a file path of a file type.
	 */
	public SourceTxt(String source) {
		this.source = source;
	}
	
	// Runtime: O(n) - JSoup parse has a run time O(n)
	/**
	 * Method uses the input string as a file path to load a File object.
	 * 
	 * @return A string of the text parsed from the File object.
	 */
	@Override
	public String loadSource() {
		Document doc = new Document(null);
		File input = new File(source);
		String charSet = "UTF-8";
		try {
			// Runtime: O(n)- Parses html into a document
			doc = Jsoup.parse(input, charSet);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		String textPage = returnText(doc); // Method parses all text with the html body tags
		return textPage;
	}

}
