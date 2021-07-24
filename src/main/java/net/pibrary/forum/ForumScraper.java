package net.pibrary.forum;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForumScraper {

    public List<ForumThread> getRecentThreads(int intervalHours) {
        List<ForumThread> recentThreads = new ArrayList<>();
        List<String> urlToScrape = getUrlToScrape();
        List<ForumThread> allThreads = getAllThreads(urlToScrape);
        if (allThreads == null) { return null; }
        long currentTime = new Date().getTime();

        for (ForumThread thread : allThreads) {
            long postedTime = thread.getDate().getTime();
            long hourTimeDifference = ( currentTime - postedTime ) / 3600000;
            if (hourTimeDifference < intervalHours) {
                recentThreads.add(thread);
            }
        }

        return recentThreads;
    }

    private List<ForumThread> getAllThreads(List<String> urlToScrape) {
        List<ForumThread> threads = new ArrayList<>();

        for (String forumUrl : urlToScrape) {
            Document document = null;
            try {
                document = Jsoup.connect(forumUrl).get();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            ForumThreadFactory threadFactory = new ForumThreadFactory();
            List<ForumThread> createdThreads = threadFactory.createForumThread(document);
            // Listを結合
            threads = Stream.concat(threads.stream(), createdThreads.stream()).collect(Collectors.toList());
        }

        return threads;
    }

    private List<String> getUrlToScrape() {
        final String baseForumUrl = "http://bbs2.sekkaku.net/bbs/sukenqanda/page=";
        List<String> forumUrls = new ArrayList<>();

        for (int i = 0; i < 1; i++) { //ページ数指定
            int page = 1 + i * 10;
            String forumUrl = baseForumUrl + page;
            forumUrls.add(forumUrl);
        }

        return forumUrls;
    }
}
