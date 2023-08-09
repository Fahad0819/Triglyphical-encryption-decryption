package Enc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import NotEnc.NodeCode;


/**
 * This class is used to decrypt a message from a file. 
 * 
 * 
 * @author Fahad Arain
 * 
 *
 */
public class Decrypter {

	public static void main(String[] args) {
		new Decrypter();
		new Decrypter("EncRandom.txt", "RecRandom.txt");
		new Decrypter("EncThinkingFast.txt", "RecThinkingFast.txt");
	}

	private LinkedList<NodeCode> codex; // holds the codex data. 
	private LinkedList<String> message; // holds the message to be decrypted.
	private String inputName; // name of the input file.
	private String outputName; // name of the output file.
	private Scanner input; // used to read a file.

	/**
	 * This is a 1/3 constructors for the Decrypter class. This constructor provides
	 * a default input and output file names.
	 * 
	 */
	public Decrypter() {
		inputName = "encrypted.txt";
		outputName = "recovered.txt";
		startUp();

	}

	/**
	 * This is 2/3 constructors for the Decrypter class. This constructor requires a
	 * String parameter which will represent the the name of the input file needed
	 * to be read.
	 * 
	 * @param input
	 * 
	 */
	public Decrypter(String input) {
		inputName = input;
		outputName = "recovered.txt";
		startUp();
	}

	/**
	 * This is 3/3 constructors for the Decrypter class. This constructor requires a
	 * 2 String parameter which will represent the the name of the input file that
	 * will be read, and the name of the output file needed to be read.
	 * 
	 * @param input
	 * @param output
	 * 
	 */
	public Decrypter(String input, String output) {
		inputName = input;
		outputName = output;
		inputName = input;
		startUp();

	}

	/**
	 * This method is used to run the the program. Firstly it runs 'populateCodex',
	 * then 'storeMessage', and lastly 'decode'.
	 * 
	 */
	public void startUp() {
		codex = new LinkedList<>();
		message = new LinkedList<>();
		try {
			input = new Scanner(new File("src/NotEnc/" + inputName)); // reads the input file stored at the specified
																		// path
		} catch (IOException ioe) {
			System.out.println("Dernit!");
		} // don't use Dernit

		populateCodex(input);
		storeMessage(input);
		decode();
	}

	/**
	 * This method stores the codex data into a LinkedList called 'codex'.
	 * 
	 * @param file
	 */
	private void populateCodex(Scanner file) {

		String data;
		String[] line;
		String num;

		while (!(data = file.nextLine()).equals("---")) {
			line = data.split("\t");
			System.out.println(line[0] + " " + line[1]);

			codex.add(new NodeCode(line[0].charAt(0), line[1]));

		}

	}

	/**
	 * This method stores the the encrypted messages in to an LinkedList named
	 * 'message'.
	 * 
	 * @param file
	 */
	private void storeMessage(Scanner file) {
		String temp;

		while (file.hasNext()) {
			temp = file.next();
			System.out.println(temp);
			message.add(temp);
		}

	}

	/**
	 * This method decrypts the encrypted file, writing the solution to an output
	 * file.
	 * 
	 */
	private void decode() {
		PrintWriter output = openFileForSave("src/NotEnc/" + outputName);
		String code;
		String codexCode;

		for (String s : message) {
			for (NodeCode nc : codex) {
				if (nc.getCode().contentEquals(s)) {
					output.print(nc.getCharacter());
					System.out.println(nc.getCharacter());
				}
			}
		}
		output.close();
	}

	/**
	 * This method creates an output file which will contain the decrypted code.
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

}
