/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.poo.proyectoedd;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ctingo
 */
public class GameController implements Initializable {

    @FXML
    private TextField questionCountField;
    @FXML
    private VBox questionArea;
    @FXML
    private Label questionLabel;
    
    
    private int questionCount;
    private int currentQuestionIndex;
    private List<String> questions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        questionArea.setVisible(false);  // Ocultar el área de preguntas al inicio
    }

    @FXML
    private void handleStart(ActionEvent event) {
        try {
            questionCount = Integer.parseInt(questionCountField.getText());
            if (questionCount < 1 || questionCount > 20) {
                throw new NumberFormatException("Número de preguntas fuera de rango");
            }

            // Inicializa la lógica del juego aquí
            startGame();

            // Muestra el área de preguntas
            questionArea.setVisible(true);
            showNextQuestion();
        } catch (NumberFormatException e) {
            questionLabel.setText("Ingrese un número válido entre 1 y 20.");
            questionArea.setVisible(false);
        }
    }

    @FXML
    private void handleYes(ActionEvent event) {
        // Lógica para manejar la respuesta "Sí"
        currentQuestionIndex++;
        showNextQuestion();
    }

    @FXML
    private void handleNo(ActionEvent event) {
        // Lógica para manejar la respuesta "No"
        currentQuestionIndex++;
        showNextQuestion();
    }

    private void startGame() {
        // Aquí puedes cargar las preguntas desde tu lógica o archivo
        questions.clear();
        questions.add("¿Es un mamífero?");
        questions.add("¿Es carnívoro?");
        questions.add("¿Se para en cuatro patas?");
        questions.add("¿Vive en el agua?");
        // Añadir más preguntas según la lógica de tu juego

        currentQuestionIndex = 0;  // Reiniciar el índice de preguntas
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questionCount && currentQuestionIndex < questions.size()) {
            questionLabel.setText("Pregunta " + (currentQuestionIndex + 1) + ": " + questions.get(currentQuestionIndex));
        } else {
            questionLabel.setText("Fin del juego. Gracias por jugar!");
            questionArea.setVisible(false);  // Ocultar el área de preguntas al terminar
        }
    }
}