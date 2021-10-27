package ru.nstu.lab1.data;

import ru.nstu.lab1.data.builder.Builder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class ListUtils {

    public static <T> void saveToFile(String filename, List<T> list, Builder<T> builder) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(builder.typeName());
            list.forEach(el -> writer.println(builder.toString(el)));
        }
    }

    public static <T> List<T> loadFromFile(String filename, Builder<T> builder) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            List<T> list = new List<>();

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
