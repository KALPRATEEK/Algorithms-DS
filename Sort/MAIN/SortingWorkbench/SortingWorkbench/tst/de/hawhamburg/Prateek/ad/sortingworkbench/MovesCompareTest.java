package de.hawhamburg.hamann.ad.sortingworkbench;

import de.hawhamburg.hamann.ad.sortingworkbench.sorter.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MovesCompareTest {

    private BubbleSort bubble;
    private InsertionSort insertion;

    private MergeSort merge;
    private SelectionSort selection;
    private HybridQuickInsertionSorter hybird;
    private QuickSort quick;
    private SortingMetrics metrics_rev;
    private SortingMetrics metrics_ran;
    private SortingMetrics metrics_par;


    @Before
    public void setUp() {
        hybird = new HybridQuickInsertionSorter();
        quick = new QuickSort();
        bubble = new BubbleSort();
        selection=new SelectionSort();
        insertion=new InsertionSort();
        merge=new MergeSort();

        metrics_rev = new SortingMetrics(SortingMetrics.ListType.REVERSE_ORDERED);
        metrics_ran = new SortingMetrics(SortingMetrics.ListType.RANDOM);
        metrics_par = new SortingMetrics(SortingMetrics.ListType.PARTIAL_ORDERED);

    }
    @Test
    public void quickcheck() {

        DataRetriever r = new DataRetriever();

        List<Integer> measure1 = r.createRandomIntegerList(200000);
        List<Integer> measure2 = r.createRandomIntegerList(400000);
        List<Integer> measure3 = r.createRandomIntegerList(600000);
        List<Integer> measure4 = r.createRandomIntegerList(800000);
        List<Integer> measure5 = r.createRandomIntegerList(900000);
        List<Integer> measure6 = r.createRandomIntegerList(2000000);
        List<Integer> measure7 = r.createRandomIntegerList(4000000);
        List<Integer> measure8 = r.createRandomIntegerList(6000000);
        List<Integer> measure9 = r.createRandomIntegerList(7000000);
        List<Integer> measure10 = r.createRandomIntegerList(8000000);
        List<Integer> measure11 = r.createRandomIntegerList(14000000);
        List<Integer> measure12 = r.createRandomIntegerList(16000000);

        long start1 = System.nanoTime() / 1_000;
        quick.sort(measure1, metrics_par);
        long end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 10000 " + quick.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        quick.sort(measure2, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 20000 " + quick.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        quick.sort(measure3, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 30000 " + quick.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        quick.sort(measure4, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 40000 " + quick.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());


        start1 = System.nanoTime() / 1_000;
        quick.sort(measure5, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 50000 " + quick.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        quick.sort(measure6, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 100_000 " + quick.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        quick.sort(measure7, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 200_000 " + quick.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        quick.sort(measure8, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 500_000 " + quick.getName() +"; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        quick.sort(measure9, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 800_000 " + quick.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        quick.sort(measure10, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 1000_000 " + quick.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());



       quick.sort(measure11, metrics_par);
        System.out.println("Elapsed Time in nano seconds: for size 14_000_000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        quick.sort(measure11, metrics_par);
        System.out.println("Elapsed Time in nano seconds: for size 16_000_000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());


        System.out.println("------------------------------------------------------------------------");

    }
    @Test
    public void bubblecheck() {
        DataRetriever r = new DataRetriever();

        List<Integer> measure1 = r.createRandomIntegerList(50000);
        List<Integer> measure2 = r.createRandomIntegerList(100000);
        List<Integer> measure3 = r.createRandomIntegerList(200000);
        List<Integer> measure4 = r.createRandomIntegerList(300000);
        List<Integer> measure5 = r.createRandomIntegerList(400000);
        List<Integer> measure6 = r.createRandomIntegerList(500000);
        List<Integer> measure7 = r.createRandomIntegerList(600000);
        List<Integer> measure8 = r.createRandomIntegerList(800000);
        List<Integer> measure9 = r.createRandomIntegerList(900000);
        List<Integer> measure10 = r.createRandomIntegerList(1000000);

        long start1 = System.nanoTime() / 1_000;
        bubble.sort(measure1, metrics_par);
        long end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 10000 " + bubble.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        bubble.sort(measure2, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 20000 " + bubble.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        bubble.sort(measure3, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 30000 " + bubble.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        bubble.sort(measure4, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 40000 " + bubble.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());


        start1 = System.nanoTime() / 1_000;
        bubble.sort(measure5, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 50000 " + bubble.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        bubble.sort(measure6, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 100_000 " + bubble.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        bubble.sort(measure7, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 200_000 " + bubble.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());


        start1 = System.nanoTime() / 1_000;
        bubble.sort(measure8, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 500_000 " +bubble.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

//        start1 = System.nanoTime() / 1_000;
//        bubble.sort(measure9, metrics_par);
//        end1 = System.nanoTime() / 1_000;
//        System.out.println("Elapsed Time in nano seconds: for size 800_000 " + bubble.getName() + "  " + (end1 - start1));
//
//        start1 = System.nanoTime() / 1_000;
//        bubble.sort(measure10, metrics_par);
//        end1 = System.nanoTime() / 1_000;
//        System.out.println("Elapsed Time in nano seconds: for size 1000_000 " + bubble.getName() + "  " + (end1 - start1));
        System.out.println("------------------------------------------------------------------------");
    }
    @Test
    public void insertionCheck() {
        DataRetriever r = new DataRetriever();

        List<Integer> measure1 = r.createRandomIntegerList(50000);
        List<Integer> measure2 = r.createRandomIntegerList(100000);
        List<Integer> measure3 = r.createRandomIntegerList(200000);
        List<Integer> measure4 = r.createRandomIntegerList(300000);
        List<Integer> measure5 = r.createRandomIntegerList(400000);
        List<Integer> measure6 = r.createRandomIntegerList(500000);
        List<Integer> measure7 = r.createRandomIntegerList(600000);
        List<Integer> measure8 = r.createRandomIntegerList(800000);
        List<Integer> measure9 = r.createRandomIntegerList(900000);
        List<Integer> measure10 = r.createRandomIntegerList(1000000);

        long start1 = System.nanoTime() / 1_000;
        insertion.sort(measure1, metrics_par);
        long end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 10000 " + insertion.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        insertion.sort(measure2, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 20000 " + insertion.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        insertion.sort(measure3, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 30000 " + insertion.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        insertion.sort(measure4, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 40000 " + insertion.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());


        start1 = System.nanoTime() / 1_000;
        insertion.sort(measure5, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 50000 " + insertion.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        insertion.sort(measure6, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 100_000 " + insertion.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        insertion.sort(measure7, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 200_000 " +insertion.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());


        start1 = System.nanoTime() / 1_000;
        insertion.sort(measure8, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 500_000 " +insertion.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());
//        start1 = System.nanoTime() / 1_000;
//        insertion.sort(measure9, metrics_par);
//        end1 = System.nanoTime() / 1_000;
//        System.out.println("Elapsed Time in nano seconds: for size 800_000 " + insertion.getName() + "  " + (end1 - start1));
//
//        start1 = System.nanoTime() / 1_000;
//        insertion.sort(measure10, metrics_par);
//        end1 = System.nanoTime() / 1_000;
//        System.out.println("Elapsed Time in nano seconds: for size 1000_000 " +insertion.getName() + "  " + (end1 - start1));

        System.out.println("------------------------------------------------------------------------");
    }
    @Test
    public void mergeCheck() {
        DataRetriever r = new DataRetriever();

        List<Integer> measure1 = r.createRandomIntegerList(200000);
        List<Integer> measure2 = r.createRandomIntegerList(400000);
        List<Integer> measure3 = r.createRandomIntegerList(600000);
        List<Integer> measure4 = r.createRandomIntegerList(800000);
        List<Integer> measure5 = r.createRandomIntegerList(900000);
        List<Integer> measure6 = r.createRandomIntegerList(2000000);
        List<Integer> measure7 = r.createRandomIntegerList(4000000);
        List<Integer> measure8 = r.createRandomIntegerList(6000000);
        List<Integer> measure9 = r.createRandomIntegerList(7000000);
        List<Integer> measure10 = r.createRandomIntegerList(8000000);
        List<Integer> measure11 = r.createRandomIntegerList(14000000);
        List<Integer> measure12 = r.createRandomIntegerList(16000000);
        long start1 = System.nanoTime() / 1_000;
        merge.sort(measure1, metrics_par);
        long end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 10000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        merge.sort(measure2, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 20000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        merge.sort(measure3, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 30000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());
        start1 = System.nanoTime() / 1_000;
        merge.sort(measure4, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 40000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());


        start1 = System.nanoTime() / 1_000;
        merge.sort(measure5, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 50000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        merge.sort(measure6, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 100_000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        merge.sort(measure7, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 200_000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());


        start1 = System.nanoTime() / 1_000;
        merge.sort(measure8, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 500_000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        merge.sort(measure9, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 800_000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        merge.sort(measure10, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 1000_000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());


        merge.sort(measure11, metrics_par);
        System.out.println("Elapsed Time in nano seconds: for size 14_000_000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        merge.sort(measure11, metrics_par);
        System.out.println("Elapsed Time in nano seconds: for size 16_000_000 " + merge.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:"+ metrics_par.getNumCompares());

        System.out.println("------------------------------------------------------------------------");
    }
    @Test
    public void selectionCheck() {
        DataRetriever r = new DataRetriever();

        List<Integer> measure1 = r.createRandomIntegerList(50000);
        List<Integer> measure2 = r.createRandomIntegerList(100000);
        List<Integer> measure3 = r.createRandomIntegerList(200000);
        List<Integer> measure4 = r.createRandomIntegerList(300000);
        List<Integer> measure5 = r.createRandomIntegerList(400000);
        List<Integer> measure6 = r.createRandomIntegerList(500000);
        List<Integer> measure7 = r.createRandomIntegerList(600000);
        List<Integer> measure8 = r.createRandomIntegerList(800000);
        List<Integer> measure9 = r.createRandomIntegerList(900000);
        List<Integer> measure10 = r.createRandomIntegerList(1000000);

        long start1 = System.nanoTime() / 1_000;
        selection.sort(measure1, metrics_par);
        long end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 10000 " + selection.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:" + metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        selection.sort(measure2, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 20000 " + selection.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:" + metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        selection.sort(measure3, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 30000 " + selection.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:" + metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        selection.sort(measure4, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 40000 " + selection.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:" + metrics_par.getNumCompares());


        start1 = System.nanoTime() / 1_000;
        selection.sort(measure5, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 50000 " + selection.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:" + metrics_par.getNumCompares());

        start1 = System.nanoTime() / 1_000;
        selection.sort(measure6, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 100_000 " + selection.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:" + metrics_par.getNumCompares());
/*
        start1 = System.nanoTime() / 1_000;
        selection.sort(measure7, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 200_000 " + selection.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:" + metrics_par.getNumCompares());


        start1 = System.nanoTime() / 1_000;
        selection.sort(measure8, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 500_000 " + selection.getName() + "; MOVE: " + metrics_par.getNumMoves() + "; COMPARE:" + metrics_par.getNumCompares());
*/
   /*     start1 = System.nanoTime() / 1_000;
        selection.sort(measure9, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 800_000 " + selection.getName() + "  " + (end1 - start1));

        start1 = System.nanoTime() / 1_000;
        selection.sort(measure10, metrics_par);
        end1 = System.nanoTime() / 1_000;
        System.out.println("Elapsed Time in nano seconds: for size 1000_000 " + selection.getName() + "  " + (end1 - start1));*/
        System.out.println("------------------------------------------------------------------------");
    }

}
