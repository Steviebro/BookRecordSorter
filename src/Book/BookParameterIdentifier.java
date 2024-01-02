package Book;

public class BookParameterIdentifier {
	
	private String record;
	
	private String title;
	private String authors;
	private double price;
	private String isbn;
	private String genre;
	private int year;
	
	public BookParameterIdentifier(String record) {
		this.record = record;
		splitRecord();
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
		this.authors = recordFields[titleEnd+1];
		this.price = Double.parseDouble(recordFields[titleEnd+2]);
		this.isbn = recordFields[titleEnd+3];
		this.genre = recordFields[titleEnd+4];
		this.year = Integer.parseInt(recordFields[titleEnd+5]);
	}
	

	public String getTitle() {
		return this.title;
	}
	
	public String getAuthors() {
		return this.authors;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public int getYear() {
		return this.year;
	}
	
}	

