package ru.nstu.lab1.data;

import org.reflections.Reflections;
import ru.nstu.lab1.data.builder.TypeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TypeBuilderFactory {
    private final static List<TypeBuilder> builders = new ArrayList<>();

    static {
        Reflections reflections = new Reflections("ru.nstu.lab1.data.builder");
        Set<Class<? extends TypeBuilder>> buildersClasses = reflections.getSubTypesOf(TypeBuilder.class);
        buildersClasses.forEach(bc -> {
            try {
                TypeBuilder typeBuilder = bc.newInstance();
                builders.add(typeBuilder);
            } catch (Exception ignored) {
                throw new RuntimeException("Something went wrong...");
            }
        });
    }

    public static Set<String> getAllTypes() {
        return builders.stream().map(TypeBuilder::typeName).collect(Collectors.toSet());
    }

    public static TypeBuilder getBuilder(String name) {
        if (name == null) throw new NullPointerException();
        for (TypeBuilder b : builders) {
            if (name.equals(b.typeName())) return b;
        }
        throw new IllegalArgumentException();
    }
}
