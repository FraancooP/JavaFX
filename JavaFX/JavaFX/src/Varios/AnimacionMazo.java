import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimacionMazo extends Application {
    private StackPane cardContainer;
    private List<ImageView> cardImages;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Card Animation Example");

        cardContainer = new StackPane();
        cardContainer.setAlignment(Pos.CENTER);

        // Crear las imágenes de las cartas boca abajo
        ImageView cardBack = new ImageView(new Image("file:C:\\Users\\franc\\Desktop\\JavaFX\\JavaFX\\src\\Cartas\\Atras.png"));
        cardContainer.getChildren().add(cardBack);

        // Crear imágenes de las cartas reales (se muestran boca abajo inicialmente)
        cardImages = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView card = new ImageView(new Image("file:C:\\Users\\franc\\Desktop\\JavaFX\\JavaFX\\src\\Cartas\\Todas\\O" + (i + 1) + ".png"));
            card.setOpacity(0); // Ocultar las cartas reales al principio
            cardContainer.getChildren().add(card);
            cardImages.add(card);
        }

        Button generateButton = new Button("Generar Cartas Aleatorias");
        generateButton.setOnAction(event -> animateCardDeal());

        VBox root = new VBox(10, cardContainer, generateButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void animateCardDeal() {
        // Barajar las cartas
        Collections.shuffle(cardImages);

        // Crear y ejecutar una animación para revelar las cartas
        for (int i = 0; i < cardImages.size(); i++) {
            ImageView card = cardImages.get(i);

            // Calcular la posición final de la carta
            double finalX = card.getTranslateX() - 200; // Mover la carta hacia la izquierda
            double finalY = card.getTranslateY() + 50 * (i - 1); // Mover la carta hacia abajo con un desfase

            TranslateTransition transition = new TranslateTransition(Duration.seconds(1), card);
            transition.setToX(finalX);
            transition.setToY(finalY);
            transition.setDelay(Duration.seconds(0.2 * i)); // Añadir un pequeño retardo para la animación

            // Al terminar la animación, volver a colocar la carta en su posición inicial
            transition.setOnFinished(e -> {
                card.setTranslateX(0); // Volver a la posición inicial en X
                card.setTranslateY(0); // Volver a la posición inicial en Y
                card.setOpacity(0); // Ocultar la carta
            });

            transition.play();

            // Mostrar la carta real después de un pequeño retardo
            transition.setOnFinished(e -> {
                card.setOpacity(1);
            });
        }
    }
}
