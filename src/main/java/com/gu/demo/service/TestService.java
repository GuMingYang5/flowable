package com.gu.demo.service;

import com.gu.demo.dto.Info;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: guMingYang
 * @DATE: 2019/5/12
 */
public interface TestService {
    List<Map<Object,Object>> query();
    List<Info> query1();
}
