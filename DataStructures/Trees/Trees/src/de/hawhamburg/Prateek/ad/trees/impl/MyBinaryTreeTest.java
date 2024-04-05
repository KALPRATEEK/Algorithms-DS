package de.hawhamburg.hamann.ad.trees.impl;
/*
Copyright This class is Created and Programmed by:KALPRATEEK
*/
import de.hawhamburg.hamann.ad.trees.BinaryTree;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class MyBinaryTreeTest {

    private MyBinaryTree<Data> tree;
    Node<Data> n;
    Data d;
    Data f;

    @Before
    public void setUp() {
        // Initialize a new tree before each test
        tree = new MyBinaryTree<>();
        n = new Node<>(d);
    }

    @Test
    public void testGetData() {
        assertNull(tree.getData()); // An empty tree should return null for getData
        tree.setData(d);
        assertEquals(d, tree.getData());
    }

    @Test
    public void testSetData() {
        tree.setData(d);
        assertEquals(d, tree.getData());
        tree.setData(f);
        assertEquals(f, tree.getData());
    }

    @Test
    public void testGetLeftNode() {
        // Ensure that the left node is not null before setting data
        // Ensure that the left node is not null before setting data
        if (tree.getLeftNode() != null) {
            assertNull(tree.getLeftNode().getData()); // Check if data is null
            tree.getLeftNode().setData(5);
            assertNotNull(tree.getLeftNode());
            assertEquals(Integer.valueOf(5), tree.getLeftNode().getData());
        }
    }

    @Test
    public void testGetRightNode() {
        // Ensure that the Right node is not null before setting data
        // Ensure that the Right node is not null before setting data
        if (tree.getRightNode() != null) {
            assertNull(tree.getRightNode().getData()); // Check if data is null
            tree.getRightNode().setData(5);
            assertNotNull(tree.getRightNode());// now as we set data it should not be null
            assertEquals(Integer.valueOf(5), tree.getRightNode().getData()); // checking the setted value
        }


    }


    @Test
    public void testIsLeafForNonEmptyTree() {
        MyBinaryTree<Integer> tree = new MyBinaryTree<>();
        tree.setData(42); // Add data to the root
        assertTrue("A tree with only the root should be considered a leaf", tree.isLeaf());

        // You can add more nodes or test cases for a non-empty tree structure
    }

    @Test
    public void testIsLeafForEmptyTree() {
        MyBinaryTree<Integer> tri = new MyBinaryTree<>();
        assertFalse("An empty tree should not be considered a leaf", tree.isLeaf());
    }

    @Test
    public void testVisitPreOrder() {
        MyBinaryTree<Integer> tree = createSampleTree();

        List<Integer> visitedNodes = new ArrayList<>();
        tree.visitPreOrder(new Consumer<BinaryTree<Integer>>() {
            @Override
            public void accept(de.hawhamburg.hamann.ad.trees.BinaryTree<Integer> node) {
                visitedNodes.add(node.getData());
            }
        });

        List<Integer> expectedOrder = List.of(1, 2, 4, 5, 3, 6, 7);
        assertEquals(expectedOrder, visitedNodes);
    }

    @Test
    public void testVisitInOrder() {
        MyBinaryTree<Integer> tree = createSampleTree();

        List<Integer> visitedNodes = new ArrayList<>();
        tree.visitInOrder(new Consumer<de.hawhamburg.hamann.ad.trees.BinaryTree<Integer>>() {
            @Override
            public void accept(de.hawhamburg.hamann.ad.trees.BinaryTree<Integer> node) {
                visitedNodes.add(node.getData());
            }
        });

        List<Integer> expectedOrder = List.of(4, 2, 5, 1, 6, 3, 7);
        assertEquals(expectedOrder, visitedNodes);
    }

    /**
     * Tests the visitPostOrder method of MyBinaryTree.
     * This method ensures that the post-order traversal of the tree is correct,
     * and that the Consumer correctly processes each visited node.
     */
    @Test
    public void testVisitPostOrder() {
        // Create a sample binary tree
        MyBinaryTree<Integer> tree = createSampleTree();

        // List to store the visited nodes in post-order
        List<Integer> visitedNodes = new ArrayList<>();

        // Apply the visitPostOrder method with a Consumer
        tree.visitPostOrder(new Consumer<de.hawhamburg.hamann.ad.trees.BinaryTree<Integer>>() {
            /**
             * Implementation of the accept method for the Consumer.
             * Adds the data of each visited node to the visitedNodes list.
             *
             * @param node The visited node during the traversal.
             */
            @Override
            public void accept(de.hawhamburg.hamann.ad.trees.BinaryTree<Integer> node) {
                visitedNodes.add(node.getData());
            }
        });

        // Expected order of visited nodes in post-order
        List<Integer> expectedOrder = List.of(4, 5, 2, 6, 7, 3, 1);

        // Assert that the visited nodes match the expected order
        assertEquals(expectedOrder, visitedNodes);
    }

    private MyBinaryTree<Integer> createSampleTree() {
        MyBinaryTree<Integer> tree = new MyBinaryTree<>();
        tree.setData(1);
        tree.root.Left = createTreeNode(2);
        tree.root.Right = createTreeNode(3);
        tree.root.Left.Left = createTreeNode(4);
        tree.root.Left.Right = createTreeNode(5);
        tree.root.Right.Left = createTreeNode(6);
        tree.root.Right.Right = createTreeNode(7);
        return tree;
    }

    private Node<Integer> createTreeNode(int data) {
        Node<Integer> node = new Node<>(data);
        node.data = data;
        return node;
    }
}




