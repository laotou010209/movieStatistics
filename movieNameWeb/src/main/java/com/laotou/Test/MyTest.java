package com.laotou.Test;

import com.laotou.entity.Movie;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MyTest {
    public static void main(String[] args) {
        //加载核心配置文件
        Configuration conf=new Configuration();
        conf.configure("hibernate.cfg.xml");
        //获取会话工厂
        SessionFactory factory=conf.buildSessionFactory();
        Session session=factory.openSession();
       String sql="select * from movie_name ORDER BY ticketPrice DESC limit 3";
        SQLQuery query=session.createSQLQuery(sql);
        query.addEntity(Movie.class);
        List<Movie> movieList=query.list();
        System.out.println(movieList.toString());

    }

}
