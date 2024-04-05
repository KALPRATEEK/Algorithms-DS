package de.hawhamburg.hamann.ad.sortingworkbench;


import de.hawhamburg.hamann.ad.sortingworkbench.sorter.HybridQuickInsertionSorter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HybridTest {

    private HybridQuickInsertionSorter Hybrid;
    private SortingMetrics metrics_rev;
    private SortingMetrics metrics_ran;
    private SortingMetrics metrics_par;


    @Before
    public void setUp() {
       Hybrid= new HybridQuickInsertionSorter();
        metrics_rev=new SortingMetrics(SortingMetrics.ListType.REVERSE_ORDERED);
        metrics_ran=new SortingMetrics(SortingMetrics.ListType.RANDOM);
        metrics_par=new SortingMetrics(SortingMetrics.ListType.PARTIAL_ORDERED);

    }

    @Test
    public void testInsertion_rev() {
        List<Integer> toSort = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));

        Hybrid.sort(toSort, metrics_rev);

        // Assert that the list is sorted
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, toSort.toArray());
        System.out.println("testing of "+ Hybrid.getName()+"with a list type of"+" "+ metrics_rev.getListType()+" "+"Successfull");
    }


    @Test
    public void testInsetion_ran() {
        List<Integer> toSort = new ArrayList<>(Arrays.asList(2,4,1,5,3));

        Hybrid.sort(toSort, metrics_ran);

        // Assert that the list is sorted
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, toSort.toArray());
        System.out.println("testing of "+Hybrid.getName()+"with a list type of"+" "+ metrics_ran.getListType()+" "+"Successfull");
    }

    @Test
    public void testInsertion_par() {
        List<Integer> toSort = new ArrayList<>(Arrays.asList(1,2,3,5,4));

        Hybrid.sort(toSort, metrics_par);

        // Assert that the list is sorted
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, toSort.toArray());
        System.out.println("testing of "+Hybrid.getName()+"with a list type of"+" "+ metrics_par.getListType()+" "+"Successfull");
    }

}
