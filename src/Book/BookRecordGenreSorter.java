package Book;

import SyntaxExceptions.MissingFieldException;
import SyntaxExceptions.SyntaxException;
import SyntaxExceptions.TooFewFieldsException;
import SyntaxExceptions.TooManyFieldsException;
import SyntaxExceptions.UnknownGenreException;

public class BookRecordGenreSorter {
	
	private String record;
	
	private int numOfFields;
	private String genre;
	
	private String missingField;
	private String syntaxError;
	
	private String genreFileName;
	
	public BookRecordGenreSorter (String record) {
		this.record = record;
		try {
			validateSyntax();
			this.syntaxError = "No Syntax Error";
		} catch (MissingFieldException mfe) {
			this.syntaxError = "Missing "+this.missingField;
		} catch (UnknownGenreException uge) {
			this.syntaxError = "Unknown Genre";
		} catch (TooFewFieldsException tffe) {
			this.syntaxError = "Too Few Fields";
		} catch (TooManyFieldsException tmfe) {
			this.syntaxError = "Too Many Fields";
		} catch (SyntaxException se) {
			this.syntaxError = "Syntax Error";
		} finally {
			assignGenreFile(); //assigns the genre file upon instantiation with a book record String
		}
	}
	
	public String getGenreFileName() {
		return this.genreFileName;
	}
	
	public String getSyntaxError() {
		return this.syntaxError;
	}
	
	public String getRecord() {
		return this.record;
	}
	
	/**
	 * Verifies if the genre is valid
	 * @param genre a string of the genre of the record
	 * @return true if the genre matches the valid genres, false otherwise
	 */
	public boolean verifyGenre(String genre) {
		switch (genre) {
		case "CCB":
		case "HCB":
		case "MTV":
		case "MRB":
		case "NEB":
		case "OTR":
		case "SSM":
		case "TPA":
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * Identifies the missing field based on the current number of fields in the record
	 * @return a string of the name of the missing field
	 */
	public String identifyMissingField() {
		switch (this.numOfFields) {
		case 1:
			return "title";
		case 2:
			return "authors";
		case 3:
			return "price";
		case 4:
			return "isbn";
		case 5:
			return "genre";
		case 6:
			return "year";
		default:
			return "error";
		}
	}
	
	/**
	 * Verifies the fields in the record
	 * @return the number of fields UNLESS there is a missing field or unknown genre.
	 * @return missing field: -1
	 * @return unknown genre: -2
	 */
	public int verifyFields() {
		this.numOfFields = 1;
		this.missingField = "";
		this.genre = "";
		boolean validGenre = false;
		boolean inQuotes = false;
		String field = "";
		
		for (int i = 0 ; i < this.record.length() ; i++) {
			if (this.record.charAt(i) == '\"')
				inQuotes = !inQuotes;
			else if (this.record.charAt(i) == ',' && !inQuotes) {
				if (field == "") {
					this.missingField = identifyMissingField();
				}
				this.numOfFields++;
				field = "";
			}
			else {
				if (this.numOfFields == 5)
					this.genre = this.genre + this.record.charAt(i);
				field = field + this.record.charAt(i);
			}
		}
		
		//final check in case the record ends with ','
		if (field == "") {
			if (this.numOfFields == 6)
				this.missingField = identifyMissingField();
			else if (this.numOfFields == 7)
				this.numOfFields = 6;
		}
		
		validGenre = verifyGenre(this.genre);
		
		//return
		if (this.missingField != "")
			return -1;
		else if (!validGenre) {
			return -2;
		}
		else
			return numOfFields;
	}
	
	/**
	 * Calls verifyFields() and throws an exception if required
	 * @throws SyntaxException in the event of a syntax error
	 */
	public void validateSyntax() throws SyntaxException{
		this.numOfFields = verifyFields();
		
		if (this.numOfFields == -2)
			throw new UnknownGenreException();
		else if (this.numOfFields == -1)
			throw new MissingFieldException();
		else if (this.numOfFields<6)
			throw new TooFewFieldsException();
		else if (this.numOfFields>6)
			throw new TooManyFieldsException();
	}
	
	/**
	 * Assigns a genre file to the record based on the genre.
	 * Assigns the record to the syntax error file if there is a syntax error
	 */
	public void assignGenreFile() {
		if (this.syntaxError.equals("No Syntax Error")) {
			switch (this.genre) {
			case "CCB":
				this.genreFileName = "Cartoons_Comics.csv.txt";
				break;
			case "HCB":
				this.genreFileName = "Hobbies_Collectibles.csv.txt";
				break;
			case "MTV":
				this.genreFileName = "Movies_TV_Books.csv.txt";
				break;
			case "MRB":
				this.genreFileName = "Music_Radio_Books.csv.txt";
				break;
			case "NEB":
				this.genreFileName = "Nostalgia_Eclectic_Books.csv.txt";
				break;
			case "OTR":
				this.genreFileName = "Old_Time_Radio_Books.csv.txt";
				break;
			case "SSM":
				this.genreFileName = "Sports_Sports_Memorabilia.csv.txt";
				break;
			case "TPA":
				this.genreFileName = "Trains_Planes_Automobiles.csv.txt";
				break;
			default:
				this.genreFileName = "syntax_error_file.txt";
				break;
			}
		}
		else
			this.genreFileName = "syntax_error_file.txt";
		
	}
	
	/**
	 * 
	 * @return the message to be printed to the syntax error file
	 */
	public String syntaxErrorPrint() {
		return ("==========================================="
				+"\nSyntax Error: "+this.syntaxError
				+"\nRecord: "+this.record+ "\n");
	}
	
	

}
