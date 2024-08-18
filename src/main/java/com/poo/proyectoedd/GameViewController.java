/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.poo.proyectoedd;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class GameViewController {

    @FXML
    private Button loadFilesButton;

    @FXML
    private Button startGameButton;

    @FXML
    private Label statusLabel;

    @FXML
    private TextArea maxQuestionsInput;

    private AnimalGuessingGame game;
    private int maxQuestions;

    @FXML
    private void loadFiles() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar archivo de preguntas");
        File questionsFile = fileChooser.showOpenDialog(new Stage());

        fileChooser.setTitle("Cargar archivo de respuestas");
        File answersFile = fileChooser.showOpenDialog(new Stage());

        try {
            List<String> questions = FileReaderUtil.readLines(questionsFile.getAbsolutePath());
            List<String> answers = FileReaderUtil.readLines(answersFile.getAbsolutePath());

            BinaryTree<String> tree = AnimalTreeBuilder.buildTree(questions, answers);
            this.game = new AnimalGuessingGame(tree);

            statusLabel.setText("Archivos cargados correctamente");
        } catch (Exception e) {
            statusLabel.setText("Error al cargar archivos");
            e.printStackTrace();
        }
    }

    @FXML
    private void startGame() {
        if (game == null) {
            statusLabel.setText("Cargue los archivos antes de iniciar el juego.");
            return;
        }

        try {
            maxQuestions = Integer.parseInt(maxQuestionsInput.getText().trim());
            QuestionController questionController = new QuestionController(game, maxQuestions);
            questionController.show();
        } catch (NumberFormatException e) {
            statusLabel.setText("Ingrese un número válido para el máximo de preguntas.");
        } catch (Exception e) {
            statusLabel.setText("Error al iniciar el juego.");
            e.printStackTrace();
        }
    }
}
