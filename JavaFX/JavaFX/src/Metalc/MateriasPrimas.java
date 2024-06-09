package Metalc;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.Alert.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class MateriasPrimas {
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
                materiasPrimasInterfaz(primaryStage);
            } else {
                System.out.println("Usuario o contraseña incorrectos");
                alertaError("Error", "Usuario incorrecto o no existente");
                // Aquí puedes mostrar un mensaje de error a la interfaz
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar errores de conexión y otros
        }
    }
    
    public void materiasPrimasInterfaz(Stage primaryStage){
        primaryStage.setTitle("Materias Primas");
    MenuBar menuBar = new MenuBar();
    Menu menu1 = new Menu("Stock");
    Menu menu2 = new Menu("Pedidos");
    MenuItem menu1Item1 = new MenuItem("Consultar Stock");
    MenuItem menu2Item1 = new MenuItem("Registrar nuevo pedido");
    MenuItem menu2Item2 = new MenuItem("Registrar nuevo proveedor");
    MenuItem menu2Item3 = new MenuItem("Consultar registro de Pedidos");
    menu1.getItems().add(menu1Item1);
    menu2.getItems().add(menu2Item1);
    menu2.getItems().add(menu2Item2);
    menu2.getItems().add(menu2Item3);
    menuBar.getMenus().add(menu1);
    menuBar.getMenus().add(menu2);
    VBox root = new VBox(menuBar);
    Scene scene = new Scene(root, 960, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
    
    menu1Item1.setOnAction(event -> {
        primaryStage.setTitle("Stock Materias Primas");
        root.getChildren().removeIf(node -> node != menuBar);
        TableView tablaMateriaPrima= new TableView();
        TableColumn<MateriaPrima, Integer> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn<MateriaPrima, String> column2 = new TableColumn<>("Nombre");
        column2.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        TableColumn<MateriaPrima, String> column3 = new TableColumn<>("Cantidad");
        column3.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        tablaMateriaPrima.getColumns().add(column1);
        tablaMateriaPrima.getColumns().add(column2);
        tablaMateriaPrima.getColumns().add(column3);
        tablaMateriaPrima.getItems().add(new MateriaPrima(1,"Cobre","300G"));
        tablaMateriaPrima.getItems().add(new MateriaPrima(2,"Esaño","1KG"));
        tablaMateriaPrima.getItems().add(new MateriaPrima(3,"Bronce","25KG"));
        
        Label busquedaMateria = new Label("Buscar materia Prima");
        Label nombreMateria = new Label("Ingrese nombre de Materia Prima");
        TextField nombreInput = new TextField();
        Label idMateria = new Label("Ingrese ID de Materia Prima");
        TextField idInput = new TextField();
        Button botonBuscar = new Button("Buscar");
        
        Label cargaMateria = new Label("Cargar Materia Prima");
        Label nuevaMateria = new Label("Ingrese nombre de Materia Prima");
        TextField nuevaInput = new TextField();
        Label cantidad = new Label("Ingrese cantidad");
        TextField cantidadInput = new TextField();
        Button botonCargar = new Button("Cargar");
        
        Label modificarMateria = new Label("Modificar Materia Prima");
        Button botonModificar = new Button("Modificar");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.addRow(0,busquedaMateria);
        grid.addRow(1,nombreMateria,nombreInput);
        grid.addRow(2,idMateria,idInput);
        grid.addRow(3,botonBuscar);
        grid.addRow(4,cargaMateria);
        grid.addRow(5,nuevaMateria,nuevaInput);
        grid.addRow(6,cantidad,cantidadInput);
        grid.addRow(7,botonCargar);
        grid.addRow(8,modificarMateria,botonModificar);
        root.getChildren().add(tablaMateriaPrima);
        root.getChildren().add(grid);
    });
    
    
    menu2Item1.setOnAction(event ->{
        
        primaryStage.setTitle("Registrar Pedido Materia Prima");
        root.getChildren().removeIf(node -> node != menuBar);
        Label nombreProveedor = new Label("Ingrese nombre del Proveedor:");
        TextField proveedorInput = new TextField();
        Label materiaSolicitada = new Label("Ingrese materia Prima solicitada:");
        TextField materiaInput = new TextField();
        Label cantidad = new Label("Ingrese cantidad:");
        TextField cantidadInput = new TextField();
        Label factura = new Label("Ingrese datos de la factura del pedido");
        Label numeroF = new Label("Numero Factura:");
        TextField numeroFInput = new TextField();
        Label fechaF = new Label("Fecha Factura:");
        TextField fechaFInput = new TextField();
        Label numeroRF = new Label("Numero Referencia Factura:");
        TextField numeroRFInput = new TextField();
        Label direccionF = new Label("Direccion: ");
        TextField direccionFInput = new TextField();
        Label telefonoF = new Label("Telefono: ");
        TextField telefonoFInput = new TextField();
        Label proveedorF = new Label("Proveedor: ");
        TextField proveedorFInput = new TextField();
        Label materiaF = new Label("Materias Solicitadas(M1,M2,M3):");
        TextField materiaFInput = new TextField();
        Label cantidadF = new Label("Cantidades(C1,C2,C3):");
        TextField cantidadFInput = new TextField();
        Label precioF = new Label("Precios Unitarios(P1,P2,P3):");
        TextField precioFInput = new TextField();
        Label montoF = new Label("Monto total:");
        TextField montoFInput = new TextField();
        Label estado = new Label("Seleccione un estado para la factura");
        SplitMenuButton buttonEstado = new SplitMenuButton();
        buttonEstado.setText("Seleccione Estado");
        MenuItem pendiente = new MenuItem("Pendiente");
        MenuItem completado = new MenuItem("Completada");
        buttonEstado.getItems().addAll(pendiente,completado);
        Button registrarButton = new Button("Registrar Pedido");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15, 15, 15, 15));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.addRow(0,nombreProveedor,proveedorInput);
        grid.addRow(1,materiaSolicitada,materiaInput,cantidad,cantidadInput);
        grid.addRow(2,factura);
        grid.addRow(3,numeroF,numeroFInput,fechaF,fechaFInput,numeroRF,numeroRFInput);
        grid.addRow(4,direccionF,direccionFInput,telefonoF,telefonoFInput,proveedorF,proveedorFInput);
        grid.addRow(5,materiaF,materiaFInput,cantidadF,cantidadFInput,precioF,precioFInput,montoF,montoFInput);
        grid.addRow(6,estado,buttonEstado);
        grid.addRow(7,registrarButton);
        root.getChildren().add(grid);
    });
    
    
    
    
    
    
    
    
    
    menu2Item2.setOnAction(event ->{});
    menu2Item3.setOnAction(event ->{});
    }
    
}
