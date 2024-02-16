package ui;

import model.Product;
import model.ShoppingList;

import java.util.ArrayList;
import java.util.Scanner;

// shopping list tracker application
public class ShoppingListApp {
    private Product product;
    private ShoppingList shoppingList;
    private ArrayList<ShoppingList> shoppingLists;

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: runs shopping list tracker application
    public ShoppingListApp() {
        runShoppingList();
    }

    // REQUIRES: nothing
    // MODIFIES: nothing
    // EFFECTS: runs main menu
    public void runShoppingList() {
        mainMenu();
    }

    // REQUIRES: if 'product' is entered, a shopping list must already be created
    // MODIFIES: nothing
    // EFFECTS: opens main menu
    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Main Menu:"
                + "\nCreate New Shopping List: Enter 'list'"
                + "\nCreate New Product: Enter 'product'"
                + "\nReturn to Main Menu: Enter 'main'");
        String input = scanner.nextLine();

        if (input.equals("list")) {
            createShoppingList();
        } else if (input.equals("product")) {
            whetherAddProduct();
        } else if (input.equals("main")) {
            mainMenu();
        } else {
            System.out.println("Please select an option!");
            mainMenu();
        }
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

        shoppingLists = new ArrayList<>();
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
}
