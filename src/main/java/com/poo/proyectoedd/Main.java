/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.proyectoedd;

/**
 *
 * @author abrahamcedeno
 */
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> questions = FileReaderUtil.readLines("src/main/resources/preguntas.txt");
            List<String> answers = FileReaderUtil.readLines("src/main/resources/respuestas.txt");
            BinaryTree<String> tree = AnimalTreeBuilder.buildTree(questions, answers);
            AnimalGuessingGame game = new AnimalGuessingGame(tree);
         
            game.play();

        } catch (IOException e) {
            System.err.println("Error al leer los archivos: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error en los datos de entrada: " + e.getMessage());
        }
    }
}
