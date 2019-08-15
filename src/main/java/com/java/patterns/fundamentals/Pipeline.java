package com.java.patterns.fundamentals;

import java.util.function.UnaryOperator;

public interface Pipeline{
    default Pipeline incrementByOne(){
        Pipeline t = x -> x+1;
        return orderPipe(t);
    }

    default Pipeline squared(){
        return orderPipe(x -> x*x);
    }

    default Pipeline doubled(){
        return orderPipe(x -> 2*x);
    }

    default Pipeline orderPipe(Pipeline operation){
        return (item) -> operation.executePipeLine(executePipeLine(item));
    }

    Integer executePipeLine(Integer x);

    static Integer halve(Integer num){
        return num/2;
    }


}
