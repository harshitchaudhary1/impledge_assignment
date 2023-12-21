package com.impledge.assignment;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.util.PropertySource.Comparator;

public class CSVReaderExample {
    public static void main(String[] args) throws CsvValidationException, FileNotFoundException, IOException {
        String csvFilePath1 = "C:/Users/HP/Downloads/Input_01.csv";
        int timetaken=0;
        String second="";

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath1))) {
            String[] nextLine;
            Set<String> words = new HashSet<>();

            while ((nextLine = reader.readNext()) != null) {
                words.addAll(Arrays.asList(nextLine));
            }

            String longestCompoundedWord = findLongestCompoundedWord(words);
            System.out.println("Longest Compounded Word: " + longestCompoundedWord);
            System.out.println("Secomd Longest Compounded Word: "+second);
            System.out.println("Time Taken: "+timetaken );
        }
    }

    private static String findLongestCompoundedWord(Set<String> words) {
        String[] wordArray = words.toArray(new String[0]);

        Arrays.sort(wordArray, (s1, s2) -> Integer.compare(s2.length(), s1.length()));

        for (String word : wordArray) {
            Set<String> wordsCopy = new HashSet<>(words);
            if (canBeConstructed(word, wordsCopy)) {
                return word;
            }
        }

        return null;
    }

    private static boolean canBeConstructed(String word, Set<String> words) {
        if (word.isEmpty()) {
            return true;
        }

        for (int i = 1; i <= word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);

            if (words.contains(prefix) && words.contains(suffix) && canBeConstructed(suffix, words)) {
                return true;
            }
        }

        
        return false;
    }
}