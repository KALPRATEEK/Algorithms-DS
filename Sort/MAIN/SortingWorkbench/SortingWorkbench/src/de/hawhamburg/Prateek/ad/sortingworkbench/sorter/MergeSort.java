/*
Completed and Modified by: Prateek Kalra
Explanation:

The sort method initializes the merge sort by calling the divide method.

The divide method recursively divides the list into halves until individual elements are reached.

The conquer method merges the divided lists back together in a sorted manner.

The algorithm uses an auxiliary list (temp) for merging.

The metrics object is used to track the number of comparisons and moves made during the sorting process.

This implementation has a time complexity of O(n log n), making it more efficient
 for large datasets compared to some other sorting algorithms with O(n^2) time complexity.

 */

package de.hawhamburg.hamann.ad.sortingworkbench.sorter;

import de.hawhamburg.hamann.ad.sortingworkbench.SortingMetrics;

import java.util.ArrayList;
import java.util.List;

public class MergeSort extends BaseSort {
//nlogn
@Override
public String getName() {
    return "MergeSort";
}

    @Override
    public <T extends Comparable<T>> void sort(List<T> toSort, SortingMetrics metrics) {
        divide(new ArrayList<>(toSort), 0, toSort.size() - 1, toSort, metrics);
    }

    private <T extends Comparable<T>> void divide(List<T> temp, int low, int high, List<T> toSort, SortingMetrics metrics) {
        if (low < high) {
            int mid = low + (high - low) / 2;

            divide(temp, low, mid, toSort, metrics);
            divide(temp, mid + 1, high, toSort, metrics);

            conquer(temp, low, mid, high, toSort, metrics);
        }
    }

    private <T extends Comparable<T>> void conquer(List<T> temp, int low, int mid, int high, List<T> toSort, SortingMetrics metrics) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        List<T> left = new ArrayList<>(toSort.subList(low, mid + 1));
        List<T> right = new ArrayList<>(toSort.subList(mid + 1, high + 1));

        int i = 0, j = 0, k = low;

        while (i < n1 && j < n2) {
            metrics.incrementCompares();
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                toSort.set(k, left.get(i));
                i++;
            } else {
                toSort.set(k, right.get(j));
                j++;
            }
            k++;
            metrics.incrementMoves();
        }

        while (i < n1) {
            toSort.set(k, left.get(i));
            i++;
            k++;
            metrics.incrementMoves();
        }

        while (j < n2) {
            toSort.set(k, right.get(j));
            j++;
            k++;
            metrics.incrementMoves();
        }
    }
}
