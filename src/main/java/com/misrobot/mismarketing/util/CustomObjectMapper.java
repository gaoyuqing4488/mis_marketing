package com.misrobot.mismarketing.util;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by GYQ on 2017/7/26.
 */
public class CustomObjectMapper extends ObjectMapper {
//    public CustomObjectMapper() {
//        CustomSerializerFactory factory = new CustomSerializerFactory();
//
//        factory.addGenericMapping(Date.class, new JsonSerializer<Date>() {
//            @Override
//            public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException,
//                    JsonProcessingException {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                jsonGenerator.writeString(sdf.format(value));
//            }
//        });
//
//
//        this.setSerializerFactory(factory);
//    }

}
