package example.avro.不用生成代码._4_array数组;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericArray;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 写入数据，不生成实体类
 *
 * @author Wang ZiJian
 */
public class AvroWriterMain {
    public static void main(String[] args) throws IOException {

        Schema schema = new Schema.Parser().parse(
                new File("C:\\Users\\Administrator\\AppData\\Local\\Temp\\avro\\src\\main\\resources\\books.avsc"));
        List<String> list =new ArrayList<>();
        list.add("bookA");
        list.add("bookB");
        list.add("bookC");
        list.add("bookD");
        GenericArray books = new GenericData.Array<>(schema,list);

        // 将用户 1 和用户 2 序列化到磁盘
        File file = new File("file.avro");
        DatumWriter<GenericArray> datumWriter = new GenericDatumWriter<GenericArray>(schema);
        DataFileWriter<GenericArray> dataFileWriter = new DataFileWriter<GenericArray>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(books);
        dataFileWriter.close();
    }
}