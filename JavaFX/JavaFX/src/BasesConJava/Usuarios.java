package BasesConJava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class Usuarios extends Application {

    @Override
    public void start(Stage primaryStage) {
        VerificarUsuario verificar = new VerificarUsuario();
        CrearUsuario nuevo = new CrearUsuario();
        primaryStage.setTitle("Usuarios");
        Button logear = new Button("Iniciar Sesion");
        Button registrar = new Button("Registrar Usuario");
        VBox iniciarSesion = verificar.verificarUnUsuario();
        VBox registrarUsuario = nuevo.crearUnUsuario();
        VBox inicio = new VBox(logear, registrar);
        inicio.setAlignment(Pos.CENTER);
        Scene scene = new Scene(inicio, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        logear.setOnAction(event -> {
            inicio.getChildren().clear();
            inicio.getChildren().addAll(iniciarSesion);
        });
        registrar.setOnAction(event ->{
            inicio.getChildren().clear();
            inicio.getChildren().addAll(registrarUsuario);
        });
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
