package ru.geekbrains.lesson4.methods;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    @FunctionalInterface
    interface IndexFinder {
        int getFirstIndex(Integer n, Integer[] a);
    }

    @FunctionalInterface
    interface Function<T> {
        T get(T t);
    }

    @FunctionalInterface
    interface Max {
        Integer get(Integer[] a);
    }

    @FunctionalInterface
    interface Avg {
        Double get(List<Integer> l);
    }

    /**
     * Searches an array for a given {@link Integer} and returns its index
     * or -1 if number is not found.
     *
     * @param number {@link Integer} object which index needs to be found.
     * @param list Array of {@link Integer} objects.
     * @return Index of number in a list.
     */
    public static int search(Integer number, Integer[] list) {
        IndexFinder index = (n, a) -> {
            for (int i = 0; i < a.length; i++) {
                if (a[i].equals(n)) return i;
            }
            return -1;
        };
        return index.getFirstIndex(number, list);
    }

    /**
     * Reverses given {@link String} and returns the result.
     *
     * @param str {@link String} to reverse.
     * @return Reversed {@link String}.
     */
    public static String reverse(String str) {
        Function<String> reverse = t -> {
            StringBuilder sb = new StringBuilder(t);
            return sb.reverse().toString();
        };

        return reverse.get(str);
    }

    /**
     * Returns maximum value of an {@link Integer} array.
     *
     * @param list An array of {@link Integer} objects.
     * @return Maximum {@link Integer} value of a given array.
     */
    public static Integer maximum(Integer[] list) {
        Max maximum = l -> {
            Integer max = Integer.MIN_VALUE;
            for (Integer e : l) {
                if (e > max) max = e;
            }
            return max;
        };
        return maximum.get(list);
    }

    /**
     * Returns average {@link Double} value of an {@link Integer} array.
     *
     * @param list An array of {@link Integer} objects.
     * @return Average value of type {@link Double} of a given array.
     */
    public static Double average(List<Integer> list) {
        Avg average = l -> {
            Double sum = 0.0;
            for (Integer e : l) {
                sum += e;
            }
            return sum / l.size();
        };
        return average.get(list);
    }

    /**
     * Returns a list of 3-letter strings starting with 'a'.
     *
     * @param list <tt>List</tt> collection of {@link String} elements to search through.
     * @return <tt>List</tt> collection of 3-letter {@link String} elements starting with 'a'.
     */
    public static List<String> search(List<String> list) {
        Function<List<String>> words = t -> {
            ArrayList<String> result = new ArrayList<>();
            for (String e : t) {
                if (e.charAt(0) == 'a' && e.length() == 3) result.add(e);
            }
            return result;
        };

        return words.get(list);
    }
}


