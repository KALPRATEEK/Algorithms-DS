/*
Completed and Modified by: Prateek Kalra
The sort method initializes the hybrid sorting by calling the hybridQuickInsertionSort method.

The hybridQuickInsertionSort method checks if the size of the subarray is below a threshold
(INSERTION_SORT_THRESHOLD). If it is, it uses Insertion Sort. Otherwise, it performs the Quick Sort steps.

The insertionSort method is a standard Insertion Sort implementation.

The rest of the methods (partition, swap, and chooseRandomPivotIndex) are similar to those in a
standard Quick Sort implementation.

The hybrid approach leverages the efficiency of Quick Sort for larger subarrays and the simplicity of
Insertion Sort for smaller subarrays, resulting in improved performance for certain scenarios.
Adjust the INSERTION_SORT_THRESHOLD as needed based on the characteristics of your data and system.
 */
package de.hawhamburg.hamann.ad.sortingworkbench.sorter;

import de.hawhamburg.hamann.ad.sortingworkbench.SortingMetrics;

import java.util.List;
import java.util.Random;

public class HybridQuickInsertionSorter extends BaseSort {

    private static final int INSERTION_SORT_THRESHOLD = 6;  // Adjust the threshold as needed
    private InsertionSort insert = new InsertionSort();
    @Override
    public String getName() {
        return "Hybrid Quick-Insertion Sort";
    }



    @Override
    public <T extends Comparable<T>> void sort(List<T> toSort, SortingMetrics metrics) {
     hybridQuickInsertionSort(toSort, 0, toSort.size() - 1, metrics);
     insert.sort( toSort,metrics);

    }

    private <T extends Comparable<T>> void hybridQuickInsertionSort(List<T> toSort, int low, int high, SortingMetrics metrics) {
        if (low < high) {
            if (high - low + 1 <= INSERTION_SORT_THRESHOLD) {
               return;
            }
            else {
               // int pivotIndex = chooseRandomPivotIndex(low, high);
                int pivotIndex=(low+high)/2;
                pivotIndex = partition(toSort, low, high, pivotIndex, metrics);

                hybridQuickInsertionSort(toSort, low, pivotIndex - 1, metrics);
                hybridQuickInsertionSort(toSort, pivotIndex + 1, high, metrics);
            }
        }
    }

    private <T extends Comparable<T>> void insertionSort(List<T> toSort, int low, int high, SortingMetrics metrics) {
        for (int i = low + 1; i <= high; i++) {
            T key = toSort.get(i);
            int j = i - 1;

            while (j >= low && toSort.get(j).compareTo(key) > 0) {
                metrics.incrementCompares();
                toSort.set(j + 1, toSort.get(j));
                metrics.incrementMoves();
                j--;
            }
            toSort.set(j + 1, key);
            metrics.incrementMoves();
        }
    }

    private <T extends Comparable<T>> int partition(List<T> toSort, int low, int high, int pivotIndex, SortingMetrics metrics) {
        T pivotValue = toSort.get(pivotIndex);

        // Move pivot to the end
        swap(toSort, pivotIndex, high);

        int storeIndex = low;
        for (int i = low; i < high; i++) {
            metrics.incrementCompares();
            if (toSort.get(i).compareTo(pivotValue) < 0) {
                swap(toSort, i, storeIndex);
                storeIndex++;
                metrics.incrementMoves();
            }
        }

        swap(toSort, storeIndex, high);

        return storeIndex;
    }

    private <T extends Comparable<T>> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private int chooseRandomPivotIndex(int low, int high) {
        Random rand = new Random();
        return rand.nextInt(high - low + 1) + low;
    }
}
