package ui;

import model.Product;
import model.ShoppingList;

import java.util.ArrayList;
import java.util.Scanner;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

// used JsonSerializationDemo as reference

// shopping list tracker application
public class ShoppingListApp {
    private Product product;
    private ShoppingList shoppingList;
    private ArrayList<ShoppingList> shoppingLists = new ArrayList<>();
    private static final String JSON_STORE = "./data/shoppinglist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: runs shopping list tracker application
    public ShoppingListApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runShoppingList();
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: runs main menu
    public void runShoppingList() {
        mainMenu();
    }

    // REQUIRES: if '+' is entered, a shopping list must already be created
    // MODIFIES: nothing
    // EFFECTS: opens main menu
    @SuppressWarnings("methodlength")
    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        displayMainMenu();

        String input = scanner.nextLine();

        if (input.equals(">")) {
            createShoppingList();
        } else if (input.equals("+")) {
            whetherAddProduct();
        } else if (input.equals("-")) {
            viewShoppingLists();
        } else if (input.equals("=")) {
            viewProducts();
        } else if (input.equals("/")) {
            viewProduct();
        } else if (input.equals(".")) {
            saveShoppingList();
        } else if (input.equals(",")) {
            loadShoppingList();
        } else if (input.equals("!")) {
            System.out.println("Closing shopping list application...");
        } else if (input.equals("<")) {
            mainMenu();
        } else {
            System.out.println("Please select an option!");
            mainMenu();
        }
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: prints main menu
    public void displayMainMenu() {
        System.out.println("Main Menu:" + "\nCreate New Shopping List: Enter '>'"
                + "\nCreate New Product: Enter '+'"
                + "\nView Shopping Lists: Enter '-'"
                + "\nView Products in Selected Shopping List: Enter '='"
                + "\nView Product Details: Enter '/'"
                + "\nSave Shopping List to File: Enter '.'"
                + "\nLoad Shopping List from File: Enter ','"
                + "\nQuit Application: Enter '!'"
                + "\nReturn to Main Menu: Enter '<'");
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: creates shopping list with user input
    public void createShoppingList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input Shopping List Name: ");
        String name = scanner.nextLine();
        System.out.println("Your Shopping List: " + name);

        shoppingList = new ShoppingList(name);

        shoppingLists.add(shoppingList);
        whetherAddProduct();
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: create and add product with user input
    public void addShoppingListProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input Product Name: ");
        String name = scanner.nextLine();

        System.out.println("Input Product Price: ");
        double price = scanner.nextDouble();

        System.out.println("Input Product Discount: ");
        double discount = scanner.nextDouble();

        System.out.println("Input Product Code: ");
        String code = scanner.next();

        System.out.println("Input Product Link: ");
        String link = scanner.next();

        product = new Product(name, price, discount, code, link);
        shoppingList.addProduct(product);
        System.out.println("Your product has been added!");
        System.out.println("Returning to main menu...");
        mainMenu();
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: determines whether to add product based on user input
    public void whetherAddProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'add' to add a product.");
        String input = scanner.nextLine();

        if (input.equals("add")) {
            addShoppingListProduct();
        } else {
            whetherAddProduct();
        }
    }

    // REQUIRES: a shopping list must already be created
    // MODIFIES: this
    // EFFECTS: views all shopping lists created
    public void viewShoppingLists() {
        System.out.println("Your Shopping Lists:");
        for (ShoppingList shoppingList : shoppingLists) {
            System.out.println(shoppingList.getName());
        }

        mainMenu();
    }

    // REQUIRES: a shopping list must already be created
    // MODIFIES: this
    // EFFECTS: selects a shopping list
    public ShoppingList selectShoppingList() {
        ShoppingList selectedShoppingList = new ShoppingList("placeholder");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your selected shopping list:");
        String input = scanner.nextLine();

        for (ShoppingList shoppingList : shoppingLists) {
            if (input.equals(shoppingList.getName())) {
                selectedShoppingList = shoppingList;
            }
        }
        return selectedShoppingList;
    }

    // REQUIRES: a shopping list must already be created
    // MODIFIES: this
    // EFFECTS: views all products in selected shopping list
    public void viewProducts() {
        ShoppingList shoppingListProduct = selectShoppingList();
        System.out.println("Your Products in " + shoppingListProduct.getName() + ":");

        for (Product product : shoppingListProduct.getProducts()) {
            System.out.println(product.getName());
        }

        mainMenu();
    }

    // REQUIRES: a product must already be created
    // MODIFIES: this
    // EFFECTS: selects a product in a selected shopping list
    public Product selectProduct() {
        ShoppingList selectedShoppingList = selectShoppingList();

        Product selectedProduct = new Product("",0,0,"","");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your selected product:");
        String input = scanner.nextLine();

        for (Product product : selectedShoppingList.getProducts()) {
            if (input.equals(product.getName())) {
                selectedProduct = product;
            }
        }
        return selectedProduct;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: views details of selected product in selected shopping list
    public void viewProduct() {
        Product product = selectProduct();
        System.out.println("Your Product:"
                + "\nName: " + product.getName()
                + "\nPrice: " + product.getPrice()
                + "\nDiscount: " + product.getDiscount()
                + "\nCode: " + product.getCode()
                + "\nLink: " + product.getLink()
                + "\nStatus: " + product.getStatus());

        mainMenu();
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: saves the shopping list to file
    private void saveShoppingList() {
        try {
            jsonWriter.open();
            jsonWriter.write(shoppingList);
            jsonWriter.close();
            System.out.println("Saved " + shoppingList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: loads shopping list from file
    private void loadShoppingList() {
        try {
            shoppingList = jsonReader.read();
            System.out.println("Loaded " + shoppingList.getName() + " from " + JSON_STORE);

            shoppingLists.add(shoppingList);
            mainMenu();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
