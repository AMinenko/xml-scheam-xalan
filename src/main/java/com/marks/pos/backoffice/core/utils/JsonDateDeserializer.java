package com.marks.pos.backoffice.core.utils;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.TextNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static java.lang.String.format;

@Slf4j
public class JsonDateDeserializer extends JsonDeserializer<Date> implements DateFormattingProps {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        TextNode node = oc.readTree(jp);
        String dateString = node.textValue();
        return parse(dateString);
    }

    private static Date parse(String dateString, String formatPattern) {
        DateFormat df = new SimpleDateFormat(formatPattern);
        df.setTimeZone(TimeZone.getTimeZone(UTC_TIMEZONE));
        Date result = null;
        try {
            result = df.parse(dateString);
        } catch (ParseException e) {
            log.info("Can't parse date " + dateString + ", pattern " + formatPattern, e);
        }
        return result;
    }

    public static Date parse(String dateString) throws IOException {
        for (String formatPattern: DATE_FORMAT_DESERIALIZER_PATTERNS) {
            Date date = parse(dateString, formatPattern);
            if (date != null) {
                return date;
            }
        }
        throw new JsonMappingException(format("Date '%s' must be in format '%s'", dateString,
                DATE_FORMAT_DESERIALIZER_PATTERNS));
    }
}
