package com.marks.pos.backoffice.core.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JsonDateSerializer extends JsonSerializer<Date> implements DateFormattingProps {

    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException {
        DateFormat todayDateFormat = new SimpleDateFormat(DATE_FORMAT_SERIALIZER_PATTERN);
        todayDateFormat.setTimeZone(TimeZone.getTimeZone(UTC_TIMEZONE));

        String dateString = todayDateFormat.format(date);
        generator.writeString(dateString);
    }
}
