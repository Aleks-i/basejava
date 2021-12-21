package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String toStringLocalDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String toHtmlLocalDate(LocalDate localDate) {
        return localDate.isAfter(LocalDate.now()) ? "Сейчас" : localDate.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }
}