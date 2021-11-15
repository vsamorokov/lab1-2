package ru.nstu.java.part.data.builder;

import ru.nstu.java.part.data.Comparator;

public interface ObjectBuilder extends Builder<Object> {
    String typeName();

    Object create();

    Comparator<Object> getComparator();

    Object createFromString(String s);

    String toString(Object object);
}
