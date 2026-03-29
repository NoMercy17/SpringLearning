package org.example.multiple;

// Be aware of circular dependencies, circular dependency is easy to avoid. You just need to make sure you don’t
// define objects whose creation depends on the other.

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

class Parrot {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parrot : " + name;
    }
}

class Person {
    private String name;
    private Parrot parrot;

// we can link the 2 beans using the @Qualifier so we minimize the chance that someone would refactor
// the name of the variable, modifying which bean is selected by Spring based on matching the name
//    public Person(@Qualifier("parrot2") Parrot parrot) {
//        this.parrot = parrot;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parrot getParrot() {
        return parrot;
    }

    public void setParrot(Parrot parrot) {
        this.parrot = parrot;
    }
}

@Configuration
class ProjectConfig {
    @Bean
    public Parrot parrot1() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean
    @Primary
    public Parrot parrot2() {
        Parrot p = new Parrot();
        p.setName("Miki");
        return p;
    }

//    @Bean
//    public Person person(Parrot parrot2) {
// instead of relying on the name of the parameter we can express the intention using the @Qualifier
//        Person p = new Person();
//        p.setName("Ella");
//        p.setParrot(parrot2);
//        return p;
//    }

    @Bean
    public Person person( @Qualifier("parrot2") Parrot parrot ) {
        Person p = new Person();
        p.setName("Ella");
        p.setParrot(parrot);
        return p;
    }
}

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person person = context.getBean(Person.class);
        Parrot parrot = context.getBean(Parrot.class);

        System.out.println(person.getName());
        System.out.println(parrot.getName());
        System.out.println(person.getParrot());
    }
}
