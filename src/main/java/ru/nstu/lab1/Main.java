package ru.nstu.lab1;

import ru.nstu.lab1.data.List;
import ru.nstu.lab1.data.ObjectBuilderFactory;
import ru.nstu.lab1.data.builder.ObjectBuilder;
import ru.nstu.lab1.ui.UI;

public class Main {

    public static void main(String[] args) {
        ObjectBuilder stringBuilder = ObjectBuilderFactory.getBuilder("Integer");
        new UI();

        List<Object> list = new List<>();
        list.add(stringBuilder.create());
        list.add(stringBuilder.create());
        list.add(stringBuilder.create());
        list.add(stringBuilder.create());
        list.add(stringBuilder.create());
        System.out.println("initial");
        list.forEach(System.out::println);

        list.sort(stringBuilder.getComparator());
        System.out.println("\nafter sort");
        list.forEach(System.out::println);

        list.remove(1);
        System.out.println("\nafter remove from 1 index");
        list.forEach(System.out::println);

        list.remove(0);
        System.out.println("\nafter remove from 0 index");
        list.forEach(System.out::println);

        list.remove(2);
        System.out.println("\nafter remove from 2 index");
        list.forEach(System.out::println);

        list.add(stringBuilder.create(), 1);
        System.out.println("\nafter add to 1 index");
        list.forEach(System.out::println);

        list.add(stringBuilder.create(), 0);
        System.out.println("\nafter add to 0 index");
        list.forEach(System.out::println);

        list.add(stringBuilder.create(), 3);
        System.out.println("\nafter add to 3 index");
        list.forEach(System.out::println);
    }
}
