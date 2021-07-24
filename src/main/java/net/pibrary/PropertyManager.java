package net.pibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static PropertyManager instance = new PropertyManager();
    public static PropertyManager getInstance() {
        return instance;
    }

    private Properties properties;

    private PropertyManager() {
        properties = new Properties();
        FileInputStream in = null;

        try {
            in = new FileInputStream("MathClubBot3.properties");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Properties properties() {
        return properties;
    }
}
