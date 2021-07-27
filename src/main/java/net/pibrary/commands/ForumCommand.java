package net.pibrary.commands;

import net.pibrary.Debug;
import net.pibrary.events.CommandSendEvent;
import net.pibrary.forum.ForumNotifier;
import net.pibrary.forum.ForumScraper;
import net.pibrary.forum.ForumThread;

import java.util.ArrayList;
import java.util.List;

public class ForumCommand {
    // /suken forum get <intervalHours>
    public static void getForum(CommandSendEvent event) {
        if (event.getArgSize() > 2 && event.getArg(0).equals("forum") && event.getArg(1).equals("get")) {
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

    public static void searchThread(CommandSendEvent event) {
        if (event.getArgs().size() > 3 && event.getArg(0).equals("forum") && event.getArg(1).equals("search")) {
            try {
                int maxPage = Integer.parseInt(event.getArg(2)) - 1; // ページは0からカウントするため1減らす
                if (maxPage < 1) return;
                ForumScraper scraper = new ForumScraper();
                List<ForumThread> threads = scraper.getAllThreads(maxPage);
                if (threads != null) {
                    List<ForumThread> matchThreads = new ArrayList<>();

                    List<String> searchWords = event.getArgs();
                    searchWords.subList(0, 3).clear();

                    for (ForumThread thread : threads) {
                        Debug.log(thread.toString());
                        boolean isMatch = true;
                        for (String searchWord : searchWords) {
                            if (!thread.toString().contains(searchWord)) {
                                isMatch = false;
                                break;
                            }
                        }

                        if (isMatch) {
                            matchThreads.add(thread);
                        }
                    }

                    ForumNotifier notifier = new ForumNotifier();
                    notifier.sendNotice(matchThreads, event.getChannelId());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
