package com.github.ashuguptagamer.abdevs.webhookbuilder.options;

import com.github.ashuguptagamer.abdevs.webhookbuilder.WebhookBuilder;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Constants;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.JsonConfigManager;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.URISyntaxException;
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

        String avatarUrl = "url";
        while (!avatarUrl.equals("") || isNotURL(avatarUrl)) {
            Utils.log("Type an Avatar URL for the embed message (Press Enter to skip): ");
            Utils.logInput();
            avatarUrl = scanner.nextLine();
            if (!avatarUrl.equals("") || isNotURL(avatarUrl)) Utils.log("The provided string is not a valid URL!");
        }

        Utils.log("Type a message to be sent with embed message (Press Enter to skip): ");
        Utils.logInput();
        final String message = scanner.nextLine();

        Utils.log("Type an author name of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String author = scanner.nextLine();

        String authorIconUrl = "url";
        while (!authorIconUrl.equals("") || isNotURL(authorIconUrl)) {
            Utils.log("Type an author icon url of the embed message (Press Enter to skip): ");
            Utils.logInput();
            authorIconUrl = scanner.nextLine();
            if (!authorIconUrl.equals("") || isNotURL(authorIconUrl))
                Utils.log("The provided string is not a valid URL!");
        }

        String authorUrl = "url";
        while (!authorUrl.equals("") || isNotURL(authorUrl)) {
            Utils.log("Type an author url of the embed message (Press Enter to skip): ");
            Utils.logInput();
            authorUrl = scanner.nextLine();
            if (!authorUrl.equals("") || isNotURL(authorUrl)) Utils.log("The provided string is not a valid URL!");
        }

        Utils.log("Type a title of the embed message (Press Enter to skip): ");
        Utils.logInput();
        final String title = scanner.nextLine();

        String titleUrl = "url";
        while (!titleUrl.equals("") || isNotURL(titleUrl)) {
            Utils.log("Type a title url of the embed message (Press Enter to skip): ");
            Utils.logInput();
            titleUrl = scanner.nextLine();
            if (!titleUrl.equals("") || isNotURL(titleUrl)) Utils.log("The provided string is not a valid URL!");
        }

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

        String footerUrl = "url";
        while (!footerUrl.equals("") || isNotURL(footerUrl)) {
            Utils.log("Type a footer url of the embed message (Press Enter to skip): ");
            Utils.logInput();
            footerUrl = scanner.nextLine();
            if (!footerUrl.equals("") || isNotURL(footerUrl)) Utils.log("The provided string is not a valid URL!");
        }

        String imageUrl = "url";
        while (!imageUrl.equals("") || isNotURL(imageUrl)) {
            Utils.log("Type a image url of the embed message (Press Enter to skip): ");
            Utils.logInput();
            imageUrl = scanner.nextLine();
            if (!imageUrl.equals("") || isNotURL(imageUrl)) Utils.log("The provided string is not a valid URL!");
        }

        String thumbnailUrl = "url";
        while (!thumbnailUrl.equals("") || isNotURL(thumbnailUrl)) {
            Utils.log("Type a thumbnail url of the embed message (Press Enter to skip): ");
            Utils.logInput();
            thumbnailUrl = scanner.nextLine();
            if (!thumbnailUrl.equals("") || isNotURL(thumbnailUrl))
                Utils.log("The provided string is not a valid URL!");
        }

        Utils.log("Should the timestamp be enabled (true/false): ");
        Utils.logInput();
        boolean isTimestampEnabled = Boolean.parseBoolean(scanner.nextLine());

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
        webhookMessageObject.add("fields", new JsonArray());

        JsonConfigManager.addContents(identifier, webhookMessageObject);

        Utils.log("Successfully added the new message in the config.");

        WebhookBuilder.initialOptions();
    }

    private boolean isNotURL(String url) {
        try {
            new URI(url);
            return false;
        } catch (URISyntaxException e) {
            return true;
        }
    }
}
