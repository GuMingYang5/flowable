package com.flowable1.demo.bpmn.listen;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineLifecycleListener;

/**
 * @author gumingyang
 * @description 自定义流程监听器
 * @date 2019/7/1 9:59
 **/
public class ProcessEnginesListen implements ProcessEngineLifecycleListener{
    /**
     * 监听引擎启动
     * @param processEngine
     */
    @Override
    public void onProcessEngineBuilt(ProcessEngine processEngine) {
        System.out.println("引擎启动：" + processEngine);
    }

    /**
     * 监听引擎关闭
     * @param processEngine
     */
    @Override
    public void onProcessEngineClosed(ProcessEngine processEngine) {
        System.out.println("引擎销毁：" + processEngine);
    }
}
