package com.cloudpaas.demo.ui.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * 在默认情况下，jackson会将joda time序列化为较为复杂的形式，不利于阅读，并且对象较大。
 * <p>
 * JodaTime 序列化的时候可以将datetime序列化为字符串，更容易读
 **/
public class DateTimeSerializer extends JsonSerializer<DateTime> {

    private static DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(value.toString(dateFormatter));
    }
}
