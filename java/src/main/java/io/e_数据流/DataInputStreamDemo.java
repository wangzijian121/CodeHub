package io.e_数据流;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zijian Wang
 */
public class DataInputStreamDemo {
    public static void main(String[] args) throws IOException {
        DataOutputStream dos = null;
        String outputPath = "java/target/classes/io/output.txt";

        try {
            dos = new DataOutputStream(new FileOutputStream(outputPath));
            byte b = 100;
            short s = 200;
            int i = 300;
            long l = 400L;
            float f = 3.0F;
            double d = 3.14;
            boolean sex = false;
            char c = 'a';

            // 写
            // 把数据以及数据的类型一并写入到文件当中。
            dos.writeByte(b);
            dos.writeShort(s);
            dos.writeInt(i);
            dos.writeLong(l);
            dos.writeFloat(f);
            dos.writeDouble(d);
            dos.writeBoolean(sex);
            dos.writeChar(c);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
