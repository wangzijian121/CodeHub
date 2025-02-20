package com.zjyun.hadoop;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import  org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;

/**
 * @author Wang ZiJian
 */
public class AddressMaskUseGenericUDF extends   GenericUDF{

    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        return null;
    }

    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
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

    @Override
    public String getDisplayString(String[] strings) {
        return "";
    }
}