import com.github.jinahya.bit.io.BitInput;
import com.github.jinahya.bit.io.BitOutput;
import com.github.jinahya.bit.io.BitOutputFactory;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Huffman {
    static final int chars = 256;
    int[] freq = new int[chars];
    Map<Integer, String> codeTable = new HashMap<>();
    PriorityQueue<BinaryTree<HuffNode>> heap = new PriorityQueue<>(Comparator.comparingInt(tree -> tree.data.frequency));
    Stack<Integer> path = new Stack<>();
    BinaryTree<HuffNode> root;

    /**
     * A) Encoding
     */
    public void calculateCharacterFrequencies(InputStream inputStream) throws IOException {
        int character;
        while ((character = inputStream.read()) != -1) {
            freq[character]++;
        }
        for (int ch = 0; ch < chars; ch++) {
            if (freq[ch] > 0) {
                heap.add(new BinaryTree<>(new HuffNode(ch, freq[ch])));
            }
        }
    }

    public void buildHuffmanTree() {
        while (heap.size() > 1) {
            BinaryTree<HuffNode> node1 = heap.poll();
            BinaryTree<HuffNode> node2 = heap.poll();
            heap.add(new BinaryTree<>(new HuffNode(-1, node1.data.frequency + node2.data.frequency)));
        }
        root = heap.poll();
    }

    public void calculateCodeFromHuffmanTree() {
        traverseHuffmanTree(root, new StringBuilder());
    }

    private void traverseHuffmanTree(BinaryTree<HuffNode> aTree, StringBuilder currentPath) {
        if (aTree.isLeaf()) {
            codeTable.put(aTree.data.character, currentPath.toString());
            return;
        }

        currentPath.append('0');
        traverseHuffmanTree(aTree.left, currentPath);
        currentPath.deleteCharAt(currentPath.length() - 1);

        currentPath.append('1');
        traverseHuffmanTree(aTree.right, currentPath);
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

    public void encodeDataFile(InputStream inputStream, BitOutput bitOut) throws IOException {
        int character;
        while ((character = inputStream.read()) != -1) {
            String code = codeTable.get(character);
            for (char bit : code.toCharArray()) {
                // Convert char '0' or '1' to boolean
                boolean isBitSet = (bit == '1');
                bitOut.writeBoolean(isBitSet);
            }
        }
        bitOut.align(1);
    }

    /*
     * B) Decoding
     */
    public void readCharacterFrequencies(InputStream inputStream) throws IOException {
        int character;
        while ((character = inputStream.read()) != -1) {
            int frequency = inputStream.read();
            freq[character] = frequency;
        }
        for (int ch = 0; ch < chars; ch++) {
            if (freq[ch] > 0) {
                HuffNode huffNode = new HuffNode(ch, freq[ch]);
                heap.add(new BinaryTree<>(huffNode));
            }
        }
    }

    public void decodeDataFile(BitInput bitIn, OutputStream outputStream) throws IOException {
        try {
            while (true) {
                BinaryTree<HuffNode> currentNode = root;

                while (!currentNode.isLeaf()) {
                    boolean bit = bitIn.readBoolean();  // Assuming readBoolean for reading a bit
                    currentNode = bit ? currentNode.right : currentNode.left;
                }

                outputStream.write(currentNode.data.character);
            }
        } catch (EOFException e) {
            // End-of-file reached, no more bits to read
        }
    }


    public static void main(String[] args) {
        try(OutputStream output = new FileOutputStream("sfs")) {
            BitOutput bitOut = BitOutputFactory.from(output);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
