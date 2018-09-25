package com.laotou.job;

import com.laotou.entity.Movie;
import com.laotou.map.AreaNameMap;
import com.laotou.reduce.AreaNameReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 根据地区名统计类
 */
public class AreaNamejob {
    public static void main(String[] args) {
        try {
            Configuration conf=new Configuration();
            System.setProperty("HADOOP_USER_NAME","root");
            conf.set("fs.defaultFS","hdfs://master:9000");
            conf.set("yarn.resourcemanager.hostname","master");
            Job job = Job.getInstance(conf);
            job.setJobName("movieAreaJob");
            job.setJarByClass(AreaNamejob.class);
            //找到map
            job.setMapperClass(AreaNameMap.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(LongWritable.class);
            //找到reduce
            job.setReducerClass(AreaNameReduce.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(LongWritable.class);
            //读取数据来源
            FileInputFormat.addInputPath(job,new Path("/movieData.txt"));
            FileSystem fs=FileSystem.get(conf);
            //如果输出目录存在，进行删除
            Path outPath=new Path("/areaName_out");
            if(fs.exists(outPath)){
                fs.delete(outPath,true);
            }
            FileOutputFormat.setOutputPath(job,outPath);
            boolean b = job.waitForCompletion(true);
            if(b){
                System.out.println("任务完成");
                //从hdfs中下载文件
                FSDataInputStream fis=fs.open(new Path("/areaName_out/part-r-00000"));
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                IOUtils.copyBytes(fis,bos,conf,true);
                String[] str=bos.toString().split("\n");
                //获取hibernate的配置文件
                org.hibernate.cfg.Configuration con= new org.hibernate.cfg.AnnotationConfiguration();
                con.configure("hibernate.cfg.xml");
                //创建会话工厂
                SessionFactory factory=con.buildSessionFactory();
                //获取会话对象
                Session session=factory.openSession();
                session.beginTransaction();//开启事务
                for(int i=0;i<str.length;i++){
                    Movie movie=new Movie();
                    movie.setAreaName(str[i].split("\t")[0]);
                    movie.setTicketPrice(Double.parseDouble(str[i].split("\t")[1]));
                    session.save(movie);
                }
                session.getTransaction().commit();//提交事务
                session.close();
                System.out.println("数据导入成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
