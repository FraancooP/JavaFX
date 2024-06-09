package Metalc;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MateriasPrimas {
    private void alertaError(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
    }

    public void login(Stage primaryStage) {
        Stage loginStage = new Stage();
        loginStage.setTitle("Login Ventas");

        // Crear el GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Nombre de usuario Label
        Label nameLabel = new Label("Usuario:");
        GridPane.setConstraints(nameLabel, 0, 0);

        // Nombre de usuario Input
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        // Contraseña Label
        Label passLabel = new Label("Contraseña:");
        GridPane.setConstraints(passLabel, 0, 1);

        // Contraseña Input
        PasswordField passInput = new PasswordField();
        GridPane.setConstraints(passInput, 1, 1);

        // Botón de inicio de sesión
        Button loginButton = new Button("Iniciar Sesión");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> handleLogin(nameInput.getText(), passInput.getText(), loginStage, primaryStage));

        // Añadir todo al grid
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);

        // Crear la escena y mostrar la ventana
        Scene scene = new Scene(grid, 300, 200);
        loginStage.setScene(scene);
        loginStage.show();
    }

    private void handleLogin(String username, String password, Stage loginStage, Stage primaryStage) {
        // Aquí debes colocar tu lógica para conectar a la base de datos y verificar el usuario y contraseña
        String url = "jdbc:mysql://localhost:3306/metalce";
        String dbUser = "root";
        String dbPassword = "mfw23xat9c";

        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword)) {
            String query = "SELECT * FROM usuario WHERE user = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Inicio de sesión exitoso");
                // Puedes cerrar la ventana de inicio de sesión aquí si lo deseas
                loginStage.close();
                primaryStage.close();
                // Continuar con el proceso de inicio de sesión
            } else {
                System.out.println("Usuario o contraseña incorrectos");
                alertaError("Error", "Usuario incorrecto o no existente");
                // Aquí puedes mostrar un mensaje de error a la interfaz
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar errores de conexión y otros
        }
    }
}
