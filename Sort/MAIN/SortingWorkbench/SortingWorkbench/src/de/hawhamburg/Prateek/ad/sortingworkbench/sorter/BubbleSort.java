/*
Completed and Modified by: Prateek Kalra

The sort method takes a List to be sorted and a SortingMetrics object to track comparisons and moves.

It iterates through the list using two nested loops.

The outer loop (for i) represents the passes over the list.
The inner loop (for j) compares adjacent elements and swaps them if they are in the wrong order.
The swapped variable is used to check whether any swaps occurred in the inner loop.
 If no swaps occurred, the array is considered sorted,
 and the sorting process stops.

The algorithm has a time complexity of O(n^2) in the worst case. It's a basic sorting algorithm
often used for educational purposes due to its simplicity, but it is less efficient for large datasets
 compared to more advanced sorting algorithms.


 */
package de.hawhamburg.hamann.ad.sortingworkbench.sorter;

import de.hawhamburg.hamann.ad.sortingworkbench.SortingMetrics;

import java.util.List;

public class BubbleSort extends BaseSort {
    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
    public <T extends Comparable<T>> void sort(List<T> toSort, SortingMetrics metrics) {
        int n = toSort.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                metrics.incrementCompares();// increment cuz we are gonna compare here
                if (toSort.get(j).compareTo(toSort.get(j + 1)) > 0) {
                    // Swap toSort[j] and toSort[j + 1]
                    T temp = toSort.get(j);
                    toSort.set(j, toSort.get(j + 1));
                    toSort.set(j + 1, temp);

                    metrics.incrementMoves();// increment cuz we are gonna make a move and swap it
                    swapped = true;
                }
            }

            // If no two elements were swapped in the inner loop, the array is already sorted
            if (!swapped) {
                break;
            }
        }
    }
}
