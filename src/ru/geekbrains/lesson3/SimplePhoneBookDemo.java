package ru.geekbrains.lesson3;

public class SimplePhoneBookDemo {

    public static void main(String[] args) {
        SimplePhoneBook phoneBook = new SimplePhoneBook();

        phoneBook.add("John Doe", "8 800 555-55-55");
        phoneBook.add("John Doe", "8 800 777-77-77");
        phoneBook.add("Lucy Marston", "8 800 333-33-33");
        phoneBook.add("Lucy Marston", "8 800 444-44-44");
        phoneBook.add("Lucy Marston", "8 800 222-22-22");
        phoneBook.add("Wendy Roslyn", "8 800 111-11-11");

        phoneBook.printNumbers("Lucy Marston");
        phoneBook.printNumbers("John Doe");
        phoneBook.printNumbers("Wendy Roslyn");
    }
}
