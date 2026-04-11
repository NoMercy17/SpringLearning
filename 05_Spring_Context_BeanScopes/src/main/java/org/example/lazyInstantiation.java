package org.example;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
class CommentService2{
    public CommentService2(){
        System.out.println("CommentService2() instance created");
    }
}

@Configuration
@ComponentScan(basePackages = "org.example")
class ProjectConfig2{

}

public class lazyInstantiation {
    public static void main(String[] args) {
        var context =  new AnnotationConfigApplicationContext(ProjectConfig2.class);
        var service = context.getBean(CommentService2.class);
        System.out.println("After retrieving the service");
    }
}
