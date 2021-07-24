package net.pibrary.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.reaction.ReactionEmoji;

import java.util.Random;

public class FunnyCommand {
    public static void sendHaruHaru(MessageCreateEvent event) {
        Message message = event.getMessage();

        if (message.getAuthor().map(user -> !user.isBot()).orElse(false)) {

            if (message.getContent().equals("はるはる！")) {
                StringBuilder haruharuBuilder = new StringBuilder();
                Random random = new Random();
                String[] Chara = {"は", "る"};

                for (int i = 0; i < 4; i++) {
                    haruharuBuilder.append(Chara[random.nextInt(2)]);
                }

                String haruharu = haruharuBuilder.toString();

                MessageChannel messageChannel = event.getMessage().getChannel().block();
                Message sentMessage = messageChannel.createMessage(haruharu + "！").block();

                if (haruharu.equals("はるはる")) {
                    sentMessage.addReaction(ReactionEmoji.unicode("\uD83C\uDF89")).subscribe();
                }
            }
        }
    }
}
