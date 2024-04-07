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
        EventLog.getInstance().logEvent(new Event("Shopping list created."));
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
        EventLog.getInstance().logEvent(new Event("Product added."));
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

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: signals that lowest price was found
    public void lowestPrice() {
        EventLog.getInstance().logEvent(new Event("Product with lowest price found."));
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: signals that highest price was found
    public void highestPrice() {
        EventLog.getInstance().logEvent(new Event("Product with highest price found."));
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
