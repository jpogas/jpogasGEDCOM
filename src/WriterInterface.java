
/**
 * Writer Interface 
 * @author ipongas
 */
public interface WriterInterface {

	public void print(String s);
	
	public void printf(String s, Object... args);
	
	public void close();
}
