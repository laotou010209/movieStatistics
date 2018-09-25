package com.laotou.service;

import com.laotou.entity.Movie;
import com.laotou.entity.MovieArea;

import java.util.List;


public interface NameService {
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
