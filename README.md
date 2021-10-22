# ContactsApp (Java SE 11.0.4)

A simple Contacts Management app.

----- RMIT University Vietnam -----<br> 
**Course:** INTE2512 Object-Oriented Programming<br> 
**Semester:** 2019C<br> 
**Assessment:** Assignment 1<br> 
**Student Name:** Nguyen Minh Trang<br> 
**Student ID:** 3751450<br> 

## 1. Introduction
- This CLI program provides functions to manage a contact list
- Users can import data from a text file, or manually add to the list
- Users can also export list to a file, which overwrites the old file

## 2. Functions
- Create an empty contact list at launch and free it upon exit
- Store all contacts by separate data fields: (name/number/email/address)
- Run indefinitely until prompted quit
- Fault-tolerance: display error messages upon bad console input and let user enter again until valid
- Duplicate detection and removal during adding new contacts and editing existing contacts
- Load contacts from a text file: read file by line and add its content to list
- **View all contacts:** display all contacts in the list
- **Add new contact:** add a new contact to bottom of list
- **Edit a contact:** edit a contact by selected index and field (name/number/email/address)
- **Delete a contact:** delete a contact by selected index from list
- Search the contact list by a given string and display all matching results
- Sort contact list by one of given fields (name/number/email/address) in ascending/alphabetical order
- Save contacts to a text file: overwrite by formatted line to file

## 3. Installation
1. Have the files `ContactsApp.java`, `ContactListManager.java`, `Contact.java`, and `contacts.txt` in the same project directory
2. Compile and run ContactsApp.class

## 4. Known Bugs
- Program cannot handle bad data when reading from file (POSSIBLE WORKAROUND: skip bad line or fill invalid/unavailable fields with dummy value - NOT IMPLEMENTED)
- Sometimes program freezes while taking input (WORKAROUND: keep pressing Enter; if error message appears just re-enter input until program recovers)

## 5. References
[1] Stack Overflow, 28 Sep 2009. [Online]. Available: https://stackoverflow.com/questions/1486077/good-way-to-encapsulate-integer-parseint. [Accessed 17 Nov 2019].

[2] L. Gupta, "Java email validation using regex," 2015. [Online]. Available: https://howtodoinjava.com/regex/java-regex-validate-email-address/. [Accessed 17 Nov 2019].

[3] Oracle Corporation, "Pattern (Java SE 11 & JDK 11)," Oracle Corporation, [Online]. Available: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html. [Accessed 17 Nov 2019].

[4] Oracle Corporation, "Scanner (Java SE 11 & JDK 11)," [Online]. Available: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Scanner.html#nextLine. [Accessed 17 Nov 2019].

[5] Oracle Corporation, "BufferedReader (Java SE 11 & JDK 11)," [Online]. Available: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/BufferedReader.html#readLine(). [Accessed 17 Nov 2019].

[6] S. Mittal, "8 Difference Between BufferedReader And Scanner In Java With Example," Java Hungry, [Online]. Available: https://javahungry.blogspot.com/2018/12/difference-between-bufferedreader-and-scanner-in-java-examples.html. [Accessed 17 Nov 2019].

[7] A. Boroumand, "Sort a List of Objects by Field in Java," 9 May 2018. [Online]. Available: https://www.codebyamir.com/blog/sort-list-of-objects-by-field-java. [Accessed 17 Nov 2019].

[8] Oracle Corporation, "List (Java SE 11 & JDK 11)," [Online]. Available: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html#sort(java.util.Comparator). [Accessed 17 Nov 2019].

[9] Oracle Corporation, "String (Java SE 11 & JDK 11)," [Online]. Available: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#split(java.lang.String). [Accessed 17 Nov 2019].

[10] D. Y. Liang, Introduction to Java Programming, Comprehensive Version, Global Edition, vol. 10th ed, Pearson, Boston, chapter 10-11 & 15, 2015.
