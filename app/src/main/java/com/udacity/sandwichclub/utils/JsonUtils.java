package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility to manipulate sandwich Json string
 */
public class JsonUtils {

    /** sandwich json keys */
    static final String nameKey = "name";
    static final String mainNameKey = "mainName";
    static final String alsoKnownAsKey = "alsoKnownAs";
    static final String placeOfOriginKey = "placeOfOrigin";
    static final String descriptionKey = "description";
    static final String imageKey = "image";
    static final String ingredientsKey = "ingredients";

    /**
     * Parse a json string into Sandwich object
     *
     * @param json the sandwich json string
     * @return Sandwich object result from parsing the given json String
     * @throws JSONException if the parse fails
     */
    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject sandwichJson = new JSONObject(json);
        JSONObject nameJson = sandwichJson.getJSONObject(nameKey);
        String mainName = nameJson.getString(mainNameKey);
        JSONArray alsoKnownAsJson = nameJson.getJSONArray(alsoKnownAsKey);
        List<String> alsoKnowAs = makeAlsoKnownAsList(alsoKnownAsJson);
        String placeOfOrigin = sandwichJson.getString(placeOfOriginKey);
        String description = sandwichJson.getString(descriptionKey);
        String image = sandwichJson.getString(imageKey);
        List<String> ingredients = makeIngredientsList(sandwichJson.getJSONArray(ingredientsKey));
        Sandwich sandwich = new Sandwich(mainName, alsoKnowAs, placeOfOrigin, description, image, ingredients);
        return sandwich;
    }

    private static List<String> makeIngredientsList(JSONArray jsonArray) throws JSONException {
        final List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String ingredient = jsonArray.getString(i);
            ingredients.add(ingredient);
        }
        return Collections.unmodifiableList(ingredients);
    }

    private static List<String> makeAlsoKnownAsList(final JSONArray alsoKnownAsJson) throws JSONException {
        final List<String> alsoKnownAs = new ArrayList<>();
        for (int i = 0; i < alsoKnownAsJson.length() ; i++) {
            String name = alsoKnownAsJson.getString(i);
            alsoKnownAs.add(name);
        }
        return Collections.unmodifiableList(alsoKnownAs);
    }
}
