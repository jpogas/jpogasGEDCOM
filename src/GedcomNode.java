import java.util.ArrayList;
import java.util.List;

/**
 * GedcomNode represents a node in the parsing tree
 * @author ipongas
 *
 */
public class GedcomNode {

	//record stored to the node
	public Record record;
	
	//list of childrens of this node
	private List<GedcomNode> children;
	
	/**
	 * Constructs a new GedcomNode	
	 * @param record the data of the node
	 */
	public GedcomNode(Record record){
		
		this.record = record;
		this.children = new ArrayList<GedcomNode>();
	}
	
	/**
	 * Returns the children of the GedcomNode
	 * @return list of GedcomNode objects
	 */
	public List<GedcomNode> getChildren() {
		return children;
	}

	/**
	 * Adds a children to the GedcomNode
	 * @param child the new child of the GedcomNode
	 */
	public void addChildren(GedcomNode child) {
		this.children.add(child);
	}
	
}
