package com.urise.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Тел.: "),
    SKYPE("Skype: "),
    EMAIL("Почта: "),
    PROFILE_LINKEDIN("Профиль LinkedIn: "),
    PROFILE_GITHUB("Профиль GitHub: "),
    PROFILE_STACKOVERFLOW("Профиль Stackoverflow: "),
    HOME_PAGE("Сайт: ");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
