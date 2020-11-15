package com.github.ashuguptagamer.abdevs.webhookbuilder.options;

import com.github.ashuguptagamer.abdevs.webhookbuilder.WebhookBuilder;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Constants;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.JsonConfigManager;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Utils;

import java.util.Scanner;
import java.util.Set;

public class CreateWebhookUrlWizard {
    public void start() {
        final Scanner scanner = new Scanner(System.in);
        String identifier = "";
        final Set<String> ids = Constants.webhooks.keySet();
        while (identifier.equals("") || ids.contains(identifier)) {
            Utils.log("Type an ID for the webhook Url: ");
            Utils.logInput();
            identifier = scanner.nextLine();
            if (identifier.equals("")) Utils.log("You can't use an empty string as a ID!");
            if (ids.contains(identifier)) Utils.log("The ID you typed is already assigned to another webhook url!");
        }

        Utils.log("Type the Webhook URL: ");
        Utils.logInput();
        final String webhookUrl = scanner.nextLine();
        JsonConfigManager.addWebhookUrl(identifier, webhookUrl);
        Utils.log("Successfully added the new webhook url in the config.");
        WebhookBuilder.initialOptions();
    }
}
