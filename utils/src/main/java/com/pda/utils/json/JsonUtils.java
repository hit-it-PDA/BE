package com.pda.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            // 예외 처리 로직 추가
            throw new RuntimeException("Error deserializing JSON to object", e);
        }
    }
}
