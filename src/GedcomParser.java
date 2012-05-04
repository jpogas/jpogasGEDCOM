import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * GedcomParser parses a given GEDCOM data file
 * and produces an XML representation of the data.
 * @author ipongas
 *
 */
public class GedcomParser {

	private String filename;
	
	/**
	 * Constructs a GEDCOM parser 
	 * @param filename the filename path containing the GEDCOM data
	 */
	public GedcomParser(String filename){
		this.filename = filename;
	}
	
	/**
	 * Check if this is an ID node
	 * @param s node data
	 * @return true if it is an ID node false otherwise
	 */
	public boolean isID(String s){
		
		String pattern = "^[0-2]\\s+@[0-9|a-z|A-Z]*@\\s+[a-z|A-Z]*";		
		return s.matches(pattern);
	}
	
	/**
	 * Check if this is a TAG node
	 * @param s node data
	 * @return true if it is a TAG node false otherwise
	 */
	public boolean isTAG(String s){
	
		//[[^.*]
		String pattern = "^[0-2]\\s+[a-z|A-Z]{3,4}(\\s+.*)?";
		return s.matches(pattern);
	}
	
	/**
	 * Tranforms the GEDCOM data to XML format
	 * @param br
	 * @throws Exception
	 */
	public void printGEDCOM_XML(BufferedReader br) throws Exception{
		
		String strLine;
		GedcomTree tree = new GedcomTree();
		
		OutputWriter.getWriter().printf("<gedcom>\n");
		
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			
			// Print the content on the console
			//System.out.print(strLine);
			
			Record rec = null;
			if(isID(strLine)){
				
				rec = IdRecord.createIdRecord(strLine);
				
			}else if(isTAG(strLine)){
				
				rec = TagRecord.createTagRecord(strLine);
			}

			
			if(rec.level==0){
				
				if(!tree.isEmpty())
					tree.printTreeHieararchy();

				tree = new GedcomTree();
				GedcomNode node = new GedcomNode(rec);
				tree.addNode(node);
			}else{
				
				GedcomNode node = new GedcomNode(rec);
				tree.addNode(node);
			}
			
		}

		if(!tree.isEmpty())
			tree.printTreeHieararchy();	
		
		OutputWriter.getWriter().printf("</gedcom>");

	}
	
	
	
	/**
	 * Parser the GEDCOM file and produces an XML description of the data
	 */
	public void parseFile(){
		
		try {
			
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(filename);
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));			
			
			printGEDCOM_XML(br);
			
	        in.close();
			
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Main Entry point to the GedcomParser
	 * The GedcomParser requires a GEDCOM file as a first parameter.
	 * The second parameter is optional. If provided the output
	 * will be written to the this destination, otherwise the 
	 * standard output will be used.
	 * @param args
	 */
	public static void main(String[] args){

		 // Check how many arguments were passed in
	    if(args.length == 0 || args[0].compareTo("-help")==0 ||
	    					   args[0].compareTo("-help")==0 || 
	    					   args[0].compareTo("--help")==0)
	    {
	        System.out.println("Proper Usage is: GEOParser GEDCOM_file [XML_file]");
	        System.exit(0);
	    }
	    
	    if(args.length == 2)
			OutputWriter.initOutWriter(args[1]);	    	
	    else
			OutputWriter.initOutWriter();
	    
		GedcomParser reader = new GedcomParser(args[0]);
		reader.parseFile();
		
		try {
			OutputWriter.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("GEDCOM parsing completed!!!");
	}
	
}
