/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.poo.proyectoedd;

import java.io.BufferedReader;
import java.io.FileReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author ctingo
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Inicialización del controlador, si es necesario
    }    

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Buscar y validar el usuario en el archivo
        Usuario user = loadUser(username);
        if (user != null && user.validatePassword(password)) {
            // Si las credenciales son correctas, redirigir a la pantalla del juego
            Stage stage = (Stage) usernameField.getScene().getWindow();
            showGameInterface(stage);
        } else {
            // Mostrar mensaje de error o realizar alguna acción cuando las credenciales son incorrectas
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private Usuario loadUser(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(username)) {
                    return new Usuario(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el usuario: " + e.getMessage());
        }
        return null;
    }

    private void showGameInterface(Stage stage) {
        try {
            // Cargar la interfaz del juego
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameUI.fxml"));
            Parent root = loader.load();
            stage.setTitle("Animal Guessing Game");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}