package com.example.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author zijian Wang
 */
public class StringUtils {
    public static String getRandomStr(int length) {
        if (length == 0) {
            throw new Error("cant zero!");
        }
        ThreadLocalRandom current = ThreadLocalRandom.current();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            //97~122 65~90
            stringBuilder.append((char) (122 - current.nextInt(26)));
        }
//        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        IntStream.range(0, 20).forEach(x -> getRandomStr(5));
    }
}
