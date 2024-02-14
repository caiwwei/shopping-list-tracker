package model;

import java.util.ArrayList;

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

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

}
