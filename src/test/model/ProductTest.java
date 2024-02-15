package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product testProduct;

    @BeforeEach
    void runBefore() {
        testProduct = new Product("toner", 10, 0.25, "CODE",
                "www.website.com");
    }

    @Test
    void testProduct() {
        assertEquals("toner", testProduct.getName());
        assertEquals(10, testProduct.getPrice());
        assertEquals(0.25, testProduct.getDiscount());
        assertEquals("CODE", testProduct.getCode());
        assertEquals("www.website.com", testProduct.getLink());
    }

    @Test
    void testGetName() {
        assertEquals("toner", testProduct.getName());
    }

    @Test
    void testGetPrice() {
        assertEquals(10, testProduct.getPrice());
    }

    @Test
    void testGetDiscount() {
        assertEquals(0.25, testProduct.getDiscount());
    }

    @Test
    void testGetCode() {
        assertEquals("CODE", testProduct.getCode());
    }

    @Test
    void testGetLink() {
        assertEquals("www.website.com", testProduct.getLink());
    }

    @Test
    void testInputName() {
        testProduct.inputName("serum");

        assertEquals("serum", testProduct.getName());
    }

    @Test
    void testInputPrice() {
        testProduct.inputPrice(12);

        assertEquals(12, testProduct.getPrice());
    }

    @Test
    void testInputDiscount() {
        testProduct.inputDiscount(0.5);

        assertEquals(0.5, testProduct.getDiscount());
    }

    @Test
    void testInputCode() {
        testProduct.inputCode("CODE1");

        assertEquals("CODE1", testProduct.getCode());
    }

    @Test
    void testInputLink() {
        testProduct.inputLink("www.website1.com");

        assertEquals("www.website1.com", testProduct.getLink());
    }
}
