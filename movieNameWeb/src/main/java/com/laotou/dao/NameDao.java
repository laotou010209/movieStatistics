package com.laotou.dao;


import com.laotou.entity.Movie;
import com.laotou.entity.MovieArea;

import java.util.List;

public interface NameDao {
    /**
     * 根据电影名查询
     * @return
     */
    List<Movie> findName();

    /**
     * 根据地区查询
     * @return
     */
    List<MovieArea> findArea();
}
