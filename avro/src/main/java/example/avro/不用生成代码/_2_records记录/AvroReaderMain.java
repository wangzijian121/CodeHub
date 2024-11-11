package example.avro.不用生成代码._2_records记录;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;

import java.io.File;
import java.io.IOException;

/**
 * @author Wang ZiJian
 */
public class AvroReaderMain {
    public static void main(String[] args) throws IOException {
        Schema schema = new Schema.Parser().parse(new File("C:\\Users\\Administrator\\AppData\\Local\\Temp\\avro\\src\\main\\resources\\user.avsc"));
        // 从磁盘反序列化用户
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File("users_dont_use_model.avro"), datumReader);
        GenericRecord user = null;
        while (dataFileReader.hasNext()) {
            // 通过将用户对象传递给 next() 来重复使用用户对象。这样我们就不必分配和垃圾回收许多对象。
            // 许多项目。
            user = dataFileReader.next(user);
            System.out.println(user);
        }
    }
}