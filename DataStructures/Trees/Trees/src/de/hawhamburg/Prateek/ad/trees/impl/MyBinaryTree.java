package de.hawhamburg.hamann.ad.trees.impl;
/*
Copyright This class is Created and Programmed by:KALPRATEEK
*/

import java.util.function.Consumer;

public class MyBinaryTree<Data> implements de.hawhamburg.hamann.ad.trees.BinaryTree<Data> {


 Node<Data> root;
    public MyBinaryTree() {
        this.root = null; // IMPORTANT  other test would be not true..
    }
    public MyBinaryTree(Data data) {
        this.root = new Node<>(data);
    }

    @Override
    public Data getData() {
        return (root != null) ? root.data : null;
    }

    @Override
    public void setData(Data data)  //Integer can also be added since data is generic.
    {

        if (this.root == null) {
            this.root = new Node<>(data); // Create a new root node if the tree is empty
        } else {
            this.root.data = data; // Update the data of the existing root node
        }

    }

    @Override
    public de.hawhamburg.hamann.ad.trees.BinaryTree getLeftNode() {
        if (root != null && root.Left != null) {
            MyBinaryTree<Data> leftTree = new MyBinaryTree<>();
            leftTree.root = root.Left;
            return leftTree;
        }
        return null;
    }

    @Override
    public de.hawhamburg.hamann.ad.trees.BinaryTree getRightNode() {
        if (root != null && root.Right != null) {
            MyBinaryTree<Data> rightTree = new MyBinaryTree<>();
            rightTree.root = root.Right;
            return rightTree;
        }

        return null;
    }

    @Override
    public boolean isLeaf() {
        return root != null && getLeftNode() == null && getRightNode() == null;
    }
  public Node getroot()
  {
      return root;
  }

    /*
    Interface Consumer<T>
    Type Parameters:
    T - the type of the input to the operation
    All Known Subinterfaces:
    Stream.Builder<T>
    Functional Interface:
    This is a functional interface and can therefore be used as the assignment target for a lambda expression or method reference.

    @FunctionalInterface
    public interface Consumer<T>
    Represents an operation that accepts a single input argument and returns no result. Unlike most other functional interfaces, Consumer is expected to operate via side-effects.
    This is a functional interface whose functional method is accept(Object).

    Since:
    1.8
    Method Summary
    All MethodsInstance MethodsAbstract MethodsDefault Methods
    Modifier and Type	Method and Description
    void	accept(T t)  #impo
    Performs this operation on the given argument.
    default Consumer<T>	andThen(Consumer<? super T> after)
    Returns a composed Consumer that performs, in sequence, this operation followed by the after operation.
    Method Detail
    accept
    void accept(T t)
    Performs this operation on the given argument.
    Parameters:
    t - the input argument
     */
    public void visitPreOrder(Consumer visitor) {
        visitPreOrder(root, visitor);
    }

    private void visitPreOrder(Node<Data> node, Consumer visitor) {  // order will be root ,left, right
        if (node != null) {
            visitor.accept(new MyBinaryTree<>(node.data)); // this= instance of this binray tree
            visitPreOrder(node.Left, visitor);
            visitPreOrder(node.Right, visitor);
        }
    }

    @Override
    public void visitInOrder(Consumer visitor) {
        visitInOrder(root, visitor);

    }

    private void visitInOrder(Node<Data> node, Consumer visitor) {
        if (node != null) {
            visitInOrder(node.Left, visitor);
            visitor.accept(new MyBinaryTree<>(node.data));
            visitInOrder(node.Right, visitor);
        }
    }

    @Override
    public void visitPostOrder(Consumer visitor) {
        visitPostOrder(root, visitor);
    }

    private void visitPostOrder(Node<Data> node, Consumer visitor) {
        if (node != null) {
            visitPostOrder(node.Left, visitor);   //order will be left , rigth ,root.
            visitPostOrder(node.Right, visitor);
            visitor.accept(new MyBinaryTree<>(node.data));
            /*
            After visiting the left and right subtrees, the visitor.accept(this) line is executed.
            This means that the Consumer provided to the visitPostOrder method will perform some action
            on the current node (this refers to the current instance of the BinaryTree).
             */
        }
    }


}
