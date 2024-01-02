# BookRecordSorter

The application begins by inputting book records from a csv formatted text file into a 2-D String array. First, the records are separated and outputted into separate text files based on their genre while the records with syntax errors are placed into a syntax error file.

Then, the newly created text files are inputted to another 2-D String array where they are checked for semantic errors (invalid field formats). Records with semantic errors are outputted to a semantic error file while book objects are created for each of the valid records. Once all the records have been processed, the Book objects are serialized and outputted to binary files (one for each genre).

Finally, the binary files are inputted and deserialized back into a 2-D Book object array. An interactive menu is then provided to the user to navigate through the books by genre.
