package Metalc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inicio de Sesión");

        // Imagen inicial con efecto de desvanecimiento
        ImageView initialImageView = new ImageView(new Image("file:/home/franco/Java/JavaFX/JavaFX/src/Metalc/Imagenes/Logo.jpg"));
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), initialImageView);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(event -> showLoginOptions(primaryStage));

        StackPane root = new StackPane(initialImageView);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        fadeTransition.play();
    }

    private void showLoginOptions(Stage primaryStage) {
        ImageView imageView1 = new ImageView(new Image("file:/home/franco/Java/JavaFX/JavaFX/src/Metalc/Imagenes/Ventas.jpg"));
        ImageView imageView2 = new ImageView(new Image("file:/home/franco/Java/JavaFX/JavaFX/src/Metalc/Imagenes/MateriaPrima.jpg"));
        ImageView imageView3 = new ImageView(new Image("file:/home/franco/Java/JavaFX/JavaFX/src/Metalc/Imagenes/Envios.jpg"));
        double imageWidth = 200;
        double imageHeight = 150;
        
        imageView1.setFitWidth(imageWidth);
        imageView1.setFitHeight(imageHeight);
        imageView2.setFitWidth(imageWidth);
        imageView2.setFitHeight(imageHeight);
        imageView3.setFitWidth(imageWidth);
        imageView3.setFitHeight(imageHeight);

        Button ventasButton = new Button("Ingresar Ventas");
        ventasButton.setOnAction(event -> new Ventas().login(primaryStage));

        Button materiasPrimasButton = new Button("Ingresar Materias Primas");
        materiasPrimasButton.setOnAction(event -> new MateriasPrimas().login(primaryStage));

        Button enviosButton = new Button("Ingresar Envíos");
        enviosButton.setOnAction(event -> new Envios().login(primaryStage));
        VBox vBox1 = new VBox(10,imageView1,ventasButton);
        VBox vBox2 = new VBox(10,imageView2,materiasPrimasButton);
        VBox vBox3 = new VBox(10,imageView3,enviosButton);
        HBox hbox = new HBox(20,vBox1,vBox2,vBox3);
        hbox.setAlignment(Pos.CENTER);
        hbox.setTranslateY(180);
        //VBox vBox = new VBox(10, imageView1, ventasButton, imageView2, materiasPrimasButton, imageView3, enviosButton);
        Scene loginScene = new Scene(new StackPane(hbox), 800, 300);
        primaryStage.setScene(loginScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
