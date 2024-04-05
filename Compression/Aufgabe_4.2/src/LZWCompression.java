import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class LZWCompression {

    public static String compressLZWWithBinarySearchTree(String input) {
        StringBuilder result = new StringBuilder();
        BinarySearchTree<String, Integer> dictionary = new BinarySearchTree<>();
        int dictionarySize = 1024; // 2^10 entries (1024)
        int totalEncodedLength = 0; // Variable to track the total length of encoded strings
        int count = 1024;
        // Initialize dictionary with individual characters
        for (int i = 0; i < dictionarySize; i++) {
            dictionary.insert(String.valueOf((char) i), i);
        }

        StringBuilder current = new StringBuilder();

        for (char c : input.toCharArray()) {
            current.append(c); // build  squence
            if (!dictionary.contains(current.toString()))  // new sequence found
            {
                // Output the code for the current sequence (excluding the last character)
                int code = dictionary.get(current.substring(0, current.length() - 1));
                result.append(code).append(" ");
                totalEncodedLength += String.valueOf(code).length() + 1; // Add length of code + space

                // Print the new word added to the dictionary

                // String newWord = current.toString();
                //System.out.println("New word added to dictionary: " + newWord+" at number =" +count);
                //count=count+1;
                // Add the new sequence to the dictionary
                dictionary.insert(current.toString(), dictionarySize++);// increase size of dict
                // Reset the current sequence to the last character
                current = new StringBuilder(String.valueOf(c));
            }
        }

        // Output the code for the last sequence
        int lastCode = dictionary.get(current.toString());
        result.append(lastCode);
        totalEncodedLength += String.valueOf(lastCode).length();
        // Calculate the compression rate
        double originalSize = input.length() * 8.0; // Assuming 8 bits per character
        double compressedSize = totalEncodedLength;
        double compressionRate = (1 - compressedSize / originalSize) * 100;

        System.out.println("Compression Rate: " + compressionRate + "%");
        // Calculate the average encoded string length
        double averageEncodedLength = (double) totalEncodedLength / input.length();

        System.out.println("Average Encoded String Length: " + averageEncodedLength);

        return result.toString();
    }

    public static String compressLZWWithHashMap(String input) {
        StringBuilder result = new StringBuilder();
        Map<String, Integer> dictionary = new HashMap<>();
        int dictionarySize = 1024; // Initial dictionary size

        // Initialize dictionary with individual characters
        for (int i = 0; i < dictionarySize; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }

        StringBuilder current = new StringBuilder();

        for (char c : input.toCharArray()) {
            current.append(c);
            if (!dictionary.containsKey(current.toString())) {
                // Output the code for the current sequence (excluding the last character)
                result.append(dictionary.get(current.substring(0, current.length() - 1))).append(" ");
                // Add the new sequence to the dictionary
                dictionary.put(current.toString(), dictionarySize++);
                // Reset the current sequence to the last character
                current = new StringBuilder(String.valueOf(c));
            }
        }

        // Output the code for the last sequence
        result.append(dictionary.get(current.toString()));

        return result.toString();
    }

    public static void main(String[] args) {

        int initialStringLength = 1000;


        for (int i = 0; i < 10; i++) {
            System.out.println("Iteration " + (i + 1) + ":");

            int currentStringLength = initialStringLength * (int) Math.pow(2, i);

            // Generate a random string
            String randomString = generateRandomString(currentStringLength);

            System.out.println("String Length: " + currentStringLength);

            // Measure the runtime for BinarySearchTree
            long startTimeBST = System.nanoTime();
            LZWCompression.compressLZWWithBinarySearchTree(randomString);

            long endTimeBST = System.nanoTime();
            long durationBST = endTimeBST - startTimeBST;

            System.out.println("BinarySearchTree Duration: " + durationBST + " nanoseconds");

            // Measure the runtime for HashMap
            long startTimeHashMap = System.nanoTime();

            LZWCompression.compressLZWWithHashMap(randomString);

            long endTimeHashMap = System.nanoTime();
            long durationHashMap = endTimeHashMap - startTimeHashMap;
            System.out.println("HashMap Duration: " + durationHashMap + " nanoseconds");

            System.out.println(); // Separate iterations
        }



    }



        // Rest of the class remains unchanged




    private static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            randomString.append(randomChar);
        }
        return randomString.toString();
    }


}
