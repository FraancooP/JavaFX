import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.stage.Stage;
public class HolaMundo extends Application{
    public void start(Stage primaryStage) throws Exception{
        //Creamos nueva ventana.
        primaryStage.setTitle("El Primer Paso");//Nueva Ventana
        Label texto1 = new Label("Mi primer texto.");//Creo texto
        Scene escena1 = new Scene(texto1, 800, 600);
        primaryStage.setScene(escena1); 
        
        
        
        
        
        
  
        primaryStage.show();//Mostramos Ventana
    }
    
    
    
    
    
    
    
    
    
    
    
    
    //Inicializador---------------------------
    public static void main(String[] args) {
        Application.launch(args);
    }
}
