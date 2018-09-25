package com.laotou.service.impl;

import com.laotou.dao.NameDao;
import com.laotou.entity.Movie;
import com.laotou.entity.MovieArea;
import com.laotou.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class NameServiceImpl implements NameService {
    @Autowired
    private NameDao nameDao;

    /**
     * 根据电影名查询
     * @return
     */
    @Override
    public List<Movie> findName() {
        return nameDao.findName();
    }

    /**
     * 根据地区查询
     * @return
     */
    @Override
    public List<MovieArea> findArea() {
        return nameDao.findArea();
    }
}
