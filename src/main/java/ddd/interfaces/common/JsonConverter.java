package ddd.interfaces.common;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

public class JsonConverter {

    public static String toJson(Object object) {
        return new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
                .toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
                .fromJson(json, clazz);
    }
}
