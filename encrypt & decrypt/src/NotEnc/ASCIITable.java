package NotEnc;

import java.util.LinkedList;

/**
 *This class creates a frequency table of characters. 
 *
 * @author Fahad Arain
 *
 */
public class ASCIITable {
	
	private LinkedList<Node> asciiList;  // this list is the frequency table
	
		public ASCIITable() {
			
			asciiList = new LinkedList<>();
			
			for(char i = ' '; i<= '~'; i++) {
				asciiList.add(new Node(i,0));  // the frequencies of each node begin with 0
				
			}
		}

		public LinkedList<Node> getAsciiList() {
			return asciiList;
		}
	
}
