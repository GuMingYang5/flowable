package com.flowable1.demo.bpmn;

import org.flowable.engine.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Map;

/**
 * @author gumingyang
 * @description 读取流程配置文件
 * @date 2019/6/28 10:36
 **/
public class BpmnTest {
    ProcessEngine defaultProcessEngine;
    @Before
    public void initException(){
        if(defaultProcessEngine == null){
            InputStream resourceAsStream = BpmnTest.class.getClassLoader().getResourceAsStream("flowable.cfg.xml");
            defaultProcessEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(resourceAsStream).buildProcessEngine();
        }
    }
    @Test
    public void getProcessEngines(){

        System.out.println("默认流程引擎类：" + defaultProcessEngine);

        TaskService taskService = defaultProcessEngine.getTaskService();
        System.out.println("操作任务类：" + taskService);

        FormService formService = defaultProcessEngine.getFormService();
        System.out.println("表单引擎类" + formService);

        DynamicBpmnService dynamicBpmnService = defaultProcessEngine.getDynamicBpmnService();
        System.out.println("动态bpmn服务类：" + dynamicBpmnService);

        HistoryService historyService = defaultProcessEngine.getHistoryService();
        System.out.println("历史记录类：" + historyService);

        IdentityService identityService = defaultProcessEngine.getIdentityService();
        System.out.println("操作人员和组织类：" + identityService);

        ManagementService managementService = defaultProcessEngine.getManagementService();
        System.out.println("执行cmd以及job相关类:" + managementService);

        ProcessEngineConfiguration processEngineConfiguration = defaultProcessEngine.getProcessEngineConfiguration();
        System.out.println("引擎配置类：" + processEngineConfiguration);

        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        System.out.println("流程定义类:" + repositoryService);

        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        System.out.println("做流程实例类:" + runtimeService);

        Map<Object, Object> beans = processEngineConfiguration.getBeans();
        if(processEngineConfiguration.getBeans().containsKey("idmTest")){
            Object idmTest = beans.get("idmTest");
            System.out.println(idmTest);
        }
       // Assert.notNull(null,"不能为null");
        /**
         * 这里获取不到数据的不允许这么操作
         */
//        Set<Map.Entry<Object, Object>> entries = beans.entrySet();
//        entries.forEach(item->{
//            System.out.println(item.getKey());
//            System.out.println(item.getValue());
//        });



    }
    @After
    public void close(){
        defaultProcessEngine.close();
    }
}
