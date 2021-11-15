package ru.nstu.java.part.data;

import ru.nstu.java.part.data.builder.Builder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class ListUtils {

    public static <T> void saveToFile(String filename, IList<T> list, Builder<T> builder) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(builder.typeName());
            list.forEach(el -> writer.println(builder.toString(el)));
        }
    }

    public static <T> IList<T> loadFromFile(String filename, Builder<T> builder, IList<T> list) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            line = br.readLine();
            if (!builder.typeName().equals(line)) {
                throw new Exception("Wrong file structure");
            }

            while ((line = br.readLine()) != null) {
                list.add(builder.createFromString(line));
            }
            return list;
        }
    }
}
