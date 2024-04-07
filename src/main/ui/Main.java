package ui;

import java.io.FileNotFoundException;

// used JsonSerializationDemo as reference

// main
public class Main {
    public static void main(String[] args) {
        try {
            new ShoppingListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

