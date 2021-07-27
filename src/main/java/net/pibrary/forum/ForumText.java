package net.pibrary.forum;

public class ForumText {
    private int id;
    private String text;

    public ForumText(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "ForumText{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
