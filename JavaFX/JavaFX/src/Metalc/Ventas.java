package Metalc;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class Ventas {
    private void alertaError(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
    }

    public void login(Stage primaryStage) {
        Stage loginStage = new Stage();
        loginStage.setTitle("Login Ventas");

        // Crear el GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Nombre de usuario Label
        Label nameLabel = new Label("Usuario:");
        GridPane.setConstraints(nameLabel, 0, 0);

        // Nombre de usuario Input
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        // Contraseña Label
        Label passLabel = new Label("Contraseña:");
        GridPane.setConstraints(passLabel, 0, 1);

        // Contraseña Input
        PasswordField passInput = new PasswordField();
        GridPane.setConstraints(passInput, 1, 1);

        // Botón de inicio de sesión
        Button loginButton = new Button("Iniciar Sesión");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> handleLogin(nameInput.getText(), passInput.getText(), loginStage, primaryStage));

        // Añadir todo al grid
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);

        // Crear la escena y mostrar la ventana
        Scene scene = new Scene(grid, 300, 200);
        loginStage.setScene(scene);
        loginStage.show();
    }

    private void handleLogin(String username, String password, Stage loginStage, Stage primaryStage) {
        // Aquí debes colocar tu lógica para conectar a la base de datos y verificar el usuario y contraseña
        String url = "jdbc:mysql://localhost:3306/metalce";
        String dbUser = "root";
        String dbPassword = "mfw23xat9c";

        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword)) {
            String query = "SELECT * FROM usuario WHERE user = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Inicio de sesión exitoso");
                // Puedes cerrar la ventana de inicio de sesión aquí si lo deseas
                loginStage.close();
                primaryStage.close();
                // Continuar con el proceso de inicio de sesión
                //Metodo para interfaz de Ventas
                ventasInterfaz(primaryStage);
            } else {
                System.out.println("Usuario o contraseña incorrectos");
                alertaError("Error", "Usuario o contraseña Invalidos.");
                // Aquí puedes mostrar un mensaje de error a la interfaz
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar errores de conexión y otros
        }
    }
    public void ventasInterfaz(Stage primaryStage){
    primaryStage.setTitle("Ventas");
    MenuBar menuBar = new MenuBar();
    Menu menu1 = new Menu("Venta");
    Menu menu2 = new Menu("Cliente");
    Menu menu3 = new Menu("Producto");
    MenuItem menu1Item1 = new MenuItem("Registrar nueva venta");
    MenuItem menu1Item2 = new MenuItem("Ver Historial de ventas");
    MenuItem menu2Item1 = new MenuItem("Registrar nuevo cliente");
    MenuItem menu2Item2 = new MenuItem("Ver clientes Registrados");
    MenuItem menu3Item1 = new MenuItem("Buscar Producto");
    menu1.getItems().add(menu1Item1);
    menu1.getItems().add(menu1Item2);
    menu2.getItems().add(menu2Item1);
    menu2.getItems().add(menu2Item2);
    menu3.getItems().add(menu3Item1);
    menuBar.getMenus().add(menu1);
    menuBar.getMenus().add(menu2);
    menuBar.getMenus().add(menu3);
    
    VBox root = new VBox(menuBar);
    Scene scene = new Scene(root, 960, 600);

    // Establecer la escena en el primaryStage
    primaryStage.setScene(scene);
    primaryStage.show();

    // Manejadores de eventos para las opciones del menú
    menu1Item1.setOnAction(event -> {
        primaryStage.setTitle("Registrar Venta");
        root.getChildren().removeIf(node -> node != menuBar);
        // Crear etiquetas y campos de entrada
        Label clientLabel = new Label("Ingrese Nombre del cliente:");
        TextField clientInput = new TextField();
        Label productLabel = new Label("Ingrese Producto:");
        TextField productInput = new TextField();
        Label quantityLabel = new Label("Ingrese Cantidad:");
        TextField quantityInput = new TextField();
        Label fechaVenta = new Label("Ingrese Fecha(dd/mm/aa)");
        TextField fecha = new TextField();
        
        
        
        
        Button registerButton = new Button("Registrar Venta");
        registerButton.setOnAction(e -> {
            String cliente = clientInput.getText();
            String producto = productInput.getText();
            String cantidad = quantityInput.getText();
            //if(cliente==null);
            //alertaError("Error", "Debe ingresar un nombre de cliente.");
            //if(producto==null);
            //alertaError("Error", "Debe ingresar un nombre de producto.");
            //if(cantidad==null);
            //alertaError("Error", "Debe ingresar una cantidad valida.");
            // Lógica para registrar la venta...
            System.out.println("Venta registrada:");
            System.out.println("Cliente: " + cliente);
            System.out.println("Producto: " + producto);
            System.out.println("Cantidad: " + cantidad);
        });
        // Organizar elementos en una cuadrícula
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.addRow(0, clientLabel, clientInput);
        grid.addRow(1, productLabel, productInput, quantityLabel, quantityInput,fechaVenta,fecha);
        grid.addRow(2, registerButton);
        
        
        // Agregar la cuadrícula al nodo raíz
        root.getChildren().add(grid);
    });
    menu1Item2.setOnAction(e ->{
        primaryStage.setTitle("Registrar Venta");
        root.getChildren().removeIf(node -> node != menuBar);
        TableView tablaVentas = new TableView();
        TableColumn<StockVentas, Integer> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn<StockVentas, String> column2 = new TableColumn<>("Cliente");
        column2.setCellValueFactory(new PropertyValueFactory<>("Cliente"));
        TableColumn<StockVentas, String> column3 = new TableColumn<>("Producto");
        column3.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        TableColumn<StockVentas, Integer> column4 = new TableColumn<>("Cantidad");
        column4.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        TableColumn<StockVentas, String> column5 = new TableColumn<>("Estado");
        column5.setCellValueFactory(new PropertyValueFactory<>("Estado"));
        TableColumn<StockVentas, String> column6 = new TableColumn<>("Fecha");
        column6.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        TableColumn<StockVentas, String> column7 = new TableColumn<>("FechaPago");
        column7.setCellValueFactory(new PropertyValueFactory<>("FechaPago"));
        tablaVentas.getColumns().add(column1);
        tablaVentas.getColumns().add(column2);
        tablaVentas.getColumns().add(column3);
        tablaVentas.getColumns().add(column4);
        tablaVentas.getColumns().add(column5);
        tablaVentas.getColumns().add(column6);
        tablaVentas.getColumns().add(column7);
        tablaVentas.getItems().add(new StockVentas(1, "Joaquin","Transistor",2,"Pendiente","12/10/2001","--/--/--"));
        tablaVentas.getItems().add(new StockVentas(2, "Franco","Bobina",2,"Pendiente","9/11/2001","--/--/--"));
        tablaVentas.getItems().add(new StockVentas(3, "Fabri","Cable2A",12,"Pendiente","15/6/2001","--/--/--"));
        tablaVentas.getItems().add(new StockVentas(4, "Agus","Resistor",122,"Completado","18/3/2001","18/3/2001"));
        Label buscarCliente = new Label("Filtrar por nombre de cliente");
        Label cliente = new Label("Ingrese nombre cliente:");
        Label buscarID = new Label("Filtrar venta por ID");
        Label id = new Label("Ingrese ID:");
        Label buscarAño = new Label("Filtrar venta por fecha");
        Label anio = new Label("Ingrese fecha(dd/mm/aa):");
        Label estado = new Label("Filtrar por Estado: ");
        TextField busquedaCliente = new TextField();
        TextField busquedaId = new TextField();
        TextField busquedaAnio = new TextField();
        Button busquedaClienteButton= new Button("Filtrar");
        Button busquedaIDButton= new Button("Filtrar");
        Button busquedaAnioButton= new Button("Filtrar");
        SplitMenuButton buttonEstado = new SplitMenuButton();
        buttonEstado.setText("Seleccione Estado");
        MenuItem pendiente = new MenuItem("Pendiente");
        MenuItem completado = new MenuItem("Completado");
        MenuItem cancelado = new MenuItem("Cancelado");
        buttonEstado.getItems().addAll(pendiente,completado,cancelado);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.addRow(0, buscarCliente);
        grid.addRow(1, cliente, busquedaCliente,busquedaClienteButton);
        grid.addRow(2, buscarID);
        grid.addRow(3,id,busquedaId,busquedaIDButton);
        grid.addRow(4, buscarAño);
        grid.addRow(5, anio,busquedaAnio,busquedaAnioButton);
        grid.addRow(6,estado,buttonEstado);
        root.getChildren().add(tablaVentas);
        root.getChildren().add(grid);
        });
    // Implementar el resto de manejadores de eventos para las otras opciones del menú...
}

}
