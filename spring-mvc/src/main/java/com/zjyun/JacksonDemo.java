package com.zjyun;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonDemo {
    public static void main(String[] args) {
        try {
            // 创建 User 对象
            User user = new User("John Doe", 30, "john.doe@example.com");

            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 User 对象转换为 JSON 字符串
            String jsonString = objectMapper.writeValueAsString(user);
            System.out.println("Java 对象转换为 JSON 字符串:");
            System.out.println(jsonString);

            // 将 JSON 字符串转换为 User 对象
            String jsonInput = "{\"name\":\"Jane Doe\",\"age\":25,\"email\":\"jane.doe@example.com\"}";
            User userFromJson = objectMapper.readValue(jsonInput, User.class);
            System.out.println("JSON 字符串转换为 Java 对象:");
            System.out.println(userFromJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
