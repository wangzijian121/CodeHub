package com.zjyun.map_reduce._7_使用mr实现2个表join;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/5
 */
public class JoinReduce extends Reducer<Text, StudentAndCourseJoinTableBean, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<StudentAndCourseJoinTableBean> values, Reducer<Text, StudentAndCourseJoinTableBean, Text, Text>.Context context) throws IOException, InterruptedException {

        StudentAndCourseJoinTableBean course = new StudentAndCourseJoinTableBean();
        ArrayList<StudentAndCourseJoinTableBean> students = new ArrayList<>();
        for (StudentAndCourseJoinTableBean value : values) {
            if (value.getTableName().equals("course")) {
                StudentAndCourseJoinTableBean newC = new StudentAndCourseJoinTableBean();
                try {
                    BeanUtils.copyProperties(newC, value);
                    course = newC;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

            } else {
                StudentAndCourseJoinTableBean newS = new StudentAndCourseJoinTableBean();
                try {
                    BeanUtils.copyProperties(newS, value);
                    students.add(newS);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        for (StudentAndCourseJoinTableBean student : students) {
            student.setCName(course.getCName());
            System.out.println(student);
            context.write(new Text(String.valueOf(student.getSId())),new Text(student.getCId()+"\t"+student.getSName() + "\t" + student.getCName()));
        }
    }
}
