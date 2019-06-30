package com.gu.demo.controller;

import com.gu.demo.dto.Info;
import com.gu.demo.service.TestService;
import com.gu.demo.util.cipher.Cipher;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: guMingYang
 * @DATE: 2019/5/12
 */

@Slf4j
@Controller
public class TestController {

    @Resource
    private TestService testService;
    @Resource
    RuntimeService runtimeService;

    @Resource
    TaskService taskService;

    @ResponseBody
    @RequestMapping("/connect1")
    public List<Map<Object,Object>> connect(@RequestBody String requestParams){
        return testService.query();
    }

    @GetMapping("/test")
    public String hello(){

        String pid = runtimeService.startProcessInstanceByKey("my").getId();

        Task task = taskService.createTaskQuery().processInstanceId(pid).singleResult();
        return "hello world spring boot...."+task.getId();
    }
    @ResponseBody
    @RequestMapping("/connect2")
    @Cipher
    public String connect2(@RequestBody String requestParams){
        System.out.println(requestParams);
        return testService.query().toString();
    }

    @ResponseBody
    @RequestMapping("/export-excel")
    public void exportExcel(HttpServletResponse response){
        try {
           List<Info> list = testService.query1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("826bc1b8-91a3-11e8-b446-060400ef5315");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("jdbc:mysql://127.0.0.1:3306/ry?serverTimezone=GMT%2B8&useSSL=false");
        String password = textEncryptor.encrypt("123123");
        System.out.println("加密"+"\n");
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        System.out.println("解密"+"\n");
        System.out.println("username:" + textEncryptor.decrypt(username));
        System.out.println("password:" + textEncryptor.decrypt(password));
    }
}
