package com.urise.webapp.model.organization;

import com.urise.webapp.util.xml.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static com.urise.webapp.util.DateUtil.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Link homePage;
    private List<Position> positions;

    public Organization() {
        this.positions = Collections.emptyList();
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link homePage, List<Position> positions) {
        this.id = UUID.randomUUID().toString();
        this.homePage = homePage;
        this.positions = positions;
    }

    public String getId() {
        return id;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setHomePage(Link homePage) {
        this.homePage = homePage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        positions.forEach(p -> sb.append(p.toString()));
        return homePage + "\n" + sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private String id;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Position() {
        }

        public Position(int stratYear, Month startMonth, String title, String description) {
            this(of(stratYear, startMonth), NOW, title, description);
        }

        public Position(int stratYear, Month startMonth, int endYear, Month endtMonth, String title, String description) {
            this(of(stratYear, startMonth), of(endYear, endtMonth), title, description);
        }

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.id = UUID.randomUUID().toString();
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getStartDatetoHtml() {
            return toHtmlLocalDate(startDate);
        }

        public String getEndDatetoHtml() {
            return toHtmlLocalDate(endDate);
        }

        public LocalDateTime getStartLocalDateToLocalDateTime() {
            return startDate.atTime(8, 0);
        }

        public LocalDateTime getEndLocalDateToLocalDateTime() {
            return endDate.atTime(8, 0);
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return startDate.equals(position.startDate) && endDate.equals(position.endDate) && title.equals(position.title) && Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }
    }
}