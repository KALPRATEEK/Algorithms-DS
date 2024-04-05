class BinaryTree<D> implements Comparable<BinaryTree<HuffNode>> {
    D data;
    BinaryTree<D> left;
    BinaryTree<D> right;

    public BinaryTree(D data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public static <D> BinaryTree<D> makeTree(D data) {
        BinaryTree<D> tree = new BinaryTree<>(data);
        tree.data = data;
        return tree;
    }

    public static <D> BinaryTree<D> makeTree(D data, BinaryTree<D> left, BinaryTree<D> right) {
        BinaryTree<D> tree = new BinaryTree<>(data);
        tree.left = left;
        tree.right = right;
        return tree;
    }
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(BinaryTree<HuffNode> o) {
        return 0;
    }
}
