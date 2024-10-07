package com.zjyun.map_reduce.使用mr实现3个表join;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileGenerator {
    private static final int RECORD_COUNT = 10000; // 每个文件的记录数
    private static final String[] NAMES = {"Alice", "Bob", "Charlie", "David", "Eve"};
    private static final String[] COURSES = {"Math", "Science", "History", "Art", "Music"};

    public static void main(String[] args) {
        try {
            generateStudentsFile("student.txt");
            generateCoursesFile("courses.txt");
            generateStudentCoursesFile("student_courses.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateStudentsFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 1; i <= RECORD_COUNT; i++) {
                String name = NAMES[new Random().nextInt(NAMES.length)];
                writer.write(i + "," + name);
                writer.newLine();
            }
        }
    }

    private static void generateCoursesFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 1; i <= COURSES.length; i++) {
                writer.write(i + "," + COURSES[i - 1]);
                writer.newLine();
            }
        }
    }

    private static void generateStudentCoursesFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 1; i <= RECORD_COUNT; i++) {
                int cid = new Random().nextInt(COURSES.length) + 1; // 随机课程ID
                writer.write(i + "," + i + "," + cid);
                writer.newLine();
            }
        }
    }
}
