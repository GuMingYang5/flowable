package com.gu.demo.util.cipher;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


@Slf4j
@Component
@Aspect
public class CipherAspect {
    @Autowired
    private CipherProperty cipherProperty;

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void cipherPointCut() {
    }

    @Around("cipherPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result= pjp.proceed();
        //总开关
        if(cipherProperty.getDev()){

            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            int status =  response.getStatus();

            //这里只加密状态为200,并且带有注解的方法
            String responseParam = postHandle(result);
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Cipher cipher = signature.getMethod().getAnnotation(Cipher.class);
            if(null!=cipher && status==200 && null!=result){
                result = AesEncryptUtils.encrypt(responseParam);
            }
            log.info("请求源IP:返回参数:【{}】",result);
        }
        return result;
    }


    private String preHandle(ProceedingJoinPoint joinPoint,HttpServletRequest request) {
        String reqParam = "";
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Annotation[] annotations = targetMethod.getAnnotations();

        for (Annotation annotation : annotations) {
            //可定制化注解 这里只抓取常用的注解
            if (annotation.annotationType().equals(RequestMapping.class)|| annotation.annotationType().equals(GetMapping.class)
                    ||annotation.annotationType().equals(PostMapping.class)||annotation.annotationType().equals(PutMapping.class)
                    ||annotation.annotationType().equals(PatchMapping.class)||annotation.annotationType().equals(DeleteMapping.class)) {
                reqParam = JSON.toJSONString(request.getParameterMap());
                break;
            }
        }
        return reqParam;
    }

    private String postHandle(Object retVal) {
        if(null == retVal){
            return "";
        }
        return JSON.toJSONString(retVal);
    }
}
