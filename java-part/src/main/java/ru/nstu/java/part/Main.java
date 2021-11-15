package ru.nstu.java.part;

import ru.nstu.java.part.data.List;
import ru.nstu.java.part.data.ObjectBuilderFactory;
import ru.nstu.java.part.data.builder.ObjectBuilder;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        ObjectBuilder builder = ObjectBuilderFactory.getBuilder("Integer");
//        new UI();

        List<Object> list;
        for (int i = 1; i <= 400; i++) {
            list = new List<>();
            for (int j = 0; j < 1000 * i; j++) {
                list.add(builder.create());
            }
            long start = System.nanoTime();
            try {
                list.sort((o1, o2) -> (Integer) o1 - (Integer) o2);
            } catch (StackOverflowError er) {
                System.err.println("Stack overflow");
                System.exit(0);
            }
            long end = System.nanoTime();
            System.out.println(end - start);
//            System.out.printf("%d elements took %f mills\n", 1000 * i, (end - start) * 1.0 / 1000000);
        }


//        List<Object> list = new List<>();
//        list.add(builder.create());
//        list.add(builder.create());
//        list.add(builder.create());
//        list.add(builder.create());
//        list.add(builder.create());
//        System.out.println("initial");
//        list.forEach(System.out::println);
//
//        list.sort(builder.getComparator());
//        System.out.println("\nafter sort");
//        list.forEach(System.out::println);
//
//        list.remove(1);
//        System.out.println("\nafter remove from 1 index");
//        list.forEach(System.out::println);
//
//        list.remove(0);
//        System.out.println("\nafter remove from 0 index");
//        list.forEach(System.out::println);
//
//        list.remove(2);
//        System.out.println("\nafter remove from 2 index");
//        list.forEach(System.out::println);
//
//        list.add(builder.create(), 1);
//        System.out.println("\nafter add to 1 index");
//        list.forEach(System.out::println);
//
//        list.add(builder.create(), 0);
//        System.out.println("\nafter add to 0 index");
//        list.forEach(System.out::println);
//
//        list.add(builder.create(), 3);
//        System.out.println("\nafter add to 3 index");
//        list.forEach(System.out::println);
    }
}
