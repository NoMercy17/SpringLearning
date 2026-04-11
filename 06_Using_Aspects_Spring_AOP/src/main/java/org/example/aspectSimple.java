package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

// model class
class Comment{
    private String text;
    private String author;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

@Service
class CommentService{

    private Logger logger = Logger.getLogger(CommentService.class.getName());

    public void publishComment(Comment comment){
        logger.info("Publishing comment: " +comment.getText());
    }
    public void publishComment2(Comment comment){
        logger.info("Publishing comment2: " +comment.getText());
    }
}

// class that defines the aspect

@Aspect // this doesn't create a bean!
@Component
class LoggingAspect{
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* org.example.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("Method will execute");
        joinPoint.proceed(); // calls the intercepted method publishComment()
        logger.info("Method will end");

    }
}

@Configuration
@ComponentScan(basePackages = "org.example")
@EnableAspectJAutoProxy
class ProjectConfig{


}

public class aspectSimple {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var service = context.getBean(CommentService.class);

        Comment comment = new Comment();
        comment.setText("Hello World");
        comment.setAuthor("Jack");

        service.publishComment(comment);
        service.publishComment2(comment);
    }
}