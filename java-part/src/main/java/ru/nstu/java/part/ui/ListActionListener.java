package ru.nstu.java.part.ui;

public interface ListActionListener {

    void onAdd(String text);

    void onInsert(String text);

    void onRemove();

    void onSort();

    void onSave();

    void onLoad();

    void onSelectType(String type);
}
