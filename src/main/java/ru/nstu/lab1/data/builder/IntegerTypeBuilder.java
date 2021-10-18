package ru.nstu.lab1.data.builder;

import ru.nstu.lab1.data.Comparator;

import java.util.Random;

public class IntegerTypeBuilder implements TypeBuilder {

    @Override
    public String typeName() {
        return "Integer";
    }

    @Override
    public Integer create() {
        return new Random().nextInt(100);
    }

    @Override
    public Comparator getComparator() {
        return ((o1, o2) -> (Integer) o1 - (Integer) o2);
    }
}
