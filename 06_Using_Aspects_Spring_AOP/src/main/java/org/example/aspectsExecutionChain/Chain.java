package org.example.aspectsExecutionChain;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.logging.Logger;

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


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ToLog{

}



@Aspect
@Component
@Order(2)
class LoggingAspect{
    private Logger logger =
            Logger.getLogger(LoggingAspect.class.getName());
    @Around(value = "@annotation(ToLog)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Logging Aspect: Calling the intercepted method");
        Object returnedValue = joinPoint.proceed();
        logger.info("Logging Aspect: Method executed and returned " +
                returnedValue);
        return returnedValue;
    }
}


@Aspect
@Component
@Order(1)
class SecurityAspect{
    private Logger logger =
            Logger.getLogger(SecurityAspect.class.getName());

    @Around(value = "@annotation(ToLog)")
    public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Security Aspect: Calling the intercepted method");
        Object returnedValue = joinPoint.proceed();
        logger.info("Security Aspect: Method executed and returned " +
                returnedValue);
        return returnedValue;
    }
}

@Service
class CommentService{
    private Logger logger = Logger.getLogger(CommentService.class.getName());

    @ToLog
    public String publishComment(Comment comment) {
        logger.info("Publishing comment:" + comment.getText());
        return "SUCCESS";
    }
}


@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = "org.example.aspectsExecutionChain")
class ProjectConfig{


}

public class Chain {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(org.example.aspectsExecutionChain.ProjectConfig.class);

       var service = context.getBean(CommentService.class);

       Comment comment = new Comment();
       comment.setText("Hello World");
       comment.setAuthor("Jack");

       service.publishComment(comment);
    }
}
