package com.epam.web.memento;

import com.epam.web.resource.ConfigurationManager;

public class Memento {
    private static final String INDEX_PAGE_PATH = "path.page.index";
    private static Memento instance = null;
    private String currentPage = ConfigurationManager.getProperty(INDEX_PAGE_PATH);

    private Memento() {
    }

    public static Memento getInstance() {
        if (instance == null) {
            instance = new Memento();
        }
        return instance;
    }

    public String getPreviousPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}