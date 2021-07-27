package net.pibrary;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import net.pibrary.commands.ForumCommand;
import net.pibrary.commands.FunnyCommand;
import net.pibrary.discord.DiscordGatewayHolder;
import net.pibrary.discord.DiscordListener;
import net.pibrary.forum.ForumTask;

import java.util.Locale;

public class MathClubBot3 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.JAPAN);

        ForumTask forumTask = new ForumTask();
        forumTask.startNoticeTask();

        DiscordListener listener = DiscordListener.getInstance();
        GatewayDiscordClient gateway = DiscordGatewayHolder.getInstance().getGateway();
        gateway.on(MessageCreateEvent.class).subscribe(listener::messageCreate);
        gateway.on(MessageCreateEvent.class).subscribe(FunnyCommand::sendHaruHaru);

        listener.onMessageCreate().subscribe(ForumCommand::getForum);
    }
}
