import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimacionRotacion extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Animation Example");

        // Cargar la imagen
        Image image = new Image("file:C:\\Users\\franc\\Desktop\\JavaFX\\JavaFX\\src\\Images\\ImagenesItems\\Jugar.png");
        ImageView imageView = new ImageView(image);

        // Crear una transición de traslación (movimiento)
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), imageView);
        translateTransition.setByX(200); // Mover 200 unidades en el eje X
        translateTransition.setByY(100); // Mover 100 unidades en el eje Y
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE); // Repetir la animación indefinidamente
        translateTransition.setAutoReverse(true); // Alternar la dirección de la animación

        // Iniciar la animación
        translateTransition.play();

        // Colocar la imagen en un contenedor
        Pane root = new Pane(imageView);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
