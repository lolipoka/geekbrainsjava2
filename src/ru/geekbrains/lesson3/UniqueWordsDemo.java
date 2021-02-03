package ru.geekbrains.lesson3;

import java.util.*;

public class UniqueWordsDemo {
    public static void main(String[] args) {
        String[] words = getWords();
        printUniqueWords(words);
        printWordsCount(words);
    }

    private static String[] getWords() {
        String[] words = new String[12];

        words[0] = "apple";
        words[1] = "orange";
        words[2] = "strawberry";
        words[3] = "avocado";
        words[4] = "banana";
        words[5] = "apple";
        words[6] = "apple";
        words[7] = "kiwi";
        words[8] = "grape";
        words[9] = "watermelon";
        words[10] = "banana";
        words[11] = "orange";

        return words;
    }

    private static void printUniqueWords(String[] words) {
        HashSet<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        System.out.println("Unique words:");
        for (String word : uniqueWords) {
            System.out.printf("\t%s\n", word);
        }
        System.out.println();
    }

    private static void printWordsCount(String[] words) {
        HashMap<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            Integer value = wordCount.get(word);
            wordCount.put(word, value == null ? 1 : ++value);
        }

        Iterator<Map.Entry<String, Integer>> iterator = wordCount.entrySet().iterator();

        System.out.println("Word count:");
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.printf("\t%s: %d\n", entry.getKey(), entry.getValue());
        }
        System.out.println();
    }
}
