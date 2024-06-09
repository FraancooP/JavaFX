package Truco;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Truco");
        ImageView jugarImagen = new ImageView("file:/home/franco/Java/JavaFX/JavaFX/src/Images/Jugar.png");
        jugarImagen.setFitWidth(200);
        jugarImagen.setFitHeight(200);
        Button jugar = new Button("");
        jugar.setGraphic(jugarImagen);
        jugar.setOnAction(e -> {
            Mesa mesa = new Mesa();
            mesa.partida(primaryStage);
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(jugar);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        Scene inicio = new Scene(vbox, 400, 300);
        primaryStage.setScene(inicio);
        primaryStage.show();
    }
}
