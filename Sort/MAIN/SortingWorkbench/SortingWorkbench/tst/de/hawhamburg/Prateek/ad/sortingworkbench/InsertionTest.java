/*
Completed and Modified by: Prateek Kalra
 */
package de.hawhamburg.hamann.ad.sortingworkbench;

import de.hawhamburg.hamann.ad.sortingworkbench.sorter.BubbleSort;
import de.hawhamburg.hamann.ad.sortingworkbench.SortingMetrics;
import de.hawhamburg.hamann.ad.sortingworkbench.sorter.InsertionSort;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class InsertionTest {

    private InsertionSort InsertionSort;
    private SortingMetrics metrics_rev;
    private SortingMetrics metrics_ran;
    private SortingMetrics metrics_par;


    @Before
    public void setUp() {
       InsertionSort = new InsertionSort();
        metrics_rev=new SortingMetrics(SortingMetrics.ListType.REVERSE_ORDERED);
        metrics_ran=new SortingMetrics(SortingMetrics.ListType.RANDOM);
        metrics_par=new SortingMetrics(SortingMetrics.ListType.PARTIAL_ORDERED);

    }

    @Test
    public void testInsertion_rev() {
        List<Integer> toSort = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));

       InsertionSort.sort(toSort, metrics_rev);

        // Assert that the list is sorted
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, toSort.toArray());
        System.out.println("testing of "+ InsertionSort.getName()+"with a list type of"+" "+ metrics_rev.getListType()+" "+"Successfull");
    }


    @Test
    public void testInsetion_ran() {
        List<Integer> toSort = new ArrayList<>(Arrays.asList(2,4,1,5,3));

        InsertionSort.sort(toSort, metrics_ran);

        // Assert that the list is sorted
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, toSort.toArray());
        System.out.println("testing of "+InsertionSort.getName()+"with a list type of"+" "+ metrics_ran.getListType()+" "+"Successfull");
    }

    @Test
    public void testInsertion_par() {
        List<Integer> toSort = new ArrayList<>(Arrays.asList(1,2,3,5,4));

        InsertionSort.sort(toSort, metrics_par);

        // Assert that the list is sorted
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, toSort.toArray());
        System.out.println("testing of "+InsertionSort.getName()+"with a list type of"+" "+ metrics_par.getListType()+" "+"Successfull");
    }


}
