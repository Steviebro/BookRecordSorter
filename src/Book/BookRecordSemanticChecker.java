package Book;

import SemanticExceptions.*;

public class BookRecordSemanticChecker {
	
	private String record;
	
	private String title;
	private String price;
	private String isbn;
	private String year;
	
	private String semanticError;
	
	public BookRecordSemanticChecker(String record) {
		this.record = record;
		splitRecord();
		try {
			validateSemantics();
			this.semanticError = "No Semantic Error";
		} catch (BadYearException bye) {
			this.semanticError = "Invalid Year";
		} catch (BadPriceException bpe) {
			this.semanticError = "Invalid Price";
		} catch (BadIsbn10Exception bi10e) {
			this.semanticError = "Invalid 10-ISBN";
		} catch (BadIsbn13Exception bi13e) {
			this.semanticError = "Invalid 13-ISBN";
		} catch (SemanticException se) {
			this.semanticError = "Semantic Error";
		}
	}
	
	public String getRecord() {
		return this.record;
	}
	
	public String getSemanticError() {
		return this.semanticError;
	}
	
	/**
	 * Identifies where the title ends
	 * @param fields a string array of the split record
	 * @return the index of the array where the title ends
	 */
	public int getTitleEndIndex(String[] fields) {
		int index = 0;
		for (int i = 0 ; i < fields.length ; i++) {
			if (fields[i].contains("\""))
				index = i;
		}
		return index;
	}
	
	/**
	 * Splits the record and assigns values to the fields of the object to be checked
	 */
	public void splitRecord() {
		String[] recordFields = this.record.split(",");
		int titleEnd = getTitleEndIndex(recordFields);
		this.title = "";
		for (int i = 0 ; i <= titleEnd ; i++) {
			this.title = this.title.concat(recordFields[i]);
		}
		this.price = recordFields[titleEnd+2];
		this.isbn = recordFields[titleEnd+3];
		this.year = recordFields[titleEnd+5];
	}
	
	/**
	 * Verifies that the price is valid
	 * @return true if it is valid
	 */
	public boolean verifyPrice() {
		double valPrice = Double.parseDouble(this.price);
		
		if (valPrice < 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Verifies that the year is valid
	 * @return true if it is valid
	 */
	public boolean verifyYear() {
		int valYear = Integer.parseInt(this.year);
		
		if (valYear >= 1995 && valYear <= 2010)
			return true;
		else 
			return false;
	}
	
	/**
	 * Checks the length of the isbn
	 * @return an int of the length of the isbn
	 */
	public int checkIsbnLength() {
		int length = this.isbn.length();
		return length;
	}
	
	/**
	 * Verifies that the isbn-10 is valid
	 * @return true if it is valid
	 */
	public boolean verify10Isbn() {
		int sum = 0;
		int temp = 0;
		
		for (int i = 0 ; i < this.isbn.length() ; i++) {
			temp = this.isbn.charAt(i) - '0';
			sum = sum + temp*(10-i);
		}
		
		if (sum%11 == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifies that the isbn-13 is valid
	 * @return true if it is valid
	 */
	public boolean verify13Isbn() {
		int sum = 0;
		int temp = 0;
		
		for (int i = 0 ; i < this.isbn.length() ; i++) {
			temp = this.isbn.charAt(i) - '0';
			if (i%2 == 0)
				sum = sum + temp;
			else
				sum = sum + temp*3;
		}
		
		if (sum%10 == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifies the validity of all fields to be checked
	 * @throws SemanticException in the event of a semantic error
	 */
	public void validateSemantics() throws SemanticException{
		if (!verifyYear())
			throw new BadYearException();
		else if (!verifyPrice())
			throw new BadPriceException();
		else if (checkIsbnLength() == 10 && !verify10Isbn())
			throw new BadIsbn10Exception();
		else if (checkIsbnLength() == 13 && !verify13Isbn())
			throw new BadIsbn13Exception();
	}
	
	/**
	 * 
	 * @return the message to be printed to the semantic error file
	 */
	public String semanticErrorPrint() {
		return ("==========================================="
				+"\nSemantic Error: "+this.semanticError
				+"\nRecord: "+this.record+ "\n");
	}
	

}
