package net.pibrary.discord;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import net.pibrary.PropertyManager;

public class DiscordGatewayHolder {
    private static DiscordGatewayHolder instance = new DiscordGatewayHolder();
    public static DiscordGatewayHolder getInstance() {
        return instance;
    }
    private DiscordGatewayHolder() {
        final String token = PropertyManager.getInstance().properties().getProperty("token");
        gateway = DiscordClientBuilder.create(token).build().login().block();
    }

    private GatewayDiscordClient gateway;

    public GatewayDiscordClient getGateway() {
        return gateway;
    }
}
