package com.java.patterns.fundamentals;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class InventingLambda {

    public void sortPersons(){
        // create a list of person objects with given names
        List<Person> persons  = new ArrayList<>();
        List<String> personNames = Arrays.asList("Rahul","Sunita","Kesari");
        // imperative
        for(String name: personNames){
            Person p = new Person(name);
            persons.add(p);
        }
        List<Person> personsFunctionalWay = personNames.stream().map(name -> new Person(name)).collect(toList());
        List<Person> personsFunctionalWayMethod = personNames.stream().map(Person::new).collect(toList());
        // Approach 1: Pre Java 8 version, write your own comparator: Had to create new comparator class, had to create an object and pass it.
        Collections.sort(persons,new PersonComparator());
        // Approach 2: Pre Java 8 version, you pass anonymous class: Creating an anonymous class and an object of an anonymous class but saving few lines of code.
        Collections.sort(persons, new Comparator<Person>() {

            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });

        // Approach 3:
        /**
         * In Java 7, if you were to pass function/code as a parameter, approach 2 offers the closest you can get, but still it's too verbose.
         * Lambda expressions are annonymous function,  an alternative to pass function/code as an argument and we achieve it with functional interfaces
         */
        // how lamda derives tyeps: lambda body:: lambda relies on functional interface
        Collections.sort(persons,(p1,p2)-> {
            return p1.getName().compareTo(p2.getName());
        });
        // Better than approach two, less verbose: lambda expression
        Collections.sort(persons,(p1,p2) -> p1.getName().compareTo(p2.getName()));

        // Can we do better? Yes : Method References
        Collections.sort(persons, comparing(Person::getName));
        // What if you had custom comparison logic
        Collections.sort(persons,this::comparedBasedOnAgeAndNameAndLocation);

        // still imperative, Collections.sort is a separate statement just for sorting
        persons.stream().sorted(comparing(Person::getName)).forEach(name -> System.out.println(name));
        persons.stream().sorted(comparing(Person::getName)).forEach(System.out::println);

        /**
         * Lambda dervies type information from context in which it is used, and context is provided by Functional Interface.
         *  Comparator is a functional interface since it has only one abstract method and it fits the definition of
         *  functional interface. A functional interface need not be annotated with @FunctionalInterface which only provides
         *  meta information to avoid someone accidently adding more functions to an interface which is meant to be a functional interface.
         */
        Comparator<Person> personComparator = (p1,p2) -> p1.getName().compareTo(p2.getName());

        Collections.sort(persons,personComparator);
        ;

        // Example 2:
        // Print Hello in a new thread
        // Approach 1: Pre Java 8: Your own Runnable class
        Thread t = new Thread(new HelloRunnable());
        t.start();
        // Approach 2: Annonymous class
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        });
        t1.start();
        //Approach 3: Anonymous functoin /lambda
        Thread t2 = new Thread(()-> System.out.println("Hello"));
        t2.start();

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        int sum = 0;
        for(Integer i: list){
            sum+=i;
        }
        // sum using reduce
        int result = list.stream().reduce(0,(sumContainer,i) -> sumContainer += i);
        result = list.stream().reduce(0,(sumContainer,i) -> sumContainer + i);
        result = list.stream().reduce(0,Integer::sum);
        // sum using IntStream sum function
        result = list.stream().mapToInt((wrapperInteger) -> wrapperInteger.intValue()).sum();
        result = list.stream().mapToInt(Integer::intValue).sum();
        result = list.stream().mapToInt(x -> x).sum();
        result = list.stream().mapToInt(x -> x).sum();

        // sum using collect
        result = list.stream().collect(Collectors.summingInt(wrapper -> wrapper));
        result = list.stream().collect(Collectors.summingInt(wrapper -> wrapper.intValue()));
        result = list.stream().collect(Collectors.summingInt(Integer::intValue));
        /**
         * collect method has two variants:
         * 1. collect(Supplier<R> identity, BiConsumer<R, ? super T> accumulator, BiConsumer<R,R> combiner)
         * 2. collect(Collector<T,A,R>): T: input arg for reduction, A identity, R tranformed return type
         * Let's see first usage of collector
         */
        int[] results = list.stream().collect(() -> new int[1], (a, b) -> a[0] = a[0] + b.intValue(), (a, h) -> a[0] = a[0] + h[0]);
        /**
         * Now let's see second usage of collector where you write your own custom collector
         * Supplier<A> :: Identity
         * BiConsumer<A,T> :: Accumulator
         * BinaryOperator<A> :: Combiner
         * Function<A,R> :: Finisher
         * Earlier collect returns the result container but we want result so a finisher is needed to transform the result to the desired value
         */
        result = list.stream().collect(Collector.of(() -> new int[1], (a, b) -> a[0] = a[0] + b.intValue(), (a, h) -> {a[0] = a[0] + h[0]; return a;},a -> a[0]));

        int[] arrOfInts = {1,2,3,4,5}; // List<Integer>
        Integer[] arrOfInteger = {1,2,3,4,5};
        List<Integer> listOfIntegers = Arrays.stream(arrOfInts).boxed().collect(Collectors.toList());
        // better less verbose and mroe expressive
    }

    class HelloRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("Hello");
        }
    }

    public int comparedBasedOnAgeAndNameAndLocation(Person p1, Person p2){
        return 1;
    }

    class PersonComparator implements Comparator<Person>{

        @Override
        public int compare(Person p1, Person p2) {
            return p1.getName().compareTo(p2.getName());
        }
    }
}
