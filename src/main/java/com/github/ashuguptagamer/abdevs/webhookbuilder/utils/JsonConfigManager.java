/*
This will read data from the config.json file.
 */
package com.github.ashuguptagamer.abdevs.webhookbuilder.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class JsonConfigManager {
    public static String getConfigJsonAsString() {
        final File jsonFile = new File(".", "config.json");
        StringBuilder lines = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(jsonFile));
            br.lines().forEach(lines::append);
            return lines.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void addContents(@NotNull String identifier, @NotNull JsonObject messageObject) {
        final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        final String configJsonAsString = getConfigJsonAsString();
        final JsonObject configObject = JsonParser.parseString(configJsonAsString).getAsJsonObject();
        final JsonObject contents = configObject.get("contents").getAsJsonObject();
        contents.add(identifier, messageObject);
        final File jsonFile = new File(".", "config.json");
        try (final FileWriter fileWriter = new FileWriter(jsonFile)) {
            gson.toJson(configObject, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
