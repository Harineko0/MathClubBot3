package net.pibrary.commands;

import net.pibrary.events.CommandSendEvent;
import net.pibrary.forum.ForumNotifier;
import net.pibrary.forum.ForumScraper;
import net.pibrary.forum.ForumThread;

import java.util.List;

public class ForumCommand {
    // /suken forum get <intervalHours>
    public static void getForum(CommandSendEvent event) {
        if (event.getArg(0).equals("forum") && event.getArg(1).equals("get")) {
            try {
                int intervalHours = Integer.parseInt(event.getArg(2));
                ForumScraper scraper = new ForumScraper();
                List<ForumThread> threads = scraper.getRecentThreads(intervalHours);
                if (threads != null) {
                    ForumNotifier notifier = new ForumNotifier();
                    notifier.sendNotice(threads, event.getChannelId());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
