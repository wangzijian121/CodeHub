package com.zjyun.map_reduce._8_使用mr实现3个表join.二阶段;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/5
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BeanB implements WritableComparable<BeanB> {

    //学生ID和名字
    private int sId;
    private String sName = "";

    //课程ID
    private int cId;
    private String cName = "";

    //表标识
    private String tableName = "";

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(sId);
        out.writeUTF(sName);

        out.writeInt(cId);
        out.writeUTF(cName);

        out.writeUTF(tableName);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.sId = in.readInt();
        this.sName = in.readUTF();

        this.cId = in.readInt();
        this.cName = in.readUTF();
        this.tableName = in.readUTF();
    }

    @Override
    public String toString() {
        return sId + "\t" + sName + "\t" + cId + "\t" + cName + "\t" + tableName;
    }

    @Override
    public int compareTo(BeanB o) {
        if (this.sId > o.sId) {
            return 1;
        } else if (this.sId < o.sId) {
            return -1;
        }else  {
            return 0;
        }
    }
}
