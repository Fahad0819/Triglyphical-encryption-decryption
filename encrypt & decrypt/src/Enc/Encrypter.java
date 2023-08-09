package Enc;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

import NotEnc.ASCIITable;
import NotEnc.Node;
import NotEnc.NodeCode;
import NotEnc.Tree;

/**
 * This class is used to encrypt a message from a file. 
 * 
 * 
 * @author Fahad Arain
 * 
 *
 */
public class Encrypter {

	public static void main(String[] args) {

		if(args.length == 0){
		     new Encrypter();
		   } else if(args.length == 1){
		     new Encrypter(args[0]);
		   } else if(args.length == 2){
		      new Encrypter(args[0], args[1]);
		  }
		

	}

	private String inputName; // input file name
	private String outputName; // output file name
	private PriorityQueue<Tree> treeQueue; // will hold forest of trees
	private ASCIITable freqTable; // holds all the symbols represented by ASCII
	private Scanner input; // the data a client provides
	private String encMe;
	private char[] inputArray;
	

	/**
	 * This is a 1/3 constructors for the Encrypter class. This constructor provides
	 * a default input and output file names.
	 * 
	 */
	public Encrypter() {
	
		inputName = "testing.txt";
		outputName = "encrypted.txt";

		startUp();

	}

	/**
	 * This is 2/3 constructors for the Encrypter class. This constructor requires a
	 * String parameter which will represent the the name of the input file needed
	 * to be read.
	 * 
	 * @param input
	 * 
	 */
	public Encrypter(String input) {

		inputName = input;
		outputName = "encrypted.txt";

		startUp();

	}

	/**
	 * This is 3/3 constructors for the Encrypter class. This constructor requires a
	 * 2 String parameter which will represent the the name of the input file that
	 * will be read, and the name of the output file needed to be read.
	 * 
	 * @param input
	 * @param output
	 * 
	 */
	public Encrypter(String input, String output) {

		inputName = input;
		outputName = output;
		startUp();

	}

	/**
	 * This method is used to run the program, as it has executes the essential
	 * methods needed to run the program.
	 */
	private void startUp() {

		freqTable = new ASCIITable(); // this object holds the frequency table.
		treeQueue = new PriorityQueue<>(); // will be used to hold the trees for the maxHeap

		try {
			input = new Scanner(new File("src/NotEnc/" + inputName)); // reads the input file stored at the specified
																		// path
		} catch (IOException ioe) {
			System.out.println("Dernit!");
		} // don't use Dernit

		input.useDelimiter("afalkjfmlajf;jfl;aj flkjaflkajflkjalkfjaljf;alkfjnlkafnmlkanflkanflk");// just so I can get
																									// the whole txt lol
		while (input.hasNext())
			encMe = input.next();
	
		inputArray = encMe.toCharArray();
		updateFreqTable(inputArray);
		populateTreeQueue();
		simuMaxHeap();
		createCodex();

	}

	/**
	 * This method recursively finds the binary path of a given character (target),
	 * represented by a String of 0's and 1's.
	 *
	 * @param tree
	 * @param target
	 * @return String
	 */
	private String findBinaryPath(Tree tree, char target) {
		String binary; // 0 for left and 1 for right.
		String temp; // holds the result of the recursive call.

		if (tree.getFreq() <= 0) { // the tree is is empty.
			return null;
		}

		else if (tree.getLabel().equals(target + "")) { // the target is found.
			return "";
		}

		else if (tree.getLeft().getLabel().contains(target + "")) { // the target is in the left subtree or leaf.
			binary = "0";
			temp = findBinaryPath(tree.getLeft(), target);
			return temp.equals(null) ? null : binary + temp;
		}

		else if (tree.getRight().getLabel().contains(target + "")) { // the target is in the right subtree or leaf.
			binary = "1";
			temp = findBinaryPath(tree.getRight(), target);
			return temp.equals(null) ? null : binary + temp;
		}

		return null;

	}

	/**
	 * This method returns a LinkedList of characters who's frequencies are greater
	 * than 0.
	 * 
	 * @return LinkedList
	 */
	private LinkedList<Character> getLetters() {
		LinkedList<Character> list = new LinkedList<>();

		for (Node n : freqTable.getAsciiList()) {
			if (n.getFreq() > 0) {
				list.add(n.getCharacter());
			}
		}

		return list;
	}

	/**
	 * This method creates the codex which will be output as a text file.
	 */
	public void createCodex() {

		PrintWriter output = openFileForSave("src/NotEnc/" + outputName); // the file to be output.
		LinkedList<Character> list = getLetters(); // a list of characters with frequencies > 0.
		LinkedList<NodeCode> codex = new LinkedList<>(); // holds the path of each letter.

		
		for (char c : list) {
			codex.add(new NodeCode(c, findBinaryPath(treeQueue.peek(), c)));
			output.println(c + "\t" + findBinaryPath(treeQueue.peek(), c)); // writes the results to the output file.
		}

		output.println("---");

		// outputs the binary code for each character in inputArray
		for (char c : inputArray) {
			for (NodeCode nc : codex) {
				if (c == nc.getCharacter())
					output.print(nc.getCode() + "\t");
			}

		}

		output.close(); // closes the file
	}

	/**
	 * This method generates a maxHeap using the tree nodes stored in 'treeQueue'.
	 */
	private void simuMaxHeap() {
		Tree one; // left node
		Tree two; // right node
		Tree novel; // combination of the left and right node

		while (treeQueue.size() > 1) {

			one = treeQueue.remove();
			two = treeQueue.remove();
			novel = new Tree(one, two); // crates a new tree with one on the left and two on the right.

			// System.out.println(one.getLabel() + ", " + two.getLabel() + "," +
			// novel.getLabel() + ": " + novel.getFreq());
			treeQueue.add(novel);
		}
	}

	/**
	 * This method updates the frequency table based on the information given in the
	 * 'array'.
	 * 
	 * @param array
	 */
	private void updateFreqTable(char[] array) {
		for (char c : array) {
			for (Node d : freqTable.getAsciiList()) {
				if (d.getCharacter() == c)
					d.setFreq(d.getFreq() + 1);
			}
		}
	}

	/**
	 * This methods generates a forest of tree nodes that are stored in array.
	 */
	private void populateTreeQueue() {
		Tree temp;
		for (Node n : freqTable.getAsciiList()) {
			if (n.getFreq() > 0) {
				temp = new Tree(n.getCharacter() + "", null, null);
				temp.setFreq(n.getFreq());
				treeQueue.add(temp);

			}
		}
	}

	/**
	 * This method creates a oputfile, that will be used to store the encrypted data.
	 * 
	 * @param filename
	 * @return
	 */
	private PrintWriter openFileForSave(String filename) {
		try {
			return new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		} catch (IOException ioe) {
			System.out.println("Ah dangit.");
		}
		return null;
	}

	// helper method for debugging, delete upon completion of assignment.
	private void treeQueueInfo() {
		for (Tree t : treeQueue)
			System.out.println("--------------------\n" + t.getLabel() + " : " + t.getFreq());
	}

	// helper method for debugging, delete upon completion of assignment.
	private void freqTableInfo() {
		System.out.println("--------------------------------------------");
		for (Node n : freqTable.getAsciiList())
			System.out.println(n.getCharacter() + ": " + n.getFreq());
	}

}
