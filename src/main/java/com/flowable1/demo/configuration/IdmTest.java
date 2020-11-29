package com.flowable1.demo.configuration;

import org.flowable.common.engine.api.management.TableMetaData;
import org.flowable.idm.api.*;
import org.flowable.idm.engine.IdmEngine;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gumingyang
 * @description idm测试使用
 * 
 **/
public class IdmTest {
    private  IdmEngine idmEngine;
    private IdmEngineConfiguration idmEngineConfiguration;
    private IdmIdentityService idmIdentityService;
    private IdmManagementService idmManagementService;
    @Before
    public void intit(){
        InputStream resourceAsStream = IdmTest.class.getClassLoader().getResourceAsStream("flowable.idm.cfg.xml");
        idmEngine = IdmEngineConfiguration.createIdmEngineConfigurationFromInputStream(resourceAsStream).buildIdmEngine();
        idmEngineConfiguration = idmEngine.getIdmEngineConfiguration();
        idmIdentityService = idmEngine.getIdmIdentityService();
        idmManagementService = idmEngine.getIdmManagementService();
        String name = idmEngine.getName();
    }

    /**
     * 添加用户
     */
    @Test
    public void addUser(){
        UserEntityImpl user = new UserEntityImpl();
        user.setId("test2");
        user.setPassword("12341234");
        user.setRevision(1);
        idmIdentityService.updateUserPassword(user);
    }

    /**
     * 添加组
     */
    @Test
    public void saveGroup(){
        GroupEntityImpl group = new GroupEntityImpl();
        group.setId("gu");
        group.setName("gu");
        group.setRevision(0);
        idmIdentityService.saveGroup(group);
    }

    /**
     * 添加组成员
     */
    @Test
    public void createMembership(){
        String userId = "test2";
        String groupId = "gu";
        idmIdentityService.createMembership(userId,groupId);
    }
    /**
     * 添加权限信息
     */
    @Test
    public void addGroupPrivilegeMapping(){
        //PrivilegeEntity privilege = (PrivilegeEntity) idmIdentityService.createPrivilege("delete");
       // PrivilegeQuery delete = idmIdentityService.createPrivilegeQuery().privilegeName("delete");
        //System.out.println(privilege);
        String privilege="23489d24-98b3-11e9-b9f0-6c4b908b382e";
        String groupId= "gu";
        idmIdentityService.addGroupPrivilegeMapping(privilege,groupId);
//        String userId="";
//        idmIdentityService.addUserPrivilegeMapping(privilege,userId);
    }
    /**
     * 查询用户信息
     */
    @Test
    public void createUserQuery(){
        List<User> list = idmIdentityService.createUserQuery().list();
        for (User u:list) {
            System.out.println("id=" + u.getId());
            System.out.println("password=" + u.getPassword());
        }
    }

    /**
     * 查询组信息
     */
    @Test
    public void createGroupQuery(){
        List<Group> list = idmIdentityService.createGroupQuery().list();
        for (Group u:list) {
            System.out.println("groupId=" + u.getId());
            System.out.println("groupName=" + u.getName());
        }
    }
    /**
     * 查询权限信息
     */
    @Test
    public void createPrivilegeQuery(){
        List<Privilege> list = idmIdentityService.createPrivilegeQuery().list();
        for (Privilege u:list) {
            System.out.println("groupId=" + u.getId());
            System.out.println("groupName=" + u.getName());
        }
    }

    /**
     * 查询表信息
     */
    @Test
    public void getTableCount(){
        Map<String, Long> tableCount = idmManagementService.getTableCount();
        Set<Map.Entry<String, Long>> entries = tableCount.entrySet();
        for (Map.Entry<String, Long> u:entries) {
            System.out.println("Key=" + u.getKey());
            System.out.println("Value=" + u.getValue());
        }
    }
    /**
     * 查询具体业务所设计的表
     */
    @Test
    public void getTableMetaData(){
        TableMetaData act_id_user = idmManagementService.getTableMetaData("ACT_ID_USER");
        act_id_user.getColumnNames().forEach(item->{
            System.out.println(item);
        });
    }
    /**
     * 获取配置文件 框架bug
     */
    @Test
    public void getProperties(){
        Map<String, String> properties = idmManagementService.getProperties();
        System.out.println(properties);
    }
}
