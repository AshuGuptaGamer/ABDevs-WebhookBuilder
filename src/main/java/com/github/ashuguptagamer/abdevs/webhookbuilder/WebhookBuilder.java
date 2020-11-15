package com.github.ashuguptagamer.abdevs.webhookbuilder;

import com.github.ashuguptagamer.abdevs.webhookbuilder.options.CreateWebhookMessageWizard;
import com.github.ashuguptagamer.abdevs.webhookbuilder.options.CreateWebhookUrlWizard;
import com.github.ashuguptagamer.abdevs.webhookbuilder.options.WebhookSenderWizard;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Constants;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Utils;

import java.util.Scanner;

public class WebhookBuilder {

    public static void initialOptions() {
        loadConfigValues();
        final Scanner scanner = new Scanner(System.in);
        String selection = "";
        label:
        while (true) {
            switch (selection) {
                case "1":
                    new WebhookSenderWizard().start();
                    break label;
                case "2":
                    new CreateWebhookMessageWizard().start();
                    break label;
                case "3":
                    new CreateWebhookUrlWizard().start();
                    break label;
                default:
                    System.out.println("------------------[OPTIONS]------------------");
                    Utils.log("1. WebhookSender Wizard");
                    Utils.log("2. CreateWebhookMessage Wizard");
                    Utils.log("3. CreateWebhookUrl Wizard");
                    Utils.logInput();
                    selection = scanner.nextLine();
                    if (!selection.equals("1") && !selection.equals("2") && !selection.equals("3"))
                        Utils.log("Invalid Selection. Try Again!");
                    break;
            }
        }
    }

    public static void loadConfigValues() {
        final Constants constants = new Constants();
        constants.setDefaultValues();
    }

    public static void main(String[] args) {
        System.out.println("\n                    Welcome!\nYou are using ABDevs-WebhookBuilder application.\nYou can send Webhook messages to your Discord channels very easily.\n\n");
        initialOptions();
    }
}
