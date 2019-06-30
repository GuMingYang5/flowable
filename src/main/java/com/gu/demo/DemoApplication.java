package com.gu.demo;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.gu.demo.mapper")
@ServletComponentScan
public class DemoApplication {

	public static void main(String[] args) {
		System.setProperty("jasypt.encryptor.password", "826bc1b8-91a3-11e8-b446-060400ef5315");
		System.setProperty("spring.cipher.key", "4564560123456789");
		SpringApplication.run(DemoApplication.class, args);
	}
}
