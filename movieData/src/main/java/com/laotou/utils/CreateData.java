package com.laotou.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据生成工具类
 * 数据生成后并将数据写入本地文件
 * 并进行文件数据追加到HDFS中
 */
public class CreateData {
    public static void main(String[] args) throws Exception {
        String[] movieNames = {"情圣", "铁道飞虎", "长城",
                "酒国英雄之摆渡人", "血战钢锯岭", "那年夏天你去了哪",
                "冰雪女皇之冬日魔", "你好，疯子！", "罗曼蒂克消亡史"};
        String[] areaNames = {"成都", "北京", "上海", "广州", "重庆", "福建", "深圳", "杭州", "天津"};
        double[] ticketPrices = {20, 25, 30};
        //随机生成数据，并放入对象当中，再放入集合中
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 1000; i++) {
            String movieName = movieNames[(int) (Math.random() * movieNames.length)];
            String areaName = areaNames[(int) (Math.random() * areaNames.length)];
            double ticketPrice = ticketPrices[(int) (Math.random() * ticketPrices.length)];
            sb.append(movieName+"\t"+areaName+"\t"+ticketPrice+"\n");
        }
        //设置链接
        Configuration conf = new Configuration();
        conf.setBoolean("dfs.support.append", true);
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        FileSystem fs = FileSystem.get(URI.create("hdfs://master:9000"), conf, "root");
        Path path=new Path("/movieData.txt");
        //判断文件是否在hdfs中存在
        if(!fs.exists(path)){
            fs.createNewFile(path);
        }
        //追加数据到hdfs中
        FSDataOutputStream out=fs.append(path);
        ByteArrayInputStream is = new ByteArrayInputStream(sb.toString().getBytes());
        IOUtils.copyBytes(is, out, 4096);
       // 每一次写入数据后沉睡两秒，继续
       Thread.sleep(2000);
    }
    }

