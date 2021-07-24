package net.pibrary.forum;

import java.util.*;

public class ForumTask {
    public static void startNoticeTask() {
        final int intervalHour = 12;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, intervalHour);
        Date date = calendar.getTime();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ForumScraper scraper = new ForumScraper();
                List<ForumThread> recentThreads = scraper.getRecentThreads(intervalHour);
                ForumNotifier notifier = new ForumNotifier();
                notifier.sendNotice(recentThreads);
                startNoticeTask(); // Repeat
            }
        };
        timer.schedule(task, date);
    }
}
