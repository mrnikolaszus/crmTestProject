package com.javarush.jira;

import com.javarush.jira.common.internal.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableCaching
public class JiraRushApplication {
//test
    public static void main(String[] args) {
        var springApplication = new SpringApplication(JiraRushApplication.class);
    }
}