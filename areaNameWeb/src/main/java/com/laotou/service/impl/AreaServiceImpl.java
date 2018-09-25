package com.laotou.service.impl;

import com.laotou.dao.AreaDao;
import com.laotou.entity.MovieArea;
import com.laotou.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao nameDao;

    @Override
    public List<MovieArea> find() {
        return nameDao.find();
    }
}
