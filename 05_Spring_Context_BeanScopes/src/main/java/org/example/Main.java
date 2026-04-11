package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProjectConfig{
    @Bean
    public CommentService commentService(){
        return new CommentService();
    }
}

class CommentService{

}

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var c1 = context.getBean("commentService");
        var c2 = context.getBean("commentService");

        if(c1.equals(c2)){
            System.out.println("True");
        }

    }
}