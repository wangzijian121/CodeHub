package com.zjyun.map_reduce.使用mr实现2个表join;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hadoop.io.Writable;
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
@NoArgsConstructor
@AllArgsConstructor
public class StudentAndCourseJoinTableBean implements Writable {

    private int sId;
    private String sName = "";

    private int cId;
    private String cName = "";
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
        return sId+"\t"+sName + "\t" + cId + "\t" + cName + "\t" + tableName;
    }
}
