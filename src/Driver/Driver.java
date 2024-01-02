package Driver;

import Book.*;
import Files.*;
import Menu.InteractiveMenu;

public class Driver {
	
	/**
	 * Does part 1.
	 */
	public static void doPart1() {
		String[] inputFileNames = InputFileHandler.getPart1InputFileNames();
		String[][] splitDownloadedFiles = InputFileHandler.splitDownloadedFiles(inputFileNames); //2D array storing each record from each file
		
		
		BookRecordGenreSorter genreSorter; //genre sorter object for removing syntax errors and sorting by genre
		
		for (int i = 0 ; i < splitDownloadedFiles.length ; i++) {
			for (int j = 0 ; j < splitDownloadedFiles[i].length ; j++) {
				if (splitDownloadedFiles[i][j] != "") { //for catching empty files
					genreSorter = new BookRecordGenreSorter(splitDownloadedFiles[i][j]); //genreSorter object assigns the output file upon instantiation with a book record String
					if (genreSorter.getSyntaxError().equals("No Syntax Error"))
						OutputFileHandler.writeToFile(genreSorter.getGenreFileName(), genreSorter.getRecord());
					else
						OutputFileHandler.writeToFile(genreSorter.getGenreFileName(), "Syntax Error in file "+inputFileNames[i]+"\n"+genreSorter.syntaxErrorPrint());
				}
			}
		}
		
	}
	
	/**
	 * Does part 2.
	 */
	public static void doPart2() {
		String[] inputFileNames = InputFileHandler.getPart2InputFileNames();
		String[][] splitDownloadedFiles = InputFileHandler.splitDownloadedFiles(inputFileNames); //2D array storing each record from each file
		
		BookRecordSemanticChecker semanticChecker; //semantic checker object for removing semantic errors
		String[] validRecords = new String[inputFileNames.length]; //temporary storage for valid records
		String semanticErrors = ""; //temporary storage for invalid records
		
		Book[][] books = new Book[inputFileNames.length][];
		String[] splitValidRecords; 
		BookParameterIdentifier bpi; //parameter identifier object for identifying the parameters required for creating a book object
		
		//Step 1: Sort, then create and fill books array
		for (int i = 0 ; i < splitDownloadedFiles.length ; i++) {
			validRecords[i] = ""; //(just in case)
			
			//Sort valid and invalid semantics===================================
			for (int j = 0 ; j < splitDownloadedFiles[i].length ; j++) {
				semanticChecker = new BookRecordSemanticChecker(splitDownloadedFiles[i][j]);
				if (semanticChecker.getSemanticError().equals("No Semantic Error")) {
					validRecords[i] = validRecords[i].concat(semanticChecker.getRecord()+"\n");
				}
				else
					semanticErrors = semanticErrors.concat("Semantic Error in file "+inputFileNames[i]+"\n"+semanticChecker.semanticErrorPrint()+"\n");
			}
			
			//Create book objects from valid records =====================================
			splitValidRecords = validRecords[i].split("\n");
			books[i] = new Book[splitValidRecords.length];
			
			for (int j = 0 ; j < splitValidRecords.length ; j++) {
				bpi = new BookParameterIdentifier(splitValidRecords[j]);
				books[i][j] = new Book(bpi.getTitle(),bpi.getAuthors(),bpi.getPrice(),bpi.getIsbn(),bpi.getGenre(),bpi.getYear());
			}
			
			//end of loop iteration, now onto the next file
		}
		
		
		//Step 2: serialize books
		String[] outputFileNames = OutputFileHandler.getPart2OutputFileNames();
		
		for (int i = 0 ; i < books.length ; i++) {
			OutputFileHandler.writeObjectToBinaryFile(outputFileNames[i],books[i]);
		}
		
		//Step 3: semantic error file
		OutputFileHandler.writeToFile("semantic_error_file.txt", semanticErrors);
	}
	
	/**
	 * Does part 3.
	 */
	public static void doPart3() {
		String[] inputFileNames = OutputFileHandler.getPart2OutputFileNames();
		Book[][] books = new Book[inputFileNames.length][];
		int[] numOfBooks = new int[inputFileNames.length];
		
		//Step 1: deserialize book files into a 2D book array
		for (int i = 0 ; i < inputFileNames.length ; i++) {
			books[i] = (Book[]) InputFileHandler.readObjectFromBinaryFile(inputFileNames[i]);
			numOfBooks[i] = books[i].length;
		}
		
		//Step 2: interactive menu
		InteractiveMenu menu = new InteractiveMenu(inputFileNames, books);
		menu.displayMainMenu();
		
	}
	
	/**
	 * Main
	 * @param args main
	 */
	public static void main(String[] args) {
		
		System.out.println("Welcome to the Book Record Sorter!!!\n\n");
		
		doPart1(); //sorts the files by genre and removes syntax error records
		doPart2(); //removes semantic errors and creates arrays of Book objects from each genre then serializes the arrays into binary files
		doPart3(); //deserializes the book arrays and provides an interactive menu for browsing through the books from the files
		
		System.out.println("Thank you for using the Book Record Sorter!");

	}

}
