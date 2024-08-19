/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.proyectoedd;

/**
 *
 * @author abrahamcedeno
 */
import java.util.*;
import java.util.stream.Collectors;


public class AnimalGuessingGame {
    private BinaryTree<String> tree;

    public AnimalGuessingGame(BinaryTree<String> tree) {
        this.tree = tree;
    }

     
    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número máximo de preguntas: ");
        int maxQuestions = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        NodeBinaryTree<String> currentNode = tree.getRoot();
        int questionCount = 0;

        while (currentNode != null && !currentNode.isLeaf() && questionCount < maxQuestions) {
            System.out.println(currentNode.getQuestion());
            String answer = scanner.nextLine().trim().toLowerCase();
            questionCount++;
            System.out.println("Pregunta " + questionCount + ": " + currentNode.getQuestion() + " - Respuesta: " + answer);

            if (answer.equals("sí")) {
                currentNode = currentNode.getLeft() != null ? currentNode.getLeft().getRoot() : null;
            } else {
                currentNode = currentNode.getRight() != null ? currentNode.getRight().getRoot() : null;
            }
        }

        if (currentNode != null && currentNode.isLeaf()) {
            System.out.println("¿Es " + currentNode.getQuestion() + "?");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.compareTo("sí") == 0) {
                System.out.println("¡Adiviné el animal!");
            } else {
                System.out.println("No pude adivinar el animal.");
            }
         } else {
                List<String> possibleAnswers = currentNode != null 
                    ? collectPossibleAnswers(currentNode).stream()
                          .filter(answer -> answer.compareTo("¿Es un depredador?") != 0)
                          .collect(Collectors.toList()) 
                    : new ArrayList<>();
                System.out.println("No pude adivinar el animal. Las posibles respuestas son: " + possibleAnswers);
       }
    }
    private List<String> collectPossibleAnswers(NodeBinaryTree<String> node) {
        List<String> answers = new ArrayList<>();
        if (node != null) {
            collectLeaves(node, answers);
        }
        return answers;
    }

    private void collectLeaves(NodeBinaryTree<String> node, List<String> answers) {
        if (node.isLeaf()) {
            answers.add(node.getQuestion());
        } else {
            if (node.getLeft() != null) {
                collectLeaves(node.getLeft().getRoot(), answers);
            }
            if (node.getRight() != null) {
                collectLeaves(node.getRight().getRoot(), answers);
            }
        }
    }
}