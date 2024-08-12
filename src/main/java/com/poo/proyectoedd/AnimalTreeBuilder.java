/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.proyectoedd;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author abrahamcedeno
 */
import java.util.*;

public class AnimalTreeBuilder {
    public static BinaryTree<String> buildTree(List<String> questions, List<String> answers) {
        if (questions == null || questions.isEmpty()) {
            throw new IllegalArgumentException("La lista de preguntas no puede estar vacía.");
        }

        System.out.println("Construyendo el árbol de preguntas...");

        BinaryTree<String> tree = new BinaryTree<>(new NodeBinaryTree<>(questions.get(0)));
        Queue<NodeBinaryTree<String>> queue = new LinkedList<>();
        queue.add(tree.getRoot());


        int questionIndex = 1;
        int level = 1;
    
        while (questionIndex < questions.size()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                NodeBinaryTree<String> currentNode = queue.poll();

                if (currentNode != null) {
                    if (questionIndex < questions.size()) {
                        NodeBinaryTree<String> leftChild = new NodeBinaryTree<>(questions.get(level));
                        
                        currentNode.setLeft(new BinaryTree<>(leftChild));
                        queue.add(leftChild);

                    }

                    if (questionIndex < questions.size()) {
                        NodeBinaryTree<String> rightChild = new NodeBinaryTree<>(questions.get(level));
                        currentNode.setRight(new BinaryTree<>(rightChild));
                        
                        queue.add(rightChild);

                    }
                }
            }
            level++;
            questionIndex++;
        }

        System.out.println("Asignando respuestas...");
        for (String answerLine : answers) {
            String[] parts = answerLine.split(" ");
            String animal = parts[0];
           
            NodeBinaryTree<String> currentNode = tree.getRoot();
            for (int i = 1; i < 21; i++) {
                if (currentNode == null) {
                    break;
                }
                if (parts[i].equals("sí")) {
                    
                    if (currentNode.getLeft() == null) {
                        currentNode.setLeft(new BinaryTree<>(new NodeBinaryTree<>(animal)));
           
                        
                        break;
                    } else {
                        currentNode = currentNode.getLeft().getRoot();
                    }
                } else {
                  
                    if (currentNode.getRight() == null) {
                        currentNode.setRight(new BinaryTree<>(new NodeBinaryTree<>(animal)));
                        
                        
                        break;
                    } else {
                        currentNode = currentNode.getRight().getRoot();
                    }
                }
            }
            System.out.println("Asignando animal: " + animal + " en el camino " + String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)));
        }

        return tree;
    }
}