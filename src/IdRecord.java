
/**
 * ID Gedcom node 
 * @author ipongas
 *
 */
public class IdRecord extends Record {

	//the id of the GEDCOM node
	private String id;
	
	//the type of the GEDCOM node
	private String type;
	
	/**
	 * Constructor of the Id GEDCOM node
	 * @param level the level of the GEOCOM node
	 * @param id  the id of the GEDCOM node
	 * @param type the type of the GEDCOM node
	 */
	private IdRecord(int level, String id, String type){
		
		this.level = level;
		this.id = id;
		this.type = type;
	}
	
	/**
	 * Returns the GEDCOM node id
	 * @return id
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * Returns the GEDCOM node type
	 * @return type
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * Creates a GEDCOM ID node if the given string representation is valid.
	 * @param s string representation of the GEDCOM node
	 * @return a new ID node
	 * @throws Exception the given string representation of the ID GEDCOM node was not valid
	 */
	public static IdRecord createIdRecord(String s) throws Exception{
		
		String[] splitString = s.split("\\s+");
		
		if(splitString.length == 3){
			
			return new IdRecord(Integer.valueOf(splitString[0]),
								splitString[1],
								splitString[2]);
		}else{
			
			//error
			throw new Exception(s+"\nId node has invalid format!");
		}
	}	
}
