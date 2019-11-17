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

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/* Java class to handle list-specific methods */
class ContactListManager {

    // Create static objects
    private static File textFile = new File("contacts");
    private static ArrayList<Contact> list = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    /* MANAGER METHODS */

    // 1. Copy all contacts from text file and append to current list (assume good input)
    static void loadContactsFromFile() {
        int lines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            for (String contactString; (contactString = br.readLine()) != null; ) {

                // Split each string by RegEx then construct new contact from extracted fields
                String[] fields = contactString.split("; ");
                Contact newContact = new Contact(fields[0], fields[1], fields[2], fields[3]);

                // Check duplicate before adding
                if (!isDuplicateAddition(newContact.getNoSpaceString())) {
                    list.add(newContact);
                    lines++;
                }
            }
        } catch (IOException ignored) {}
        System.out.println("Loaded " + lines + " new contacts from contacts.txt");
    }

    // 2. Display current contact list to console
    static void viewAllContacts() {
        if (isListNotEmpty()) {
            System.out.println("\nCONTACT LIST (" + list.size() + " entries)");
            for (Contact contact: list) {
                System.out.print("Index [" + list.indexOf(contact) + "]: ");
                System.out.println(contact.getContactString());
            }
        }
    }

    // 3. Add a new contact to the bottom of list
    static void addNewContact() {
        Contact contact = new Contact();
        for (int field = 1; field <= 4; field++) {
            contact.setContact(field);
        }
        if (isDuplicateAddition(contact.getNoSpaceString())) {
            System.out.println("This contact is a duplicate and will not be added");
        } else {
            list.add(contact);
            System.out.println("Added contact: Index [" + list.indexOf(contact) + "]: " + contact.getContactString());
        }
    }

    // 4. Edit a contact in current list
    static void editContact() {
        if (isListNotEmpty()) {
            int index = getChoice(2);
            Contact contact = list.get(index);

            int function;
            while (true) {
                System.out.println("\nCurrent contact: " + contact.getContactString());
                System.out.println("1. Edit name\n2. Edit number\n3. Edit email\n4. Edit address\n5. Finish edit\n");
                function = getChoice(3);
                if (function == 5) break;
                contact.setContact(function);
            }
            if (isDuplicateEdition(index)) {
                System.out.println("This contact is a duplicate and will be removed");
                list.remove(index);
            }
        }
    }

    // 5. Delete a contact in current list
    static void deleteContact() {
        if (isListNotEmpty()) {
            int index = getChoice(2);

            System.out.println("\nCurrent contact: " + list.get(index).getContactString());
            System.out.print("Delete contact? (y/n): ");
            String delete = input.nextLine();

            if (delete.equals("y")) {
                list.remove(index);
                System.out.println("Deleted contact of index [" + index + "]");
            }
            else
                System.out.println("You did not choose to delete the contact");
        }
    }

    // 6. Search contact list from a string for an index
    static void searchContact() {
        if (isListNotEmpty()) {
            ArrayList<Contact> matchedContacts = new ArrayList<>();
            String search;
            while (true) {
                System.out.print("Enter your search item: ");
                search = input.nextLine();
                if (search.isEmpty())
                    System.out.println("Input is empty, please enter again");
                else break;
            }

            // Find all matching contacts by searching each field
            Pattern searchItem = Pattern.compile(Pattern.quote(search), Pattern.CASE_INSENSITIVE);
            for (Contact contact: list) {
                if (searchItem.matcher(contact.getName()).find() ||
                    searchItem.matcher(contact.getNumber()).find() ||
                    searchItem.matcher(contact.getEmail()).find() ||
                    searchItem.matcher(contact.getAddress()).find()) {
                    System.out.print("Index [" + list.indexOf(contact) + "]: ");
                    System.out.println(contact.getContactString());
                    matchedContacts.add(contact);
                }
            }
            System.out.println("Found " + matchedContacts.size() + " matching result(s)");
        }
    }

    // 7. Sort list by one field in ascending/alphabetical order
    static void sortContactList() {
        if (isListNotEmpty()) {
            System.out.println("\n1. Sort by name\n2. Sort by number\n3. Sort by email\n4. Sort by address\n5. Cancel\n");
            int function = getChoice(3);

            switch (function) {
                case 1: list.sort(Comparator.comparing(Contact::getName)); break;
                case 2: list.sort(Comparator.comparing(Contact::getNumber)); break;
                case 3: list.sort(Comparator.comparing(Contact::getEmail)); break;
                case 4: list.sort(Comparator.comparing(Contact::getAddress)); break;
            }
            if (function != 5)
                System.out.println("Sort succeeded");
        }
    }

    // 8. Overwrite text file with current list
    static void saveContactsToFile() {
        if (isListNotEmpty()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(textFile))){
                for (Contact contact : list) {
                    bw.write(contact.getContactString());
                    bw.write("\n");
                }
            } catch (IOException ignored) {}
            System.out.println("Saved " + list.size() + " contacts to contacts.txt");
        }
    }

    /* SUPPORTING METHODS */

    // Get user's manager method/contact index/contact field
    static int getChoice(int type) {
        /*
        Type 1: Find contacts manager functions (1 -> 9)
        Type 2: Find contact index in list (0 -> list.size() - 1)
        Type 3: Find add/edit contact fields functions (1 -> 4) or Exit (5)
        */
        String[] types = {"function (1-9)", "contact index (0-" + (list.size() - 1) + ")", "function (1-5)"};
        int choice;
        while (true) {
            System.out.print("Select a " + types[type - 1] + ": ");
            choice = tryParse(input.nextLine());
            if (!isValidChoice(type, choice))
                System.out.println("Invalid " + types[type - 1] + ", please enter again");
            else break;
        }
        return choice;
    }

    // Verify user's function (int) by type of functions
    private static boolean isValidChoice(int type, int choice) {
        switch (type) {
            case 1: return (1 <= choice && choice <= 9);
            case 2: return (0 <= choice && choice <= list.size() - 1);
            case 3: return (1 <= choice && choice <= 5);
        }
        return false;
    }

    // Handle non-int input
    private static int tryParse(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Check if array list is empty
    private static boolean isListNotEmpty() {
        if (list.size() == 0) {
            System.out.println("Contact list is empty");
            return false;
        }
        return true;
    }

    // Check duplicates by index (for existing contacts)
    private static boolean isDuplicateEdition(int index) {
        for (int i = 0; i < list.size(); i++) {
            if (i != index) {
                // Filter whitespaces
                String s1 = list.get(i).getNoSpaceString();
                String s2 = list.get(index).getNoSpaceString();
                if (s1.equals(s2))
                    return true;
            }
        }
        return false;
    }

    // Check duplicates for adding/loading new inputs
    private static boolean isDuplicateAddition(String newContact) {
        for (Contact contact : list) {
            if (newContact.equals(contact.getNoSpaceString()))
                return true;
        }
        return false;
    }
}
