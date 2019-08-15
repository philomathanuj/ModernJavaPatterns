package com.java.patterns.fundamentals;

import com.java.patterns.Playground;
import com.java.patterns.model.EcomData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Kinds of Method References
 * There are four kinds of method references:
 *
 * Kind	Example
 * Reference to a static method	 -> ContainingClass::staticMethodName
 * Reference to an instance method of a particular object ->	containingObject::instanceMethodName
 * Reference to an instance method of an arbitrary object of a particular type -> ContainingType::methodName
 * Reference to a constructor -> ClassName::new
 */

public class StreamsPlayground extends Playground {
    private static int value1 = 5;
    private static int value2 = 10;

    public EcomData searchByStockCode(String key){
        return getEcomDataList().stream().filter(record -> record.getStockCode().equals(key)).findFirst().orElse(null);
    }

    public void test(){
        StreamsPlayground t = new StreamsPlayground();
        b(t::a);
        b(StreamsPlayground::c);
        d(String::toUpperCase);
        e(StreamsPlayground::new);
        f(Math::max);
        Calculator c = (x,y,z) -> x+y+z;
        g(c::findSum,1,2,3);
        BinaryOperator<Integer> mathsMax = Math::max;
        mathsMax.apply(5,10);
        int result = h(Math::max);
        i(Integer::bitCount);
        p().get().get().get();

        Pipeline p = x -> x+2;
        int output1 = p.orderPipe(y-> y+2).orderPipe(z->z+2).orderPipe(g -> g*3).executePipeLine(6);
        int output2 = p.incrementByOne().squared().doubled().executePipeLine(6);
        Integer r = p.orderPipe(Pipeline::halve) // 1.Reference to a Static Method
                .orderPipe(t::instanceExampleMethod). // 2. Reference to an Instance Method of a Particular Object
                        orderPipe(Integer::new). // 3. Reference to a Constructor
                        orderPipe(Math::abs) // Reference to a Static Method
                .orderPipe(Integer::bitCount) // Reference to a Static Method
                .executePipeLine(6);
        //4. Reference to an Instance Method of an Arbitrary Object of a Particular Type
        String output3 = getValueByIncrementOne(Person::getName); // arbitrary object
        Integer output4 = demoArbitraryMethod(Person::getWordsInString);// person is arg and on that person you call a method from person class
        Integer output5 = demoArbitraryMethodTwo(Person::getNameLength); // getObjValue must not have any arg, like a Supplier
        Integer output6 = demoArbitraryMethodThree(StreamsPlayground::a); // Current Class Usage
    }

    private Integer demoArbitraryMethodThree(Function<StreamsPlayground,Integer> f) {
        return f.apply(new StreamsPlayground());
    }

    private String getValueByIncrementOne(Function<Person,String> f) {
        return  f.apply(new Person("Ravi Kumar"));
    }

    private Integer demoArbitraryMethod(Function<Person,Integer> f){
    return f.apply(new Person("Ravi Kumar"));
    }

    private Integer demoArbitraryMethodTwo(Function<Person,Integer> f){
        return f.apply(new Person("Ravi KUmar"));
    }


    public static Supplier<Supplier<Supplier<Integer>>> p(){
        return StreamsPlayground::q;
    }

    public static Supplier<Supplier<Integer>> q(){
        return StreamsPlayground::r;
    }

    public static Supplier<Integer> r(){
        Supplier<Integer> sup = StreamsPlayground::applyChain;
        return StreamsPlayground::applyChain;
    }

    public static Integer applyChain(){
        return 5;
    }

    private Integer i(Function<Integer,Integer> x) {
        Function<Integer,Integer> y = t -> t+1;
        Function<Integer,Integer> z = g -> g+1;
        return x.andThen(y).andThen(z).apply(value1);
    }

    private int h(BinaryOperator<Integer> mathsMax) {
        return mathsMax.apply(value1,value2);
    }

    private Integer g(Calculator c, Integer x, Integer y, Integer z) {
        return c.findSum(x,y,z);
    }

    public static Integer f(BiFunction<Integer,Integer,Integer> max){
        return max.apply(5,10);
    }

    public StreamsPlayground e(Supplier<StreamsPlayground> p){
        return p.get();
    }


    public Integer a(){
        return 1;
    }

    public void d(UnaryOperator<String> x){
        System.out.println(x.apply("Hello"));
    }

    public Integer b(Supplier<Integer> f){
        return f.get();
    }

    public static Integer c(){
        return 2;
    }

    public Integer instanceExampleMethod(Integer i){
        return i+1;
    }

    public static void main(String[] args) {
        StreamsPlayground t = new StreamsPlayground();
        // sum by different field values
        Map<String, Long> stockCodeCountMap = t.getEcomDataList().stream().collect(Collectors.groupingBy(EcomData::getStockCode, Collectors.counting()));
        // sum by different fields:: immutable reduction through reduce method:: new object created every time
        EcomData identity = new EcomData();
        identity.setUnitPrice(new BigDecimal(0));
        identity.setQuantity(0);
        EcomData aggregatorObject =
        t.getEcomDataList().stream().reduce(identity,(accumulator,current) -> {
            accumulator.setQuantity(accumulator.getQuantity()+current.getQuantity());
            accumulator.setUnitPrice(accumulator.getUnitPrice().add(current.getUnitPrice()));
            return accumulator;
        });
        // sum by different fields:: mutable operation by collect: same object is modified each time
        EcomData aggregateByCollection = t.getEcomDataList().stream().collect(EcomData::new, EcomData::accept,EcomData::combine);

        Map<String, Long> stockCodeCountMapx = t.getEcomDataList().stream().collect(Collectors.groupingBy((ecomData)-> {
            if(ecomData.getStockCode().equals("22633")){
                return "SHOWWTIHDATE";
            }
            else{
                return "ORDER";
            }
        }, Collectors.counting()));
        // see how instead of returning list of ecomData we can return list of description by using mapping method
        Map<String, List<String>> stockCodeAndDescriptionListMap = t.getEcomDataList().stream().collect(Collectors.groupingBy(EcomData::getStockCode, Collectors.mapping(ecom -> ecom.getDescription(), Collectors.toList())));
        System.out.println("Hello");
    }

}
