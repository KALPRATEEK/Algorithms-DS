import com.sun.source.tree.BinaryTree;

class HuffNode {
    int character;
    int frequency;
    BinaryTree left;
    BinaryTree right;

    public HuffNode(int character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    // Additional constructor for internal nodes
    public HuffNode(int frequency, BinaryTree left, BinaryTree right) {
        this.character = 0;  // Set a default value for internal nodes
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}

