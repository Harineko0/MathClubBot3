package net.pibrary.forum;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.rest.util.Color;
import net.pibrary.discord.DiscordGatewayHolder;

import java.util.List;

public class ForumNotifier {
    final private Snowflake channelId = Snowflake.of("782966879694094356");

    public void sendNotice(List<ForumThread> threads) {
        sendNotice(threads, channelId);
    }

    public void sendNotice(List<ForumThread> threads, Snowflake channelId) {
        GatewayDiscordClient gateway = DiscordGatewayHolder.getInstance().getGateway();

        for (ForumThread thread : threads) {
//            gateway.getChannelById(channelId).cast(TextChannel.class).flatMap(channel -> channel.createEmbed(embedCreateSpec -> thread.toEmbed())).block();

            if (thread.isParent()) {
                gateway.getChannelById(channelId).cast(TextChannel.class).flatMap(channel -> channel.createEmbed(embedCreateSpec -> embedCreateSpec
                        .setTitle("[" + thread.getId() + "] " + thread.getSubject())
                        .setDescription(thread.getText())
                        .setAuthor(thread.getAuthor(), thread.getUrl(), "https://deliver.commons.nicovideo.jp/thumbnail/nc206071?size=l")
                        .setColor(Color.GRAY))).block();
            } else {
                gateway.getChannelById(channelId).cast(TextChannel.class).flatMap(channel -> channel.createEmbed(embedCreateSpec -> embedCreateSpec
                        .setTitle("[" + thread.getId() + "] " + thread.getSubject())
                        .setDescription(thread.getText())
                        .setAuthor(thread.getAuthor(), thread.getUrl(), "https://deliver.commons.nicovideo.jp/thumbnail/nc206071?size=l")
                        .setFooter(">> [" + thread.getReplyTo() + "]", "https://www.silhouette-illust.com/wp-content/uploads/2016/10/15245-300x300.jpg")
                        .setColor(Color.GRAY))).block();
            }
        }
    }
}
