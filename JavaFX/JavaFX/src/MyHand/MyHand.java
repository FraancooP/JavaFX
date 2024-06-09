import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class MyHand extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        MyHandCalculadora operaciones = new MyHandCalculadora();
        MyHandNotas notas = new MyHandNotas();
        MyHandInventario inventario = new MyHandInventario();

        //Creamos nueva ventana.
        primaryStage.setTitle("My Hand");//Nueva Ventana
        MenuBar menuBar = new MenuBar();//Creamos una barra de menus

        Menu menu1 = new Menu("Calculadora");//Creamos un menu
        ImageView calculadoraImagen = new ImageView("file:C:\\Users\\franc\\Desktop\\JavaFX\\JavaFX\\src\\Images\\calculadora.png");
        calculadoraImagen.setFitWidth(120);//ancho
        calculadoraImagen.setFitHeight(120);//Alto
        menu1.setGraphic(calculadoraImagen);

        Menu menu2 = new Menu("Agenda de Notas");
        ImageView agendaImagen = new ImageView("file:C:\\Users\\franc\\Desktop\\JavaFX\\JavaFX\\src\\Images\\agenda.png");
        agendaImagen.setFitWidth(120);
        agendaImagen.setFitHeight(120);
        menu2.setGraphic(agendaImagen);

        Menu menu3 = new Menu("Inventario");
        ImageView inventarioImagen = new ImageView("file:C:\\Users\\franc\\Desktop\\JavaFX\\JavaFX\\src\\Images\\inventario.png");
        inventarioImagen.setFitWidth(120);
        inventarioImagen.setFitHeight(120);
        menu3.setGraphic(inventarioImagen);

        //-----ItemsMenu1------------------------------------------------
        MenuItem menuItem1 = new MenuItem("Calculadora Basica");
        MenuItem menuItem2 = new MenuItem("Calculadora Cientifica");

        ImageView menu1Item1Imagen = new ImageView("file:C:\\Users\\franc\\Desktop\\JavaFX\\JavaFX\\src\\Images\\ImagenesItems\\m1i1.png");
        menu1Item1Imagen.setFitWidth(20);
        menu1Item1Imagen.setFitHeight(20);
        menuItem1.setGraphic(menu1Item1Imagen);
        menu1.getItems().add(menuItem1);

        ImageView menu1Item2Imagen = new ImageView("file:C:\\Users\\franc\\Desktop\\JavaFX\\JavaFX\\src\\Images\\ImagenesItems\\m1i2.png");
        menu1Item2Imagen.setFitWidth(20);
        menu1Item2Imagen.setFitHeight(20);
        menuItem2.setGraphic(menu1Item2Imagen);
        menu1.getItems().add(menuItem2);
        //-----ItemsMenu1------------------------------------------------

        //-----ItemsMenu2------------------------------------------------
        MenuItem menuItem21 = new MenuItem("Notas");
        MenuItem menuItem22 = new MenuItem("Crear Nota");
        MenuItem menuItem23 = new MenuItem("Borrar Nota");
        menu2.getItems().add(menuItem21);
        menu2.getItems().add(menuItem22);
        menu2.getItems().add(menuItem23);
        //-----ItemsMenu2------------------------------------------------

        //-----ItemsMenu3------------------------------------------------
        MenuItem menuItem31 = new MenuItem("Mostrar Inventario");
        MenuItem menuItem32 = new MenuItem("Buscar");
        menu3.getItems().add(menuItem31);
        menu3.getItems().add(menuItem32);
        //-----ItemsMenu3------------------------------------------------

        //Agregamos los menus--------------------------------------------------
        menuBar.getMenus().add(menu1);//Agregamos un menu.
        menuBar.getMenus().add(menu2);
        menuBar.getMenus().add(menu3);

        // Crear la calculadora
        VBox calculadoraPane = operaciones.crearCalculadora();

        VBox vbox1 = new VBox(menuBar);
        Scene escena1 = new Scene(vbox1, 800, 600);
        primaryStage.setScene(escena1);
        primaryStage.show();//Mostramos Ventana

        // Asignar acción al hacer clic en "Calculadora Básica"
        menuItem1.setOnAction(e -> {
            // Limpiamos la escena y mostramos la calculadora
            vbox1.getChildren().clear();
            vbox1.getChildren().addAll(menuBar, calculadoraPane);
        });

        // Asignar acciones a los elementos del menú
        menuItem2.setOnAction(e -> {

        });
        menuItem21.setOnAction(e -> {
            notas.mostrarNotas();
        });
        menuItem22.setOnAction(e -> {
            notas.crearNota();
        });
        menuItem23.setOnAction(e -> {
            notas.borrarNota();
        });
        menuItem31.setOnAction(e -> {
            inventario.mostrarInventario();
        });
        menuItem32.setOnAction(e -> {
            inventario.buscar();
        });
    }
}
