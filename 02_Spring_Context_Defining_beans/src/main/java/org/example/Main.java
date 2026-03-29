package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProjectConfig{

    @Bean(value = "mimi")
    Parrot parrot(){
            var p = new Parrot();
            p.setName("MIMI");
            return p;
    }

    @Bean
    Parrot parrot1() {
        var p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean
    Parrot parrot2(){
        var p = new Parrot();
        p.setName("Pipi");
        return p;
    }

    @Bean
    String hello(){
        return "hello!";
    }

    @Bean
    Integer ten(){
        return 10;
    }
}


class Parrot{
    private String name;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot p = context.getBean("mimi" ,Parrot.class);
        System.out.println(p.getName());

        String s = context.getBean(String.class);
        System.out.println(s);

        Integer i = context.getBean(Integer.class);
        System.out.println(i);
    }
}