package com.udacity.sandwichclub.utils;

/**
 * Created by Waheed on 15-Feb-18.
 */

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

import static com.udacity.sandwichclub.utils.JsonUtils.*;

@RunWith(JUnit4.class)
public class JsonUtilsTest {

    @Test
    public void testParseSandwichJson() throws JSONException {
        final Sandwich sandwich = parseSandwichJson(jsonString);
        assertEquals(mainName, sandwich.getMainName());
        assertEquals(placeOfOrigin, sandwich.getPlaceOfOrigin());
        assertEquals(description, sandwich.getDescription());
        assertEquals(image, sandwich.getImage());
        assertEquals(new HashSet<>(alsoKnownAs), new HashSet<>(sandwich.getAlsoKnownAs()));
        assertEquals(new HashSet<>(ingredients), new HashSet<>(sandwich.getIngredients()));
    }

    private final String jsonString;

    public JsonUtilsTest() throws JSONException {
        final JSONObject sandwichJson = new JSONObject();
        final JSONObject nameValue = new JSONObject();
        nameValue.put(mainNameKey, mainName);
        nameValue.put(alsoKnownAsKey, new JSONArray(alsoKnownAs));

        sandwichJson.put(nameKey, nameValue);
        sandwichJson.put(placeOfOriginKey, placeOfOrigin);
        sandwichJson.put(descriptionKey, description);
        sandwichJson.put(imageKey, image);
        sandwichJson.put(ingredientsKey, new JSONArray(ingredients));

        jsonString = sandwichJson.toString();
    }

    /** some dummy sandwich properties*/
    private static final String mainName = "Vada Pav";
    private static final List<String> alsoKnownAs =
            Arrays.asList("Vada Pao", "Wada PAv");
    private static final String placeOfOrigin = "India";
    private static final String description = "Vada Pav is a vegetnside";
    private static final String image =
            "https://upload.wikimedia.org/wikipedia/commons/1/15/Vada_Paav-The_Mumbai_Burger.jpg";
    private static final List<String> ingredients =
            Arrays.asList("chilli peppers", "garlic");

}
