package ru.geekbrains.lesson3;

import java.util.ArrayList;

public class SimplePhoneBook {
    private final ArrayList<String> names = new ArrayList<>();
    private final ArrayList<String> phones = new ArrayList<>();

    public void add(String name, String phoneNumber) {
        names.add(name);
        phones.add(phoneNumber);
    }

    public ArrayList<String> get(String name) {
        ArrayList<String> foundPhones = new ArrayList<>();

        for (int i = 0, namesSize = names.size(); i < namesSize; i++) {
            String phoneOwner = names.get(i);
            if (phoneOwner.equals(name)) {
                foundPhones.add(phones.get(i));
            }
        }

        return foundPhones;
    }

    public void printNumbers(String name) {
        System.out.printf("Phone numbers of %s:\n", name);

        String regex = "[\\[\\]]";
        System.out.println(get(name).toString().replaceAll(regex, ""));

        System.out.println();
    }
}
