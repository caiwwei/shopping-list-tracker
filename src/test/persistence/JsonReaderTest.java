package persistence;

import model.Product;
import model.ShoppingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// used JsonSerializationDemo as reference

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ShoppingList sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyShoppingList() {
        try {
            ShoppingList sl = new ShoppingList("Shopping List");
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyShoppingList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyShoppingList.json");
            ShoppingList sl1 = reader.read();
            assertEquals("Shopping List", sl1.getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralShoppingList() {
        try {
            ShoppingList sl = new ShoppingList("Shopping List");
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralShoppingList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralShoppingList.json");
            ShoppingList sl1 = reader.read();
            assertEquals("Shopping List", sl1.getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}