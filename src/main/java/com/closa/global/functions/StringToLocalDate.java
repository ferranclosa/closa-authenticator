package com.closa.global.functions;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDate extends StdConverter<String, LocalDate>{
    DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String s) {
        LocalDate ldt = LocalDate.parse(s, formatter);
        return ldt;

    }
}
