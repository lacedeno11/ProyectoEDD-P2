/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.poo.proyectoedd;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;

public class ResultController {

    @FXML
    private Label resultLabel;

    @FXML
    private Button closeButton;

    private NodeBinaryTree<String> resultNode;
    private int currentQuestion;
    private int maxQuestions;

    public ResultController(NodeBinaryTree<String> resultNode, int currentQuestion, int maxQuestions) {
        this.resultNode = resultNode;
        this.currentQuestion = currentQuestion;
        this.maxQuestions = maxQuestions;
    }

    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Resultado");
        stage.show();
        displayResult();
    }

    private void displayResult() {
        if (resultNode == null) {
            resultLabel.setText("No pude adivinar el animal.");
        } else if (resultNode.isLeaf()) {
            resultLabel.setText("¿Es " + resultNode.getQuestion() + "?");
        } else if (currentQuestion >= maxQuestions) {
            resultLabel.setText("No estoy seguro, pero podría ser: " + resultNode.getQuestion());
        } else {
            resultLabel.setText("¡Adiviné el animal!");
        }
    }

    @FXML
    private void closeGame() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
