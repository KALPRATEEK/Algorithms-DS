package de.hawhamburg.hamann.ad.trees.impl;

import java.util.Random;
/*
Copyright This class is Created and Programmed by:KALPRATEEK
*/
public class BSTAnalysis {

    public static void main(String[] args) {
        // Generate and plot data
        plotBSTAnalysis();
    }

    private static void plotBSTAnalysis() {
        // Initialize arrays to store data
        int[] valuesOfN = new int[100];  // Values from 100 to 10000 in steps o 100
        double[] avgPathLengths = new double[100];
        double[] theoreticalPathLengths = new double[100];

        // Generate and plot data for each value of n
        for (int i = 0, n = 100; n <= 10000; i++, n += 100) {
            valuesOfN[i] = n;
            avgPathLengths[i] = calculateAveragePathLength(n);
            theoreticalPathLengths[i] = 1.39 * Math.log(n) / Math.log(2) - 1.85;
            System.out.println("n: " + n + ", Avg Path Length: " + avgPathLengths[i] +
                    ", Theoretical Path Length: " + theoreticalPathLengths[i]);
        }


    }

    private static double calculateAveragePathLength(int n) {
        int repetitions = 1000;
        long totalPathLength = 0;

        for (int i = 0; i < repetitions; i++) {
            // Create a random BST and choose a random key
            BSTree<Integer, String> bst = generateRandomBST(n);
            int randomKey = generateRandomKey();

            // Measure the path length for the random key
            int pathLength = bst.getPathLength(randomKey);

            // Accumulate the path lengths
            totalPathLength += pathLength;
        }

        // Calculate and return the average path length
        return (double) totalPathLength / repetitions;
    }

    private static BSTree<Integer, String> generateRandomBST(int n) {
        BSTree<Integer, String> bst = new BSTree<>();
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int randomKey = generateRandomKey();
            bst.insert(randomKey, "Value" + i);
        }

        return bst;
    }

    private static int generateRandomKey() {
        Random random = new Random();
        return random.nextInt(100000);
    }
}
