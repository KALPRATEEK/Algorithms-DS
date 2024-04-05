import java.io.*;
import java.util.*;

public class LZW {

    private static final int BITS_PER_CODE = 12;
    private static final int MAX_TABLE_SIZE = (1 << BITS_PER_CODE);

    private Map<String, Integer> dictionary;
    private ByteArrayOutputStream compressedData;
    private ByteArrayOutputStream decompressedData;

    public LZW() {
        initializeDictionary();
    }

    private void initializeDictionary() {
        dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }
    }

    private void resetDictionary() {
        initializeDictionary();
    }

    private void compress(byte[] data) {
        compressedData = new ByteArrayOutputStream();
        int nextCode = 256;
        ByteArrayOutputStream currentString = new ByteArrayOutputStream();

        for (byte b : data) {
            currentString.write(b);
            String combinedString = currentString.toString();

            if (dictionary.containsKey(combinedString)) {
                continue;
            }

            Integer code = dictionary.get(combinedString);
            if (code == null) {
                throw new IllegalStateException("Dictionary entry not found for: " + combinedString);
            }

            writeCodeToStream(code);

            if (nextCode < MAX_TABLE_SIZE) {
                dictionary.put(combinedString, nextCode++);
            }

            currentString.reset();
            currentString.write(b);
        }

        if (currentString.size() > 0) {
            Integer code = dictionary.get(currentString.toString());
            if (code == null) {
                throw new IllegalStateException("Dictionary entry not found for: " + currentString.toString());
            }

            writeCodeToStream(code);
        }
    }


    private void writeCodeToStream(int code) {
        // Ensure that the code is within the byte range (0-255)
        code = code & 0xFF;
        compressedData.write(code);
    }

    private byte[] decompress(byte[] compressedData) throws IOException {
        decompressedData = new ByteArrayOutputStream();
        int nextCode = 256;
        List<Integer> compressedCodes = getCompressedCodes(compressedData);
        Map<Integer, String> reverseDictionary = getReverseDictionary();

        int previousCode = compressedCodes.get(0);
        String currentString = reverseDictionary.get(previousCode);

        decompressedData.write(currentString.getBytes());

        for (int i = 1; i < compressedCodes.size(); i++) {
            int code = compressedCodes.get(i);

            if (reverseDictionary.containsKey(code)) {
                currentString = reverseDictionary.get(code);
            } else {
                currentString = reverseDictionary.get(previousCode) + reverseDictionary.get(previousCode).charAt(0);
            }

            decompressedData.write(currentString.getBytes());

            if (nextCode < MAX_TABLE_SIZE) {
                reverseDictionary.put(nextCode++, reverseDictionary.get(previousCode) + currentString.charAt(0));
            }

            previousCode = code;
        }

        return decompressedData.toByteArray();
    }


    private List<Integer> getCompressedCodes(byte[] compressedData) {
        List<Integer> compressedCodes = new ArrayList<>();
        for (byte b : compressedData) {
            compressedCodes.add(Byte.toUnsignedInt(b));
        }
        return compressedCodes;
    }

    private Map<Integer, String> getReverseDictionary() {
        Map<Integer, String> reverseDictionary = new HashMap<>();
        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            reverseDictionary.put(entry.getValue(), entry.getKey());
        }
        return reverseDictionary;
    }

    public byte[] compressFile(String inputFilePath) {
        try (FileInputStream fileInputStream = new FileInputStream(inputFilePath);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            ByteArrayOutputStream inputByteArray = new ByteArrayOutputStream();
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                inputByteArray.write(buffer, 0, bytesRead);
            }

            byte[] inputData = inputByteArray.toByteArray();
            compress(inputData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return compressedData.toByteArray();
    }

    public void decompressFile(byte[] compressedData, String outputFilePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

            byte[] decompressedBytes = decompress(compressedData);
            bufferedOutputStream.write(decompressedBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LZW lzw = new LZW();

        // Example usage: compress and decompress a text file
        String inputFile = "text.use";
        String compressedFile = "compressed.lzw";
        String decompressedFile = "decompressed.txt";

        byte[] compressedData = lzw.compressFile(inputFile);
        lzw.decompressFile(compressedData, decompressedFile);
    }
}
