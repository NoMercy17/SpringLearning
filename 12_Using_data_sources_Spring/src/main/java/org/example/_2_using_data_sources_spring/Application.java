package org.example._2_using_data_sources_spring;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}


// for if we create a custom data source

@Configuration
class ProjectConfig{

    @Value("${custom.datasource.url}")
    private String datasourceUrl;
    @Value("${custom.datasource.username}")
    private String datasourceUsername;
    @Value("${custom.datasource.password}")
    private String datasourcePassword;


    @Bean
    public DataSource dataSource(){
        HikariDataSource dataSource =
        new HikariDataSource();
        dataSource.setJdbcUrl(datasourceUrl);
        dataSource.setUsername(datasourceUsername);
        dataSource.setPassword(datasourcePassword);
        dataSource.setConnectionTimeout(1000);
        return dataSource;
    }

}
