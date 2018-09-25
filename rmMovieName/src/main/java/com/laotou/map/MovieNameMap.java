package com.laotou.map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class MovieNameMap extends Mapper<LongWritable,Text,Text,LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //罗曼蒂克消亡史  杭州 20.0
        String[] split=value.toString().split("\t");
        context.write(new Text(split[0]),new LongWritable((long) Double.parseDouble(split[2])));
    }
}
