package net.pibrary.forum;

import java.util.Date;

public class ForumThread {
    protected int id;
    protected int replyTo;
    protected int log;
    protected String subject;
    protected String text;
    protected String author;
    protected Date date;

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
        return "ForumThread{" +
                "id=" + id +
                ", replyTo=" + replyTo +
                ", log=" + log +
                ", url='" + getUrl() + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
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
}
