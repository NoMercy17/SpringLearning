package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.util.function.Supplier;

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

// Using stereotype annotation

@Component
class Dog{
    private String name;

    @PostConstruct
    public void init(){
        this.name = "Rex";
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

@Configuration
@ComponentScan(basePackages = "org.example")
class ProjectConfigDog{

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

        // using stereotype annotations
        var contextDog = new AnnotationConfigApplicationContext(ProjectConfigDog.class);
        Dog d = contextDog.getBean(Dog.class);
        System.out.println(d.getName());


        // Programatically adding beans
        var contextX = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Parrot x = new Parrot();
        x.setName("X");

        Supplier<Parrot> parrotSupplier = () -> x;
        context.registerBean("parrot1", Parrot.class, parrotSupplier);

        Parrot p1 = context.getBean("parrot1",Parrot.class);
        System.out.println(p1.getName());
    }
}