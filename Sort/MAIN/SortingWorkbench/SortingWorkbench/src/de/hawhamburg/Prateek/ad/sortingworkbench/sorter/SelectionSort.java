/*
Completed and Modified by: Prateek Kalra

Selection: It iterates through the list, assuming the current element is the minimum.
Find Minimum: It then searches for the minimum element in the unsorted part of the list.
Swap: If a smaller element is found, it swaps it with the assumed minimum element.
This process repeats until the entire list is sorted. The algorithm has a time
 complexity of O(n^2) in the worst and average cases, making it inefficient for large
 datasets. It's primarily used for educational purposes due to its simplicity,
 but it's not the most efficient sorting algorithm for practical applications.
 */
package de.hawhamburg.hamann.ad.sortingworkbench.sorter;

import de.hawhamburg.hamann.ad.sortingworkbench.SortingMetrics;

import java.util.List;

public class SelectionSort extends BaseSort {

    @Override
    public String getName() {
        return "Selection Sort";
    }

    @Override
    public <T extends Comparable<T>> void sort(List<T> toSort, SortingMetrics metrics) {
        // Iterate through the list, considering elements one by one
        for (int i = 0; i < toSort.size() - 1; i++) {
            int minIndex = i; // Assume the current element is the minimum

            // Find the index of the minimum element in the unsorted part of the list
            for (int j = i + 1; j < toSort.size(); j++) {
                metrics.incrementCompares(); // Increment comparison counter
                if (toSort.get(j).compareTo(toSort.get(minIndex)) < 0) {
                    minIndex = j; // Update the minimum index if a smaller element is found
                }
            }

            // If the minimum element's index is not the same as the current index 'i', swap them
            if (minIndex != i) {
                T temp = toSort.get(i);
                toSort.set(i, toSort.get(minIndex));
                toSort.set(minIndex, temp);

                metrics.incrementMoves(); // Increment move counter
            }
        }
    }


}
