package com.github.ashuguptagamer.abdevs.webhookbuilder.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Constants {
    //Defining some constant static variables to keep the code clean.
    public static JsonObject webhooks;
    public static JsonObject contents;

    public void setDefaultValues() {
        final JsonElement configElement = JsonParser.parseString(JsonConfigManager.getConfigJsonAsString());
        final JsonObject configObject = configElement.getAsJsonObject();
        webhooks = configObject.get("webhooks").getAsJsonObject();
        contents = configObject.get("contents").getAsJsonObject();
    }
}
