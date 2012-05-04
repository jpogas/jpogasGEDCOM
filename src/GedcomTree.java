import java.util.LinkedList;

/**
 * Parse tree with GedcomNode nodes
 * @author ipongas
 *
 */
public class GedcomTree {

	//holds the order of the tree nodes
	private LinkedList<GedcomNode> stack;
	
	//root node of the tree
	private GedcomNode root;
	
	/**
	 * Constructor of the GedcomTree
	 */
	public GedcomTree(){
		this.stack = new LinkedList<GedcomNode>();
		this.root = null;
	}

	/**
	 * Checks if the Gedcom tree is emtpy
	 * @return
	 */
	public boolean isEmpty(){
		
		if(this.root == null)
			return true;
		else
			return false;
	}
	
	/**
	 * Adds a new GedcomNode to the tree
	 * @param node to be added to the tree
	 */
	public void addNode(GedcomNode node){
		
		if(root == null){
			this.root = node;
			this.stack.addLast(node);
		
		}else{ 
			
			GedcomNode currentNode = this.stack.getLast();
			
			while(node.record.getLevel() <= currentNode.record.getLevel()){
				this.stack.removeLast();
				currentNode = this.stack.getLast();
			}
				
			currentNode.addChildren(node);
			this.stack.addLast(node);				
		}
	}
		
	/**
	 * Prints the open tag of the GedcomNode XML representation 
	 * @param node the given GedcomNode
	 * @throws Exception
	 */
	public void printStartTag(GedcomNode node) throws Exception{
		
		//get no of tags
		int numTabs = node.record.getLevel();
		//print tabs
		Utils.printTabs(numTabs);
					
		//based on the record type print the start tag
		if(node.record instanceof IdRecord){
			
			IdRecord rec = (IdRecord)node.record;
			OutputWriter.getWriter().printf("<%s id=\"%s\">\n", rec.getType(), rec.getId());
			
		}else{
			
			TagRecord rec = (TagRecord)node.record;
			
			/*
			if(rec.getData() == null){
				OutputWriter.getWriter().printf("<%s>\n", rec.getTag());
			
			}else{

				if(!node.getChildren().isEmpty())
					OutputWriter.getWriter().printf("<%s value=\"%s\">\n", rec.getTag(), rec.getData());					
				else
					OutputWriter.getWriter().printf("<%s>%s", rec.getTag(), rec.getData());							
			}
			*/
			
			if(!node.getChildren().isEmpty()){
				if(rec.getData() == null)
					OutputWriter.getWriter().printf("<%s>\n", rec.getTag());
				else
					OutputWriter.getWriter().printf("<%s value=\"%s\">\n", rec.getTag(), rec.getData());					
			}else{
				if(rec.getData() == null)
					OutputWriter.getWriter().printf("<%s>", rec.getTag());
				else
					OutputWriter.getWriter().printf("<%s>%s", rec.getTag(), rec.getData());					
			}
			
		}	
	}
	
	
	/**
	 * Prints the end tag of the XML GedcomNode representation
	 * @param node the given GedcomNode
	 * @throws Exception
	 */
	public void printEndTag(GedcomNode node) throws Exception{
		
		//get no of tags
		int numTabs = node.record.getLevel();
		//print tabs
		
		//based on the record type print the start tag
		if(node.record instanceof IdRecord){
			
			IdRecord rec = (IdRecord)node.record;
			Utils.printTabs(numTabs);
			OutputWriter.getWriter().printf("</%s>\n", rec.getType());
			
		}else{
			
			TagRecord rec = (TagRecord)node.record;
			
			if(!node.getChildren().isEmpty())
				Utils.printTabs(numTabs);
				
			OutputWriter.getWriter().printf("</%s>\n", rec.getTag());				
		}
		
	}
	
	/**
	 * Prints the GedcomTree XML representation
	 * @param node the root of the GedcomTree
	 */
	public void printTree(GedcomNode node){

		try {

			printStartTag(node);
		
			for(GedcomNode child : node.getChildren())
				printTree(child);
			
			printEndTag(node);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prints the tree hierarchy starting by the root node
	 */
	public void printTreeHieararchy(){
		
		printTree(root);
	}
	
}
