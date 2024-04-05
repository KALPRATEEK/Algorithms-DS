import com.github.jinahya.bit.io.BitInput;
import com.github.jinahya.bit.io.BitInputFactory;
import com.github.jinahya.bit.io.BitOutput;
import com.github.jinahya.bit.io.BitOutputFactory;

import java.io.*;
import java.util.Stack;
import java.util.*;


public class Huffman {
    static final int chars = 256;
    int[] freq = new int[chars];
    Map<Integer, String> codeTable = new HashMap<>();
    PriorityQueue<MyBinaryTree<HuffNode>> heap = new PriorityQueue<>(Comparator.comparingInt(tree -> tree.getRoot().getData().frequency));
    Stack<Integer> path = new Stack<>();
    MyBinaryTree<HuffNode> root;

    public void calculateCharacterFrequencies(InputStream inputStream) throws IOException {
        int character;
        while ((character = inputStream.read()) != -1) {
            freq[character]++;
        }

        for (int ch = 0; ch < chars; ch++) {
            if (freq[ch] > 0) {
                heap.add(new MyBinaryTree<>(new HuffNode(ch, freq[ch])));
            }
        }
    }

    public void buildHuffmanTree() {
        while (heap.size() > 1) {
            MyBinaryTree<HuffNode> node1 = heap.poll();
            MyBinaryTree<HuffNode> node2 = heap.poll();

            HuffNode newNodeData = new HuffNode(-1, node1.getData().frequency + node2.getData().frequency);
            MyBinaryTree<HuffNode> newNode = new MyBinaryTree<>(newNodeData);
            newNode.getRoot().setLeft(node1.getRoot());
            newNode.getRoot().setRight(node2.getRoot());

            heap.add(newNode);
        }
        root = heap.poll();
    }

    public void calculateCodeFromHuffmanTree() {
        traverseHuffmanTree(root, new StringBuilder());
    }

    private void traverseHuffmanTree(BinaryTree<HuffNode> aTree, StringBuilder currentPath) {
        if (aTree == null) {
            return;
        }

        if (aTree.isLeaf()) {
            char character = (char) aTree.getData().character;
            String code = currentPath.toString();
           // System.out.println(character + "\t" + code);
            codeTable.put(aTree.getData().character, code);
            return;
        }

        currentPath.append('0');
        traverseHuffmanTree(aTree.getLeftNode(), new StringBuilder(currentPath));
        currentPath.deleteCharAt(currentPath.length() - 1);

        currentPath.append('1');
        traverseHuffmanTree(aTree.getRightNode(), new StringBuilder(currentPath));
        currentPath.deleteCharAt(currentPath.length() - 1);
    }

    public void writeCharacterFrequencies(OutputStream outputStream) throws IOException {
        for (int ch = 0; ch < chars; ch++) {
            if (freq[ch] > 0) {
                outputStream.write(ch);
                outputStream.write(freq[ch]);
            }
        }
    }
 /*
    public String encodeDataFile(InputStream inputStream) throws IOException {
        // Build Huffman tree and calculate codes
        buildHuffmanTree();
        calculateCodeFromHuffmanTree();

        StringWriter encodedBits = new StringWriter();
        try {
            int character;
            while ((character = inputStream.read()) != -1) {
                String code = codeTable.get(character);

                // Check if code is null for the current character
                if (code != null) {
                    System.out.println("Character: " + (char) character + ", Huffman Code: " + code);

                    // Append each bit of the Huffman code to the StringWriter
                    encodedBits.append(code);
                } else {
                    // Handle the case when there is no Huffman code for the character
                    System.out.println("Warning: No Huffman code for character " + character);
                }
            }
        } finally {
            // Close the StringWriter here if needed
        }

        return encodedBits.toString();
    }


  */
    // B) Decoding
    public void readCharacterFrequencies() throws IOException {
        int character;
        for (int ch = 0; ch < 256; ch++) {
            if (freq[ch] > 0) {
                heap.add(new MyBinaryTree<>(new HuffNode(ch, freq[ch])));
            }
        }
    }

    public void decodeDataFile(BitInput bitIn, OutputStream outputStream, int totalCount) throws IOException {
        try {
            int count = 0;
            while (count < totalCount) {
                BinaryTree<HuffNode> currentNode = root;

                while (!currentNode.isLeaf()) {
                    boolean bit = bitIn.readBoolean();
                    currentNode = bit ? currentNode.getRightNode() : currentNode.getLeftNode();
             //       System.out.println("Inside while loop, bit: " + bit);
                }

                char decodedCharacter = (char) currentNode.getData().character;
               // System.out.println("Decoded Character: " + decodedCharacter);
                outputStream.write(decodedCharacter);
                count++;
            }
        } catch (EOFException e) {
            // End-of-file reached, no more bits to read
        }
    }

