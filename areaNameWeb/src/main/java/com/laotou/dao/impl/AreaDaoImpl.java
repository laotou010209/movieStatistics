package com.laotou.dao.impl;

import com.laotou.dao.AreaDao;
import com.laotou.entity.MovieArea;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AreaDaoImpl implements AreaDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();//使用CurrentSession，自动关闭，也会自动提交事务
    }
    @Override
    public List<MovieArea> find() {
        String sql="select * from movie_area ORDER BY ticketPrice DESC limit 3";
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(MovieArea.class);
         return  query.list();
    }
}
