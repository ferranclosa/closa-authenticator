package com.closa.global.functions;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class StringToInstant extends StdConverter<String, Instant> {

    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss:nnnn O")
            .withZone(ZoneId.systemDefault());

    @Override
    public Instant convert(String value) {

        LocalDateTime ldt = LocalDateTime.parse(value, formatter);
        return Instant.from(ldt);
    }
}
