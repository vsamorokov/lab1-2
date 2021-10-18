package ru.nstu.lab1.data.builder;

import ru.nstu.lab1.data.Comparator;

import java.io.*;

public interface TypeBuilder {
    String typeName();

    Object create();

    Comparator getComparator();

    default Object load(InputStream stream) {
        try {
            ObjectInputStream ois = new ObjectInputStream(stream);
            return ois.readObject();
        } catch (EOFException eof) {
            return null;
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    default void save(Object o, OutputStream stream) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(o);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
