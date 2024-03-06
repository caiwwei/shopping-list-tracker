package persistence;

import model.Product;
import model.ShoppingList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ShoppingList sl = new ShoppingList("Shopping List");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyShoppingList() {
        try {
            ShoppingList sl = new ShoppingList("Shopping List");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShoppingList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShoppingList.json");
            sl = reader.read();
            assertEquals("Shopping List", sl.getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShoppingList() {
        try {
            ShoppingList sl = new ShoppingList("Shopping List");
            sl.addProduct(new Product("toner", 10, 0.25,
                    "code", "www.website.com"));
            sl.addProduct(new Product("serum", 20, 0.10,
                    "code", "www.website.com"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoppingList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingList.json");
            sl = reader.read();
            assertEquals("Shopping List", sl.getName());
            checkProduct("toner", 10, 0.25,
                    "code", "www.website.com", sl.getProducts().get(0));
            checkProduct("serum", 20, 0.10,
                    "code", "www.website.com", sl.getProducts().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}