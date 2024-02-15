package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {
    private Product testProduct;
    private ArrayList<Product> testProducts;
    private ArrayList<Product> testEmptyProducts;
    private ShoppingList testShoppingList;

    @BeforeEach
    void runBefore() {
        testProduct = new Product("toner", 10, 0.25, "CODE",
                "www.website.com");
        testShoppingList = new ShoppingList("skincare products");
        testProducts = new ArrayList<>();
        testProducts.add(testProduct);
        testEmptyProducts = new ArrayList<>();
    }

    @Test
    void testShoppingList() {
        assertEquals(0, testShoppingList.getAmount());
        assertEquals("skincare products", testShoppingList.getName());
        assertEquals(testEmptyProducts, testShoppingList.getProducts());
    }

    @Test
    void testGetAmount() {
        assertEquals(0, testShoppingList.getAmount());
    }

    @Test
    void testGetName() {
        assertEquals("skincare products", testShoppingList.getName());
    }

    @Test
    void testGetProducts() {
        assertEquals(testEmptyProducts, testShoppingList.getProducts());
    }

    @Test
    void testAddProduct() {
        testShoppingList.addProduct(testProduct);

        assertEquals(testProducts, testShoppingList.getProducts());
        assertEquals(1, testShoppingList.getAmount());
    }

    @Test
    void testDeleteProduct() {
        testShoppingList.addProduct(testProduct);
        testShoppingList.deleteProduct(testProduct);

        assertEquals(testEmptyProducts, testShoppingList.getProducts());
        assertEquals(0, testShoppingList.getAmount());
    }

    @Test
    void testBoughtProducts() {
        assertEquals(0, testShoppingList.boughtProducts());

        testProduct.productBought();
        assertEquals(0, testShoppingList.boughtProducts());
    }
}