/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.poo.proyectoedd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author ctingo
 */
public class InicioController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/path/to/your/image.png");
        imageView.setImage(image);
    }

    @FXML
    private void handleRegister() {
        // Crear el cuadro de diálogo de registro
        Stage registerStage = new Stage();
        registerStage.setTitle("Registro de Usuario");

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        Label userLabel = new Label("Nuevo Usuario:");
        TextField userField = new TextField();
        Label passLabel = new Label("Nueva Contraseña:");
        PasswordField passField = new PasswordField();
        Button registerButton = new Button("Registrar");

        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passField, 1, 1);
        gridPane.add(registerButton, 1, 2);

        Scene scene = new Scene(gridPane, 300, 200);
        registerStage.setScene(scene);
        registerStage.show();

        // Manejar evento de registro
        registerButton.setOnAction(event -> {
            String newUsername = userField.getText();
            String newPassword = passField.getText();

            if (newUsername.isEmpty() || newPassword.isEmpty()) {
                System.out.println("Debe ingresar un usuario y una contraseña.");
                return;
            }

            Usuario newUser = new Usuario(newUsername, newPassword);
            saveNewUser(newUser);

            System.out.println("Usuario registrado exitosamente.");

            registerStage.close();
        });
    }

    private void saveNewUser(Usuario user) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));
            writer.write(user.getUsername() + ":" + user.getPassword());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.err.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validación de las credenciales de usuario
        if (validateUser(username, password)) {
            // Si las credenciales son correctas, redirigir a la pantalla del juego
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            showGameInterface(stage);
        } else {
            // Mostrar mensaje de error o realizar alguna acción cuando las credenciales son incorrectas
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private boolean validateUser(String username, String password) {
        // Lógica básica de validación
        // Por simplicidad, aquí se compara con un usuario y contraseña predefinidos
        // En una implementación real, deberías comparar contra una base de datos o archivo
        return "admin".equals(username) && "1234".equals(password);
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
