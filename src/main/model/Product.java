package model;

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

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public String getCode() {
        return code;
    }

    public String getLink() {
        return link;
    }
}
