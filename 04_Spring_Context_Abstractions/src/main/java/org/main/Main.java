package org.main;


import org.model.Comment;
import org.proxies.EmailCommentNotificationProxy;
import org.repositories.DBCommentRepository;
import org.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"org.proxies", "org.repositories", "org.services"})
class ProjectConfig{

}



public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var comment = new Comment();

        comment.setAuthor("Antonio");
        comment.setText("Miau");
        var commentService = context.getBean(CommentService.class);

        commentService.publishComment(comment);





        // WITHOUT SPRING ENV
        //        var commentRepository = new DBCommentRepository();
//        var commentNotificationProxy = new EmailCommentNotificationProxy();
//
//        var commentService = new CommentService(commentRepository, commentNotificationProxy);
//
//        var comment =  new Comment();
//
//        comment.setText("Hello World");
//        comment.setAuthor("Antonio");
//
//        commentService.publishComment(comment);
    }
}