package com.closa.global.functions;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantToString extends StdConverter<Instant, String> {
    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss:nnnn O")
            .withZone(ZoneId.systemDefault());

    @Override
    public String convert(Instant instant) {
        return formatter.format(instant);
    }
}
