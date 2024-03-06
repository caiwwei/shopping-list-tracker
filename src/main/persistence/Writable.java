package persistence;

import org.json.JSONObject;

// used JsonSerializationDemo as reference

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}