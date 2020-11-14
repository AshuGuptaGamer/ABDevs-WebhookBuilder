package com.github.ashuguptagamer.abdevs.webhookbuilder;

import com.github.ashuguptagamer.abdevs.webhookbuilder.options.CreateWebhookMessageWizard;
import com.github.ashuguptagamer.abdevs.webhookbuilder.options.WebhookSenderWizard;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Constants;
import com.github.ashuguptagamer.abdevs.webhookbuilder.utils.Utils;

import java.util.Scanner;

public class WebhookBuilder {

    public static void initialOptions() {
        System.out.println("\n                    Welcome!\nYou are using ABDevs-WebhookBuilder application.\nYou can send Webhook messages to your Discord channels very easily.\n\n");
        final Scanner scanner = new Scanner(System.in);
        String selection = "";
        while (true) {
            if (selection.equals("1")) {
                new WebhookSenderWizard().start();
                break;
            } else if (selection.equals("2")) {
                new CreateWebhookMessageWizard().start();
                break;
            } else {
                System.out.println("------------------[OPTIONS]------------------");
                Utils.log("1. WebhookSender Wizard");
                Utils.log("2. CreateWebhookMessage Wizard");
                Utils.logInput();
                selection = scanner.nextLine();
                if (!selection.equals("1") && !selection.equals("2")) {
                    Utils.log("Invalid Selection. Try Again!");
                }
            }
        }
    }

    public static void main(String[] args) {
        final Constants constants = new Constants();
        constants.setDefaultValues();
        initialOptions();
    }
}
