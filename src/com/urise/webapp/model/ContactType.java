package com.urise.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Тел."),
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("skype:" + value, value);
        }
    },
    EMAIL("Почта") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("mailto:" + value, value);
        }
    },
    PROFILE_LINKEDIN("Профиль LinkedIn") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    PROFILE_GITHUB("Профиль GitHub") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    PROFILE_STACKOVERFLOW("Профиль Stackoverflow") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    HOME_PAGE("Сайт") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, title);
    }
}
