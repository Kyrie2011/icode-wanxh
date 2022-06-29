package com.icode.wanxh.template;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.icode"})
@EnableDiscoveryClient
public class TemplateSrvApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TemplateSrvApplication.class, args);
    }
}
