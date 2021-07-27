package net.pibrary.forum;

import java.util.*;

public class ForumTask extends TimerTask {

    private ForumScraper scraper = new ForumScraper();
    private ForumNotifier notifier = new ForumNotifier();
    private final int intervalHour = 12;

    public void startNoticeTask() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, intervalHour);
        Date date = calendar.getTime();

        Timer timer = new Timer();
        timer.schedule(this, date);
    }

    @Override
    public void run() {
        List<ForumThread> recentThreads = scraper.getRecentThreads(intervalHour);
        notifier.sendNotice(recentThreads);
        startNoticeTask();
        this.cancel();
    }
}
