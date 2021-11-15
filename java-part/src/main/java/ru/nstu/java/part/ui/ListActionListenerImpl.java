package ru.nstu.java.part.ui;

import ru.nstu.java.part.data.IList;
import ru.nstu.java.part.data.List;
import ru.nstu.java.part.data.ListUtils;

import java.io.FileNotFoundException;

public class ListActionListenerImpl extends AbstractListActionListener {


    protected IList<Object> items = new List<>();

    @Override
    public void onAdd(String text) {
        if (text.isEmpty()) return;
        Object data = builder.createFromString(text);
        items.add(data);
        listModel.addElement(data);
    }

    @Override
    public void onInsert(String text, int index) {
        if (text.isEmpty()) return;
        Object data = builder.createFromString(text);
        items.add(data, index);
        listModel.add(index, data);
    }

    @Override
    public void onRemove(int index) {
        items.remove(index);
        listModel.remove(index);
    }

    @Override
    public void onSort() {
        items.sort(builder.getComparator());
        listModel.clear();
        items.forEach(listModel::addElement);
    }

    @Override
    public void onSave() {
        try {
            ListUtils.saveToFile(filename, items, builder);
        } catch (FileNotFoundException e) {
            System.err.println("Unable to write list to a file");
            e.printStackTrace();
        }
    }

    @Override
    public void onLoad() {
        try {
            items = ListUtils.loadFromFile(filename, builder, new List<>());
            listModel.clear();
            items.forEach(listModel::addElement);
        } catch (Exception e) {
            System.err.println("Unable to read list from a file");
            e.printStackTrace();
        }
    }
}
