package com.cloudpaas.demo.ui.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * JodaTime 反序列化将字符串转化为datetime
 **/
public class DatetimeDeserializer extends JsonDeserializer<DateTime> {

    private static DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public DateTime deserialize(JsonParser jp, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String s = node.asText();
        DateTime parse = DateTime.parse(s, dateFormatter);
        return parse;
    }
}
