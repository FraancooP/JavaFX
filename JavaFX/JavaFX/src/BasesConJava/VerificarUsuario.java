package BasesConJava;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class VerificarUsuario {
    public VBox verificarUnUsuario(){
    Label user = new Label("Ingrese Nombre");
    TextField nombre = new TextField("Nombre");
    Label password = new Label("Ingrese Contraseña");
    TextField contra = new TextField("Contraseña");
    Button login = new Button("Aceptar");
    Button volver = new Button("Volver");
    VBox inicio = new VBox(user,nombre,password,contra,login,volver);
    inicio.setAlignment(Pos.CENTER);
    return inicio;
    }
}