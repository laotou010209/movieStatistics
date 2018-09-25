package com.laotou.controller;

import com.laotou.entity.MovieArea;
import com.laotou.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("areaName")
public class AreaController {

    @Autowired
    private AreaService nameService;
    @RequestMapping("area")
    public String areaName(){
        return "movieShow";
    }
    @RequestMapping("find")
    @ResponseBody
    public List<MovieArea> movieName(){

        List<MovieArea> movieList=nameService.find();
        return movieList;
    }
}
