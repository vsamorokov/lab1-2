package ru.nstu.lab1.data.builder;

import ru.nstu.lab1.data.Comparator;

public interface ObjectBuilder extends Builder<Object> {
    String typeName();

    Object create();

    Comparator<Object> getComparator();

    Object createFromString(String s);

    String toString(Object object);
}
