package ru.nstu.lab1.data;

public interface IList<T> {
    void add(T data);
    T get(int index);
    void remove(int index);
    int size();
    void add(T data, int index);
    void forEach(Action<T> a);
    void sort(Comparator comparator);
}
