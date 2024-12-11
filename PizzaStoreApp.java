//CSC210
//Group 11: Asmaa Ait Hammou – Caleb Adjei
//Project Version 1

package com.example.csc210gui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PizzaStoreApp extends Application {

    private PizzaStore pizzaStore;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create pizzaStore object
        pizzaStore = new PizzaStore();

        // Ask for user authentication
        login(primaryStage);
    }

    private void login(Stage primaryStage) {
        //  Create UI elements for username and password input
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");


        // Set up the layout for the login screen
        GridPane loginGrid = new GridPane();
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setPadding(new Insets(40, 40, 40, 40));
        loginGrid.add(usernameLabel, 0, 0);
        loginGrid.add(usernameField, 1, 0);
        loginGrid.add(passwordLabel, 0, 1);
        loginGrid.add(passwordField, 1, 1);
        loginGrid.add(loginButton, 1, 2);

        // Set the scene for the login screen
        Scene loginScene = new Scene(loginGrid, 350, 200);

        // Handle login button action
        loginButton.setOnAction(e -> {
            // Validate login credentials
            if (validateLogin(usernameField.getText(), passwordField.getText())) {
                // Display menu
                displayMenu(primaryStage);
            } else {
                // Show an alert for invalid login
                showAlert("Invalid Login", "Incorrect username or password. Try again.");
            }
        });

        // Set button event handler for login
        loginButton.setOnAction(e -> {
            // Validate login credentials
            if (validateLogin(usernameField.getText(), passwordField.getText())) {
                // Display menu
                displayMenu(primaryStage);
            } else {
                // Show an alert for invalid login
                showAlert("Invalid Login", "Incorrect username or password. Try again.");
            }
        });

        // Show login screen
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Pizza Store Login");
        primaryStage.show();
    }

    private boolean validateLogin(String username, String password) {
        // Check if input matches the stored login credentials
        // Use the Pizza Store class methods for validation
        return username.equals(pizzaStore.getUsername()) && password.equals(pizzaStore.getPassword());
    }

    private void displayMenu(Stage primaryStage) {
        // Create layout for pizza menu
        GridPane menuGrid = new GridPane();
        menuGrid.setHgap(10);
        menuGrid.setVgap(10);
        menuGrid.setPadding(new Insets(40, 40, 40, 40));

        // Populate menu with item names and prices
        String[] itemNames = pizzaStore.getItemNames();
        for (int i = 0; i < itemNames.length; i++) {
            Label itemNameLabel = new Label(itemNames[i]);
            double itemPrice = pizzaStore.getPrice(itemNames[i]);
            Label itemPriceLabel = new Label("$" + itemPrice);
            menuGrid.add(itemNameLabel, 0, i);
            menuGrid.add(itemPriceLabel, 1, i);
        }

        // Create input fields, button, and label for cost display
        TextField itemTextField = new TextField();
        TextField quantityTextField = new TextField();
        Button calculateButton = new Button("Calculate Cost");
        Label costDetailsLabel = new Label();

        // Add input fields, button, and cost details label to the layout
        menuGrid.add(new Label("Select Item:"), 0, itemNames.length);
        menuGrid.add(itemTextField, 1, itemNames.length);
        menuGrid.add(new Label("Enter Quantity:"), 0, itemNames.length + 1);
        menuGrid.add(quantityTextField, 1, itemNames.length + 1);
        menuGrid.add(calculateButton, 0, itemNames.length + 2);
        menuGrid.add(costDetailsLabel, 0, itemNames.length + 3, 2, 1); // Span across two columns

        // Set up the menu scene
        Scene menuScene = new Scene(menuGrid, 500, 500);

        // Handle calculation button action
        calculateButton.setOnAction(e -> {
            // Calculate and display cost
            String costDetails = calculateCost(itemTextField.getText(), quantityTextField.getText());
            costDetailsLabel.setText(costDetails); // Update the label with cost details
        });

        // Show the menu screen
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Pizza Store Menu");
        primaryStage.show();
    }

    private String calculateCost(String selectedItem, String quantity) {
        try {
            // Validate the quantity input
            int quantityValue = Integer.parseInt(quantity);
            double unitCost = pizzaStore.getPrice(selectedItem);
            if (unitCost != -1) {
                // Calculate pre-cost, tax, and total cost
                double preCost = pizzaStore.calculateCost(selectedItem, quantityValue);
                double taxes = pizzaStore.calculateTaxes(preCost);
                double totalCost = pizzaStore.calculateTotalCost(preCost, taxes);

                // Return cost details
                return String.format("Pre-cost: $%.2f\nTaxes: $%.2f\nTotal Cost: $%.2f", preCost, taxes, totalCost);
            } else {
                // Return an error message for invalid pizza selection
                return "Invalid Item: Please select a valid item from the menu.";
            }
        } catch (NumberFormatException e) {
            // Return an error message for invalid quantity
            return "Invalid Input: Please enter a valid quantity.";
        }
    }

    private void showAlert(String title, String content) {
        // // Display an alert dialog with the given title and content
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
