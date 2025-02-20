package com.zjyun.hadoop;



import org.apache.avro.generic.GenericRecord;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import javax.security.auth.login.Configuration;
import java.io.IOException;

public class ParquetWriterExample {
    public static void main(String[] args) throws IOException {
        // 定义Parquet文件的输出路径和压缩方式
        Path outputPath = new Path("example.parquet");
        CompressionCodecName codecName = CompressionCodecName.SNAPPY;

        // 创建ParquetWriter对象
        Configuration conf = new Configuration();
        ParquetWriter<GenericRecord> writer = AvroParquetWriter.<GenericRecord>builder(HadoopOutputFile.fromPath(outputPath, conf))
                .withSchema(DataGenerator.getSchema())
                .withCompressionCodec(codecName)
                .build();

        // 生成数据并写入Parquet文件
        GenericRecord data = DataGenerator.generateData(DataGenerator.getSchema(), "Alice", 30);