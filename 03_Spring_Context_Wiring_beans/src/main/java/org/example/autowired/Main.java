package org.example.autowired;

// Using @Autowired, 3 ways

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class Parrot {
    private String name = "Koko";

    @Override
    public String toString() {
        return "Parrot : " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@Component
class Person {
    private String name = "Ella";

//    @Autowired
//    private Parrot parrot;  injecting the value through the class field, not ok because its not final and its harder
//    // to manange and test

    private final Parrot parrot;

    @Autowired
    public Person(Parrot parrot) { // it calls the constructor with @Autowired, provides a bean Parrot from context as value
        // for the parameter
        this.parrot = parrot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parrot getParrot() {
        return parrot;
    }
}

@Configuration
@ComponentScan(basePackages = "org.example.autowired")
class ProjectConfig {

}

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person person = context.getBean(Person.class);
        System.out.println(person.getName());
        System.out.println(person.getParrot());
    }
}
