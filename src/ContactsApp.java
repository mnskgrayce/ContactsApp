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

/* Java program to manage contacts from/to a text file */

// User Interface class
public class ContactsApp {
    /* MAIN */
    public static void main(String[] args) {
        System.out.print(
                "========== CONTACTS MANAGEMENT APPLICATION ==========\n" +
                "Contact format: [name]; [number]; [email]; [address]\n" +
                "=====================================================");

        while (true) {
            int method = getMethod();
            if (callMethod(method) == 9) break;
        }
    }

    // Get user's desired method (return int)
    private static int getMethod() {
        System.out.println(
                        "\n1. Load contacts from file\n" +
                        "2. View all contacts\n" +
                        "3. Add new contact\n" +
                        "4. Edit a contact\n" +
                        "5. Delete a contact\n" +
                        "6. Search contact list\n" +
                        "7. Sort contact list\n" +
                        "8. Save contacts to file\n" +
                        "9. Quit\n"
        );
        return ContactListManager.getChoice(1);
    }

    // Call desired function
    private static int callMethod(int method) {
        switch (method) {
            case 1: ContactListManager.loadContactsFromFile(); break;
            case 2: ContactListManager.viewAllContacts(); break;
            case 3: ContactListManager.addNewContact(); break;
            case 4: ContactListManager.editContact(); break;
            case 5: ContactListManager.deleteContact(); break;
            case 6: ContactListManager.searchContact(); break;
            case 7: ContactListManager.sortContactList(); break;
            case 8: ContactListManager.saveContactsToFile(); break;
        }
        return method;
    }
}