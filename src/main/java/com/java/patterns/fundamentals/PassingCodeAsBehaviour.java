package com.java.patterns.fundamentals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PassingCodeAsBehaviour {

    List<Person> persons;

    public PassingCodeAsBehaviour(){
        persons = new ArrayList<>();
        Person p1 = new Person("Rahul",20);
        Person p2 = new Person("Kriti",30);
        Person p3 = new Person("Sunita",28);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
    }
    // Requirement 1: given a person name return that person
    public Person findPersonByName(String name){
        for(Person person: persons){
            if(person.getName().equals(name)){
                return person;
            }
        }
        return  null;
    }
    // Requirement 2: given the age of person, return that person
    public Person findPersonByAge(int age){
        for(Person person: persons){
            if(person.getAge() == age){
                return person;
            }
        }
        return  null;
    }

    // Requirement 3: given the age range, return the person
    public Person findPersonByAgeRange(int startAge,int endAge){
        for(Person person: persons){
            if(person.getAge() > startAge && person.getAge() < endAge){
                return person;
            }
        }
        return  null;
    }

    // Requirement 4: Find person by given name and age
    public Person findPersonByNameAndAge(String name,int age){
        for(Person person: persons){
            if(person.getName().equals(name) && person.getAge() == age){
                return person;
            }
        }
        return  null;
    }

    // Requirement 5: Find person by given name and age range
    public Person findPersonByNameAndAgeRange(String name,int startAge,int endAge){
        for(Person person: persons){
            if(person.getName().equals(name) && person.getAge() > startAge && person.getAge() < endAge){
                return person;
            }
        }
        return  null;
    }

    /**
     * Code is same in all the methods except for the condition check. So how would you avoid code duplication in pre-Java-8
     */


    public Person findByAttribute(PersonCondition personCondition){
        for(Person person: persons){
            if(personCondition.testCondition(person)){
                return person;
            }
        }
        return  null;
    }

    // Requirement 1: given a person name return that person:: using annonymous class
    public Person findPersonByNameAC(String name){
        return findByAttribute(new PersonCondition() {
            @Override
            public boolean testCondition(Person person) {
                return person.getName().equals(name);
            }
        });
    }

    // Requirement 2: given the age of person, return that person:: using annonymous class
    public Person findPersonByAgeAC(int age){
        return  findByAttribute(new PersonCondition() {
            @Override
            public boolean testCondition(Person person) {
                return person.getAge() == age;
            }
        });
    }

    // Assume other things are implemented too this way
    // This looks ugly for just passing one line condition, it's too verbose and bard to understand
    // I can pass function instead of class as an argument in Java 8. But I need functional interface to do so.
    // I also see that PersonCondition interface that I just created already fits the definitino of a functional interface and I can use
    // the same.

    // Requirement 1: given a person name return that person:: using lambda and your custom functional interface
    public Person findPersonByNameByLambda(String name){
        return findByAttribute(person -> person.getName().equals(name));
    }

    // But, wait a minute, for such simple requirement, do I need to create functional interface?
    // I am sure this pattern will repeat for several use cases.
    // Don't I have something off the shelf to represent a scenario wherein something is passed as an argument
    // and boolean is returned.
    // Yes, it's predicate - a built in interface in java which serves this purpos along with other built-in functions like
    // Consumer,Supplier,Function etc.


    // In fact I wouln't need a seaparate fun findPersonByName, the caller the just provide lambda expression on generic function
    // findByAttribute.

    // Requirement 1: given a person name return that person:: using lambda and built-in functional interface

    public Person findByAttributeBuiltinFI(Predicate<Person> personPredicate){
        for(Person person: persons){
            if(personPredicate.test(person)){
                return person;
            }
        }
        return  null;
    }
    public Person findPersonByNameByLambdaBuiltinFI(String name){
        return findByAttributeBuiltinFI(person -> person.getName().equals(name));
    }

    public Person findPersonByAgeByLambdaBuiltinFI(String name){
        return findByAttributeBuiltinFI(person -> {
            return person.getAge().equals(name);});
    }

    @FunctionalInterface
    interface PersonCondition{ public boolean testCondition(Person person);
    }

}
