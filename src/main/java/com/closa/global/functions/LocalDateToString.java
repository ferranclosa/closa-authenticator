package com.closa.global.functions;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateToString extends StdConverter<LocalDate, String> {
    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");
    @Override
    public String convert(LocalDate localDate) {
        return localDate.format(formatter);
    }
}
