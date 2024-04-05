import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainLZWCompression {

    public static void main(String[] args) {
        // Specify the path to the input file
        String inputFile = "test.use";

        // Specify the path for the compressed output file
        String outputFile = "Encode.txt";

        try {
            // Read the content of the input file
            String fileContent = readFileContent(inputFile);

            // Compress the file content using LZW compression with BinarySearchTree
            String compressedResult = LZWCompression.compressLZWWithBinarySearchTree(fileContent);

            // Write the compressed result to the output file
            writeToFile(outputFile, compressedResult);

            System.out.println("File compression completed. Compressed file saved at: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] fileBytes = Files.readAllBytes(path);
        return new String(fileBytes);
    }

    private static void writeToFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, content.getBytes());
    }
}
