package org.example.differentParam;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.logging.Logger;

class Comment1{
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
class CommentService1 {
    private Logger logger = Logger.getLogger(CommentService1.class.getName());

    public String publishComment(Comment1 comment) {
        logger.info("Publishing comment:" + comment.getText());
        return "SUCCESS";
    }
}

@Aspect
@Component
class LoggingAspect1{
    private Logger logger = Logger.getLogger(LoggingAspect1.class.getName());

    @Around("execution(* org.example.differentParam.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("Method: "+ methodName+ " with parameters: "+ Arrays.asList(args) + " will execute");

        Object returnedByMethod = joinPoint.proceed();

        logger.info("Method executed and returned "+ returnedByMethod);
        return returnedByMethod;
    }
}

@Configuration
@ComponentScan(basePackages = "org.example.differentParam")
@EnableAspectJAutoProxy
class ProjectConfig2{


}

public class aspectDifferentParameters {
    private static Logger logger = Logger.getLogger(aspectDifferentParameters.class.getName());

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig2.class);

        var service = context.getBean(CommentService1.class);

        Comment1 comment = new Comment1();
        comment.setText("Hello World");
        comment.setAuthor("Jack");

        String value = service.publishComment(comment);

        logger.info(value);
    }
}
