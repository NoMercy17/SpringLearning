package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository

class CommentRepository{

}

@Service
class CommentService1{

    private final CommentRepository commentRepository;

    public CommentService1(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public CommentRepository getCommentRepository(){
        return commentRepository;
    }
}

@Service
class UserService{

    @Autowired
    private CommentRepository commentRepository;

    public CommentRepository getCommentRepository(){
        return commentRepository;
    }
}

@Configuration
@ComponentScan(basePackages = "org.example")
class ProjectConfig1{

}


public class SingletonSpring {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig1.class);

        var service1 = context.getBean(CommentService1.class);
        var service2 = context.getBean(UserService.class);

        boolean b = service1.getCommentRepository() == service2.getCommentRepository();
        System.out.println(b);
    }


}
