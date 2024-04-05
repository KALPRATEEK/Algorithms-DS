/*
Completed and Modified by: Prateek Kalra
It iterates through the list starting from the second element.
For each element, it considers it as the "key" to be inserted into the sorted part.
It compares the key with the elements in the sorted part and shifts elements to the right until
it finds the correct position for the key.
Once the correct position is found, it places the key in that position.
This process repeats until the entire list is sorted in ascending order.
The algorithm has a time complexity of O(n^2) in the worst case, making it suitable for small
datasets but less efficient for large datasets compared to more advanced sorting algorithms.
 */
package de.hawhamburg.hamann.ad.sortingworkbench.sorter;

import de.hawhamburg.hamann.ad.sortingworkbench.SortingMetrics;

import java.util.List;

public class InsertionSort extends BaseSort {

    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public <T extends Comparable<T>> void sort(List<T> toSort, SortingMetrics metrics) {
        int n = toSort.size();

        for (int i = 1; i < n; i++) {
            T key = toSort.get(i);
            int j = i - 1;

            while (j >= 0 && toSort.get(j).compareTo(key) > 0) {
                metrics.incrementCompares();
                toSort.set(j + 1, toSort.get(j));
                metrics.incrementMoves();
                j--;
            }
            toSort.set(j + 1, key);
            metrics.incrementMoves();
        }
    }

    }

