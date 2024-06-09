import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioBD extends Application {

    private Connection conn;

    @Override
    public void start(Stage primaryStage) {
        // Establecer la conexión a la base de datos
        establishConnection();

        // Crear la interfaz de usuario
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label usernameLabel = new Label("Usuario:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Contraseña:");
        PasswordField passwordField = new PasswordField();
        Button registerButton = new Button("Registrarse");
        Button loginButton = new Button("Iniciar Sesión");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(registerButton, 0, 2);
        grid.add(loginButton, 1, 2);

        // Evento para el botón de registro
        registerButton.setOnAction(e -> registerUser(usernameField.getText(), passwordField.getText()));

        // Evento para el botón de inicio de sesión
        loginButton.setOnAction(e -> loginUser(usernameField.getText(), passwordField.getText()));

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registro e inicio de sesión");
        primaryStage.show();
    }

    private void establishConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "mfw23xat9c");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerUser(String username, String password) {
        try { 
            PreparedStatement statement = conn.prepareStatement("INSERT INTO usuarios_info (username, password) VALUES (?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("Usuario registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    private void loginUser(String username, String password) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios_info WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Inicio de sesión exitoso.");
            } else {
                System.out.println("Credenciales incorrectas. Por favor, inténtelo de nuevo.");
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
