package de.hawhamburg.hamann.ad.trees.impl;
/*
Copyright This class is Created and Programmed by:KALPRATEEK
*/
import de.hawhamburg.hamann.ad.trees.BinarySearchTree;

import java.util.NoSuchElementException;

public class BSTree<Key extends Comparable<Key>, Element> implements BinarySearchTree<Key, Element> {

    private Node<Key, Element> root;

    @Override
    public void insert(Key key, Element e) {
        root = insertRecursive(root, key, e);
    }

    private Node<Key, Element> insertRecursive(Node<Key, Element> node, Key key, Element e) {
        if (node == null) {
            return new Node<>(key, e);
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            node.left = insertRecursive(node.left, key, e);
        } else if (compareResult > 0) {
            node.right = insertRecursive(node.right, key, e);
        } else {
            // Duplicate key, update the element
            node.element = e;
        }

        return node;
    }

    @Override
    public void remove(Key key) throws NoSuchElementException {
        root = removeRecursive(root, key);
    }

    private Node<Key, Element> removeRecursive(Node<Key, Element> node, Key key) throws NoSuchElementException {
        if (node == null) {
            throw new NoSuchElementException("Key not found");
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            node.left = removeRecursive(node.left, key);
        } else if (compareResult > 0) {
            node.right = removeRecursive(node.right, key);
        } else {
            // Node to be deleted found

            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Node with two children, get the inorder successor (smallest in the right subtree)
            node.key = minValue(node.right);
            node.right = removeRecursive(node.right, node.key);
        }

        return node;
    }

    private Key minValue(Node<Key, Element> node) {
        Key minValue = node.key;
        while (node.left != null) {
            minValue = node.left.key;
            node = node.left;
        }
        return minValue;
    }

    @Override
    public Element get(Key key) throws NoSuchElementException {
        return getRecursive(root, key);
    }

    private Element getRecursive(Node<Key, Element> node, Key key) throws NoSuchElementException {
        if (node == null) {
            throw new NoSuchElementException("Key not found");
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            return getRecursive(node.left, key);
        } else if (compareResult > 0) {
            return getRecursive(node.right, key);
        } else {
            // Key found
            return node.element;
        }
    }

    @Override
    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(Node<Key, Element> node) {
        return (node == null) ? 0 : 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }

    @Override
    public boolean contains(Key key) {
        return containsRecursive(root, key);
    }

    private boolean containsRecursive(Node<Key, Element> node, Key key) {
        if (node == null) {
            return false;
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            return containsRecursive(node.left, key);
        } else if (compareResult > 0) {
            return containsRecursive(node.right, key);
        } else {
            // Key found
            return true;
        }
    }
    private static class Node<K, E> {
        private K key;
        private E element;
        private Node<K, E> left;
        private Node<K, E> right;

        public K getKey() {
            return key;
        }

        public E getElement() {
            return element;
        }

        public Node<K, E> getLeft() {
            return left;
        }

        public Node<K, E> getRight() {
            return right;
        }

        public Node(K key, E element) {
            this.key = key;
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }
    // Helper method to print the tree structure
    // Helper method to print the tree structure
    // Helper method to print the tree structure
    // Helper method to print the tree structure
    // Helper method to print the tree structure

    public static void main(String[] args) {
        // Create a binary search tree of Integer keys and String elements
        BSTree<Integer, String> bst = new BSTree<>();

        // Insert elements
        bst.insert(5, "Five");
        bst.insert(3, "Three");
        bst.insert(7, "Seven");
        bst.insert(2, "Two");
        bst.insert(4, "Four");
        bst.insert(6, "Six");
        bst.insert(8, "Eight");

        // Print the size of the tree
        System.out.println("Size of the tree: " + bst.size());

        // Check if the tree contains a specific key
        System.out.println("Contains key 4: " + bst.contains(4));
        System.out.println("Contains key 9: " + bst.contains(9));

        // Retrieve and print the element associated with a key
        try {
            String element = bst.get(6);
            System.out.println("Element at key 6: " + element);
        } catch (NoSuchElementException e) {
            System.out.println("Key 6 not found");
        }
        bst.printInOrder();
        bst.printPreOrder();
        bst.printPostOrder();
    }
    public void printInOrder() {
        System.out.print("In-Order Traversal: ");
        inOrderTraversal(root);
        System.out.println();
    }

    public void printPreOrder() {
        System.out.print("Pre-Order Traversal: ");
        preOrderTraversal(root);
        System.out.println();
    }

    public void printPostOrder() {
        System.out.print("Post-Order Traversal: ");
        postOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node<Key, Element> node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(node.getElement() + " ");
            inOrderTraversal(node.getRight());
        }
    }

    private void preOrderTraversal(Node<Key, Element> node) {
        if (node != null) {
            System.out.print(node.getElement() + " ");
            preOrderTraversal(node.getLeft());
            preOrderTraversal(node.getRight());
        }
    }

    private void postOrderTraversal(Node<Key, Element> node) {
        if (node != null) {
            postOrderTraversal(node.getLeft());
            postOrderTraversal(node.getRight());
            System.out.print(node.getElement() + " ");
        }
    }
    // Inside the BSTree class
    public String getInOrderTraversal() {
        StringBuilder result = new StringBuilder();
        inOrderTraversalForTest(root, result);
        return result.toString();
    }

    public String getPreOrderTraversal() {
        StringBuilder result = new StringBuilder();
        preOrderTraversalForTest(root, result);
        return result.toString();
    }

    public String getPostOrderTraversal() {
        StringBuilder result = new StringBuilder();
        postOrderTraversalForTest(root, result);
        return result.toString();
    }

    private void inOrderTraversalForTest(Node<Key, Element> node, StringBuilder result) {
        if (node != null) {
            inOrderTraversalForTest(node.getLeft(), result);
            result.append(node.getElement()).append(" ");
            inOrderTraversalForTest(node.getRight(), result);
        }
    }

    private void preOrderTraversalForTest(Node<Key, Element> node, StringBuilder result) {
        if (node != null) {
            result.append(node.getElement()).append(" ");
            preOrderTraversalForTest(node.getLeft(), result);
            preOrderTraversalForTest(node.getRight(), result);
        }
    }

    private void postOrderTraversalForTest(Node<Key, Element> node, StringBuilder result) {
        if (node != null) {
            postOrderTraversalForTest(node.getLeft(), result);
            postOrderTraversalForTest(node.getRight(), result);
            result.append(node.getElement()).append(" ");
        }
    }


    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node<Key, Element> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }


    public int getPathLength(Key key) {
        return getPathLength(root, key, 0);
    }

    private int getPathLength(Node node, Key key, int currentDepth) {
        if (node == null) {
            // Key not found
            return -1;
        }


        int compareResult = key.compareTo((Key) node.getKey()); // casting required here object cant be key.

        if (compareResult < 0) {
            // Key is in the left subtree
            return 1 + getPathLength(node.left, key, currentDepth + 1);
        } else if (compareResult > 0) {
            // Key is in the right subtree
            return 1 + getPathLength(node.right, key, currentDepth + 1);
        } else {
            // Key found at the current node
            return currentDepth;
        }
    }


}
