import java.util.ArrayList;
import java.util.List;

/**
 * TAG GEDCOM node 
 * @author ipongas
 *
 */
public class TagRecord extends Record{

	//the tag of the GEOCOM node
	private String tag;
	
	//the data of the GEOCOM node 
	private String data;
	
	/**
	 * Constructor of the Tag GEDCOM node
	 * @param level the level of the GEDCOM node
	 * @param tag the tag of the GEDCOM node
	 * @param data the data of the GEDCOM node
	 */
	private TagRecord(int level, String tag, String data){
		
		this.level = level;
		this.tag = tag;
		this.data = data;
	}
	
	/**
	 * Constructor of the Tag GEDCOM node
	 * @param level the level of the GEDCOM node
	 * @param tag the tag of the GEDCOM node
	 */
	private TagRecord(int level, String tag){
		
		this.level = level;
		this.tag = tag;
		this.data = null;
	}

	/**
	 * Returns the GEDCOM node tag
	 * @return tag
	 */
	public String getTag(){
		return this.tag;
	}
	
	/**
	 * Returns the GEDCOM node data
	 * @return data
	 */
	public String getData(){
		return this.data;
	}	
	
	/**
	 * Creates a GEDCOM TAG node if the given string representation is valid.
	 * @param s string representation of the GEDCOM node
	 * @return a new TAG node
	 * @throws Exception the given string representation of the TAG GEDCOM node was not valid
	 */
	public static TagRecord createTagRecord(String s) throws Exception{
		
		String[] splitString = s.split("\\s+");
		
		if(splitString.length-2 > 0){
		
			List<String> dataWords = new ArrayList<String>();
			for(int i=2; i<splitString.length; i++){				
				dataWords.add(splitString[i]);
			}
			
			String data = Utils.concatWithDelim(dataWords, " ");
			
		
			return new TagRecord(Integer.valueOf(splitString[0]),
								splitString[1],
								data);
		
		}else if(splitString.length-2 == 0){
	
			return new TagRecord(Integer.valueOf(splitString[0]),
								 splitString[1]);
		
		}else{
			//error
			throw new Exception(s +"\nTag node has invalid format!");
		}
	}	

}
