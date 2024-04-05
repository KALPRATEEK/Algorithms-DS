public class Node<Data> {
    Data data;
    Node<Data> Left;
    Node<Data> Right;

    public Node(Data data) {
        this.data = data;
        this.Left = null;
        this.Right = null;
    }

    public Data getData() {
        return data;
    }

    public Node<Data> getLeft() {
        return Left;
    }

    public Node<Data> getRight() {
        return Right;
    }

    public void setLeft(Node<Data> left) {
        this.Left = left;
    }

    public void setRight(Node<Data> right) {
        this.Right = right;
    }
}
