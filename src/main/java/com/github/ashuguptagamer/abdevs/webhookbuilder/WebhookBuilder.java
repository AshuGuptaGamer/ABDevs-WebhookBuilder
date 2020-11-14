package com.github.ashuguptagamer.abdevs.webhookbuilder;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Constants;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.WebhookSender;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.Instant;
import java.util.Scanner;

public class WebhookBuilder {
    public static void main(String[] args) {

        final Constants constants = new Constants();
        constants.setDefaultValues();

        final JsonObject webhooks = Constants.webhooks;
        if (webhooks.keySet().isEmpty()) {
            System.out.println("No Webhook URLs found!");
            return;
        }
        final StringBuilder webhookSelectionPrompt = new StringBuilder();
        webhookSelectionPrompt.append("Choose a webhook URL:\n");
        final String separatedWebhookNames = String.join(", ", webhooks.keySet());
        webhookSelectionPrompt.append(separatedWebhookNames);
        String webhookSelection = "";
        final Scanner scanner = new Scanner(System.in);
        while (!webhooks.has(webhookSelection)) {
            System.out.println(webhookSelectionPrompt);
            webhookSelection = scanner.nextLine();
            if (!webhooks.has(webhookSelection)) System.out.println("Invalid Selection. Try Again!");
        }

        final JsonObject contents = Constants.contents;
        if (contents.keySet().isEmpty()) {
            System.out.println("No Content Found To Send!");
            return;
        }
        final StringBuilder contentSelectionPrompt = new StringBuilder();
        contentSelectionPrompt.append("Choose a content to send:\n");
        final String separatedContentNames = String.join(", ", contents.keySet());
        contentSelectionPrompt.append(separatedContentNames);
        String contentSelection = "";
        while (!contents.has(contentSelection)) {
            System.out.println(contentSelectionPrompt);
            contentSelection = scanner.nextLine();
            if (!contents.has(contentSelection)) System.out.println("Invalid Selection. Try Again!");
        }

        final String webhookUrl = webhooks.get(webhookSelection).getAsString();
        final JsonObject contentObject = contents.get(contentSelection).getAsJsonObject();

        final JsonElement usernameElement = contentObject.get("username");
        final JsonElement avatarUrlElement = contentObject.get("avatar-url");
        final JsonElement messageElement = contentObject.get("message");
        final JsonElement authorElement = contentObject.get("author");
        final JsonElement authorIconUrlElement = contentObject.get("author-icon-url");
        final JsonElement authorUrlElement = contentObject.get("author-url");
        final JsonElement titleElement = contentObject.get("title");
        final JsonElement titleUrlElement = contentObject.get("title-url");
        final JsonElement descriptionElement = contentObject.get("description");
        final JsonElement colorElement = contentObject.get("color");
        final JsonElement footerElement = contentObject.get("footer");
        final JsonElement footerUrlElement = contentObject.get("footer-url");
        final JsonElement imageElement = contentObject.get("image");
        final JsonElement thumbnailElement = contentObject.get("thumbnail");
        final JsonElement isTimestampElement = contentObject.get("is-timestamp-enable");
        final JsonElement fieldsElement = contentObject.get("fields");

        String username = usernameElement == null ? null : usernameElement.getAsString();
        String avatarUrl = avatarUrlElement == null ? null : avatarUrlElement.getAsString();
        String message = messageElement == null ? null : messageElement.getAsString();
        String author = authorElement == null ? null : authorElement.getAsString();
        String authorIconUrl = authorIconUrlElement == null ? null : authorIconUrlElement.getAsString();
        String authorUrl = authorUrlElement == null ? null : authorUrlElement.getAsString();
        String title = titleElement == null ? null : titleElement.getAsString();
        String titleUrl = titleUrlElement == null ? null : titleUrlElement.getAsString();
        String description = descriptionElement == null ? null : descriptionElement.getAsString();
        int color = colorElement == null ? 0 : colorElement.getAsInt();
        String footer = footerElement == null ? null : footerElement.getAsString();
        String footerUrl = footerUrlElement == null ? null : footerUrlElement.getAsString();
        String image = imageElement == null ? null : imageElement.getAsString();
        String thumbnail = thumbnailElement == null ? null : thumbnailElement.getAsString();
        boolean isTimestampEnabled = isTimestampElement != null && isTimestampElement.getAsBoolean();
        JsonArray fields = fieldsElement == null ? null : fieldsElement.getAsJsonArray();

        if (username != null && username.equals("")) username = null;
        if (avatarUrl != null && avatarUrl.equals("")) avatarUrl = null;
        if (message != null && message.equals("")) message = null;
        if (author != null && author.equals("")) author = null;
        if (authorIconUrl != null && authorIconUrl.equals("")) authorIconUrl = null;
        if (authorUrl != null && authorUrl.equals("")) authorUrl = null;
        if (title != null && title.equals("")) title = null;
        if (titleUrl != null && titleUrl.equals("")) titleUrl = null;
        if (description != null && description.equals("")) description = null;
        if (footer != null && footer.equals("")) footer = null;
        if (footerUrl != null && footerUrl.equals("")) footerUrl = null;
        if (image != null && image.equals("")) image = null;
        if (thumbnail != null && thumbnail.equals("")) thumbnail = null;

        final WebhookEmbedBuilder embedBuilder = new WebhookEmbedBuilder();
        WebhookEmbed.EmbedAuthor embedAuthor = null;
        WebhookEmbed.EmbedTitle embedTitle = null;
        WebhookEmbed.EmbedFooter embedFooter = null;
        if (author != null)
            embedAuthor = new WebhookEmbed.EmbedAuthor(author, authorIconUrl, authorUrl);
        if (title != null)
            embedTitle = new WebhookEmbed.EmbedTitle(title, titleUrl);
        if (footer != null)
            embedFooter = new WebhookEmbed.EmbedFooter(footer, footerUrl);
        embedBuilder.setAuthor(embedAuthor).setTitle(embedTitle).setDescription(description).setColor(color).setFooter(embedFooter).setImageUrl(image).setThumbnailUrl(thumbnail);
        if (isTimestampEnabled)
            embedBuilder.setTimestamp(Instant.now());

        if (fields != null)
            fields.forEach(jsonElement -> {
                final JsonArray array = jsonElement.getAsJsonArray();
                embedBuilder.addField(new WebhookEmbed.EmbedField(array.get(0).getAsBoolean(), array.get(1).getAsString(), array.get(2).getAsString()));
            });

        final WebhookSender webhookSender = new WebhookSender();
        final WebhookMessageBuilder webhookMessageBuilder = new WebhookMessageBuilder();
        if (message != null) webhookMessageBuilder.setContent(message);
        if (!embedBuilder.isEmpty()) webhookMessageBuilder.addEmbeds(embedBuilder.build());
        if (webhookMessageBuilder.isEmpty()) {
            System.out.println("Cannot send the message because Message and Embed both are empty!");
            return;
        }
        webhookMessageBuilder.setAvatarUrl(avatarUrl).setUsername(username);
        webhookSender.webhookClient(webhookUrl).send(webhookMessageBuilder.build()).join();
    }
}
