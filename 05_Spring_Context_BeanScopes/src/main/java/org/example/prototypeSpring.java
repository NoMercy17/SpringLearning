package org.example;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

class CommentService3{

}


@Configuration
class ProjectConfig3{

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CommentService3 commentService3(){
        return new CommentService3();
    }
}

public class prototypeSpring {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig3.class);

        var s1 = context.getBean(CommentService3.class);
        var s2 = context.getBean(CommentService3.class);

        boolean b1 = s1.equals(s2);
        System.out.println(b1);
    }
}
