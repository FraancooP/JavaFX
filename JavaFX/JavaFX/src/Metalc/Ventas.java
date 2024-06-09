package Metalc;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.Alert.*;
import javafx.scene.control.cell.*;
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
        primaryStage.setTitle("Historial de Ventas");
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
        TableColumn<StockVentas, String> column8 = new TableColumn<>("IDFacturaAsociada");
        column8.setCellValueFactory(new PropertyValueFactory<>("IDFacturaAsociada"));
        tablaVentas.getColumns().add(column1);
        tablaVentas.getColumns().add(column2);
        tablaVentas.getColumns().add(column3);
        tablaVentas.getColumns().add(column4);
        tablaVentas.getColumns().add(column5);
        tablaVentas.getColumns().add(column6);
        tablaVentas.getColumns().add(column7);
        tablaVentas.getColumns().add(column8);
        tablaVentas.getItems().add(new StockVentas(1, "Joaquin","Transistor",2,"Pendiente","12/10/2001","--/--/--",1));
        tablaVentas.getItems().add(new StockVentas(2, "Franco","Bobina",2,"Pendiente","9/11/2001","--/--/--",2));
        tablaVentas.getItems().add(new StockVentas(3, "Fabri","Cable2A",12,"Pendiente","15/6/2001","--/--/--",3));
        tablaVentas.getItems().add(new StockVentas(4, "Agus","Resistor",122,"Completado","18/3/2001","18/3/2001",4));
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
    
    menu2Item1.setOnAction(e ->{
        primaryStage.setTitle("Registrar Cliente");
        root.getChildren().removeIf(node -> node != menuBar);
        Label clienteLabel = new Label("Ingrese los datos del cliente.");
        Label nombreCliente = new Label("Ingrese nombre:");
        Label apellidoCliente = new Label("Ingrese apellido:");
        Label dni = new Label("Ingrese DNI:");
        Label domicilio = new Label("Ingrese Direccion:");
        Label email = new Label("Ingrese EMAIL:");
        Label telefono = new Label("Ingrese numero de telefono:");
        
        TextField nombreInput = new TextField();
        TextField apellidoInput = new TextField();
        TextField dniInput = new TextField();
        TextField domicilioInput = new TextField();
        TextField emailInput = new TextField();
        TextField telefonoInput = new TextField();
        
        Button confirmarButton = new Button("Confirmar Registro");
        //confirmarButton.setOnAction(eh); Hacer logica de boton
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.addRow(0, clienteLabel);
        grid.addRow(1, nombreCliente,nombreInput,apellidoCliente,apellidoInput);
        grid.addRow(2, dni,dniInput);
        grid.addRow(3, domicilio,domicilioInput);
        grid.addRow(4, email,emailInput);
        grid.addRow(5, telefono,telefonoInput);
        grid.addRow(6,confirmarButton);
        root.getChildren().add(grid);
        
    });
    menu2Item2.setOnAction(e ->{
        primaryStage.setTitle("Clientes Registrados");
        root.getChildren().removeIf(node -> node != menuBar);
        TableView tablaClientes= new TableView();
        TableColumn<StockVentas, Double> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn<StockVentas, String> column2 = new TableColumn<>("Nombre");
        column2.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        TableColumn<StockVentas, String> column3 = new TableColumn<>("Apellido");
        column3.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        TableColumn<StockVentas, String> column4 = new TableColumn<>("DNI");
        column4.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        TableColumn<StockVentas, String> column5 = new TableColumn<>("Email");
        column5.setCellValueFactory(new PropertyValueFactory<>("Email"));
        TableColumn<StockVentas, String> column6 = new TableColumn<>("Telefono");
        column6.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        tablaClientes.getColumns().add(column1);
        tablaClientes.getColumns().add(column2);
        tablaClientes.getColumns().add(column3);
        tablaClientes.getColumns().add(column4);
        tablaClientes.getColumns().add(column5);
        tablaClientes.getColumns().add(column6);
        tablaClientes.getItems().add(new Cliente(1, "Juan","Perez","43807738","Constancia123","direccion@gmail.com","3547579693"));
        tablaClientes.getItems().add(new Cliente(2, "Carlos","Gomez","43807738","Constancia213","direccion@gmail.com","3547579693"));
        tablaClientes.getItems().add(new Cliente(3, "Fabricio","Posada","43807738","Constancia521","direccion@gmail.com","3547579693"));
        tablaClientes.getItems().add(new Cliente(4, "Agustin","Rodeyro","43807738","Constancia146","direccion@gmail.com","3547579693"));
        Label busquedaCliente = new Label("Ingrese nombre de cliente a buscar:");
        TextField clienteInput = new TextField();
        Button botonBusqueda = new Button("Buscar");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.addRow(0,busquedaCliente,clienteInput);
        grid.addRow(1,botonBusqueda);
        root.getChildren().add(tablaClientes);
        root.getChildren().add(grid);
    });
    menu3Item1.setOnAction(e ->{
        primaryStage.setTitle("Buscar Producto");
        root.getChildren().removeIf(node -> node != menuBar);
        TableView tablaProductos= new TableView();
        TableColumn<Producto, Integer> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn<Producto, String> column2 = new TableColumn<>("Nombre");
        column2.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        TableColumn<Producto, String> column3 = new TableColumn<>("Precio");
        column3.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        TableColumn<Producto, String> column4 = new TableColumn<>("Cantidad");
        column4.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        tablaProductos.getColumns().add(column1);
        tablaProductos.getColumns().add(column2);
        tablaProductos.getColumns().add(column3);
        tablaProductos.getColumns().add(column4);
        tablaProductos.getItems().add(new Producto(1,"Transistor","$350000",300));
        tablaProductos.getItems().add(new Producto(2,"Cable","$100",500));
        tablaProductos.getItems().add(new Producto(3,"Bobina","$50123",20));
        tablaProductos.getItems().add(new Producto(4,"Amperimetro","$45000",150));
        Label busquedaProducto = new Label("Ingrese nombre de Producto");
        TextField productoInput = new TextField();
        Button botonBusqueda = new Button("Buscar");
        Button botonCarga = new Button("Cargar Producto");
        Button botonModificar = new Button("Modificar Productos");
        Label cargarProducto = new Label("Cargar un nuevo producto");
        Label nuevoProducto = new Label("Ingrese Nombre Producto: ");
        Label precio = new Label("Ingrese Precio");
        Label cantidad = new Label("Ingrese Cantidad");
        Label modificar = new Label("Modificar Productos");
        TextField nuevoProductoInput = new TextField();
        TextField precioInput = new TextField();
        TextField cantidadInput = new TextField();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.addRow(0,busquedaProducto,productoInput);
        grid.addRow(1,botonBusqueda);
        grid.addRow(2,cargarProducto);
        grid.addRow(3,nuevoProducto,nuevoProductoInput);
        grid.addRow(4,precio,precioInput);
        grid.addRow(5,cantidad,cantidadInput);
        grid.addRow(6,botonCarga);
        grid.addRow(7,modificar,botonModificar);
        root.getChildren().add(tablaProductos);
        root.getChildren().add(grid);
        
    
    
    
    });
    
    
    
    // Implementar el resto de manejadores de eventos para las otras opciones del menú...
}

}
