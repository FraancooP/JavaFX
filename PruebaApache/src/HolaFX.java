import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HolaFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Creamos un objeto Label con el texto "Hola Mundo!"
        Label label = new Label("¡Hola Mundo!");
        Button boton = new Button("Salir");
        // Creamos un layout StackPane y añadimos el label a él
        StackPane root = new StackPane();
        root.getChildren().add(label);
        
        // Creamos la escena con el layout como raíz y la configuramos con un tamaño
        VBox main = new VBox(label,boton);
        Scene scene = new Scene(main, 300, 200);
        // Configuramos el título de la ventana
        primaryStage.setTitle("Hola Mundo JavaFX");

        // Establecemos la escena en el escenario (Stage)
        primaryStage.setScene(scene);

        // Mostramos el escenario
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        // Lanzamos la aplicación JavaFX
        launch(args);
    }
}
