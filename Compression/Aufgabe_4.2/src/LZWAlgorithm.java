import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LZWAlgorithm {

    private static final int MAX_DICTIONARY_SIZE = 65536; // Maximum dictionary size (2^16)

    private BinarySearchTree<String, Integer> dictionary; // Binary Search Tree for the dictionary

    public LZWAlgorithm() {
        this.dictionary = new BinarySearchTree<>();
        initializeDictionary();
    }

    private void initializeDictionary() {
        for (int i = 0; i < 256; i++) {
            String symbol = String.valueOf((char) i);
            dictionary.insert(symbol, i);
        }
    }

    public void compress(String inputFile, String compressedFile) {
        try (InputStream inputStream = new FileInputStream(inputFile);
             DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(compressedFile))) {

            StringBuilder current = new StringBuilder();
            int nextCode = 256; // Start with the ASCII values

            int byteRead;
            while ((byteRead = inputStream.read()) != -1) {
                char ch = (char) byteRead;
                String currentSymbol = current.toString() + ch;
                if (dictionary.contains(currentSymbol)) {
                    current = new StringBuilder(currentSymbol);
                } else {
                    outputStream.writeShort(dictionary.get(current.toString()));
                    if (nextCode < MAX_DICTIONARY_SIZE) {
                        dictionary.insert(currentSymbol, nextCode++);
                    }
                    current = new StringBuilder(String.valueOf(ch));
                }
            }

            if (!current.toString().isEmpty()) {
                outputStream.writeShort(dictionary.get(current.toString()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decompress(String compressedFile, String decompressedFile) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(compressedFile));
             OutputStream outputStream = new FileOutputStream(decompressedFile)) {

            Map<Integer, String> reverseDictionary = new HashMap<>();
            initializeReverseDictionary(reverseDictionary);

            int nextCode = 256; // Start with the ASCII values
            int currentCode = inputStream.read();
            String currentSymbol = reverseDictionary.get(currentCode);
            outputStream.write(currentSymbol.getBytes());

            while (true) {
                currentCode = inputStream.read();
                if (currentCode == -1) {
                    break; // End of file reached
                }

                String entry;
                if (reverseDictionary.containsKey(currentCode)) {
                    entry = reverseDictionary.get(currentCode);
                } else if (currentCode == nextCode) {
                    entry = currentSymbol + currentSymbol.charAt(0);
                } else {
                    throw new IllegalArgumentException("Invalid compressed file");
                }

                outputStream.write(entry.getBytes());
                if (nextCode < MAX_DICTIONARY_SIZE) {
                    reverseDictionary.put(nextCode++, currentSymbol + entry.charAt(0));
                }

                currentSymbol = entry;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void initializeReverseDictionary(Map<Integer, String> reverseDictionary) {
        for (int i = 0; i < 256; i++) {
            reverseDictionary.put(i, String.valueOf((char) i));
        }
    }

    public static void main(String[] args) {
        LZWAlgorithm lzw = new LZWAlgorithm();
        String inputFile = "input.txt";
        String compressedFile = "compressed.lzw";
        String decompressedFile = "decompressed.txt";

        // Compression
        lzw.compress(inputFile, compressedFile);

        // Decompression
        lzw.decompress(compressedFile, decompressedFile);
    }
}
