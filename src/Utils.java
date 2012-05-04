import java.util.Collection;

/**
 * Utility functions for printing and stirng concatenation
 * @author ipongas
 *
 */
public class Utils {

	/**
	 * Join a collection of strings with the given delimiter.
	 * @require words.size() > 0 && words != null
	 * @param words words to be joined
	 * @param delim delimiter
	 * @return string containing the concatenation 
	 */
	public static String concatWithDelim(Collection<String> words, String delim) {
	    
		StringBuilder wordList = new StringBuilder();
	    
		for (String word : words) {
	        wordList.append(word + delim);
	    }
	    
		return new String(wordList.deleteCharAt(wordList.length() - 1));
	}	
	
	/**
	 * Prints the given number of tabs using the OutputWriter
	 * @param numTabs
	 */
	public static void printTabs(int numTabs){
		
		for(int i=0; i<numTabs+1; i++){
			try {
				OutputWriter.getWriter().print("\t");
			} catch (Exception e) {
				e.printStackTrace();	
			}
		}	
	}
}
