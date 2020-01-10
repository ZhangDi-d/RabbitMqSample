package com.ryze.test.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ryze.test.util.StringUtil;

import java.io.IOException;

/**
 * 百分比 0.1245 -> 12.45%
 * 使用时,需要在bean上加@JsonSerializer(using = DataPercentSerializer.class)
 * Created by xueLai on 2020/1/10.
 */
public class DataPercentSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (o instanceof String || o instanceof Float || o instanceof Double) {
            jsonGenerator.writeString(StringUtil.toPercent(o.toString()));
        } else {
            jsonGenerator.writeObject(o);
        }
    }


}
