package com.zjyun.map_reduce.大数据量的join;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class User {
    private static Random random = new Random();

    public static void main(String[] args) {

        try (FileWriter fileWriter = new FileWriter("user.txt")) {
            Random random = new Random();
            for (int i = 1; i <=3000_000_00L; i++) {
                String empNo = String.valueOf(i); // emp_no (3 letters + 3 digits)
                String firstName = generateRandomChineseSurnames(1) + generateRandomAlphaNumeric(5); // first_name (3 letters)
                String lastName = generateRandomAlphaNumeric(5); // last_name (3 letters)
                String gender = random.nextBoolean() ? "M" : "F"; // gender
                String careers = String.valueOf(random.nextInt(100) + 1);
                fileWriter.write(empNo + "\t" + firstName + "\t" + lastName + "\t" + gender + "\t" + careers + "\n");
            }
            System.out.println("Data generated successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private static String generateRandomChineseSurnames(int length) {
        String chineseSurnames[] = {
                "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈",
                "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
                "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏",
                "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章",
                "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦",
                "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳",
                "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺",
                "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常",
                "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余",
                "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹"
        };

        return chineseSurnames[random.nextInt(chineseSurnames.length)];
    }

    private static String generateRandomAlphaNumeric(int length) {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphaNumericString.charAt(random.nextInt(alphaNumericString.length())));
        }
        return sb.toString();
    }
}