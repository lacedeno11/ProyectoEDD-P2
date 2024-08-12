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
public class BinaryTree<E>{
    NodeBinaryTree<E> root;

    public BinaryTree(NodeBinaryTree<E> root) {
        this.root = root;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public boolean isLeaf(){
        // root no esta vacio
        if (!this.isEmpty()){
            return root.getLeft() == null && root.getRight() == null;
            // return root.getLeft().isEmpty() && root.getRight().isEmpty();
        }
        return false;
    }

    public NodeBinaryTree<E> getRoot() {
        return root;
    }

    public void setRoot(NodeBinaryTree<E> root) {
        this.root = root;
    }
       

     
    
}
