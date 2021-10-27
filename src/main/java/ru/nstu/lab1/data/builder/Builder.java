package ru.nstu.lab1.data.builder;

import ru.nstu.lab1.data.Comparator;

public interface Builder<T> {
    String typeName();

    T create();

    Comparator<T> getComparator();

    T createFromString(String s);

    String toString(T object);
}
