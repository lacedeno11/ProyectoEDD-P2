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

public class QuestionController {

    @FXML
    private Label questionLabel;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    private AnimalGuessingGame game;
    private int maxQuestions;
    private int currentQuestion = 0;

    private NodeBinaryTree<String> currentNode;

    public QuestionController(AnimalGuessingGame game, int maxQuestions) {
        this.game = game;
        this.maxQuestions = maxQuestions;
        this.currentNode = game.getTree().getRoot();
    }

    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Pregunta");
        stage.show();
        nextQuestion();
    }

    @FXML
    private void answerYes() {
        currentNode = currentNode.getLeft().getRoot();
        nextQuestion();
    }

    @FXML
    private void answerNo() {
        currentNode = currentNode.getRight().getRoot();
        nextQuestion();
    }

    private void nextQuestion() {
        currentQuestion++;
        if (currentNode == null || currentQuestion > maxQuestions || currentNode.isLeaf()) {
            showResult();
        } else {
            questionLabel.setText(currentNode.getQuestion());
        }
    }

    private void showResult() {
        try {
            ResultController resultController = new ResultController(currentNode, currentQuestion, maxQuestions);
            resultController.show();
            Stage stage = (Stage) questionLabel.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
