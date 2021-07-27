package com.urise.webapp.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonLocaleDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    @Override
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
    }
}