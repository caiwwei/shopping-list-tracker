package model;

import org.json.JSONObject;
import persistence.Writable;

// used JsonSerializationDemo as reference

// represents a single product with name, price, discount, code, link, and status

public class Product implements Writable {
    private String name;        // name of product
    private double price;       // price of product
    private double discount;    // discount percentage (in decimal form)
    private String code;        // discount code
    private String link;        // link to product
    private boolean status;     // true if product has been bought

    // constructor
    public Product(String productName, double productPrice, double productDiscount, String productCode,
                   String productLink) {
        this.name = productName;
        this.price = productPrice;
        this.discount = productDiscount;
        this.code = productCode;
        this.link = productLink;
        this.status = false;
    }

    // getter
    public String getName() {
        return name;
    }

    // getter
    public double getPrice() {
        return price;
    }

    // getter
    public double getDiscount() {
        return discount;
    }

    // getter
    public String getCode() {
        return code;
    }

    // getter
    public String getLink() {
        return link;
    }

    // getter
    public boolean getStatus() {
        return status;
    }

    // setter
    public void inputName(String productName) {
        this.name = productName;
    }

    // setter
    public void inputPrice(double productPrice) {
        this.price = productPrice;
    }

    // setter
    public void inputDiscount(double productDiscount) {
        this.discount = productDiscount;
    }

    // setter
    public void inputCode(String productCode) {
        this.code = productCode;
    }

    // setter
    public void inputLink(String productLink) {
        this.link = productLink;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes status of product to true
    public void productBought() {
        this.status = true;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("discount", discount);
        json.put("code", code);
        json.put("link", link);
        return json;
    }
}
