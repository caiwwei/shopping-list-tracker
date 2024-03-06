package persistence;

import model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProduct(String name, double price, double discount, String code,
                               String link, Product product) {
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(discount, product.getDiscount());
        assertEquals(code, product.getCode());
        assertEquals(link, product.getLink());
    }
}