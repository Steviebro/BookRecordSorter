package Menu;

import java.util.Scanner;
import Book.Book;

public class InteractiveMenu {
	//current file selection
	private String selectedFile;
	private int selectedFileIndex;
	
	//filenames
	private String[] fileNames;
	
	//user input
	private Scanner keyboard;
	private int numChoice;
	private String strChoice;
	
	//books
	private Book[][] books;
	
	public InteractiveMenu(String[] fileNames, Book[][] books) {
		this.keyboard = new Scanner(System.in);
		
		String[] nameTemp = new String[fileNames.length];
		Book[][] bookTemp = new Book[books.length][];
		
		for (int i = 0 ; i < fileNames.length ; i++) {
			nameTemp[i] = fileNames[i];
		}
		
		for (int i = 0 ; i < books.length ; i++) {
			bookTemp[i] = new Book[books[i].length];
			for (int j = 0 ; j < books[i].length ; j++) {
				bookTemp[i][j] = new Book(books[i][j]);
			}
			
		}
		this.books = bookTemp;
		this.fileNames = nameTemp;
		this.selectedFileIndex = 0;
		this.selectedFile = this.fileNames[this.selectedFileIndex];
	}
	
	/**
	 * Assigns values for the current selected file
	 * @param selection an int of the index from the fileNames string to assign
	 */
	public void assignSelectedFile(int selection) {
		this.selectedFileIndex = (selection - 1);
		this.selectedFile = this.fileNames[this.selectedFileIndex];
	}
	
	/**
	 * The starting point of the menu. Allows for 3 choices
	 * Method exits when the user enters "x"
	 */
	public void displayMainMenu() {
		do {
			System.out.print("--------------------------------"
					+ "\n\tMain Menu"
					+ "\n--------------------------------"
					+ "\n v  View the selected file: "+this.selectedFile
					+ "\n s  Select a file to view"
					+ "\n x  Exit"
					+ "\n--------------------------------"
					+ "\n\nEnter your choice: ");
			this.strChoice = this.keyboard.next();
			
			
			if (this.strChoice.equalsIgnoreCase("v")) {
				System.out.println("Viewing: "+this.selectedFile);
				openViewFileSession();
			}
			else if (this.strChoice.equalsIgnoreCase("s"))
				displayFileSubMenu();
			else if (!this.strChoice.equalsIgnoreCase("x")) {
				System.out.println("The choice you have entered is invalid. Please try again");
			}
		
		} while (!this.strChoice.equalsIgnoreCase("x"));
		
	}
	
	/**
	 * Sub-menu for selecting the current selected file. Calls assign method at the end based on user input.
	 */
	public void displayFileSubMenu() {
		do {
			System.out.println("--------------------------------"
					+ "\n\tFile Sub-Menu"
					+ "\n--------------------------------");
			for (int i = 0 ; i < this.fileNames.length ; i++) {
				if (i == 0 || i == 2 || i ==3 ) {
					System.out.println(" "+(i+1)+"  "+this.fileNames[i]+"\t\t("+this.books[i].length+" books)");
				}
				else
					System.out.println(" "+(i+1)+"  "+this.fileNames[i]+"\t("+this.books[i].length+" books)");
			}
			System.out.print(" 9  Exit"
					+ "\n--------------------------------"
					+ "\n\nEnter your choice: ");
			this.numChoice = this.keyboard.nextInt();
			
			if (this.numChoice < 1 || this.numChoice > 9)
				System.out.println("The choice you have entered is invalid. Please try again");
		} while (this.numChoice < 1 || this.numChoice > 9);
		if (this.numChoice != 9)
			assignSelectedFile(this.numChoice);
	}
	
	/**
	 * Opens a view file session ending when the user enters 0 for n.
	 */
	public void openViewFileSession() {
		int currentObjectIndex = 0;
		do {
			System.out.println("Enter a value for n: ");
			this.numChoice = keyboard.nextInt();
			currentObjectIndex = displayObjectRange(currentObjectIndex, this.numChoice);
		} while (this.numChoice != 0);
		
		
	}
	
	/**
	 * Displays the objects within a selected range
	 * @param currentObjectIndex an int pointing to the current index
	 * @param n an int defining the direction and range of objects to be displayed
	 * @return an int for the new current index
	 */
	public int displayObjectRange(int currentObjectIndex, int n) {
		if (n == 0)
			return currentObjectIndex;
		
		int newCurrentObjectIndex = currentObjectIndex;
		
		//displaying
		if (n > 0) {
			for (int i = currentObjectIndex ; i < n+currentObjectIndex && i < this.books[this.selectedFileIndex].length ; i++) {
				System.out.println(this.books[this.selectedFileIndex][i]);
			}
		}
		else {
			for (int i = currentObjectIndex ; i > n+currentObjectIndex && i >= 0 ; i--) {
				System.out.println(this.books[this.selectedFileIndex][i]);
			}
		}
		
		//returning new current index
		newCurrentObjectIndex = currentObjectIndex + n;
		if (n > 0)
			newCurrentObjectIndex--;
		else
			newCurrentObjectIndex++;
		
		if (newCurrentObjectIndex > books[this.selectedFileIndex].length - 1)
			return books[this.selectedFileIndex].length - 1;
		else if (newCurrentObjectIndex < 0)
			return 0;
		else
			return newCurrentObjectIndex;
	}

}
