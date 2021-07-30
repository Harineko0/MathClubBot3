package net.pibrary.discord;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import net.pibrary.events.CommandSendEvent;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DiscordListener {
    // region Singleton
    private static DiscordListener instance = new DiscordListener();
    public static DiscordListener getInstance() {
        return instance;
    }
    private DiscordListener() {}
    // endregion

    private Subject<CommandSendEvent, CommandSendEvent> commandSendSubject = BehaviorSubject.create();
    public Observable<CommandSendEvent> onMessageCreate() {
        return commandSendSubject;
    }

    public void messageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();
        String context = message.getContent();

        if (message.getAuthor().map(user -> !user.isBot()).orElse(false)
                && context.startsWith("/suken")) {
            List<String> args = new LinkedList(Arrays.asList(message.getContent().split(" ")));
            args.remove(0);
            CommandSendEvent commandSendEvent = new CommandSendEvent(args, event.getMessage().getChannelId());
            commandSendSubject.onNext(commandSendEvent);
        }
    }
}
