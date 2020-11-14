package com.github.ashuguptagamer.abdevs.webhookbuilder.utils;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;

public class WebhookSender {
    public WebhookClient webhookClient(String webhookUrl) {
        WebhookClient client;
        WebhookClientBuilder builder = new WebhookClientBuilder(webhookUrl);
        builder.setThreadFactory((job) -> {
            Thread thread = new Thread(job);
            thread.setName("Webhook-Thread");
            thread.setDaemon(true);
            return thread;
        });
        client = builder.build();
        return client;
    }
}
