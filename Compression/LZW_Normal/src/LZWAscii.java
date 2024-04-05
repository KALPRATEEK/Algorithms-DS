import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LZWAscii {

    private static final int DICTIONARY_SIZE = 1024;

    public static void main(String[] args) {
        // Example usage
        String inputFileName = "text.use";
        String compressedFileName = "compressed_ascii.txt";
        String outputFileName = "decode_ascii.txt";

        // Encoding
        Map<String, Integer> encodingTable = encodeFile(inputFileName, compressedFileName);

        // Print encoding table
        System.out.println("Encoding Table:");
        for (Map.Entry<String, Integer> entry : encodingTable.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Creating decoding dictionaries
        Map<Integer, String> decodingTable = createDecodingTable(encodingTable);

        // Print decoding table
        System.out.println("Decoding Table:");
        for (Map.Entry<Integer, String> entry : decodingTable.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Decoding the file
        decodeFile(compressedFileName, decodingTable, outputFileName);
    }

    public static Map<String, Integer> encodeFile(String inputFileName, String compressedFileName) {
        Map<String, Integer> encodingTable = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(compressedFileName))) {

            Map<String, Integer> dictionary = initializeDictionary();

            StringBuilder currentSequence = new StringBuilder();
            int currentChar;
            int nextCode = dictionary.size();

            while ((currentChar = reader.read()) != -1) {
                char c = (char) currentChar;
                currentSequence.append(c);

                String currentSubsequence = currentSequence.substring(0, currentSequence.length() - 1);
                if (!dictionary.containsKey(currentSubsequence)) {
                    encodingTable.put(currentSubsequence, nextCode);
                    writer.write(Integer.toString(nextCode));
                    writer.write(" ");

                    // Add to encoding table
                    encodingTable.put(currentSequence.toString(), nextCode);

                    // Update dictionary
                    dictionary.put(currentSequence.toString(), nextCode++);

                    currentSequence = new StringBuilder(String.valueOf(c));
                }
            }

            int lastCode = dictionary.get(currentSequence.toString());
            writer.write(Integer.toString(lastCode));
            writer.write(" ");
            System.out.println(dictionary.size());
            System.out.println("File encoding completed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodingTable;
    }

    private static Map<String, Integer> initializeDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 128; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }
        // Special code for an empty string
        return dictionary;
    }

    private static Map<Integer, String> createDecodingTable(Map<String, Integer> encodingTable) {
        Map<Integer, String> decodingTable = new HashMap<>();

        for (int i = 0; i < 128; i++) {
            decodingTable.put(i, String.valueOf((char) i));
        }
        for (Map.Entry<String, Integer> entry : encodingTable.entrySet()) {
            decodingTable.put(entry.getValue(), entry.getKey());
        }
        return decodingTable;
    }

    public static void decodeFile(String compressedFileName, Map<Integer, String> decodingTable, String outputFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(compressedFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] codes = line.trim().split(" ");
                for (String code : codes) {
                    int currentCode = Integer.parseInt(code);

                    String entry = decodingTable.get(currentCode);

                    if (entry != null) {
                        writer.write(entry);
                    }
                }
            }

            System.out.println("Decoding completed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
