package com.example.springbootdocker;

public class ValueObject {
    private String name;
    private int count;

    public static ValueObject DEFAULT(String name, int count) {
        return new ValueObject(name, count);
    }

    private ValueObject(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return this.name;
    }

    public int getCount() {
        return this.count;
    }
}
