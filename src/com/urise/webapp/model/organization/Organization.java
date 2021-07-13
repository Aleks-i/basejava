package com.urise.webapp.model.organization;

import java.util.Arrays;

public class Organization {
    private final Link homePage;
    private final Period[] periods;

    private final String description;

    public Organization(String name, String url, String description, Period... periods) {

        this.homePage = new Link(name, url);
        this.description = description;
        this.periods = periods;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", period=" + Arrays.toString(periods) +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!Arrays.equals(periods, that.periods)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + Arrays.hashCode(periods);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}