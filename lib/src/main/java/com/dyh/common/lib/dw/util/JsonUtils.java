package com.dyh.common.lib.dw.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * json管理
 *
 * @author Tom
 */
public class JsonUtils {
    private static final Gson gson = createGson();

    public static Gson createGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Integer.class, new IntegerDefaultAdapter());
        builder.registerTypeAdapter(String.class, new StringDefaultAdapter());
        return builder.create();
    }

    public static class IntegerDefaultAdapter extends TypeAdapter<Integer> {

        @Override
        public void write(JsonWriter jsonWriter, Integer integer) throws IOException {
            jsonWriter.value(String.valueOf(integer));
        }

        @Override
        public Integer read(JsonReader jsonReader) throws IOException {
            try {
                return Integer.valueOf(jsonReader.nextString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    public static class StringDefaultAdapter extends TypeAdapter<String> {

        @Override
        public void write(JsonWriter jsonWriter, String s) throws IOException {
            jsonWriter.value(s);
        }

        @Override
        public String read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return "";
            } else {
                return jsonReader.nextString();
            }
        }
    }

    public static <T> T object(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static <T> T object(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static String toJson(Object param) {
        try {
            return gson.toJson(param);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

}
