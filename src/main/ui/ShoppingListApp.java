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
    // EFFECTS: runs create shopping list
    public void runShoppingList() {
        createShoppingList();
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
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: determines whether to add product based on user input
    public void whetherAddProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'add' to add product.");
        String input = scanner.nextLine();

        if (input.equals("add")) {
            addShoppingListProduct();
        } else {
            whetherAddProduct();
        }
    }
}
