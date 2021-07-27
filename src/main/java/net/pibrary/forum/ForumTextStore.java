package net.pibrary.forum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumTextStore {
    // region Singleton
    private static ForumTextStore instance = new ForumTextStore();
    private ForumTextStore() {}
    public static ForumTextStore getInstance() {return instance;}
    // endregion

    private Map<String, List<ForumText>> urlToTexts = new HashMap<>();

    public List<ForumText> getTexts(String url) {
        return urlToTexts.get(url);
    }

    public void putTexts(String url, List<ForumText> texts) {
        urlToTexts.put(url, texts);
    }

    public boolean hasTexts(String url) {
        return urlToTexts.containsKey(url);
    }
}
