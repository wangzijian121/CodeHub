package example.avro.不用生成代码._1_基础类型;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;


/**
 * 写入数据，不生成实体类
 * @author Wang ZiJian
 */
public class AvroWriterMain {
    public static void main(String[] args) throws IOException {

        Schema schema = new Schema.Parser().parse(new File("C:\\Users\\Administrator\\AppData\\Local\\Temp\\avro\\src\\main\\resources\\user.avsc"));
        GenericRecord user = new GenericData.Record(schema);
        user.put("is_pass",true);
        user.put("name", "Wangzijian121");
        user.put("age", 28);
        user.put("id_card", Long.parseLong("2305211"));
        user.put("height", Float.parseFloat("183.5"));
        user.put("money", Double.parseDouble("888.8"));
        user.put("score_level", ByteBuffer.allocate(65));
        user.put("phone", "10086");

        // 将用户 1 和用户 2 序列化到磁盘
        File file = new File("users_dont_use_model.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(user);
        dataFileWriter.close();
    }
}