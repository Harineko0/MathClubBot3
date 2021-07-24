package net.pibrary.discord;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;

public class DiscordGatewayHolder {
    private static DiscordGatewayHolder instance = new DiscordGatewayHolder();
    public static DiscordGatewayHolder getInstance() {
        return instance;
    }
    private DiscordGatewayHolder() {
        final String token = "Nzc2MDM2ODU3MDM3MTkzMjE3.X6vCpA.S7quCWuwkq_rrRapBTgzGKeLfAA";
        gateway = DiscordClientBuilder.create(token).build().login().block();
    }

    private GatewayDiscordClient gateway;

    public GatewayDiscordClient getGateway() {
        return gateway;
    }
}
