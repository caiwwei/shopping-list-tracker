package model;

// represents a single product

public class Product {
    private String name;     // name of product
    private int price;       // price of product
    private int discount;    // discount percentage (in decimal form)
    private String code;     // discount code
    private String link;     // link to product

    // constructor
    public Product(String productName, int productPrice, int productDiscount, String productCode, String productLink) {
        this.name = productName;
        this.price = productPrice;
        this.discount = productDiscount;
        this.code = productCode;
        this.link = productLink;
    }

    // getter
    public String getName() {
        return name;
    }

    // getter
    public int getPrice() {
        return price;
    }

    // getter
    public int getDiscount() {
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

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes name of product
    public void inputName() {
        // TODO
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes price of product
    public void inputPrice() {
        // TODO
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes discount of product
    public void inputDiscount() {
        // TODO
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes code of product
    public void inputCode() {
        // TODO
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: changes link of product
    public void inputLike() {
        // TODO
    }
}
