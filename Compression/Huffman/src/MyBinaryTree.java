


import java.util.function.Consumer;

public class MyBinaryTree<HuffNode> implements BinaryTree<HuffNode> {

    public Node<HuffNode> root;

    public MyBinaryTree() {
        this.root = null;
    }

    public MyBinaryTree(HuffNode data) {
        this.root = new Node<>(data);
    }

    @Override
    public HuffNode getData() {
        return (root != null) ? root.data : null;
    }

    @Override
    public void setData(HuffNode data) {
        if (this.root == null) {
            this.root = new Node<>(data);
        } else {
            this.root.data = data;
        }
    }

    @Override
    public BinaryTree<HuffNode> getLeftNode() {
        if (root != null && root.Left != null) {
            MyBinaryTree<HuffNode> leftTree = new MyBinaryTree<>();
            leftTree.root = root.Left;
            return leftTree;
        }
        return null;
    }

    @Override
    public BinaryTree<HuffNode> getRightNode() {
        if (root != null && root.Right != null) {
            MyBinaryTree<HuffNode> rightTree = new MyBinaryTree<>();
            rightTree.root = root.Right;
            return rightTree;
        }
        return null;
    }

    @Override
    public boolean isLeaf() {
        return root != null && getLeftNode() == null && getRightNode() == null;
    }

    public Node<HuffNode> getRoot() {
        return root;
    }

    public void visitPreOrder(Consumer<BinaryTree<HuffNode>> visitor) {
        visitPreOrder(root, visitor);
    }

    private void visitPreOrder(Node<HuffNode> node, Consumer<BinaryTree<HuffNode>> visitor) {
        if (node != null) {
            visitor.accept(new MyBinaryTree<>(node.data));
            visitPreOrder(node.Left, visitor);
            visitPreOrder(node.Right, visitor);
        }
    }

    @Override
    public void visitInOrder(Consumer<BinaryTree<HuffNode>> visitor) {
        visitInOrder(root, visitor);
    }

    private void visitInOrder(Node<HuffNode> node, Consumer<BinaryTree<HuffNode>> visitor) {
        if (node != null) {
            visitInOrder(node.Left, visitor);
            visitor.accept(new MyBinaryTree<>(node.data));
            visitInOrder(node.Right, visitor);
        }
    }

    @Override
    public void visitPostOrder(Consumer<BinaryTree<HuffNode>> visitor) {
        visitPostOrder(root, visitor);
    }

    private void visitPostOrder(Node<HuffNode> node, Consumer<BinaryTree<HuffNode>> visitor) {
        if (node != null) {
            visitPostOrder(node.Left, visitor);
            visitPostOrder(node.Right, visitor);
            visitor.accept(new MyBinaryTree<>(node.data));
        }
    }


    public void setRoot(Node<HuffNode> root) {
        this.root = root;
    }
}
