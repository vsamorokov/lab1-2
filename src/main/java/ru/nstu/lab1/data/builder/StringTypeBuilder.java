package ru.nstu.lab1.data.builder;

import ru.nstu.lab1.data.Comparator;

import java.util.Random;

public class StringTypeBuilder implements TypeBuilder {

    private static String getString() {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'
        int targetStringLength = 4;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    @Override
    public String typeName() {
        return "String";
    }

    @Override
    public String create() {
        return getString();
    }

    @Override
    public Comparator getComparator() {
        return ((o1, o2) -> ((String) o1).compareTo((String) o2));
    }
}
