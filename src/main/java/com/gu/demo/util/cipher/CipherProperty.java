package com.gu.demo.util.cipher;

import lombok.Data;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description 读取秘钥相关配置
 * @author gumingyang
 **/
@Component
@ConfigurationProperties(prefix="spring.cipher")
@Data
public class CipherProperty {
    /**
     * 密钥
     */
    @Value("${spring.cipher.key}")
    private  String key;
    /**
     * 是否开启加密解密模式
     */
    @Value("${spring.cipher.dev}")
    private Boolean dev;

    public void setKey(String key) {
        if(StringUtils.isEmpty(key)){
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(System.getProperty("jasypt.encryptor.password"));
            key = textEncryptor.decrypt("LNFu8WVfdqmRJUl78Mhtj3Zut86ARlPLo9Q36hBTcQY=");
        }
        this.key = key;

    }

    /**
     *  默认 false 不开启
     * @param dev
     */
    public void setDev(Boolean dev) {
        if(StringUtils.isEmpty(key)){
            dev = false;
        }
        this.dev = dev;

    }

    public String getKey() {
        return key;
    }

    public Boolean getDev() {
        return dev;
    }
}
