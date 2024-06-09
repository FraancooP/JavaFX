import java.util.Stack;

public class TestCalcuExp {

    public static void main(String[] args) {
        String expresion = "2+3-54*(25+6)/8";
        double resultado = evaluarExpresion(expresion);
        System.out.println("Resultado: " + resultado);
    }

    public static double evaluarExpresion(String expresion) {
        // Eliminar espacios en blanco de la expresión
        expresion = expresion.replaceAll("\\s+", "");

        // Stacks para operandos y operadores
        Stack<Double> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        // Caracter de finalización para facilitar la evaluación al final
        expresion += "#";

        // Índice para recorrer la expresión
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
