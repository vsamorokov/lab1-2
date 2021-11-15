package ru.nstu.java.part.ui;

import ru.nstu.java.part.data.ObjectBuilderFactory;
import ru.nstu.java.part.data.builder.ObjectBuilder;

import javax.swing.*;

public abstract class AbstractListActionListener implements ListActionListener {
    protected final String filename = "file.dat";

    protected final DefaultListModel<Object> listModel = new DefaultListModel<>();
    protected ObjectBuilder builder;

    @Override
    public DefaultListModel<Object> getListModel() {
        return listModel;
    }

    @Override
    public void onSelectType(String type) {
        try {
            builder = ObjectBuilderFactory.getBuilder(type);
        } catch (Exception ignored) {

        }
    }
}
