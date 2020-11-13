package com.closa.global.functions;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeToString extends StdConverter<LocalDateTime, String> {
    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss")
            .withLocale(Locale.FRANCE)
            .withZone(ZoneId.systemDefault());
    @Override
    public String convert(LocalDateTime localDateTime) {

        return localDateTime.format(formatter);
    }
}
