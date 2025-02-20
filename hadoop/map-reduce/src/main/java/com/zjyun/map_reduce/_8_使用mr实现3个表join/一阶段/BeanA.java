package com.zjyun.map_reduce._8_使用mr实现3个表join.一阶段;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hadoop.io.Writable;

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

public class BeanA implements Writable {

    //学生ID和名字
    private int sId;
    private String sName = "";

    //课程ID
    private int ScId;
    private int Sc_SId;
    private int cId;
    private String cName = "";

    //表标识
    private String tableName = "";

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(sId);
        out.writeUTF(sName);

        out.writeInt(ScId);
        out.writeInt(Sc_SId);
        out.writeInt(cId);

        out.writeUTF(cName);
        out.writeUTF(tableName);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.sId = in.readInt();
        this.sName = in.readUTF();

        this.ScId = in.readInt();
        this.Sc_SId = in.readInt();
        this.cId = in.readInt();

        this.cName = in.readUTF();
        this.tableName = in.readUTF();
    }

    @Override
    public String toString() {
        return sId + "\t" + sName + "\t" + ScId + "\t" + Sc_SId + "\t" + cId + "\t" + cName + "\t" + tableName;
    }
}
