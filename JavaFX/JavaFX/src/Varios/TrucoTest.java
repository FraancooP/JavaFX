import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class TrucoTest extends Application {
    private ArrayList<ImageView> listaCartas;
    private Stage primaryStage;
    public static void main(String[] args) {
        Application.launch(args);
    }
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Truco");
        
        Button jugar = new Button("JUGAR");
        jugar.setOnAction(e ->mostrarJuego());
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(jugar);
        vbox.setPadding(new Insets(10));
        Scene inicio = new Scene(vbox, 400, 300);
        primaryStage.setScene(inicio);
        primaryStage.show();
    }
    private void mostrarJuego(){
        primaryStage.setTitle("Truco");
        listaCartas = new ArrayList<>();
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10));
        generarCartas();
        hbox.getChildren().addAll(listaCartas);
        Button actualizar = new  Button("Actualizar");
        Button envido = new Button("Envido");
        Button truco = new Button("Truco");
        Button flor = new Button("Flor");
        actualizar.setOnAction(e -> {
            hbox.getChildren().clear();
            generarCartas();
            hbox.getChildren().addAll(listaCartas);
            
        });
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(hbox, actualizar,envido,truco,flor);
        Scene escena1 = new Scene(vbox);
        primaryStage.setScene(escena1);
        primaryStage.show();
        
    }
    private void generarCartas(){
        listaCartas.clear();
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            numeros.add(i);
        }
        for (int i = 10; i <= 12; i++) {
            numeros.add(i);
        }
        
        ArrayList<String> cartas = new ArrayList<>();
        for(char letra : new char[]{'B','C','E','O'}){
            for(int numero : numeros){
                cartas.add(letra+""+numero);
            }
        }
        Collections.shuffle(cartas);
        ArrayList<String> cartasAleatorias = new ArrayList<>(cartas.subList(0, 3));
        for(String carta : cartasAleatorias){
            ImageView car = new ImageView("file:C:\\Users\\franc\\Desktop\\JavaFX\\JavaFX\\src\\Cartas\\Todas\\"+carta+".png");
            car.setOnMouseClicked(event -> mostrarCarta(carta));
            listaCartas.add(car);
        }
    }
    private void mostrarCarta(String carta){
        System.out.println("La carta Seleccionada es: "+carta);
    }
}
