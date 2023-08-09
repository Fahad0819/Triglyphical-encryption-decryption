package NotEnc;
/**
 * This class creates a NodeCode object, which is used to store the binary path of a character. 
 * Each node contains a 'character' and a 'code'.
 * 
 * @author Fahad Arain
 *
 */
public class NodeCode {
	
	private char character; 
	private String code; // will hold the binary path.
	
	/**
	 * This constructor creates a 'NodeCode' object with a given the character and String.
	 * 
	 * @param character
	 * @param freq
	 */
	public NodeCode(char character, String code){
		this.character = character;
		this.code = code;
	}

	/**
	 * This method returns the character of the Node.
	 * @return character
	 */
	public char getCharacter() {
		return character;
	}


	/**
	 * This method returns the binary path of the node.
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * This method allows you to set the binary path of a node.
	 * 
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
