import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Writer class prints the given input to a file or to the standard output
 * The user must always call the close() method to the end
 * @author ipongas
 *
 */
public class OutputWriter implements WriterInterface {

	private PrintStream ps = null;
	private static OutputWriter writer = null;
	
	/**
	 * Constructor for the standard output writer
	 */
	private OutputWriter(){
		
		ps = System.out;
	}
	
	/**
	 * Constructor for the file writer
	 * @param fileName
	 */
	private OutputWriter(String fileName){
		
		try {
			 // Create a new file output stream
			 FileOutputStream out = new FileOutputStream(fileName);
			 
			  // Connect print stream to the output stream
			  ps = new PrintStream(out);
		
		} catch (Exception e){
			System.err.println ("Error in writing to file");
		}
	}
	
	/**
	 * Initializes a standard output writer
	 */
	public static void initOutWriter(){
		
		if(writer == null)
			writer = new OutputWriter();
		else
			System.err.println("Error writer already initialized");
	}
	
	/**
	 * Initializes a file writer
	 * @param fileName name of the output file
	 */
	public static void initOutWriter(String fileName){
		
		if(writer == null)
			writer = new OutputWriter(fileName);
		else
			System.err.println("Error writer already initialized");
	}
	
	/**
	 * Get the writer
	 * @return the initializes writer
	 * @throws Exception if the writer has not been initialized
	 */
	public static OutputWriter getWriter() throws Exception{
		
		if(writer == null)
			throw new Exception("Writer is not initialized!!!");
		else
			return writer;
	}

	/**
	 * Prints the given string
	 */
	public void print(String s) {

		writer.ps.print(s);
		
	}

	/**
	 * Prints the given string
	 */
	public void printf(String s, Object... args) {

		writer.ps.printf(s, args);
		
	}

	/**
	 * Closes the writer and flushes the output.
	 * User must always call this method to the end.
	 */
	public void close() {

		writer.ps.close();
		writer.ps = null;
		writer = null;
	}

}
