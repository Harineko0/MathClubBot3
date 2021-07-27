package net.pibrary.forum;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ForumThreadFactory {
    Map<String, Document> urlToDoc = new HashMap<>();

    public List<ForumThread> createForumThread(Document document) {
        List<ForumThread> threads = new ArrayList<>();

        Elements threadElements = document.getElementsByTag("blockquote").select("font");
        int replyTo = 0;
        String pastUrl = "";

        for (Element threadElement : threadElements) {
            String stringElement = threadElement.toString();

            int id = getId(stringElement);

            String url = threadElement.select("a").attr("href");
            if (!url.isEmpty()) { // 元スレの場合
                pastUrl = url;
                replyTo = id;
            } else { //返信の場合
                url = pastUrl;
            }

            int log = Integer.parseInt(url.substring(url.indexOf("log=") + 4));

            String subject = getSubject(stringElement);
            String text = getText(url, id);
            String author = getAuthor(stringElement);
            Date date = getDate(stringElement);

            ForumThread thread = new ForumThread(id, replyTo, log, subject, text, author, date);
            threads.add(thread);
        }

        return threads;
    }

    private int getId(String element){
        return Integer.parseInt(element.substring(element.indexOf("[") + 1, element.indexOf("]")));
    }

    private Date getDate(String element){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 (E) HH時mm分");
        String dateElement = element.substring(element.indexOf("[20") + 1, element.indexOf("] </font>"));
        try {
            return dateFormat.parse(dateElement);
        } catch (ParseException e){
            e.printStackTrace();
            return new Date();
        }
    }

    private String getAuthor(String element){
        return element.substring(element.indexOf("-----") + 6, element.indexOf("[20") - 1);
    }

    private String getSubject(String element){
        int beginIndex;
        int endIndex;
        if (element.contains("▼")) {
            beginIndex = element.indexOf("▼");
            endIndex = element.indexOf("</a>");
        } else {
            beginIndex = element.indexOf("┗") + 1;
            endIndex = element.indexOf("-----") - 1;
        }

        return element.substring(beginIndex, endIndex);
    }

    private String getText(String url, int id){
        Document document = null;
        if (urlToDoc.containsKey(url)) {
            document = urlToDoc.get(url);
        } else {
            try {
                document = Jsoup.connect(url).get();
                urlToDoc.put(url, document);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (document != null) {
            Elements centerElements = document.getElementsByTag("center");
            centerElements.remove(0);
            for (Element element : centerElements) {
                if (getId(element.text()) == id) {
                    return element.select("blockquote").text();
                }
            }
        }

        return null;
    }
}
