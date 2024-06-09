import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animaciones extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fade Transition Example");

        Button button = new Button("Haz clic para desvanecer");
        VBox vbox = new VBox(button);
        vbox.setSpacing(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        // Crear una transición de desvanecimiento
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), button);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);

        // Manejar el evento de clic para iniciar la animación
        button.setOnAction(event -> {
            fadeTransition.play();
        });

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
