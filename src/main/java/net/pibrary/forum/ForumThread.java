package net.pibrary.forum;

import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ForumThread {
    private int id;
    private int replyTo;
    private int log;
    private String subject;
    private String text;
    private String author;
    private Date date;

    public ForumThread(int id, int replyTo, int log, String subject, String text, String author, Date date) {
        this.id = id;
        this.replyTo = replyTo;
        this.log = log;
        this.subject = subject;
        this.text = text;
        this.author = author;
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 (E) HH時mm分");

        return "{" +
                "id=" + id +
                ", replyTo=" + replyTo +
                ", log=" + log +
                ", url='" + getUrl() + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }

    public boolean isParent() {
        return id == replyTo;
    }

    public int getId() {
        return id;
    }

    public int getReplyTo() {
        return replyTo;
    }

    public int getLog() {
        return log;
    }

    public String getUrl() {
        return "http://bbs2.sekkaku.net/bbs/sukenqanda/&mode=res&log=" + log;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public EmbedCreateSpec toEmbed() {
        if (isParent()) {
            return new EmbedCreateSpec()
                    .setTitle("[" + id + "] " + subject)
                    .setDescription(text)
                    .setAuthor(author, getUrl(), "https://deliver.commons.nicovideo.jp/thumbnail/nc206071?size=l")
                    .setColor(Color.GRAY);
        }
        else
        {
            return new EmbedCreateSpec()
                    .setTitle("[" + id + "] " + subject)
                    .setDescription(text)
                    .setAuthor(author, getUrl(), "https://deliver.commons.nicovideo.jp/thumbnail/nc206071?size=l")
                    .setFooter(">> [" + replyTo + "]", "https://www.silhouette-illust.com/wp-content/uploads/2016/10/15245-300x300.jpg")
                    .setColor(Color.GRAY);
        }
    }
}
