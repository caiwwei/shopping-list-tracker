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
// learned swing syntax from https://web.mit.edu/6.005/www/sp14/psets/ps4/java-6-tutorial/components.html
// learned swing syntax from https://docs.oracle.com/javase/tutorial/uiswing/components/index.html
// troubleshot custom icons from https://www.youtube.com/watch?v=U3ACpPu9_kE
// troubleshot custom icons from https://www.youtube.com/watch?v=By2wARNZ-QQ
// troubleshot custom icons from https://stackoverflow.com/questions/33961793/custom-icon-joptionpane-showinputdialog
// learned string builder from https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html
// troubleshot parse double from https://www.geeksforgeeks.org/double-parsedouble-method-in-java-with-examples/
// troubleshot panel size from https://coderanch.com/t/750824/java/add-panels-JFrame-set-size
// troubleshot panel size from https://stackoverflow.com/questions/18767367/size-of-text-area-in-java

// shopping list tracker GUI application
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
    private Icon icon = new ImageIcon("icon.png");

    private ArrayList<ShoppingList> shoppingLists;

    private static final String JSON_STORE = "./data/shoppinglist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Dimension minSize = new Dimension(200, 400);

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

        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addShoppingListProduct();
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

        JButton saveShoppingListButton = new JButton("Save Shopping List");
        saveShoppingListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveShoppingList();
            }
        });

        JButton loadShoppingListButton = new JButton("Load Shopping List");
        loadShoppingListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadShoppingList();
            }
        });

        panel.add(createListButton);
        panel.add(addProductButton);
        panel.add(viewListsButton);
        panel.add(viewProductsButton);
        panel.add(saveShoppingListButton);
        panel.add(loadShoppingListButton);

        productListArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(productListArea);
        panel.add(scrollPane);

        shoppingListModel = new DefaultComboBoxModel<>();
        shoppingListComboBox = new JComboBox<>(shoppingListModel);
        panel.add(shoppingListComboBox);

        productListArea.setMinimumSize(minSize);
        scrollPane.setMinimumSize(minSize);
        shoppingListComboBox.setMinimumSize(minSize);
        productListArea.setPreferredSize(minSize);
        scrollPane.setPreferredSize(minSize);
        shoppingListComboBox.setPreferredSize(minSize);

        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: creates shopping list with user input
    private void createShoppingList() {
        String name = (String) JOptionPane.showInputDialog(frame, "Enter Shopping List Name:",
                "Shopping List", JOptionPane.INFORMATION_MESSAGE, icon, null, "");
        if (name != null && !name.isEmpty()) {
            ShoppingList shoppingList = new ShoppingList(name);
            shoppingLists.add(shoppingList);
            shoppingListModel.addElement(name);
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a name.", "Shopping List",
                    JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: create and add product with user input
    @SuppressWarnings("methodlength")
    private void addShoppingListProduct() {
        if (shoppingListComboBox.getItemCount() == 0) {
            JOptionPane.showMessageDialog(frame, "Please create a shopping list.", "Shopping List",
                    JOptionPane.INFORMATION_MESSAGE, icon);
            return;
        }

        if (productNameField.getText().equals("") || priceField.getText().equals("")
                || discountField.getText().equals("") || codeField.getText().equals("")
                || linkField.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Please input all the fields", "Shopping List",
                    JOptionPane.INFORMATION_MESSAGE, icon);
            return;
        }

        String productName = productNameField.getText();
        double price = Double.parseDouble(priceField.getText());
        double discount = Double.parseDouble(discountField.getText());
        String code = codeField.getText();
        String link = linkField.getText();

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
            JOptionPane.showMessageDialog(frame, "Please create a shopping list.", "Shopping List",
                    JOptionPane.INFORMATION_MESSAGE, icon);
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
            JOptionPane.showMessageDialog(frame, "No shopping list available to save.", "Shopping List",
                    JOptionPane.INFORMATION_MESSAGE, icon);
            return;
        }

        int selectedIndex = shoppingListComboBox.getSelectedIndex();
        ShoppingList selectedShoppingList = shoppingLists.get(selectedIndex);

        try {
            jsonWriter.open();
            jsonWriter.write(selectedShoppingList);
            jsonWriter.close();

            JOptionPane.showMessageDialog(frame, "Shopping list saved!", "Shopping List",
                    JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE,
                    "Shopping List", JOptionPane.INFORMATION_MESSAGE, icon);
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

            JOptionPane.showMessageDialog(frame, "Shopping list loaded!", "Shopping List",
                    JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Unable to read from file: " + JSON_STORE,
                    "Shopping List", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }

    public static void main(String[] args) {
        new ShoppingListGUI();
    }
}