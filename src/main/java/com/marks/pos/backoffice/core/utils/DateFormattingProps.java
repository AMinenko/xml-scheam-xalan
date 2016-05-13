package com.marks.pos.backoffice.core.utils;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * Support for multiple data formats decoding
 * is added for backward compatibility between project components.
 */
public interface DateFormattingProps {
    String DATE_FORMAT_SERIALIZER_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    List<String> DATE_FORMAT_DESERIALIZER_PATTERNS =
            unmodifiableList(asList(DATE_FORMAT_SERIALIZER_PATTERN, "yyyy-MM-dd'T'HH:mm:ssXXX"));

    String UTC_TIMEZONE = "UTC";
}
