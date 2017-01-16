package com.epam.web.resource;

import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.messages");

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
