package de.hawhamburg.hamann.ad.trees.impl;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
/*
Copyright This class is Created and Programmed by:KALPRATEEK
*/
public class BSTreeTest {

    @Test
    public void testInsert() {
        BSTree<Integer, String> bst = new BSTree<>();
        bst.insert(5, "Five");
        bst.insert(3, "Three");
        bst.insert(7, "Seven");

        assertEquals(3, bst.size());
        assertTrue(bst.contains(5));
        assertFalse(bst.contains(2));
    }

    @Test
    public void testRemove() {
        BSTree<Integer, String> bst = new BSTree<>();
        bst.insert(5, "Five");
        bst.insert(3, "Three");
        bst.insert(7, "Seven");

        bst.remove(3);

        assertEquals(2, bst.size());
        assertFalse(bst.contains(3));
    }

    @Test
    public void testGet() {
        BSTree<Integer, String> bst = new BSTree<>();
        bst.insert(5, "Five");
        bst.insert(3, "Three");
        bst.insert(7, "Seven");

        assertEquals("Three", bst.get(3));
        try {
            assertNull(bst.get(2));
        }
        catch (NoSuchElementException e){
            e.getMessage();
        }

    }

    @Test
    public void testSize() {
        BSTree<Integer, String> bst = new BSTree<>();
        bst.insert(5, "Five");
        bst.insert(3, "Three");
        bst.insert(7, "Seven");

        assertEquals(3, bst.size());

        bst.remove(3);

        assertEquals(2, bst.size());
    }

    @Test
    public void testTraversals() {
        BSTree<Integer, String> bst = new BSTree<>();
        bst.insert(5, "Five");
        bst.insert(3, "Three");
        bst.insert(7, "Seven");
        bst.insert(2, "Two");
        bst.insert(4, "Four");
        bst.insert(6, "Six");
        bst.insert(8, "Eight");

        assertEquals("Two Three Four Five Six Seven Eight ", bst.getInOrderTraversal());
        assertEquals("Five Three Two Four Seven Six Eight ", bst.getPreOrderTraversal());
        assertEquals("Two Four Three Six Eight Seven Five ", bst.getPostOrderTraversal());
    }
}
