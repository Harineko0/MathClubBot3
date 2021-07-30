package net.pibrary.forum;

import java.util.*;

public class ForumTask extends TimerTask {

    private ForumScraper scraper = new ForumScraper();
    private ForumNotifier notifier = new ForumNotifier();
    private final int intervalHour = 12;

    public void startNoticeTask() {
        int intervalPeriod = intervalHour * 60 * 60 * 1000;

        Timer timer = new Timer();
        timer.schedule(this, 0, intervalPeriod);
    }

    @Override
    public void run() {
        List<ForumThread> recentThreads = scraper.getRecentThreads(intervalHour);
        notifier.sendNotice(recentThreads);
    }
}
