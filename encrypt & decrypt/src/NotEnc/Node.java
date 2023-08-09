package NotEnc;

/**
 * This class creates a node object, which is used for the frequency table. 
 * Each node contains a character and a frequency.
 * 
 *@author Fahad Arain
 *
 */
public class Node {
	
	private char character; // character.
	private int freq; // frequency.
	
	/**
	 * This constructor creates a node object with given the character and frequency.
	 * 
	 * @param character
	 * @param freq
	 */
	public Node(char character, int freq){
		this.character = character;
		this.freq = freq;
	}
	
	/**
	 * This method returns the character of the Node.
	 * @return character
	 */
	public char getCharacter() {
		return character;
	}


	/**
	 * This method returns the frequency of the Node.
	 * @return freq
	 */
	public int getFreq() {
		return freq;
	}
	

	/**
	 * This method allows you to change the frequency of a node.
	 * 
	 */
	public void setFreq(int freq) {
		this.freq = freq;
	}

	

}
