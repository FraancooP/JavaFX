import java.util.Stack;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MyHandCalculadora {
    private TextField barraDeEntrada;
    private String expresion = "0.0";
    public VBox crearCalculadora() {
        Label texto1 = new Label("Ingrese Operacion");
        VBox calculadoraPane = new VBox();
        calculadoraPane.setPadding(new Insets(10));
        calculadoraPane.setSpacing(10);
        // Crear campo de entrada
        barraDeEntrada = new TextField();
        barraDeEntrada.setPromptText("0.0");
        // Crear contenedor para botones
        HBox buttonsPane = new HBox();
        buttonsPane.setSpacing(5);
        // Crear botones
        Button suma = new Button("+");
        Button resta = new Button("-");
        Button multiplicacion = new Button("*");
        Button division = new Button("/");
        Button igualdad = new Button("=");
        Button parentesis = new Button ("(");
        Button parentesis2 = new Button (")");
        // Añadir acciones a los botones
        suma.setOnAction(e -> {
            barraDeEntrada.appendText("+");
        });
        resta.setOnAction(e -> {
            barraDeEntrada.appendText("-");
        });
        multiplicacion.setOnAction(e -> {
            barraDeEntrada.appendText("*");
        });
        division.setOnAction(e -> {
            barraDeEntrada.appendText("/");
        });
        igualdad.setOnAction(e -> {
            expresion = barraDeEntrada.getText();
            expresion = expresion.replaceAll("\\s+", "");//Sacamos espacios.
            double resultado = realizarOperacion(expresion);
            System.out.println(resultado);
            
        });
        parentesis.setOnAction(e ->{
            barraDeEntrada.appendText("(");
        });
        parentesis2.setOnAction(e ->{
            barraDeEntrada.appendText(")");
        });
        // Añadir botones al contenedor
        buttonsPane.getChildren().addAll(suma, resta, multiplicacion, division, igualdad,parentesis,parentesis2);

        // Crear campo de resultado
        // Añadir elementos al contenedor de la calculadora
        Label resultado = new Label();
        resultado.setText(expresion);
        calculadoraPane.getChildren().addAll(texto1,barraDeEntrada, buttonsPane,resultado);
        return calculadoraPane;
    }
    public static double realizarOperacion(String expresion) {
        Stack<Double> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();
        expresion += "#";//Metemos este simbolo que indica el fin de la expresion
        System.out.println(expresion);
        int index = 0;

        while (index < expresion.length() - 1) {
            char caracter = expresion.charAt(index);

            if (Character.isDigit(caracter)) {
                // Si el caracter es un dígito, leer el número completo
                StringBuilder numero = new StringBuilder();
                while (Character.isDigit(caracter) || caracter == '.') {
                    numero.append(caracter);
                    index++;
                    caracter = expresion.charAt(index);
                }
                operandos.push(Double.parseDouble(numero.toString()));
            } else if (esOperador(caracter)) {
                // Si el caracter es un operador, procesar según la precedencia
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(caracter)) {
                    aplicarOperacion(operandos, operadores.pop());
                }
                operadores.push(caracter);
                index++;
            } else if (caracter == '(') {
                // Si el caracter es un paréntesis de apertura, agregar al stack de operadores
                operadores.push(caracter);
                index++;
            } else if (caracter == ')') {
                // Si el caracter es un paréntesis de cierre, realizar operaciones hasta encontrar el paréntesis de apertura
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    aplicarOperacion(operandos, operadores.pop());
                }
                operadores.pop(); // Pop del paréntesis de apertura
                index++;
            } else {
                // Caracter no válido
                throw new IllegalArgumentException("Caracter no válido en la expresión: " + caracter);
            }
        }

        // Realizar operaciones restantes en los stacks
        while (!operadores.isEmpty()) {
            aplicarOperacion(operandos, operadores.pop());
        }

        // El resultado final debe estar en el stack de operandos
        return operandos.pop();
    }
    private static boolean esOperador(char caracter) {
        return caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/';
    }
    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
    private static void aplicarOperacion(Stack<Double> operandos, char operador) {
        double segundoOperando = operandos.pop();
        double primerOperando = operandos.pop();
        double resultado;
        switch (operador) {
            case '+':
                resultado = primerOperando + segundoOperando;
                break;
            case '-':
                resultado = primerOperando - segundoOperando;
                break;
            case '*':
                resultado = primerOperando * segundoOperando;
                break;
            case '/':
                resultado = primerOperando / segundoOperando;
                break;
            default:
                throw new IllegalArgumentException("Operador no válido: " + operador);
        }
        operandos.push(resultado);
    }
}
