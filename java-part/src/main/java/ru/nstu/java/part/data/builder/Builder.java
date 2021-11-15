package ru.nstu.java.part.data.builder;

import ru.nstu.java.part.data.Comparator;

public interface Builder<T> {
    String typeName();

    T create();

    Comparator<T> getComparator();

    T createFromString(String s);

    String toString(T object);
}
