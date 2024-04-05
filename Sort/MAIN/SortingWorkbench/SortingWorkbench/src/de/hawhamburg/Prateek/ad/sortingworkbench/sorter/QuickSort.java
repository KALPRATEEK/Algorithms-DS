package de.hawhamburg.hamann.ad.sortingworkbench.sorter;
/*
Completed and Modified by: Prateek Kalra
The sort method initializes the quick sort by calling the quickSort method.

The quickSort method recursively divides the list into subarrays and sorts them.

The partition method selects a pivot element and partitions the array into two parts: elements
 less than the pivot and elements greater than the pivot.

The algorithm uses different pivot selection strategies, such as selecting the first element,
the last element, or a random element.

The metrics object is used to track the number of comparisons and moves made during the
sorting process.

This implementation has an average-case time complexity of O(n log n), making it efficient for a
wide range of datasets
 */
import de.hawhamburg.hamann.ad.sortingworkbench.SortingMetrics;

import java.util.List;
import java.util.Random;

public class QuickSort extends BaseSort {



    @Override
    public String getName() {
        return "Quick Sort";
    }

    @Override
    public <T extends Comparable<T>> void sort(List<T> toSort, SortingMetrics metrics) {
        quickSort(toSort, 0, toSort.size() - 1, metrics);
    }

    private <T extends Comparable<T>> void quickSort(List<T> toSort, int low, int high, SortingMetrics metrics) {
        if (low < high) {
            int pivotIndex;

            // Choose a pivot using different strategies
            // Uncomment the desired pivot selection method below

            // Pivot as the first element
            // pivotIndex = low;

            // Pivot as the last element
            //  pivotIndex = high;

            // Pivot as a random element
          pivotIndex = chooseRandomPivotIndex(low, high);

            pivotIndex = partition(toSort, low, high, pivotIndex, metrics);

            quickSort(toSort, low, pivotIndex - 1, metrics);
            quickSort(toSort, pivotIndex + 1, high, metrics);
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
