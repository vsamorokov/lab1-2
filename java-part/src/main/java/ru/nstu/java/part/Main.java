package ru.nstu.java.part;

import ru.nstu.java.part.data.List;
import ru.nstu.java.part.data.ObjectBuilderFactory;
import ru.nstu.java.part.data.builder.ObjectBuilder;
import ru.nstu.java.part.ui.ListActionListener;
import ru.nstu.java.part.ui.ListActionListenerImpl;
import ru.nstu.java.part.ui.UI;

public class Main {

    private static void test(ObjectBuilder builder) {
        for (int i = 1; i < 2000; i *= 2) {
            int n = i * 1000;
            System.out.println("N = " + n);
            List<Object> list = new List<>();
            for (int j = 0; j < n; j++) list.add(builder.create());

            long start = System.nanoTime();

            try {
                list.sort(builder.getComparator());
            } catch (StackOverflowError ignored) {
                System.err.println("Stack error");
                return;
            }
            long end = System.nanoTime();
            System.out.println("Millis elapsed " + (end - start) * 1.0 / 1_000_000);
        }
    }

    public static void main(String[] args) {
        ObjectBuilder builder = ObjectBuilderFactory.getBuilder("Integer");
        ListActionListener listActionListener = new ListActionListenerImpl();
        new UI(listActionListener);

        test(builder);

        List<Object> list = new List<>();
        list.add(builder.create());
        list.add(builder.create());
        list.add(builder.create());
        list.add(builder.create());
        list.add(builder.create());
        System.out.println("initial");
        list.forEach(System.out::println);

        list.sort(builder.getComparator());
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

        list.add(builder.create(), 1);
        System.out.println("\nafter add to 1 index");
        list.forEach(System.out::println);

        list.add(builder.create(), 0);
        System.out.println("\nafter add to 0 index");
        list.forEach(System.out::println);

        list.add(builder.create(), 3);
        System.out.println("\nafter add to 3 index");
        list.forEach(System.out::println);
    }
}
