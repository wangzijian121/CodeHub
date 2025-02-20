package example.avro.不用生成代码._5_maps映射;

import avro.shaded.com.google.common.collect.GenericMapMaker;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericArray;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import sun.net.www.content.text.Generic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 写入数据，不生成实体类
 *
 * @author Wang ZiJian
 */
public class AvroWriterMain {
    public static void main(String[] args) throws IOException {

        Schema schema = new Schema.Parser().parse(new File("C:\\Users\\Administrator\\AppData\\Local\\Temp\\avro\\src\\main\\resources\\maps.avsc"));

        GenericRecord maps = new GenericData.Record(schema);

        // 创建一个 Map 并填充数据
        Map<String, String> myMap = new HashMap<>();
        myMap.put("aaa", "111");
        myMap.put("bbb", "222");

        maps.put("myMap", myMap);

        // 将 GenericRecord 序列化到磁盘
        File file = new File("file.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(maps);
        dataFileWriter.close();
    }
}