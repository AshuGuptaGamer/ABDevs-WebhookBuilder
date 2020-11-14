/*
This will read data from the config.json file.
 */
package com.github.ashuguptagamer.abdevs.webhookbuilder.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonFileReader {
    public String getConfigJsonAsString() {
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
}
