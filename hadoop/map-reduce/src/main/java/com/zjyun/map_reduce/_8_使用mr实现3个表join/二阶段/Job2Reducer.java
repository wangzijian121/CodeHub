package com.zjyun.map_reduce._8_使用mr实现3个表join.二阶段;

import com.zjyun.map_reduce._8_使用mr实现3个表join.一阶段.BeanB;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
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
public class Job2Reducer extends Reducer<Text, BeanB, BeanB, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<BeanB> values, Reducer<Text, BeanB, BeanB, NullWritable>.Context context) throws IOException, InterruptedException {
        BeanB beanB = new BeanB();
        List<BeanB> partList = new ArrayList<>();

        for (BeanB b : values) {
            if (b.getTableName().equals("course")) {
                try {
                    BeanUtils.copyProperties(beanB, b);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            if (b.getTableName().equals("part")) {
                BeanB beanTemp = new BeanB();
                try {
                    BeanUtils.copyProperties(beanTemp, b);
                    partList.add(beanTemp);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        for (BeanB b : partList) {
            b.setCName(beanB.getCName());
            context.write(b, null);
        }
    }
}
