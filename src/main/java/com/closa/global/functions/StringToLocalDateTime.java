package com.closa.global.functions;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTime extends StdConverter<String, LocalDateTime>{
    DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime convert(String s) {
        LocalDateTime ldt = LocalDateTime.parse(s, formatter);
        return ldt;

    }
}
