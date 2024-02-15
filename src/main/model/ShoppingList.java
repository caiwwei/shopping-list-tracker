package model;

import java.util.ArrayList;

// represents a single shopping list with products

public class ShoppingList {
    private int amount;                        // amount of items in shopping list
    private String name;                       // name of the shopping list
    private ArrayList<Product> products;       // list of products in shopping list

    // constructor
    public ShoppingList(String listName) {
        this.amount = 0;
        this.name = listName;
        this.products = null;
    }

    // getter
    public int getAmount() {
        return amount;
    }

    // getter
    public String getName() {
        return name;
    }

    // getter
    public ArrayList<Product> getProducts() {
        return products;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: add a product to products
    public void addProduct(Product product){
        // TODO
    }

    // REQUIRES: product exists in products
    // MODIFIES: this
    // EFFECTS: remove a product from products
    public void deleteProduct(Product product){
        // TODO
    }

}
