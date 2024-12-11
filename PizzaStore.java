//CSC210
//Group 11:Asmaa Ait Hammou – Caleb Adjei
//Project Version 1

package com.example.csc210gui;
public class  PizzaStore {
   // fields
    private String[][] loginInfo = {{"Baskin", "100 Chambers"}};
    private String[] itemNames = {"Margherita", "Pepperoni", "Veggie Supreme", "BBQ Chicken"};
    private double[] itemPrices = {12.99, 14.99, 13.99, 15.49};
    private final double taxRate = 0.08;

// methods
    // Method to get the username
    public String getUsername() {

        return loginInfo[0][0];
    }
    // Method to get the password
    public String getPassword() {

        return loginInfo[0][1];
    }
    // Method to get the price of a specific item by name
    public double getPrice(String itemName) {
        for (int i = 0; i < itemNames.length; i++) {
            if (itemNames[i].equalsIgnoreCase(itemName)) {
                return itemPrices[i];
            }
        }
        return -1; // Return -1 if the item is not found
    }
    // Method to get the list of available pizza item names
    public String[] getItemNames() {

        return itemNames;
    }
    // Method to calculate the cost of a specific item based on quantity
    public double calculateCost(String selectedItem, int quantity) {
        double itemPrices = getPrice(selectedItem);
        if (itemPrices != -1) {
            return itemPrices * quantity;
        }
        return -1; // Return -1 if the item is not found
    }
    // Method to calculate the taxes for a given pre-tax cost
    public double calculateTaxes(double preCost) {
        return preCost * taxRate;
    }
    // Method to calculate the total cost, including taxes
    public double calculateTotalCost(double preCost, double taxes) {
        return preCost + taxes;
    }
}
