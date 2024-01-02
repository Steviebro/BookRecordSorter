package Files;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class OutputFileHandler {
	
	/**
	 * Creates a printwriter object for appending file output
	 * @param fileName a string of the file name
	 * @return an appending printwriter object for writing to files
	 */
	public static PrintWriter openOutputFile(String fileName) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream(fileName, true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	/**
	 * Writes to a file
	 * @param fileName a string of the name of the file
	 * @param toWrite a string of what to write to the file
	 */
	public static void writeToFile(String fileName, String toWrite) {
		PrintWriter output = openOutputFile(fileName);
		output.println(toWrite);
		output.close();
	}
	
	/**
	 * 
	 * @return a string array of the output file names for part 2 (also used as input for part 3)
	 */
	public static String[] getPart2OutputFileNames() {
		String[] fileNames = new String[8];
		fileNames[0] = "Cartoons_Comics.csv.ser";
		fileNames[1] = "Hobbies_Collectibles.csv.ser";
		fileNames[2] = "Movies_TV_Books.csv.ser";
		fileNames[3] = "Music_Radio_Books.csv.ser";
		fileNames[4] = "Nostalgia_Eclectic_Books.csv.ser";
		fileNames[5] = "Old_Time_Radio_Books.csv.ser";
		fileNames[6] = "Sports_Sports_Memorabilia.csv.ser";
		fileNames[7] = "Trains_Planes_Automobiles.csv.ser";
		return fileNames;
	}
	
	/**
	 * Creates an objectoutputstream object for binary file output
	 * @param fileName a string of the file name
	 * @return an objectoutputstream object for file input
	 */
	public static ObjectOutputStream openBinaryOutputFile(String fileName) {
		ObjectOutputStream output = null;
		
		try {
			output = new ObjectOutputStream(new FileOutputStream(fileName, true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	/**
	 * Writes an object to a binary file
	 * @param fileName a string of the file name
	 * @param toWrite an object to write to the file
	 */
	public static void writeObjectToBinaryFile(String fileName, Object toWrite) {
		ObjectOutputStream output = openBinaryOutputFile(fileName);
		try {
			output.writeObject(toWrite);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
