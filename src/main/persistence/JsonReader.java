package persistence;

import model.Product;
import model.ShoppingList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// used JsonSerializationDemo as reference

// Represents a reader that reads shopping list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShoppingList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShoppingList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ShoppingList parseShoppingList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ShoppingList sl = new ShoppingList(name);
        addProducts(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses products from JSON object and adds them to shopping list
    private void addProducts(ShoppingList sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("products");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addProduct(sl, nextProduct);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses product from JSON object and adds it to workroom
    private void addProduct(ShoppingList sl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        double discount = jsonObject.getDouble("discount");
        String code = jsonObject.getString("code");
        String link = jsonObject.getString("link");
        Product product = new Product(name, price, discount, code, link);
        sl.addProduct(product);
    }
}