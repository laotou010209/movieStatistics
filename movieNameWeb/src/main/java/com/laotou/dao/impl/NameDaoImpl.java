package com.laotou.dao.impl;

import com.laotou.dao.NameDao;
import com.laotou.entity.Movie;
import com.laotou.entity.MovieArea;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class NameDaoImpl implements NameDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();//使用CurrentSession，自动关闭，也会自动提交事务
    }

    /**
     * 根据电影名查询
     * @return
     */
    @Override
    public List<Movie> findName() {

        String sql="select * from movie_name ORDER BY ticketPrice DESC limit 3";
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(Movie.class);
         return  query.list();
    }

    /**
     * 根据地区查询
     * @return
     */
    @Override
    public List<MovieArea> findArea() {
        String sql="select * from movie_area ORDER BY ticketPrice DESC limit 3";
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(MovieArea.class);
        return  query.list();
    }
}
