package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.Type;
import java.util.Currency;

public class CurrencyCodec implements ObjectDeserializer, ObjectSerializer {
    public static final CurrencyCodec instance = new CurrencyCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) {
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            writer.writeNull();
        } else {
            writer.writeString(((Currency) obj).getCurrencyCode());
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        String str = (String) defaultJSONParser.parse();
        if (str == null || str.length() == 0) {
            return null;
        }
        return Currency.getInstance(str);
    }

    public int getFastMatchToken() {
        return 4;
    }
}
