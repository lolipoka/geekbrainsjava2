package ru.geekbrains.lesson4.methods;

import java.util.ArrayList;
import java.util.Arrays;

public class MethodsDemo {

    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add((int) (Math.random() * 20));
        }
        System.out.printf("Average of %s is %.2f.\n", numbers.toString(), Utils.average(numbers));

        Integer[] list = new Integer[numbers.size()];
        numbers.toArray(list);
        System.out.printf("Maximum of %s is %d.\n", Arrays.toString(list), Utils.maximum(list));

        Integer numberToFind = 6;
        System.out.printf("Index of %d in %s is %d.\n", numberToFind, Arrays.toString(list), Utils.search(numberToFind, list));

        Integer anotherNumberToFind = 15;
        System.out.printf("Index of %d in %s is %d.\n", anotherNumberToFind, Arrays.toString(list), Utils.search(anotherNumberToFind, list));

        String initialString = "java interview";
        System.out.printf("Reverse string of '%s' is '%s'.\n", initialString, Utils.reverse(initialString));

        ArrayList<String> words = new ArrayList<>();
        words.add("canoe");
        words.add("ark");
        words.add("pup");
        words.add("stone");
        words.add("carrot");
        words.add("anchovy");
        words.add("alm");
        words.add("avocado");
        words.add("air");

        System.out.printf("3-letter words starting with 'a': %s.\n",  Utils.search(words).toString());
    }


}
