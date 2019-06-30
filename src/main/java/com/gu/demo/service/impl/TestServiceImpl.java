package com.gu.demo.service.impl;

import com.gu.demo.dto.Info;
import com.gu.demo.mapper.TestMapper;
import com.gu.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: guMingYang
 * @DATE: 2019/5/12
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;
    @Override
    public List<Map<Object, Object>> query() {
        return testMapper.query();
    }

    @Override
    public List<Info> query1() {
        return testMapper.query1();
    }
}
