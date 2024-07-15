package fr.afpa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Additionneur extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label labelTitle = new Label("Calculator");

        // Create a VBox layout
        VBox content = new VBox();

        // Create a TextArea
        TextArea calculateZone = new TextArea();
        calculateZone.setPrefHeight(100);

        // Add TextArea to the VBox
        content.getChildren().add(calculateZone);

        // Create a TilePane for the number buttons
        TilePane tilePane = new TilePane();
        tilePane.setHgap(10);
        tilePane.setVgap(10);

        // Add 10 buttons to the TilePane
        for (int i = 0; i <= 9; i++) {
            int index = i; // local variable to use in the lambda expression
            Button numberButton = new Button(String.valueOf(index));
            numberButton.setOnAction(event -> {
                // Update the TextArea with the button number and a plus sign
                calculateZone.setText(calculateZone.getText() + index + "+");
            });
            tilePane.getChildren().add(numberButton);
        }

        // Add the TilePane to the VBox
        content.getChildren().add(tilePane);

        // Create action buttons
        Button buttonCalcul = new Button("Calculate");
        Button buttonClear = new Button("Empty");
        Button buttonExit = new Button("Exit");

        buttonClear.setOnAction(event -> {
            showPopupAndClearText(calculateZone);
        });

        buttonCalcul.setOnAction(event -> {
            // Use the calculate method from the CalculButton package
            String expression = calculateZone.getText();
            // For simplicity, we'll just remove the last '+' sign if it exists
            if (expression.endsWith("+")) {
                expression = expression.substring(0, expression.length() - 1);
            }
            double result = CalculButton.calculate(expression);
            calculateZone.setText(String.valueOf(result));
        });
        buttonExit.setOnAction(event->{
        primaryStage.close();
        });

        // Add the action buttons to an HBox
        HBox hboxButtons = new HBox(10, buttonCalcul, buttonClear, buttonExit);

        // Add the HBox to the VBox
        content.getChildren().add(hboxButtons);

        // Create a ScrollPane and set its content to the VBox
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(400);
        scrollPane.setPrefHeight(180);

        // Main layout
        VBox mainLayout = new VBox(10, labelTitle, scrollPane);

        // Create the initial scene and set it on the stage
        Scene initialScene = new Scene(mainLayout);
        primaryStage.setScene(initialScene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }

    private void showPopupAndClearText(TextArea calculateZone) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to delete your entry?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            calculateZone.clear();
        }
    }
}
