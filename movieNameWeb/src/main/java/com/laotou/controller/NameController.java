package com.laotou.controller;

import com.laotou.entity.Movie;
import com.laotou.entity.MovieArea;
import com.laotou.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("movieName")
public class NameController {

    @Autowired
    private NameService nameService;

    /**
     * 跳转到页面
     * @return
     */
    @RequestMapping("method")
    public String method(){
        return "movieShow";
    }

    /**
     * 根据电影名查询
     * @return
     * @throws IOException
     */
    @RequestMapping("findName")
    @ResponseBody
    public List<Movie> movieName() {
        List<Movie> list=nameService.findName();
      return  list;
    }

    /**
     * 根据地区查询
     * @return
     */
    @RequestMapping("findArea")
    @ResponseBody
    public List<MovieArea> movieArea(){
        List<MovieArea> movieList=nameService.findArea();
        return movieList;
    }
}
