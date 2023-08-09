package NotEnc;
/**
 * @author Fahad Arain
 * 
 *
 */
public class Tree implements Comparable<Tree> {
	
	private String label; // the label of the node
	private int freq; // frequency of the node
	private Tree left; // left node
	private Tree right; // right node
	
	
	/**
	 * This constructor generates a tree node, given the label, left node, and right node.
	 * 
	 * @param label
	 * @param left
	 * @param right
	 */
	public Tree(String label, Tree left, Tree right) {
		freq = 1;
		this.label = label;
		this.left = left;
		this.right = right;
		
	}
	
	/**
	 * This constructor merges two nodes into one node, given Node(one) and Node(two).
	 * 
	 * @param one
	 * @param two
	 */
	public Tree(Tree one, Tree two) {
		label = one.getLabel() + two.getLabel(); // merges the labels.
		freq = one.getFreq() + two.getFreq(); // and the frequencies together.
		left = one; 
		right = two;
		
	}
	
	
	/**
	 * This method gets the label of the node.
	 * 
	 * @return label
	 */
	public String getLabel() {
		return label;
	}


	/**
	 * This method allows you to set the label of a node.
	 * 
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	/**
	 * This method gets the frequency of a node.
	 * @return
	 */
	public int getFreq() {
		return freq;
	}


	/**
	 * This method allows you to set the frequency of a node.
	 * 
	 * @param freq
	 */
	public void setFreq(int freq) {
		this.freq = freq;
	}


	/**
	 * This method return the left child of the this node.
	 * 
	 * @return Tree
	 */
	public Tree getLeft() {
		return left;
	}


	
	/**
	 * This method allows you to set the left child node of this node.
	 * 
	 * @param left
	 */
	public void setLeft(Tree left) {
		this.left = left;
	}


	/**
	 * This method return the right child of the this node.
	 * 
	 * @return Tree
	 */
	public Tree getRight() {
		return right;
	}


	/**
	 * This method allows you to set the right child node of this node.
	 * 
	 * @param right
	 */
	public void setRight(Tree right) {
		this.right = right;
	}


	/**
	 *This method allows to tree nodes to be compared by its frequencies; returning 1 if this node is greater, -1 if its less
	 *and 0 if they are equal.
	 *
	 */
	@Override
	public int compareTo(Tree o) {
		if(this.freq > o.getFreq())
			return 1;
		else if(this.freq < o.getFreq())
			return -1;
		else
		return 0;
	}
	
}
