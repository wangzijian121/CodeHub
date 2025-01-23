package com.zjyun.hadoop;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/11/25
 */
public class AddressMaskUseUDF extends UDF {
    public Text evaluate(final Text s) {
        if (s == null) {
            return null;
        }
        String address = s.toString();
        char[] array = address.toCharArray();
        String res = "";
        for (int i = 0; i < array.length; i++) {
            if (i >= 3 && i <= 5) {
                res += "*";
            } else {
                res += array[i];
            }
        }
        return new Text(res);
    }
}
