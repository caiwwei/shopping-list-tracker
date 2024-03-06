package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// used JsonSerializationDemo as reference

// represents a single shopping list with products

public class ShoppingList {
    private int amount;                        // amount of items in shopping list
    private String name;                       // name of the shopping list
    private ArrayList<Product> products;       // list of products in shopping list

    // constructor
    public ShoppingList(String listName) {
        this.amount = 0;
        this.name = listName;
        this.products = new ArrayList<>();
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
    // EFFECTS: add a product to products and increases amount by 1
    public void addProduct(Product product) {
        this.products.add(product);
        this.amount++;
    }

    // REQUIRES: product exists in products
    // MODIFIES: this
    // EFFECTS: remove a product from products and decreases amount by 1
    public void deleteProduct(Product product) {
        this.products.remove(product);
        this.amount--;
    }

    // REQUIRES: products has at least one product
    // MODIFIES: this
    // EFFECTS: return number of all bought items
    public int boughtProducts() {
        int boughtProductsCount = 0;

        for (Product product : products) {
            if (product.getStatus()) {
                boughtProductsCount++;
            }
        }

        return boughtProductsCount;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("products", productsToJson());
        return json;
    }

    // EFFECTS: returns products in this shopping list as a JSON array
    private JSONArray productsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product p : products) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
