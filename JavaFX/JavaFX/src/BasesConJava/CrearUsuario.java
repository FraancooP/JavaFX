package BasesConJava;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CrearUsuario {
    public VBox crearUnUsuario(){
    Label user = new Label("Ingrese Nombre de Usuario");
    TextField user1 = new TextField("Nombre De Usuario");
    Label password = new Label("Ingrese Contraseña");
    TextField password1 = new TextField("Contraseña");
    Label mail = new Label("Ingrese Direccion de Correo");
    TextField mail1 = new TextField("Email");
    Label nombre = new Label("Ingrese Nombre Completo");
    TextField name1 = new TextField("Nombre");
    Button login = new Button("Aceptar");
    Button volver = new Button("Volver");
    VBox inicio = new VBox(user,user1,password,password1,mail,mail1,nombre,name1,login,volver);
    inicio.setAlignment(Pos.CENTER);
    return inicio;
    }
}
