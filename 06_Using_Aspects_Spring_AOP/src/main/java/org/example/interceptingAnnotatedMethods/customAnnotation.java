package org.example.interceptingAnnotatedMethods;

// create the custom annotation to be used to annotate the methods for your aspect to intercept

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
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

@Service
class CommentService{
    private Logger logger = Logger.getLogger(CommentService.class.getName());

    public void publishComment(Comment comment){
        logger.info("Publishing comment: " + comment.getText());
    }

    @ToLog
    public void deleteComment(Comment comment){
        logger.info("Deleting comment: "+ comment.getText());
    }


    public void editComment(Comment comment) {
        logger.info("Editing comment:" + comment.getText());
    }
}

@Aspect
@Component
class LoggingAspect{
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());


    @Around("@annotation(ToLog)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("Method: "+ methodName+ " with parameters: "+ Arrays.asList(args) + " will execute");

        Object returnedByMethod = joinPoint.proceed();

        logger.info("Method executed and returned "+ returnedByMethod);
        return returnedByMethod;
    }

}


@Configuration
@ComponentScan(basePackages = "org.example.interceptingAnnotatedMethods")
@EnableAspectJAutoProxy
class ProjectConfig{


}

public class customAnnotation {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(org.example.interceptingAnnotatedMethods.ProjectConfig.class);

        var service = context.getBean(CommentService.class);

        Comment comment = new Comment();
        comment.setText("Hello World");
        comment.setAuthor("Jack");

        service.deleteComment(comment);

        //logger.info(value);
    }
}
