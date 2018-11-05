package com.geocoo.gewcrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * desc:
 *
 * @author taopy
 * @create 2018-10-30 9:56 PM
 */
@SpringBootApplication(scanBasePackages = {"com.geocoo"})
@EnableJpaRepositories(basePackages = "com.geocoo")
@EntityScan(basePackages = "com.geocoo")
@Configuration
@PropertySource(
        {
                "classpath:crawler.properties",
                "classpath:proxy.properties",
                "file:/Users/taopy/develop/gits/geocoo/gc-web/src/main/resources/application.properties",
                "file:/Users/taopy/develop/gits/geocoo/gc-earthview/src/main/resources/earthview.properties"
        })
public class CrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class, args);
    }


}