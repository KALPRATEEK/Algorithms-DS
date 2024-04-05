import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class BinarySearchTree<Key extends Comparable<Key>, Element> implements BInary<Key, Element> {

    private Node<Key, Element> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public void insert(Key key, Element e) {
        root = insert(root, key, e);


    }

    private Node<Key, Element> insert(Node<Key, Element> node, Key key, Element e) {
        if (node == null) {
            size++;
            return new Node<>(key, e);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, e);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, e);
        } else {
            node.element = e;
        }

        return node;
    }

    @Override
    public void remove(Key key) throws NoSuchElementException {
        root = remove(root, key);
    }

    private Node<Key, Element> remove(Node<Key, Element> node, Key key) throws NoSuchElementException {
        if (node == null) {
            throw new NoSuchElementException("Key not found");
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            size--;
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node<Key, Element> temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }

    private Node<Key, Element> min(Node<Key, Element> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<Key, Element> deleteMin(Node<Key, Element> node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    @Override
    public Element get(Key key) throws NoSuchElementException {
        Node<Key, Element> result = get(root, key);
        if (result == null) {
            throw new NoSuchElementException("Key not found");
        }
        return result.element;
    }

    private Node<Key, Element> get(Node<Key, Element> node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Key key) {
        return get(root, key) != null;
    }

    private static class Node<Key extends Comparable<Key>, Element> {
        private Key key;
        private Element element;
        private Node<Key, Element> left;
        private Node<Key, Element> right;

        public Node(Key key, Element element) {
            this.key = key;
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }
    public Key getKey(Element element) {
        return getKey(root, element);
    }

    private Key getKey(Node<Key, Element> node, Element element) {
        if (node == null) {
            return null;
        }

        if (element.equals(node.element)) {
            return node.key;
        }

        Key leftKey = getKey(node.left, element);
        if (leftKey != null) {
            return leftKey;
        }

        return getKey(node.right, element);
    }


    public boolean containsValue(Element element) {
        return containsValue(root, element);
    }

    private boolean containsValue(Node<Key, Element> node, Element element) {
        if (node == null) {
            return false;
        }

        if (element.equals(node.element)) {
            return true;
        }

        return containsValue(node.left, element) || containsValue(node.right, element);
    }

}
