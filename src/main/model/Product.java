package model;

// represents a single product

public class Product {
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

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes name of product
    public void inputName(String productName) {
        this.name = productName;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes price of product
    public void inputPrice(double productPrice) {
        this.price = productPrice;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes discount of product
    public void inputDiscount(double productDiscount) {
        this.discount = productDiscount;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes code of product
    public void inputCode(String productCode) {
        this.code = productCode;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes link of product
    public void inputLink(String productLink) {
        this.link = productLink;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes status of product to true
    public void productBought() {
        this.status = true;
    }
}
