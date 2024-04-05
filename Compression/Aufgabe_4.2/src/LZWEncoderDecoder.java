import com.github.jinahya.bit.io.BitInput;
import com.github.jinahya.bit.io.BitInputFactory;
import com.github.jinahya.bit.io.BitOutput;
import com.github.jinahya.bit.io.BitOutputFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class LZWEncoderDecoder {

    private static final int DICTIONARY_SIZE = 1024;
    private static final int BITS_PER_CODE = 1;

    public static void main(String[] args) {
        // Example usage
        String inputFileName = "text.use";
        String compressedFileName = "compressed.txt";
        String decompressedFileName = "decompressed.txt";
        // Encoding
        Map<String, Integer> encodingTable = encodeFile(inputFileName, compressedFileName);

        /* Print encoding table
        System.out.println("Encoding Table:");
        for (Map.Entry<String, Integer> entry : encodingTable.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


         */
        // Decoding
        Map<Integer, String> decodingTable = getDecodingTable(encodingTable);

        /* Print decoding table
        System.out.println("Decoding Table:");
        for (Map.Entry<Integer, String> entry : decodingTable.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

         */

        LZWEncoderDecoder.decodeFile(compressedFileName, decodingTable, decompressedFileName);
    }

    public static Map<String, Integer> encodeFile(String inputFileName, String compressedFileName) {
        Map<String, Integer> encodingTable = new HashMap<>();
        Map<Integer, String> decodingTable = new HashMap<>();

        try (InputStream inputStream = new FileInputStream(inputFileName);
             OutputStream compressedOutputStream = new FileOutputStream(compressedFileName)) {

            BitOutput bitOutput = BitOutputFactory.from(compressedOutputStream);
            Map<String, Integer> dictionary = initializeDictionary();

            StringBuilder currentSequence = new StringBuilder();
            int currentChar;
            int nextCode = dictionary.size();

            while ((currentChar = inputStream.read()) != -1) {
                char c = (char) currentChar;
                currentSequence.append(c);

                if (!dictionary.containsKey(currentSequence.toString())) {
                    int code = dictionary.get(currentSequence.substring(0, currentSequence.length() - 1));
                    writeCodeToBitOutput(bitOutput, code, BITS_PER_CODE);
                  System.out.println(code);
                    // Add to encoding table
                    encodingTable.put(currentSequence.toString(), nextCode);

                    // Update decoding table
                //   decodingTable.put(nextCode, currentSequence.toString());

                    // Update dictionary
                    dictionary.put(currentSequence.toString(), nextCode++);

                    currentSequence = new StringBuilder(String.valueOf(c));
                }
            }

            int lastCode = dictionary.get(currentSequence.toString());
            writeCodeToBitOutput(bitOutput, lastCode, BITS_PER_CODE);
            System.out.println(dictionary.size());
            System.out.println("File encoding completed successfully.");
            bitOutput.align(1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodingTable;
    }



    private static Map<String, Integer> initializeDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 128; i++) {
            dictionary.put(String.valueOf((char) i), i);
            System.out.println(String.valueOf((char) i));
        }
        return dictionary;
    }

    private static void writeCodeToBitOutput(BitOutput bitOutput, int code, int bitLength) throws IOException {
        for (int i = bitLength - 1; i >= 0; i--) {
            int bit = (code >> i) & 1;
            bitOutput.writeBoolean(bit == 1);
        }
    }

    private static Map<Integer, String> getDecodingTable(Map<String, Integer> encodingTable) {
        Map<Integer, String> decodingTable = new HashMap<>();

        for (int i = 0; i < 128; i++) {
            decodingTable.put( i,String.valueOf((char) i));
            System.out.println(String.valueOf((char) i)+"-> "+i);
        }
        for (Map.Entry<String, Integer> entry : encodingTable.entrySet()) {
            decodingTable.put(entry.getValue(), entry.getKey());
            System.out.println(entry.getValue()+ entry.getKey());
        }
        return decodingTable;
    }


    public static void decodeFile(String compressedFileName, Map<Integer, String> decodingTable, String outputFileName) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(compressedFileName);
            outputStream = new FileOutputStream(outputFileName);

            // Write the first code to the output
            int currentCode = readNextCode(inputStream, BITS_PER_CODE);
            System.out.println(currentCode);

            String firstEntry = decodingTable.get(currentCode);
         System.out.println(firstEntry);

            // Check if the first code exists in the decoding table
            if (firstEntry != null) {
                outputStream.write(firstEntry.getBytes());
            } else {
                throw new IOException("Invalid input: Code not found in decoding table");
            }

            // Continue until end of stream code
            while (true) {
                int nextCode = readNextCode(inputStream, BITS_PER_CODE);

                // Handle end of stream code
                if (nextCode == -1) {
                    break;
                }

                String entry = decodingTable.get(nextCode);

                // Check if the code exists in the decoding table
                if (entry != null) {
                    // Write each entry to the output
                    outputStream.write(entry.getBytes());

                    // Add the new entry to the decoding table
                    decodingTable.put(decodingTable.size(), decodingTable.get(currentCode) + entry.charAt(0));

                    // Update the current code
                    currentCode = nextCode;
                } else {
                    throw new IOException("Invalid input: Code not found in decoding table");
                }
            }

            System.out.println("Decoding completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
/*

  */
    private static int readNextCode(InputStream inputStream, int bitLength) throws IOException {
        int result = 0;
        for (int i = bitLength - 1; i >= 0; i--) {
            int bit = inputStream.read();
            if (bit == -1) {
                throw new IOException("Unexpected end of stream");
            }
            result |= (bit << i);
        }
        return result;
    }
}
