package com.zjyun.map_reduce.使用mr实现3个表join.一阶段;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/5
 */
public class Job1Reducer extends Reducer<Text, BeanA, Text, Text> {
    private Text text=new Text();
    @Override
    protected void reduce(Text key, Iterable<BeanA> values, Reducer<Text, BeanA, Text, Text>.Context context) throws IOException, InterruptedException {
        BeanA student = new BeanA();
        List<BeanA> student_courses = new ArrayList();

        for (BeanA value : values) {
            if (value.getTableName().equals("student")) {
                try {
                    BeanUtils.copyProperties(student, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }else  if (value.getTableName().equals("student_course")) {
                BeanA newSC = new BeanA();
                try {
                    BeanUtils.copyProperties(newSC, value);
                    student_courses.add(newSC);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        for (BeanA sc : student_courses) {
            sc.setSName(student.getSName());
            text.set(sc.toString());
            context.write(key,text);
        }
    }
}
