package net.pibrary;

import discord4j.core.event.domain.message.MessageCreateEvent;
import net.pibrary.commands.ForumCommand;
import net.pibrary.commands.FunnyCommand;
import net.pibrary.discord.DiscordGatewayHolder;
import net.pibrary.discord.DiscordListener;
import net.pibrary.forum.ForumTask;

public class MathClubBot3 {
    public static void main(String[] args) {
        ForumTask.startNoticeTask();

        DiscordListener listener = DiscordListener.getInstance();
        DiscordGatewayHolder.getInstance().getGateway().on(MessageCreateEvent.class).subscribe(listener::messageCreate);
        DiscordGatewayHolder.getInstance().getGateway().on(MessageCreateEvent.class).subscribe(FunnyCommand::sendHaruHaru);

        listener.onMessageCreate().subscribe(ForumCommand::getForum);
    }
}