    /*
    public static void main(String[] args) throws IOException {
        Huffman huffman = new Huffman();

        // Step 1: Open the input file for reading
        try (InputStream input = new FileInputStream("List.use")) {
            huffman.calculateCharacterFrequencies(input);
            huffman.buildHuffmanTree();
            huffman.calculateCodeFromHuffmanTree();

            // Print frequencies for debugging
            System.out.println("Character Frequencies:");
            for (int i = 0; i < Huffman.chars; i++) {
                if (huffman.freq[i] > 0) {
                    System.out.println((char) i + ": " + huffman.freq[i]);
                }
            }
            System.out.println("Huffman Tree Built!");
            // Print Huffman codes for debugging
            System.out.println("Huffman Codes:");
            for (Map.Entry<Integer, String> entry : huffman.codeTable.entrySet()) {
                System.out.println((char) (int) entry.getKey() + ": " + entry.getValue());
            }
        }

        // Step 2: Open the input file again for reading
        try (InputStream input = new FileInputStream("List.use")) {
            // Step 3: Encode the input file and collect the encoded bits
            String encodedBits = huffman.encodeDataFile(input);

            // Print the collected encoded bits for debugging
            System.out.println("Encoded Bits: " + encodedBits);

            System.out.println("File encoding completed successfully.");
        }

    }
*/
   //libraries not wokring as expected.
     public void encodeDataFile(InputStream inputStream, BitOutput bitOut) throws IOException {

        try {
            int character;
            while ((character = inputStream.read()) != -1) {
                String code = codeTable.get(character);

                // Check if code is null for the current character
                if (code != null) {
                  //  System.out.println("Character: " + (char) character + ", Huffman Code: " + code);

                    // Write each bit of the Huffman code
                    for (char bit : code.toCharArray()) {
                        // Convert char '0' or '1' to boolean
                        boolean isBitSet = (bit == '1');
                        bitOut.writeBoolean(isBitSet);
                    }
                } else {
                    // Handle the case when there is no Huffman code for the character
                  //  System.out.println("Warning: No Huffman code for character " + character);
                }
            }
        } finally {
            bitOut.align(1);

            // Close the BitOutput stream here
        }
    }

    public static void main(String[] args) throws IOException {
        Huffman huffman = new Huffman();

        // Step 1: Open the input file for reading
        try (InputStream input = new FileInputStream("ad_5.pdf")) {
            huffman.calculateCharacterFrequencies(input);
            huffman.buildHuffmanTree();
            huffman.calculateCodeFromHuffmanTree();

            // Print frequencies for debugging
            System.out.println("Character Frequencies:");
            for (int i = 0; i < Huffman.chars; i++) {
                if (huffman.freq[i] > 0) {
                 //   System.out.println((char) i + ": " + huffman.freq[i]);
                }
            }
            System.out.println("Huffman Tree Built!");

            // Print Huffman codes for debugging
           // System.out.println("Huffman Codes:");
            for (Map.Entry<Integer, String> entry : huffman.codeTable.entrySet()) {
                //System.out.println((char) (int) entry.getKey() + ": " + entry.getValue());
            }
        }

        // Step 2: Open the input file again for reading
        try (InputStream input = new FileInputStream("ad_5.pdf");
             OutputStream compressedOutputStream = new FileOutputStream("compressedFile.bin")) {
            BitOutput bitOutput = BitOutputFactory.from(compressedOutputStream);
            huffman.encodeDataFile(input, bitOutput);
        }

        System.out.println("File encoding completed successfully.");

        // Step 5: Open the compressed file for reading
        try (InputStream compressedInput = new FileInputStream("compressedFile.bin");
             OutputStream decodedOutputStream = new FileOutputStream("decodedFile.pdf")) {
            BitInput bitInput = BitInputFactory.from(compressedInput);
            huffman.readCharacterFrequencies();
            huffman.buildHuffmanTree();
            huffman.decodeDataFile(bitInput, decodedOutputStream, getTotalCountOfCharacters(huffman.freq));
        }

        System.out.println("File decoding completed successfully.");
    }




    private static int getTotalCountOfCharacters(int[] frequencies) {
        int totalCount = 0;
        for (int frequency : frequencies) {
            totalCount += frequency;
        }
        return totalCount;
    }




}
