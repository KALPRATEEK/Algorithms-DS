package de.hawhamburg.hamann.ad.trees.impl;

/*
Copyright This class is Created and Programmed by:KALPRATEEK
*/
public class Node<Data> { // Node<Data> is a generic type parameter. It's a placeholder for the actual type of data that will be stored in each node of the binary tree
    Data data;
    Node Left;
    Node Right;


    public Node(Data data) {
        this.data = data;
        this.Left = null;
        this.Right = null;

    }

    public Data getData() {
        return data;
    }
}
