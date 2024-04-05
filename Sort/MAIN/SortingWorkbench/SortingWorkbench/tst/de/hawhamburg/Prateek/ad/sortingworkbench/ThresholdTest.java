package de.hawhamburg.hamann.ad.sortingworkbench;

import de.hawhamburg.hamann.ad.sortingworkbench.sorter.HybridQuickInsertionSorter;

import de.hawhamburg.hamann.ad.sortingworkbench.sorter.QuickSort;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ThresholdTest {


    private HybridQuickInsertionSorter hybird;
    private QuickSort quick;
    private SortingMetrics metrics_rev;
    private SortingMetrics metrics_ran;
    private SortingMetrics metrics_par;


    @Before
    public void setUp() {
       hybird =new HybridQuickInsertionSorter();
       quick =new QuickSort();
        metrics_rev = new SortingMetrics(SortingMetrics.ListType.REVERSE_ORDERED);
        metrics_ran = new SortingMetrics(SortingMetrics.ListType.RANDOM);
        metrics_par = new SortingMetrics(SortingMetrics.ListType.PARTIAL_ORDERED);

    }

    public void quick1000List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(1000);

        long start1 = System.nanoTime();
        quick.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 1000 " + quick.getName()+ "  "+ (end1 - start1));

    }


    public void hybird1000List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(1000);

        long start1 = System.nanoTime();
        hybird.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 1000 " + hybird.getName() + "  " + (end1 - start1));
        System.out.println("------------------");

    }

    @Test
    public void quick10000List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(10000);

        long start1 = System.nanoTime();
        quick.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 10000 " + quick.getName()+ "  "+ (end1 - start1));

    }

    @Test
    public void hybird10000List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(10000);

        long start1 = System.nanoTime();
        hybird.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 10000 " + hybird.getName() + "  " + (end1 - start1));
        System.out.println("------------------");
    }


    @Test
    public void quick100000List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(100000);

        long start1 = System.nanoTime();
        quick.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 100000 " + quick.getName()+ "  "+ (end1 - start1));

    }

    @Test
    public void hybird100000List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(100000);

        long start1 = System.nanoTime();
        hybird.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 100000 " + hybird.getName() + "  " + (end1 - start1));
        System.out.println("------------------");
    }

    @Test
    public void quick10_000_000List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(10_000_000);


        long start1 = System.nanoTime()/1_000_000;
        quick.sort(randomInts, metrics_par);
        long end1 = System.nanoTime()/1_000_000;
        System.out.println("Elapsed Time in nano seconds: for size 10_000_0000 " + quick.getName()+ "  "+ (end1 - start1));

    }

    @Test
    public void hybird10_000_000List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(10_000_000);

        long start1 = System.nanoTime()/1000000;
        hybird.sort(randomInts, metrics_par);
        long end1 = System.nanoTime()/1000000;
        System.out.println("Elapsed Time in nano seconds: for size 10_000_000 " + hybird.getName() + "  " + (end1 - start1));
        System.out.println("------------------");
    }
    public void quick1500List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(1500);

        long start1 = System.nanoTime();
        quick.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 1500 " + quick.getName()+ "  "+ (end1 - start1));

    }


    public void hybird1500List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(1500);

        long start1 = System.nanoTime();
        hybird.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 1500 " + hybird.getName() + "  " + (end1 - start1));
        System.out.println("------------------");
    }


    public void quick500List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(500);

        long start1 = System.nanoTime();
        quick.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 500 " + quick.getName()+ "  "+ (end1 - start1));

    }


    public void hybird500List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(500);

        long start1 = System.nanoTime();
        hybird.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 500 " + hybird.getName() + "  " + (end1 - start1));
        System.out.println("------------------");
    }


    public void quick200List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(200);

        long start1 = System.nanoTime();
        quick.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 200 " + quick.getName()+ "  "+ (end1 - start1));

    }


    public void hybird200List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(200);

        long start1 = System.nanoTime();
        hybird.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 200 " + hybird.getName() + "  " + (end1 - start1));
        System.out.println("------------------");
    }

    public void quick10List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(10);

        long start1 = System.nanoTime();
        quick.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 10 " + quick.getName()+ "  "+ (end1 - start1));

    }


    public void hybird10List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(10);

        long start1 = System.nanoTime();
        hybird.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 10 " + hybird.getName() + "  " + (end1 - start1));
        System.out.println("------------------");
    }


    public void quick5List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(5);

        long start1 = System.nanoTime();
        quick.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 5 " + quick.getName()+ "  "+ (end1 - start1));

    }

    public void hybird5List() {

        DataRetriever r = new DataRetriever();

        List<Integer> randomInts = r.createRandomIntegerList(5);

        long start1 = System.nanoTime();
        hybird.sort(randomInts, metrics_par);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: for size 5 " + hybird.getName() + "  " + (end1 - start1));
        System.out.println("------------------");
    }
}