package com.zjyun.hadoop;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;

public class DataGenerator {
    public static Schema getSchema() {
        // 定义数据模式
        String schemaString = "{\"type\":\"record\",\"name\":\"User\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int\"}]}";
        Schema.Parser parser = new Schema.Parser();
        return parser.parse(schemaString);
    }

    public static GenericRecord generateData(Schema schema, String name, int age) {
        // 生成数据
        GenericRecordBuilder builder = new GenericRecordBuilder(schema);
        builder.set("name", name);
        builder.set("age", age);
        return builder.build();
    }
}