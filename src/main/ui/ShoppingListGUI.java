package ui;

import model.Product;
import model.ShoppingList;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// used JsonSerializationDemo as reference

// shopping list tracker application
public class ShoppingListGUI {
    private JFrame frame;

    private JTextField productNameField;
    private JTextField priceField;
    private JTextField discountField;
    private JTextField codeField;
    private JTextField linkField;
    private JTextArea productListArea;

    private JComboBox<String> shoppingListComboBox;
    private DefaultComboBoxModel<String> shoppingListModel;

    private ArrayList<ShoppingList> shoppingLists;

    private static final String JSON_STORE = "./data/shoppinglist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: runs shopping list tracker application
    public ShoppingListGUI() {
        runShoppingList();
        shoppingLists = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: creates GUI for shopping list
    @SuppressWarnings("methodlength")
    private void runShoppingList() {
        frame = new JFrame("Shopping List App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        JLabel productNameLabel = new JLabel("Product Name:");
        productNameField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        JLabel discountLabel = new JLabel("Discount:");
        discountField = new JTextField();
        JLabel codeLabel = new JLabel("Code:");
        codeField = new JTextField();
        JLabel linkLabel = new JLabel("Link:");
        linkField = new JTextField();

        panel.add(productNameLabel);
        panel.add(productNameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(discountLabel);
        panel.add(discountField);
        panel.add(codeLabel);
        panel.add(codeField);
        panel.add(linkLabel);
        panel.add(linkField);

        JButton createListButton = new JButton("Create Shopping List");
        createListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createShoppingList();
            }
        });

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProductToList();
            }
        });

        JButton viewListsButton = new JButton("View Shopping Lists");
        viewListsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewShoppingLists();
            }
        });

        JButton viewProductsButton = new JButton("View Products");
        viewProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });

        JButton saveButton = new JButton("Save Shopping List");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveShoppingList();
            }
        });

        JButton loadButton = new JButton("Load Shopping List");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadShoppingList();
            }
        });

        panel.add(createListButton);
        panel.add(addButton);
        panel.add(viewListsButton);
        panel.add(viewProductsButton);
        panel.add(saveButton);
        panel.add(loadButton);

        productListArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(productListArea);
        panel.add(scrollPane);

        shoppingListModel = new DefaultComboBoxModel<>();
        shoppingListComboBox = new JComboBox<>(shoppingListModel);
        panel.add(shoppingListComboBox);

        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: creates shopping list with user input
    private void createShoppingList() {
        String name = JOptionPane.showInputDialog(frame, "Enter Shopping List Name:");
        if (name != null && !name.isEmpty()) {
            ShoppingList shoppingList = new ShoppingList(name);
            shoppingLists.add(shoppingList);
            shoppingListModel.addElement(name);
        }
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: create and add product with user input
    private void addProductToList() {
        String productName = productNameField.getText();
        double price = Double.parseDouble(priceField.getText());
        double discount = Double.parseDouble(discountField.getText());
        String code = codeField.getText();
        String link = linkField.getText();

        if (shoppingListComboBox.getItemCount() == 0) {
            JOptionPane.showMessageDialog(frame, "Please create a shopping list.");
            return;
        }

        int selectedIndex = shoppingListComboBox.getSelectedIndex();
        ShoppingList selectedShoppingList = shoppingLists.get(selectedIndex);
        Product product = new Product(productName, price, discount, code, link);
        selectedShoppingList.addProduct(product);

        productNameField.setText("");
        priceField.setText("");
        discountField.setText("");
        codeField.setText("");
        linkField.setText("");

        productListArea.setText(selectedShoppingList.toString());
    }

    // REQUIRES: a shopping list must already be created
    // MODIFIES: this
    // EFFECTS: views all shopping lists created
    private void viewShoppingLists() {
        StringBuilder sb = new StringBuilder();
        for (ShoppingList list : shoppingLists) {
            sb.append(list.getName()).append("\n");
        }
        productListArea.setText(sb.toString());
    }

    // REQUIRES: a shopping list must already be created
    // MODIFIES: this
    // EFFECTS: views all products in selected shopping list
    private void viewProducts() {
        if (shoppingListComboBox.getItemCount() == 0) {
            JOptionPane.showMessageDialog(frame, "Please create a shopping list.");
            return;
        }

        int selectedIndex = shoppingListComboBox.getSelectedIndex();
        ShoppingList selectedShoppingList = shoppingLists.get(selectedIndex);
        StringBuilder sb = new StringBuilder();
        for (Product product : selectedShoppingList.getProducts()) {
            sb.append("Name: ").append(product.getName()).append(", ")
                    .append("Price: ").append(product.getPrice()).append(", ")
                    .append("Discount: ").append(product.getDiscount()).append(", ")
                    .append("Code: ").append(product.getCode()).append(", ")
                    .append("Link: ").append(product.getLink()).append(", ")
                    .append("Status: ").append(product.getStatus()).append("\n");
        }
        productListArea.setText(sb.toString());
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: saves the shopping list to file
    private void saveShoppingList() {
        if (shoppingListComboBox.getItemCount() == 0) {
            JOptionPane.showMessageDialog(frame, "No shopping list available to save.");
            return;
        }

        int selectedIndex = shoppingListComboBox.getSelectedIndex();
        ShoppingList selectedShoppingList = shoppingLists.get(selectedIndex);

        try {
            jsonWriter.open();
            jsonWriter.write(selectedShoppingList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Shopping list saved!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Error: Unable to write to file: " + JSON_STORE);
        }
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: loads shopping list from file
    private void loadShoppingList() {
        try {
            ShoppingList shoppingList = jsonReader.read();
            shoppingLists.add(shoppingList);
            shoppingListModel.addElement(shoppingList.getName());
            JOptionPane.showMessageDialog(frame, "Shopping list loaded!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error: Unable to read from file: " + JSON_STORE);
        }
    }

    public static void main(String[] args) {
        new ShoppingListGUI();
    }
}