package com.github.ashuguptagamer.abdevs.webhookbuilder.options;

import com.github.ashuguptagamer.abdevs.webhookbuilder.WebhookBuilder;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Constants;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.JsonConfigManager;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Scanner;
import java.util.Set;

public class CreateWebhookMessageWizard {
    public void start() {
        final JsonObject webhookMessageObject = new JsonObject();
        final Scanner scanner = new Scanner(System.in);
        String identifier = "";
        final Set<String> ids = Constants.contents.keySet();
        while (identifier.equals("") || ids.contains(identifier)) {
            Utils.log("Type an ID for the message: ");
            Utils.logInput();
            identifier = scanner.nextLine();
            if (identifier.equals("")) Utils.log("You can't use an empty string as a ID!");
            if (ids.contains(identifier)) Utils.log("The ID you typed is already assigned to another message!");
        }

        Utils.log("Type an username for the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String username = scanner.nextLine();

        Utils.log("Type an Avatar URL for the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String avatarUrl = scanner.nextLine();

        Utils.log("Type a message to be sent with embed message (Press Enter to skip): ");
        Utils.logInput();
        final String message = scanner.nextLine();

        Utils.log("Type an author name of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String author = scanner.nextLine();

        Utils.log("Type an author icon url of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String authorIconUrl = scanner.nextLine();

        Utils.log("Type an author url of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String authorUrl = scanner.nextLine();

        Utils.log("Type a title of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String title = scanner.nextLine();

        Utils.log("Type a title url of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String titleUrl = scanner.nextLine();

        Utils.log("Type a description of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String description = scanner.nextLine();

        int color;
        while (true) {
            Utils.log("Type a color code of the embed message (Press Enter to skip): ");
            Utils.logInput();
            try {
                color = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                Utils.log("The provided integer is not valid");
            }
        }

        Utils.log("Type a footer of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String footer = scanner.nextLine();

        Utils.log("Type a footer url of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String footerUrl = scanner.nextLine();

        Utils.log("Type a image url of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String imageUrl = scanner.nextLine();

        Utils.log("Type a thumbnail url of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String thumbnailUrl = scanner.nextLine();

        Utils.log("Should the timestamp be enabled (true/false): ");
        Utils.logInput();
        boolean isTimestampEnabled = Boolean.parseBoolean(scanner.nextLine());

        Utils.log("Do you wanna add fields in embed message? (yes/no): ");
        Utils.logInput();
        final String isFieldBuilder = scanner.nextLine();
        JsonArray fields = new JsonArray();
        if (isFieldBuilder.equalsIgnoreCase("yes"))
            fields = fieldBuilder(scanner);

        webhookMessageObject.addProperty("username", username);
        webhookMessageObject.addProperty("avatar-url", authorUrl);
        webhookMessageObject.addProperty("message", message);
        webhookMessageObject.addProperty("author", author);
        webhookMessageObject.addProperty("author-icon-url", authorIconUrl);
        webhookMessageObject.addProperty("author-url", authorUrl);
        webhookMessageObject.addProperty("title", title);
        webhookMessageObject.addProperty("title-url", titleUrl);
        webhookMessageObject.addProperty("description", description);
        webhookMessageObject.addProperty("color", color);
        webhookMessageObject.addProperty("footer", footer);
        webhookMessageObject.addProperty("footer-url", footerUrl);
        webhookMessageObject.addProperty("image", imageUrl);
        webhookMessageObject.addProperty("thumbnail", thumbnailUrl);
        webhookMessageObject.addProperty("is-timestamp-enable", isTimestampEnabled);
        webhookMessageObject.add("fields", fields);

        JsonConfigManager.addContent(identifier, webhookMessageObject);
        Utils.log("Successfully added the new message in the config.");
        WebhookBuilder.initialOptions();
    }

    private JsonArray fieldBuilder(Scanner scanner) {
        final JsonArray array = new JsonArray();
        int count = 0;
        while (true) {
            count++;
            if (count != 1) {
                Utils.log("Do you want to add another field? (yes/no)");
                Utils.logInput();
                final String anotherField = scanner.nextLine();
                if (anotherField.equalsIgnoreCase("no")) break;
            }
            Utils.log("Inline the field? (true/false): ");
            Utils.logInput();
            final boolean inline = Boolean.parseBoolean(scanner.nextLine());

            Utils.log("Name of field: ");
            Utils.logInput();
            final String name = scanner.nextLine();

            Utils.log("Value of field: ");
            Utils.logInput();
            final String value = scanner.nextLine();

            final JsonArray inArray = new JsonArray();
            inArray.add(inline);
            inArray.add(name);
            inArray.add(value);
            array.add(inArray);
        }
        return array;
    }
}
