import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;

public class Boton extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Moving Button Example");

        Button button = new Button("Haz clic aquí");

        // Definir acción al hacer clic en el botón
        button.setOnAction(event -> {
            // Generar coordenadas aleatorias dentro del tamaño de la ventana
            Random random = new Random();
            double maxX = primaryStage.getWidth() - button.getWidth();
            double maxY = primaryStage.getHeight() - button.getHeight();
            double newX = random.nextDouble() * maxX;
            double newY = random.nextDouble() * maxY;

            // Establecer la nueva posición del botón
            button.setLayoutX(newX);
            button.setLayoutY(newY);

            // Mostrar mensaje "JAJAJAJA"
            System.out.println("JAJAJAJA");
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
