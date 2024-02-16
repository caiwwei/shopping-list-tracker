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

        System.out.println("Main Menu:" + "\nCreate New Shopping List: Enter 'list'"
                + "\nCreate New Product: Enter 'product'" + "\nView Shopping Lists: Enter 'view lists'"
                + "\nView Products in Selected Shopping List: Enter 'view products'"
                + "\nView Product Details: Enter 'view product'"
                + "\nReturn to Main Menu: Enter 'main'");
        String input = scanner.nextLine();

        if (input.equals("list")) {
            createShoppingList();
        } else if (input.equals("product")) {
            whetherAddProduct();
        } else if (input.equals("view lists")) {
            viewShoppingLists();
        } else if (input.equals("view products")) {
            viewProducts();
        } else if (input.equals("view product")) {
            viewProduct();
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
}
