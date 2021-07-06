package com.urise.webapp.model;

public class TextSection implements Section {
    private final String content;

    public TextSection() {
        this.content = "";
    }

    public TextSection(String content) {
        this.content = content;
    }



    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return (content + "\n" + "\n");
    }
}
