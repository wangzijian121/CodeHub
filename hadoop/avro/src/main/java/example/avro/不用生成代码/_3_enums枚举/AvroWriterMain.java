package example.avro.不用生成代码._3_enums枚举;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;


/**
 * 写入数据，不生成实体类
 *
 * @author Wang ZiJian
 */
public class AvroWriterMain {
    public static void main(String[] args) throws IOException {

        Schema schema = new Schema.Parser().parse(
                new File("C:\\Users\\Administrator\\AppData\\Local\\Temp\\avro\\src\\main\\resources\\color.avsc"));
        GenericEnumSymbol color1 = new GenericData.EnumSymbol(schema,"red");
        GenericEnumSymbol color2 = new GenericData.EnumSymbol(schema,"green");

        // 将用户 1 和用户 2 序列化到磁盘
        File file = new File("file.avro");
        DatumWriter<GenericEnumSymbol> datumWriter = new GenericDatumWriter<GenericEnumSymbol>(schema);
        DataFileWriter<GenericEnumSymbol> dataFileWriter = new DataFileWriter<GenericEnumSymbol>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(color1);
        dataFileWriter.append(color2);
        dataFileWriter.close();
    }
}