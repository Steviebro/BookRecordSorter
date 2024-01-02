package Files;

import java.io.*;
import java.util.Scanner;

public class InputFileHandler {
	
	/**
	 * 
	 * @return a string array of the input file names for part 1
	 */
	public static String[] getPart1InputFileNames() {
		Scanner input = null;
		int numOfFiles = 0;
		String[] fileNames = null;
		
		//Opening the input file
		input = openInputFile("part1_input_file_names.txt");
		
		//First entry is the number of files
		numOfFiles = input.nextInt();
		input.nextLine(); //junk remainder of line
		
		//Assign values to String array
		fileNames = new String[numOfFiles];
		for (int i = 0 ; i < fileNames.length ; i++) {
			fileNames[i] = input.next();
		}
		input.close();
		return fileNames;
	}
	
	/**
	 * 
	 * @return a string array of the input file names for part 2
	 */
	public static String[] getPart2InputFileNames() {
		String[] fileNames = new String[8];
		fileNames[0] = "Cartoons_Comics.csv.txt";
		fileNames[1] = "Hobbies_Collectibles.csv.txt";
		fileNames[2] = "Movies_TV_Books.csv.txt";
		fileNames[3] = "Music_Radio_Books.csv.txt";
		fileNames[4] = "Nostalgia_Eclectic_Books.csv.txt";
		fileNames[5] = "Old_Time_Radio_Books.csv.txt";
		fileNames[6] = "Sports_Sports_Memorabilia.csv.txt";
		fileNames[7] = "Trains_Planes_Automobiles.csv.txt";
		return fileNames;
		
	}
	
	/**
	 * Creates a scanner object for file input
	 * @param fileName a string of the file name
	 * @return a scanner object for file input
	 */
	public static Scanner openInputFile(String fileName) {
		Scanner input = null;
		try {
			input = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	/**
	 * Creates a String from the contents of a file
	 * @param fileName a string of the file name
	 * @return a string containing the contents of the file
	 */
	public static String fileToString(String fileName) {
		String download = "";
		Scanner input = openInputFile(fileName);
		
		try {
			while (input.hasNextLine())
				download = download.concat(input.nextLine()+"\n");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something happened while downloading to string");
		}
		input.close();
		return download;
	}
	
	/**
	 * Creates a 2D string array of the files split by record
	 * @param fileNames a string array of the file names
	 * @return a 2D string array of the files split by record
	 */
	public static String[][] splitDownloadedFiles(String[] fileNames) {
		String[] downloadedFiles = new String[fileNames.length];
		String[][] splitDownloadedFiles = new String[fileNames.length][];
		
		for (int i = 0 ; i < downloadedFiles.length ; i++) {
			downloadedFiles[i] = fileToString(fileNames[i]);
		}
		
		for (int i = 0 ; i < downloadedFiles.length ; i++) {
			splitDownloadedFiles[i] = downloadedFiles[i].split("\n");
		}
		
		return splitDownloadedFiles;
	}
	
	/**
	 * Creates an objectinputstream object for binary file input
	 * @param fileName a string of the file name
	 * @return an objectinputstream object for file input
	 */
	public static ObjectInputStream openBinaryInputFile(String fileName) {
		ObjectInputStream input = null;
		
		try {
			input = new ObjectInputStream(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return input;
	}
	
	/**
	 * Reads an object from a binary file
	 * @param fileName a string of the file name
	 * @return an object from the file
	 */
	public static Object readObjectFromBinaryFile(String fileName) {
		ObjectInputStream input = openBinaryInputFile(fileName);
		Object read = null;
		try {
			read = input.readObject();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return read;
	}
	

}
