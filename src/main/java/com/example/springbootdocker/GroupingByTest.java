package com.example.springbootdocker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GroupingByTest {

    public static void main(String[] args) {
        var list = List.of(
            ValueObject.DEFAULT("Arvin", 34),
            ValueObject.DEFAULT("Arvin", 32),
            ValueObject.DEFAULT("Arvin", 200),
            ValueObject.DEFAULT("Arvin", 1),
            ValueObject.DEFAULT("Arvin", 10),
            ValueObject.DEFAULT("Marlon", 99),
            ValueObject.DEFAULT("Marlon", 8),
            ValueObject.DEFAULT("Marlon", 123),
            ValueObject.DEFAULT("Marlon", 9)
        );

        Collector<ValueObject, IntegerContainer, IntegerContainer> integerCollector = Collector.of(
            IntegerContainer::new,
            (integerContainer, valueObject) -> {
                integerContainer.setValue(integerContainer.getValue() + valueObject.getCount());
            },
            (integerContainer, right) -> {
                integerContainer.setValue(integerContainer.getValue() + right.getValue());
                return integerContainer;
            }
        );

        var result = list.stream()
            .collect(Collectors.groupingBy(
                ValueObject::getName,
                integerCollector
            ));


        System.out.println(result);

        BiConsumer<String, List<String>> stringCollector = (value, collection) -> collection.add(value);


        collectStrings(stringCollector, "hello", "world");


    }

    public static List<String> collectStrings(BiConsumer<String, List<String>> consumer, String...args) {
        var result = new ArrayList<String>();
        for (String arg: args) {
            consumer.accept(arg, result);
        }
        return result;
    }
}

