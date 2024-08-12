/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.proyectoedd;

/**
 *
 * @author abrahamcedeno
 * @param <E>
 */
public class NodeBinaryTree<E> {
    private E question;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public NodeBinaryTree(E question) {
        this.question = question;
    }

    public E getQuestion() {
        return question;
    }

    public BinaryTree<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<E> Left) {
        this.left = Left;
    }
    public void setRight(BinaryTree<E> Right) {
        this.right = Right;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public boolean isLeaf() {
        return (left == null  && right == null );
    }
}
