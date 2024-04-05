package de.hawhamburg.hamann.ad.sortingworkbench;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataRetrieverTest {

    @Test
    void createRandomIntegerList() {
        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(200);

        assertEquals(randomInts.size(), 200);
    }

    @Test
    void createOrderedIntegerList() {
        DataRetriever r = new DataRetriever();
        List<Integer> list = r.createOrderedIntegerList(150);
        assertEquals(list.size(), 150);

        for (int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i - 1) <= list.get(i), "List is not ordered");
        }
    }

    @Test
    void createReverseOrderedIntegerList() {
        DataRetriever r = new DataRetriever();
        List<Integer> list = r.createReverseOrderedIntegerList(150);
        assertEquals(list.size(), 150);

        for (int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i - 1) >= list.get(i), "List is not in reverse order!");
        }
    }
}