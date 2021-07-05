package com.urise.webapp.model;

public enum ContactType {
    PHONENUMBER("Тел.: "),
    SKYPE("Skype: "),
    EMAIL("Почта: "),
    PROFILE("Профиль соц. сетей: "),
    URL("Сайт: ");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
