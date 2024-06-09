package Truco;
import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mesa {
    private ArrayList<ImageView> listaCartas;
    public void partida(Stage primaryStage) {
        primaryStage.setTitle("Truco");
        listaCartas = new ArrayList<>();
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10));
        generarCartas();
        hbox.getChildren().addAll(listaCartas);
        Button cambiar = new Button("Cambiar Carta");
        Button actualizar = new Button("Actualizar");
        Button envido = new Button("Envido");
        Button truco = new Button("Truco");
        Button flor = new Button("Flor");
        //Boton actualizar----------------
        actualizar.setOnAction(e -> {
            hbox.getChildren().clear();
            generarCartas();
            hbox.getChildren().addAll(listaCartas);
        });
        //--------------------------------
        envido.setOnAction(e ->{
            System.out.println("Se selecciono Envido");
        });
        truco.setOnAction(e ->{
            System.out.println("Se selecciono Truco");
        });
        flor.setOnAction(e ->{
            System.out.println("Se selecciono Flor");
        });
        
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(hbox, actualizar, envido, truco, flor);
        vbox.setAlignment(Pos.CENTER);
        Scene escena1 = new Scene(vbox);
        primaryStage.setScene(escena1);
        primaryStage.show();
    }

    private void generarCartas() {
        listaCartas.clear();
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            numeros.add(i);
        }
        for (int i = 10; i <= 12; i++) {
            numeros.add(i);
        }

        ArrayList<String> cartas = new ArrayList<>();
        for (char letra : new char[]{'B', 'C', 'E', 'O'}) {
            for (int numero : numeros) {
                cartas.add(letra + "" + numero);
            }
        }
        Collections.shuffle(cartas);
        ArrayList<String> cartasAleatorias = new ArrayList<>(cartas.subList(0, 3));
        for (String carta : cartasAleatorias) {
            ImageView car = new ImageView("file:/home/franco/Java/JavaFX/JavaFX/src/Truco/Cartas/Todas/" + carta + ".png");
            car.setOnMouseClicked(event -> mostrarCarta(carta));
            listaCartas.add(car);
        }
    }

    private void mostrarCarta(String carta) {
        System.out.println("La carta Seleccionada es: " + carta);
    }
}
