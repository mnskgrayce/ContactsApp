/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 1
  Author: Nguyen Minh Trang
  ID: 3751450
  Created  date: 12/11/2019
  Last modified: /11/2019
  Acknowledgement: If you use any resources, acknowledge here. Failure to do so will be considered as plagiarism.
*/

import java.util.Scanner;
import java.util.regex.Pattern;

/* Java class to handle field-specific methods */
class Contact {
    // Data fields
    private String name;
    private String number;
    private String email;
    private String address;

    // Construct brand new contact
    Contact() {}

    // Construct based on input
    Contact(String name, String number, String email, String address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    // Getter methods
    String getName() { return this.name; }
    String getNumber() { return this.number; }
    String getEmail() { return this.email; }
    String getAddress() { return this.address; }

    // Set contact field
    void setContact(int function) {
        final String[] fields = {"name", "phone number", "email", "address"};

        // Let user re-enter new field until matches field RegEx
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter new " + fields[function - 1] + ": ");
            String newContent = input.nextLine();

            // Verify entered input
            if (!isValidField(function, newContent))
                System.out.println("Invalid " + fields[function - 1] + ", please enter again " + validFieldFormat(function));
            else {
                switch (function) {
                    case 1: this.name = newContent; break;
                    case 2: this.number = newContent; break;
                    case 3: this.email = newContent; break;
                    case 4: this.address = newContent; break;
                }
                break;
            }
        }
    }

    // Verify new contact field by RegEx
    private boolean isValidField(int field, String newContent) {
        Pattern[] fieldsRegEx = new Pattern[4];
        fieldsRegEx[0] = Pattern.compile("^[a-zA-Z ,.'-]+$");   // Name
        fieldsRegEx[1] = Pattern.compile("^[+]?(\\d ?){5,15}$");    // Phone number
        fieldsRegEx[2] = Pattern.compile("^(.+)@(.+)$");   // No need for complex Email regEx (use send-user-email confirmation)
        fieldsRegEx[3] = Pattern.compile("^.+$");  // Address

        return fieldsRegEx[field - 1].matcher(newContent).matches();
    }

    // Store correct format for each contact field
    private String validFieldFormat(int field) {
        String[] formats = {
                "(e.g. john smith, Jeanne d'Arc, Hans Jr., Anna-Lyza)", // Name
                "(e.g +84 857 947655, 12345)",  // Number
                "(e.g. sample123@gmail.com)",   // Email
                ""};    // Address (no format)
        return formats[field - 1];
    }

    // Return whole contact as a formatted string
    String getContactString() {
        return name + "; " + number + "; " + email + "; " + address;
    }

    // Return clean contact as a space-free string for comparison
    String getNoSpaceString() {
        return (name + number + email + address).replaceAll("\\s+", "");
    }
}
