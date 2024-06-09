import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculadora extends Application {

    private TextField numField1;
    private TextField numField2;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        numField1 = new TextField();
        GridPane.setConstraints(numField1, 0, 0);

        numField2 = new TextField();
        GridPane.setConstraints(numField2, 0, 1);

        Button addButton = new Button("+");
        GridPane.setConstraints(addButton, 1, 0);
        addButton.setOnAction(e -> addNumbers());

        Button subtractButton = new Button("-");
        GridPane.setConstraints(subtractButton, 1, 1);
        subtractButton.setOnAction(e -> subtractNumbers());

        Button multiplyButton = new Button("*");
        GridPane.setConstraints(multiplyButton, 2, 0);
        multiplyButton.setOnAction(e -> multiplyNumbers());

        Button divideButton = new Button("/");
        GridPane.setConstraints(divideButton, 2, 1);
        divideButton.setOnAction(e -> divideNumbers());

        resultLabel = new Label("Result:");
        GridPane.setConstraints(resultLabel, 0, 2);

        grid.getChildren().addAll(numField1, numField2, addButton, subtractButton, multiplyButton, divideButton, resultLabel);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addNumbers() {
        double num1 = Double.parseDouble(numField1.getText());
        double num2 = Double.parseDouble(numField2.getText());
        double result = num1 + num2;
        resultLabel.setText("Result: " + result);
    }

    private void subtractNumbers() {
        double num1 = Double.parseDouble(numField1.getText());
        double num2 = Double.parseDouble(numField2.getText());
        double result = num1 - num2;
        resultLabel.setText("Result: " + result);
    }

    private void multiplyNumbers() {
        double num1 = Double.parseDouble(numField1.getText());
        double num2 = Double.parseDouble(numField2.getText());
        double result = num1 * num2;
        resultLabel.setText("Result: " + result);
    }

    private void divideNumbers() {
        double num1 = Double.parseDouble(numField1.getText());
        double num2 = Double.parseDouble(numField2.getText());
        if (num2 == 0) {
            resultLabel.setText("Result: Cannot divide by zero");
        } else {
            double result = num1 / num2;
            resultLabel.setText("Result: " + result);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
